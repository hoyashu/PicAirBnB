<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="loginForm" class="d-flex">
    <form action="${pageContext.request.contextPath}/member_write.do" method="post"
        class="row justify-content-center align-self-center form-small">
        <h2 class="text-center page-title">회원가입</h2>
        <div class="mb-3">
            <label for="id">이메일</label>
            <div class="input-group">
                <input type="email" class="form-control" name="id" id="id">
                <button type="submit" class="btn btn-secondary">인증메일발송</button>
            </div>
        </div>
        <div class="mb-3">
            <label for="certifyNo">인증번호</label>
            <div class="input-group">
                <input type="text" class="form-control" name="certifyNo" id="certifyNo">
                <button type="submit" class="btn btn-secondary">인증하기</button>
            </div>
        </div>
        <div class="mb-3">
            <label for="nick">닉네임</label>
            <div class="input-group">
                <input type="text" class="form-control" name="nick" id="nick">
                <button type="submit" class="btn btn-secondary">중복 확인</button>
            </div>
        </div>
        <div class="mb-3">
            <label for="name">이름</label> <input type="text" class="form-control" name="name" id="name">
        </div>

        <div class="mb-3">
            <label for="pwd">비밀번호</label> <input type="password" class="form-control" name="pwd" id="pwd">
        </div>

        <div class="mb-3">
            <label for="pwdCheck">비밀번호 확인</label> <input type="password" class="form-control" name="pwdCheck"
                id="pwdCheck">
        </div>

        <div class="mb-3">
        	<h4>성별</h4>
            <div class="form-check">
                <input id="M" name="paymentMethod" type="radio" class="form-check-input" name="gender" 
                value="M"><label class="form-check-label" for="M">남성</label>
                <input id="F" name="paymentMethod" type="radio" class="form-check-input" name="gender" 
                value="F"><label class="form-check-label" for="F">여성</label>
            </div>
        </div>

        <div class="mb-3">
            <label for="hp">휴대 전화</label> <input type="text" class="form-control" name="hp" id="hp">
        </div>
        <div class="mb-3">
            <label for="birthDay">생년월일</label> <input type="date" class="form-control" name="birthDay" id="birthDay">
        </div>
        <hr class="my-4">
        <button class="w-100 btn btn-lg btn-primary" type="submit">회원가입</button>
    </form>
</div>