<%-- boardLeftMenu.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="url" value="/uesrTemplateLeftMenu.jsp">
	<c:param name="nav" value="/introLeft" />
	<c:param name="content" value="/intro" />
</c:url>
boardLeftMenu.jsp 페이지입니다.<br>
<a href="${url}">게시판 주소</a>