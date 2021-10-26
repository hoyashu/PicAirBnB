<%-- memberLoginForm.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<article>
	<div class="container" role="main">
		<h2>로그인</h2>
		<form action="${pageContext.request.contextPath}/member_login.do"
			method="post">

			<div class="mb-3">
				<label for="id">이메일</label> <input type="email" class="form-control"
					name="id" id="id">
			</div>

			<div class="mb-3">
				<label for="pwd">비밀번호</label> <input type="password"
					class="form-control" name="pwd" id="pwd">
			</div>


			<div class="mb-3">
				<label for="staylogin">로그인 상태 유지</label> <input type="checkbox"
					class="form-control" name="staylogin" id="staylogin">
			</div>
			
			<div class="mb-3">
				<a href="${pageContext.request.contextPath}/member_findIdForm.jsp">아이디 찾기</a> | <a href="${pageContext.request.contextPath}/member_findPwForm.jsp">비밀번호 찾기</a>
			</div>
			
			<div>
				<button type="submit">로그인</button>
			</div>

		</form>

		<c:if test="${not empty sessionScope.message}">
			<span style="color: red;">${sessionScope.message}</span>
		</c:if>

	</div>
</article>
