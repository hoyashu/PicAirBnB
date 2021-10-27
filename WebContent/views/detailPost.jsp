<%-- detailPost.jsp --%>
<%@ page import="util.*"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	pageContext.setAttribute("CR", "\n");
	pageContext.setAttribute("BR", "<br>");
%>
<!DOCTYPE html>
<html lang='ko'>
    <head>
        <meta charset='UTF-8'>
        <title>게시글 상세보기</title>
        <style>
            table {
                width: 500px;
                border-collapse: collapse;
                margin: 50px auto;   
                font-size: 12px;             
            }

            table, tr, th, td {
                border: 1px solid blue;
            }
		
			td, th {
				height: 35px;
				padding-left: 10px;
			}
            h3 {
                text-align: center;
            }

			.content {
				height: 200px;
			}
			#div {
				width: 200px;
				margin: 20px auto;
				font-size: 12px;
			}
			
			#tbl td {
				text-align: center;
			}
			
			#attachView{
				text-align: center;
				
				
			}
			 #addComment, #modifyComment {
			width: 300px;
			margin: 20px auto;
			font-size: 12px;
			}
        </style>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="	crossorigin="anonymous">
        </script>
        <script>
        	$(document).ready(function() {
        		
        		const getAjax = function(url, boardNo, cmtContent) {
                    // resolve, reject는 자바스크립트에서 지원하는 콜백 함수이다.
                    return new Promise( (resolve, reject) => {
                        $.ajax({                        
                            url: url,
                            method: 'GET',
                            dataType: 'json',
                            data: {
                            	boardNo: boardNo,
                            	cmtContent: cmtContent.trim().replace(/\n/g, '<br/>')
                            },
                            success: function(data) {                    	
                                resolve(data);
                            }, 
                            error: function(e) {                    	
                                reject(e);
                            }
                        });
                    });
                }   
        		const updateComment = function(url, boardNo, cmtContent, no) {
                    return new Promise( (resolve, reject) => {
                        $.ajax({                        
                            url: url,
                            method: 'GET',
                            dataType: 'json',
                            data: {
                            	boardNo: boardNo,
                            	cmtContent: cmtContent.trim().replace(/\n/g, '<br/>'),
                            	no: no
                            },
                            success: function(data) {                    	
                                resolve(data);
                                $('#modifyComment').insertAfter('#addComment');  
                                $('#modifyComment').hide();
                            }, 
                            error: function(e) {                    	
                                reject(e);
                            }
                        });
                    });
                }
        		const removeComment = function(url, boardNo, no) {
                    return new Promise( (resolve, reject) => {
                        $.ajax({                        
                            url: url,
                            method: 'GET',
                            dataType: 'json',
                            data: {
                            	boardNo: boardNo,
                            	no: no                            
                            },
                            success: function(data) {                    	
                                resolve(data);
                            }, 
                            error: function(e) {                    	
                                reject(e);
                            }
                        });
                    });
                }   

                async function requestProcess(url, boardNo, cmtContent, no) {
                    try {                        
                    	let cmtList = null;
                    	if(cmtContent == null) {
                    		cmtList = await removeComment(url, boardNo, no);	
                    		
                    	} else {
                    		if(no!=null){
                    			cmtList = await updateComment(url, boardNo, cmtContent, no);
                    		} else {
                    			cmtList = await getAjax(url, boardNo, cmtContent);
                    		}                    		
                    	}                    	                        
                        $('#ListComment').html("");
					 	let htmlStr = [];
					 	for(let i = 0; i< cmtList.length; i++) {
					 		htmlStr.push('<table id=' + cmtList[i].no +'>');
					 		htmlStr.push('<tbody>');
					 		htmlStr.push('<tr>');
					 		htmlStr.push('<td>' + cmtList[i].userName + '</td>');
					 		htmlStr.push('<td>' + cmtList[i].writeday + '</td>');
					 		htmlStr.push('</tr>');		
					 		htmlStr.push('<tr>');	
					 		htmlStr.push('<td colspan="2" class="cmtContent">' + cmtList[i].content + '</td>');
					 		htmlStr.push('</tr>');
					 		htmlStr.push('<tr>');	
					 		htmlStr.push('<td colspan="2">');
					 		htmlStr.push('<button class="modifyFormBtn" type="button">수정</button>');		
					 		htmlStr.push('<button class="removeBtn" type="button">삭제</button>');			
					 		htmlStr.push('</td>');					
					 		htmlStr.push('</tr>');
					 		htmlStr.push('</tbody>');
					 		htmlStr.push('</table>');				 		
					 	}         
					 	
					 	$('#ListComment').html(htmlStr.join(""));
                    } catch (error) {
                        console.log("error : ", error);   
                    }                                        
                }
                
                //댓글 달기
                $('#addCmtBtn').on('click', function() {
                	const boardNo = '${param.no}';
                	const cmtContent = $('#addCmtContent').val().trim();
                	document.getElementById("addCmtContent").value='';
                	
                	requestProcess('${pageContext.request.contextPath}/addComment.do', boardNo, cmtContent);                	
                });
                                
               
                $('#ListComment').on('click', '.modifyFormBtn', function() {                
                	const no = $(this).parents('table').attr('id');
                	
                	$('#modifyComment').insertAfter('#' + no);                	
                	const content = $(this).parents('tbody').find('.cmtContent').html().replace(/<br>/g,'\n');   
                	console.log(content);
                	$('#modifyCmtContent').val(content);
                	$('#no').val(no);
                	$('#modifyComment').show();
                	$('#' + no).hide();       

                });
                

                //댓글 삭제
                $('#ListComment').on('click', '.removeBtn', function() { 
                	const boardNo = '${param.no}';               
                	const no = $(this).parents('table').attr('id');
                	const cmtContent = null;
                	requestProcess('${pageContext.request.contextPath}/removeComment.do',boardNo,cmtContent, no);        	
                });
                
                
                //댓글 취소
                $('#cancel').on('click', function() {
                	const no = $('#no').val();
                	$('#' + no).show();    
                	$('#modifyComment').hide();
                	$('#modifyComment').insertAfter('#addComment');
                });
                
                //댓글 수정
                $('#modifyBtn').on('click', function() {

                	const boardNo = '${param.no}';     
                	const no = $('#no').val();
                	const cmtContent = $('#modifyCmtContent').val();
                	
                	requestProcess('${pageContext.request.contextPath}/modifyComment.do',boardNo, cmtContent, no);

                	
                	
                
                });			
        	});       	
        </script>
    </head>
