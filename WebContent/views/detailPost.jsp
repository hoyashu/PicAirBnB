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
        </style>
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

</body>
</html>

















