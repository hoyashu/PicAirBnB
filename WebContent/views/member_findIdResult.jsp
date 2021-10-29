<%-- member_findIdResult.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="d-flex">
	<div class="row justify-content-center align-self-center form-small">
		<h2 class="text-center page-title">아이디 찾기 결과</h2>
		<c:if test="${empty requestScope.findIdResultList}">
			<ul>
				<li>존재하는 회원이 없습니다.<br>정보를 다시 확인하거나, 회원가입 해주세요.
				</li>
			</ul>
			<hr class="my-4">
			<button type="button" class="w-100 btn btn-lg btn-secondary mb-1"
				onclick="location.href='${pageContext.request.contextPath}/member_findIdForm.jsp'">아이디찾기</button>
			<button type="button" class="w-100 btn btn-lg btn-primary"
				onclick="location.href='${pageContext.request.contextPath}/member_writeForm.jsp'">로그인하기</button>
		</c:if>
		<c:if test="${not empty requestScope.findIdResultList}">
			<ul>
				<c:forEach var="result" items="${requestScope.findIdResultList}"
					varStatus="status">
					<li><c:if test="${fn:length(findIdResultList) > 1 }"> ${result.nick} - </c:if>아이디
						: ${result.id} 가입일 : ${result.joinDate}</li>
				</c:forEach>
			</ul>
			<hr class="my-4">
			<button type="button" class="w-100 btn btn-lg btn-secondary mb-1"
				onclick="location.href='${pageContext.request.contextPath}/member_findPwForm.jsp'">비밀번호찾기</button>
			<button type="button" class="w-100 btn btn-lg btn-primary"
				onclick="location.href='${pageContext.request.contextPath}/member_login.jsp'  ">로그인하기</button>
		</c:if>
	</div>
</div>

