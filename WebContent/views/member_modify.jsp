<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<article>
	<div class="container" role="main">
		<h2>정보수정</h2>${sessionScope.memberModidy.nick}
		<form action="${pageContext.request.contextPath}/member_modify.do"
			method="post">

			<div class="mb-3">이메일 : ${sessionScope.memberModidy.id}</div>
			<div class="mb-3">
				<label for="nick">닉네임</label> <input type="text"
					class="form-control" name="nick" id="nick"
					value="${sessionScope.memberModidy.nick}">
			</div>

			<div class="mb-3">
				<button type="button">중복 확인</button>
			</div>

			<div class="mb-3">
				<label for="name">이름</label> <input type="text" class="form-control"
					name="name" id="name" value="${sessionScope.memberModidy.name}">
			</div>

			<div class="mb-3">
				<label for="pwd">현재 비밀번호</label> <input type="password"
					class="form-control" name="pwd" id="pwd">
			</div>

			<div class="mb-3">
				<label for="pwdCheck">새 비밀번호</label> <input type="password"
					class="form-control" name="changePwd" id="changePwd">
			</div>

			<div class="mb-3">
				<label for="pwdCheck">새 비밀번호 확인</label> <input type="password"
					class="form-control" name="changePwdCheck" id="changePwdCheck">
			</div>

			<div class="mb-3">
				<label for="gender">성별</label> <input type="radio" name="gender" value="M"
					<c:if test="${sessionScope.memberModidy.gender eq 'M'}">checked</c:if>>남성
				<input type="radio" name="gender"
					value="F" <c:if test="${sessionScope.memberModidy.gender eq 'F'}">checked</c:if>>여성
			</div>

			<div class="mb-3">
				<label for="hp">휴대 전화</label> <input type="text"
					class="form-control" name="hp" id="hp" value="${sessionScope.memberModidy.hp}">
			</div>

			<div class="mb-3">
				<label for="birthDay">생년월일</label> <input type="date"
					class="form-control" name="birthDay" id="birthDay"
					value="${sessionScope.memberModidy.birth}">
			</div>

			<div>
				<button type="submit">수정하기</button>
			</div>

		</form>
	</div>
</article>