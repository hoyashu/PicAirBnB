<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, domain.MemberVo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html lang='ko'>
    <head>
        <meta charset='UTF-8'>
        <title>게시글 목록보기</title>
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
    </head>
<body>
<h1>회원 목록 조회</h1>

<table>
	<thead>
	<tr>
		<th>번호</th><th>닉네임</th><th>등급</th><th>가입일</th><th>방문수</th><th>게시글수</th><th>댓글수</th><th>성별</th>
	</tr>
	</thead>
	<tbody>
	<c:if test="${empty requestScope.members}">
		<tr><td colspan="8">등록한 게시글이 없습니다.</td></tr>
	</c:if>
	<c:if test="${not empty requestScope.members}">
		<c:forEach var="member" items="${requestScope.members}" varStatus="loop">
		<c:url var="url" value="/modifyMember.do">
			<c:param name="no" value="${pageScope.member.memNo}"></c:param>
		</c:url>
			<tr>
				<td>${requestScope.totalPostCount - (param.currentPage - 1) * requestScope.postSize - loop.index}</td>
				<td><a href="${pageScope.url}">${pageScope.member.nick}</a></td>
				<td>${pageScope.member.grade}</td>
				<td>${pageScope.member.joinDate}</td>
				<td>${pageScope.member.visitCount}</td>
				<td>${pageScope.member.boardCount}</td>
				<td>${pageScope.member.commentCount}</td>
				<td>${pageScope.member.gender}</td>
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
		<c:url var="prevUrl" value="/listAllMember.do">
				<c:param name="currentPage" value="${startPage - pageBlock}"/>
		</c:url>
		<a href="${prevUrl}">[Prev]</a> 
	</c:if>
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<c:if test="${i == currentPage}">
			&nbsp;${i}&nbsp;
		</c:if>
		<c:if test="${i != currentPage}">
			<c:url var="url" value="/listAllMember.do">
				<c:param name="currentPage" value="${i}"/>
			</c:url>
			<a href="${url}">&nbsp;${i}&nbsp;</a>
		</c:if>	
	</c:forEach>
	<c:if test="${endPage < totalPage}">
		<c:url var="nextUrl" value="/listAllMember.do">
				<c:param name="currentPage" value="${endPage + 1}"/>
		</c:url>
		<a href="${nextUrl}">[Next]</a> 
	</c:if>
</div>
</body>
</html>