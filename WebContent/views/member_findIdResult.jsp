<%-- member_findIdResult.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

아이디 찾기 결과
<ul>
	 <c:forEach var="result" items="${requestScope.findIdResultList}" varStatus="status">        	

		<li><c:if test="${fn:length(findIdResultList) > 1 }"> ${result.nick} - </c:if>아이디 : ${result.id} 가입일 : ${result.joinDate}</li>
	</c:forEach>
</ul>

<button type="button" onclick="location.href='${pageContext.request.contextPath}/member_findPwForm.jsp' ">비밀번호 찾기</button>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/member_login.jsp'  ">로그인하기</button>
