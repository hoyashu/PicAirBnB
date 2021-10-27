<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
				<td>${pageScope.member.state}</td>
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
