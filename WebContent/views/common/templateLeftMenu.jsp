<%-- templateLeftMenu.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="nav" value="${param.nav}.jsp" />
<c:set var="content" value="${param.content}.jsp" />

<jsp:include page="/views/common/header.jsp" flush="false" />
<style>
section {
	width: 80%;
	float: right;
}
</style>


<div class="container my-md-4 bd-layout">
	<aside class="bd-sidebar">
		<nav>
			<jsp:include page="${nav}" flush="false" />
		</nav>
	</aside>
	<section id="content">
		<jsp:include page="${content}" flush="false" />
	</section>
</div>
<jsp:include page="/views/common/footer.jsp" flush="false" />