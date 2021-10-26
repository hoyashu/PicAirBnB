<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, domain.LogrecordVo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div>
<form action="${pageContext.request.contextPath}/MemberLog_WeeklyGraph.do" method="GET">
	<input type="hidden" name="typeOfGraph" value="2"> 
	<button type="submit">최근 7일</button>
</form>
<br>
<form action="${pageContext.request.contextPath}/MemberLog_WeeklyGraph.do" method="GET">
	<input type="hidden" name="typeOfGraph" value="3"> 
	<button type="submit">최근 30일</button>
</form>
<br>
</div>
		
	<a href="${pageContext.request.contextPath}/MemberLog_List.do">로그 기록 조회</a>

<div>
<br>
	 <c:if test="${requestScope.typeOfGraph == 2 }">
	 
	 
		 <c:forEach var="count" varStatus="loop" items="${requestScope.typeOfGraphLecordCounts } ">
		 				<td>${fn:length(requestScope.typeOfGraphLecordCounts)-pageScope.loop.index}일 전  </td>&nbsp;
		 </c:forEach>
	 	<br>
	 	<c:forEach var="count" varStatus="loop" items="${requestScope.typeOfGraphLecordCounts } ">
	 				
	 				<td>방문자 수 : ${pageScope.count } 명  </td>&nbsp;
	 	</c:forEach>
	 	<br>
	 	<c:forEach var="postcount" varStatus="loop" items="${requestScope.typeOfGraphPostCounts } ">
	 				<td>게시글 : ${pageScope.postcount } 개  </td>&nbsp;
	 	</c:forEach>
	 	<br>
	 </c:if>
	 
	 
	 <c:if test="${requestScope.typeOfGraph == 3 }">
	 	<table>
	 <tr>
		 <c:forEach var="count" varStatus="loop" items="${requestScope.typeOfGraphLecordCounts } ">
		 				<td>${fn:length(requestScope.typeOfGraphLecordCounts)-pageScope.loop.index}일 전  </td>
		 </c:forEach>
	 </tr>
	 <tr>
	 	<c:forEach var="count" varStatus="loop" items="${requestScope.typeOfGraphLecordCounts } ">
	 				
	 				<td>방문자 수 : ${pageScope.count } 명  </td>
	 	</c:forEach>
	 </tr>
	 <tr>
	 	<c:forEach var="postcount" varStatus="loop" items="${requestScope.typeOfGraphPostCounts } ">
	 				<td>게시글 : ${pageScope.postcount } 개  </td>
	 	</c:forEach>
	 </tr>
	 	</table>
	 </c:if>
	
</div>















