<%@ page contentType="text/html; charset=utf-8" %>

<jsp:forward page="/views/common/templateLeftMenu.jsp">
	<jsp:param name="nav" value="/views/boardLeftMenu" />
	<jsp:param name="content" value="/views/detailPost" />
</jsp:forward>