<%-- memberLoginForm.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="loginForm" class="d-flex">
	<form action="${pageContext.request.contextPath}/member_login.do" method="post" class="row justify-content-center align-self-center form-signin form-small">
		<h2 class="text-center page-title">로그인</h2>
		<div class="form-floating">
			<input type="email" class="form-control" name="id" id="id"><label for="id">이메일</label>
		</div>
	
		<div class="form-floating">
			<input type="password" class="form-control" name="pwd" id="pwd"><label for="pwd">비밀번호</label>
			
			<c:if test="${not empty requestScope.message}">
			<span style="color: red;">${requestScope.message}</span>
			</c:if>
			
		</div>
		<div class="checkbox mb-3">
			<label>
				<input type="checkbox" value="remember-me"> Remember me
			</label>
		</div>
		<div class="mb-3">
			<a href="${pageContext.request.contextPath}/member_findIdForm.jsp">아이디 찾기</a> | <a
				href="${pageContext.request.contextPath}/member_findPwForm.jsp">비밀번호 찾기</a>
		</div>
		
		<button class="w-100 btn btn-lg btn-primary" type="submit">로그인</button>
	</form>
</div>