<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html lang="ko">

<head>
<meta http-equiv=”Content-Type” content=”text/html; charset=UTF-8“>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>PicAirBnB</title>
<link rel="stylesheet"
   href="${pageContext.request.contextPath}/resource/css/reset.css">
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
   integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
   crossorigin="anonymous">

<link rel="stylesheet"
   href="${pageContext.request.contextPath}/resource/css/common.css">

<script type="text/javascript"
   src="${pageContext.request.contextPath}/resource/js/jquery-1.11.0.min.js"></script>

<script type="text/javascript"
   src="${pageContext.request.contextPath}/resource/js/common.js">
   </script>
</head>

    <head>
    <style>
            table {
                width: 700px;
                border-collapse: collapse;
                margin: 50px auto;   
                font-size: 12px;             
            }
            table, tr, th, td {
                border: 1px solid blue;
                text-align: center;
            }
            
            th, td {
            	height: 35px;
            }
            h3 {
                text-align: center;
            }
            
            #paging {
            	width: 200px;
            	margin: 10px auto;
            }
        </style>
    
        <meta charset='UTF-8'>
        <title></title>
        
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" 
                integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" 
                crossorigin="anonymous"></script>
                  
        <script>
      		  $(document).ready(function(){          	
            	$('#withdrawUpdateBtn').on('click', function() {
            		let memNo_arr = [];
            		
            		$('input[name=checkbox]:checked').each(function() {
            			let temp = $(this).val();          			
            			memNo_arr.push(temp[0]);            			
            		});
            		console.log(memNo_arr);
            		
            		const url = '${pageContext.request.contextPath}/member_withdrawstate.do'
            		sendProcess(url, memNo_arr);
    				
            	});
            	       		 	
            });     
      		  
            const getAjax = function(url, memNo_arr){
      			
            	return new Promise((resolve, reject) => {

      			$.ajax({
      				url : url,
      				method: 'GET',
      				dataType: 'json',
      				traditional : true,
      			    data        : memNo_arr,
      				async: true,
      				success: function(data){
     					console.log('data : ', data) 
     					resolve(data);
     				},
     				error: function(e){
     					console.log('error : ', e)
     					reject(e);
     				}
      			});
            	});
      			
      		};
            
            async function sendProcess(url, memNo_arr){
  	  			var result = await getAjax(url, memNo_arr)
  	  			console.log(result)
  	  		};
        </script>
    </head>

<body>
<h1>탈퇴 회원 관리</h1>

		
        
		<div>
			<button  type="button" name="withdrawUpdateBtn" id="withdrawUpdateBtn">가입불가 해체</button>
		</div>
			
<table>

	<thead>
	<tr>
		<th></th><th>닉네임</th><th>사유</th><th>처리일</th><th>가입불가 여부</th>
	</tr>
	</thead>
	<tbody>
	
	<c:if test="${empty requestScope.memberstate}">
		<tr><td colspan="8">등록된 사용자가 없습니다.</td></tr>
	</c:if>
	<c:if test="${not empty requestScope.memberstate}">
	
		<c:forEach var="member" items="${requestScope.memberstate}" varStatus="loop">
		
			<tr>
			
				<td><input type="checkbox" value="${pageScope.member.memNo}" class="checkbox" name="checkbox"></td>
				<td>${pageScope.member.nick}</td>
				<td>${pageScope.member.reason}</td>
				<td>${pageScope.member.withdrawDate}</td>
				<td><%-- ${pageScope.member.state} --%>
				<c:choose>
					<c:when test="${pageScope.member.state == 1}">
						정상회원
					</c:when>
					<c:when test="${pageScope.member.state == 2}">
						탈퇴회원
					</c:when>
					<c:otherwise>강제탈퇴 회원</c:otherwise>
				</c:choose>
				</td>
				
			</tr>
			
		</c:forEach>
	</c:if>
	</tbody>
</table>

<%-- 페이징 처리 --%>
<div id="paging">
  	<c:set var="pageBlock" value="${requestScope.pageBlock}" scope="page"/>		
	<c:set var="startPage" value="${requestScope.startPage}" scope="page"/>
	<c:set var="endPage" value="${requestScope.endPage}" scope="page"/>
	<c:set var="totalPage" value="${requestScope.totalPage}" scope="page"/>	
	<c:set var="currentPage" value="${param.currentPage}" scope="page"/>
	
	<c:if test="${startPage > pageBlock}">
		<c:url var="prevUrl" value="/member_withdrawList.do">
				<c:param name="currentPage" value="${startPage - pageBlock}"/>
		</c:url>
		<a href="${prevUrl}">[Prev]</a> 
	</c:if>
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<c:if test="${i == currentPage}">
			&nbsp;${i}&nbsp;
		</c:if>
		<c:if test="${i != currentPage}">
			<c:url var="url" value="/member_withdrawList.do">
				<c:param name="currentPage" value="${i}"/>
			</c:url>
			<a href="${url}">&nbsp;${i}&nbsp;</a>
		</c:if>	
	</c:forEach>
	<c:if test="${endPage < totalPage}">
		<c:url var="nextUrl" value="/member_withdrawList.do">
				<c:param name="currentPage" value="${endPage + 1}"/>
		</c:url>
		<a href="${nextUrl}">[Next]</a> 
	</c:if>
</div>
</body>
</html>