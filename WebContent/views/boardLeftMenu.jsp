<%-- boardLeftMenu.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*, domain.NoteVo, model.dao.note.*, domain.RealTime"%>
<%
pageContext.setAttribute("realTimeMemberCount", RealTime.memberCount);
pageContext.setAttribute("realTimeMemberIds", RealTime.memberIds);
%>
<c:url var="url" value="/uesrTemplateLeftMenu.jsp">
	<c:param name="nav" value="/introLeft" />
	<c:param name="content" value="/intro" />
</c:url>
boardLeftMenu.jsp 페이지입니다.<br>
<a href="${url}">게시판 주소</a>

실시간 접속자 수 : ${pageScope.realTimeMemberCount } <br>
	
	<c:if test="${pageScope.realTimeMemberCount == 0 }" >
		접속자가 없습니다.
	</c:if>
	<c:if test="${pageScope.realTimeMemberCount != 0 }">
	현재 접속자<br>
	<c:forEach var="memberId" items="${pageScope.realTimeMemberIds }">
		${pageScope.memberId}<br>
	</c:forEach>
	</c:if>