<body>
<h3>${requestScope.post.subject}</h3>
<table border="1">
	<tr>
		<td colspan="2">작성자</td>
		<td colspan="2">${requestScope.post.writerName}</td>
		<td colspan="2">작성일자</td>
		<td colspan="2">${requestScope.post.createDate}</td>	
	<tr>
	<c:if test="${requestScope.post.boardNo == 1}">
	<tr>
		<td colspan="2">숙소번호</td>
		<td colspan="2">${requestScope.post.review.roomNo}</td>
		<td colspan="2">숙소방문일자</td>
		<td colspan="2">${requestScope.post.review.visitDate}</td>
		
	<tr>
	<tr>
		<td>위치평점</td>
		<td>${requestScope.post.review.rate_loc}</td>
		<td>위생평점</td>
		<td>${requestScope.post.review.rate_clean}</td>
		<td>의사소통평점</td>
		<td>${requestScope.post.review.rate_comu}</td>
		<td>가성비평점</td>
		<td>${requestScope.post.review.rate_chip}</td>
	<tr>
	<tr>
		<td colspan="2">추천 음식점, 장소</td>
		<td colspan="2">${requestScope.post.review.recommendPlace}</td>
		<td colspan="2">이런분들께는 비추!</td>
		<td colspan="2">${requestScope.post.review.notRecommendPerson}</td>
		
	<tr>
	</c:if>			
	<tr>
		<td colspan="2">내용</td>
	    <td colspan="6">${fn:replace(requestScope.post.content, CR, BR)}</td>	
	<tr>
	<tr>
		<td colspan="2">태그</td>
	    <td colspan="6">${requestScope.post.tag}</td>	
	<tr>			
