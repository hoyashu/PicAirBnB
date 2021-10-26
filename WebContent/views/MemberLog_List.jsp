<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, domain.LogrecordVo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<h1>방문자 로그</h1>
<table>
	<thead>
	<tr>
		<th>번호</th><th>아이피</th><th>이름</th><th>로그일자</th>
	</tr>
	</thead>
	<tbody>
		<c:if test="${empty requestScope.logrecords}">
			<tr><td colspan="4">등록된 로그가 없습니다.</td></tr>	
		</c:if>
		<c:if test="${not empty requestScope.logrecords}">			
			<c:forEach var="logrecord" items="${requestScope.logrecords}" varStatus="loop">
				<tr>
					<td>${requestScope.logrecordCount - loop.index}</td>
					<td>${pageScope.logrecord.mbIp}</td>
					<td>${pageScope.logrecord.mbId}</td>
					<td>${pageScope.logrecord.mbLog}</td>
				</tr>
			</c:forEach>
		</c:if>		
	</tbody>
</table>

<%-- 페이징 처리 --%>
	<div id="paging">
	<c:set var="pageBlock" value="${requestScope.pageBlock }" scope="page" />
	<c:set var="startPage" value="${requestScope.startPage }" scope="page" />
	<c:set var="endPage" value="${requestScope.endPage }" scope="page" />
	<c:set var="totalPage" value="${requestScope.totalPage }" scope="page" />
	<c:set var="currentPage" value="${param.currentPage }" scope="page" />
	
	<c:if test="${startPage > pageBlock }">
		<c:url var="prevUrl" value="/MemberLog_List.do">
				<c:param name="currentPage" value="${startPage - pageBlock}" />
		</c:url>
		<a href="${prevUrl }">[Prev]</a>
	</c:if>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:if test="${i == currentPage }">
			&nbsp;${i}&nbsp;
		</c:if>
		<c:if test="${i != currentPage }">
			<c:url var="url" value="/MemberLog_List.do">
				<c:param name="currentPage" value="${i}" />
			</c:url>
			<a href="${url}">&nbsp;${i}&nbsp;</a>
		</c:if>
	</c:forEach>
	<c:if test="${endPage < totalPage }">
		<c:url var="nextUrl" value="/MemberLog_List.do">
				<c:param name="currentPage" value="${endPage + 1}" />
		</c:url>
		<a href="${nextUrl }">[Next]</a>
	</c:if>
</div>