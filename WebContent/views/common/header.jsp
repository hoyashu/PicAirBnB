<%-- template.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>PicAirBnB</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/common.css">
<script src="${pageContext.request.contextPath}/resource/js/common.js"></script>
</head>

<body>
	<header>
		헤더 영역입니다
		<div>
			<c:url var="url" value="/views/common/templateLeftMenu.jsp">
				<c:param name="nav" value="/views/boardLeftMenu" />
				<c:param name="content" value="/views/boardList" />
			</c:url>
			<a href="${url}">게시판 목록</a>
		</div>
	</header>