</table>



 <c:if test="${not empty requestScope.post.attachList}">
 	<div id = "attachView">

	<c:forEach var="file" items="${requestScope.post.attachList}">
		<c:set var="fileNm" value="${fn:toLowerCase(file.systemFileName)}" /> 
		<c:forTokens var="token" items="${fileNm}" delims="." varStatus="status">
		<c:if test="${status.last}">
		<c:choose> 
			<c:when test="${token eq 'png' ||token eq 'jpg' ||token eq 'jpeg' }">
				<img src = "${pageContext.request.contextPath}/upload/${file.systemFileName}"  width = 200  width = 200 ><br>
			</c:when>
			<c:when test="${token eq 'mp4'|| token eq 'wmv'|| token eq 'mov'|| token eq 'avi' }">
				<video src = "${pageContext.request.contextPath}/upload/${file.systemFileName}"  width = 300  width = 300 controls ></video><br>
			</c:when>
		</c:choose>	
		</c:if>
		</c:forTokens>
	

  	</c:forEach>
  	</div>
  <table id="tbl">
	<tr>
		<th>파일명</th><th>파일크기</th>
	<tr>
	<c:forEach var="file" items="${requestScope.post.attachList}">
		<c:url var="downloadUrl" value="/fileDownload">
			<c:param name="originalFileName" value="${file.originalFileName}"/>
			<c:param name="systemFileName" value="${file.systemFileName}"/>
		</c:url>
		<tr>
			
			<td><a href="${downloadUrl}">${file.originalFileName}</a></td>
			<td>${file.fileSize} bytes</td>
		<tr>	
	</c:forEach>
  </table>
</c:if>

<div id="div">

	<c:url var="modifyUrl" value="/modifyPostForm.do">
		<c:param name="no" value="${requestScope.post.no}" />
	</c:url>
	<a href="${modifyUrl}">수정</a>&nbsp;&nbsp;
	
	<c:url var="modifyUrl" value="/modifyPostBoardNo.do">
		<c:param name="no" value="${requestScope.post.no}" />
	</c:url>
	<a href="${modifyUrl}">이동</a>&nbsp;&nbsp;
	
	<c:url var="removeUrl" value="/cancelPost.do">
		<c:param name="no" value="${requestScope.post.no}" />
	</c:url>
	<a href="${removeUrl}">삭제</a>&nbsp;&nbsp;
	
	<c:url var="removeUrl" value="/reportPost.do">
		<c:param name="no" value="${requestScope.post.no}" />
	</c:url>
	<a href="${removeUrl}">신고</a>&nbsp;&nbsp;
	
	<c:url var="listUrl" value="/listPost.do">	
		<c:param name="boardNo" value="${requestScope.post.boardNo}" />
	</c:url>
	<a href="${listUrl}">목록보기</a>
</div>
<div id="ListComment">
	<c:forEach var="comment" items="${requestScope.commentList}">
		<table id="${comment.no}">
			<tbody>
				<tr>
					<td>${comment.userName}</td>
					<td>${comment.writeday}</td>
				</tr>
				<tr>
					<%-- <td colspan="2" class="cmtContent">${fn:replace(comment.content, CR, BR)}</td> --%>
					<td colspan="2" class="cmtContent">${comment.content}</td>
				</tr>
				<tr>
					<td colspan="2">
						<button class="modifyFormBtn" type="button">수정</button>
						<button class="removeBtn" type="button">삭제</button>
					</td>					
				</tr>
			</tbody>
		</table>
	</c:forEach>
	</div>
	
	<%-- 댓글 달기 --%>
	<div id="addComment">
		<div>
			<textarea id="addCmtContent" rows="5" cols="50" placeholder="댓글을 입력해주세오 ."></textarea>
		</div>
		<div>
			<button id="addCmtBtn">댓글 달기</button>
		</div>
	</div>
	
	<%-- 댓글 수정--%>
	<div id="modifyComment" style="display:none;">
		<div>
			<input type="hidden" id="no"/>
			<textarea id="modifyCmtContent" rows="5" cols="50" placeholder="댓글을 입력해주세오 ."></textarea>
		</div>
		<div>
			<button id="cancel">취소</button>
			<button id="modifyBtn">수정하기</button>
		</div>
	</div>
</body>
</html>
















