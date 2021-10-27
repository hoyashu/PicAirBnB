<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, domain.MemberVo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html lang='ko'>
    <head>
        <meta charset='UTF-8'>
        <title></title>
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
        <script>
            $(document).ready(function(){
            	$(".checkbox").click(function () {
            		        	
            })
       

        </script>
    </head>
<body>
<h1>탈퇴 회원 관리</h1>

		<c:url var="url2" value="/member_withdrawList.do">
			<c:param name="memNo" value="${pageScope.member.memNo}"></c:param>
		</c:url>
        
		<div>
			<button onclick="${pageScope.url2}" type="button" name="withdrawMember" id="withdrawMember">가입불가 해체</button>
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
				<td>${pageScope.member.stateNo}</td>
				
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