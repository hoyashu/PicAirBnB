<%-- boardLeftMenu.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page
	import="java.util.*, domain.NoteVo, model.dao.note.*, domain.RealTime"%>
<%
	pageContext.setAttribute("realTimeMemberCount", RealTime.memberCount);
pageContext.setAttribute("realTimeMemberIds", RealTime.memberIds);
%>

<div id="side-title">숙소톡톡</div>
<ul class="board-list-menu">
	<li><a href="${pageContext.request.contextPath}/listPost.do?boardNo=3">숙소 사진전</a></li>
	<li><a href="${pageContext.request.contextPath}/listPost.do?boardNo=4">이벤트</a></li>
</ul>

<div class="now-connetion-member">
	실시간 접속자 수 : ${pageScope.realTimeMemberCount } <br>

	<c:if test="${pageScope.realTimeMemberCount == 0 }">
		접속자가 없습니다.
	</c:if>
	<c:if test="${pageScope.realTimeMemberCount != 0 }">
		현재 접속자<br>
		<c:forEach var="memberId" items="${pageScope.realTimeMemberIds }">
			${pageScope.memberId}<br>
		</c:forEach>
	</c:if>
</div>