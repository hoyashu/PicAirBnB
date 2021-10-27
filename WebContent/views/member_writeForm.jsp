<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta http-equiv=”Content-Type” content=”text/html; charset=UTF-8“>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
// 아이디 유효성 검사(1 = 중복 / 0 != 중복)

$(document).ready(function() {	
	
	$("#id").blur(function() {
		console.log("call");
		// id = "id_reg" / name = "userId"
		var id = $('#id').val();
		
		$.ajax({
			url : '${pageContext.request.contextPath}/member_idCheck.do?id='+ id,
			type : 'get',
			success : function(data) {
				console.log("1 = 중복o / 0 = 중복x : "+ JSON.parse(data));							
				const result = JSON.parse(data);
				
				if (result.overLapId == 1) {
						// 1 : 아이디가 중복되는 문구
						$("#id_check").text("사용중인 아이디입니다.");
						$("#id_check").css("color", "red");
						$("#submit").attr("disabled", true);
					} else {
						$("#id_check").text("사용 가능한 아이디입니다.");
						$("#id_check").css("color", "green");
						$("#submit").attr("disabled", false);
					}
				}, error : function() {
						console.log("실패");
				}
			});
		});
	$("#nick").blur(function() {
		console.log("call");
		// id = "id_reg" / name = "userId"
		var nick = $('#nick').val();
			
		$.ajax({
			url : '${pageContext.request.contextPath}/member_nickCheck.do?nick='+ nick,
			type : 'get',
			success : function(data) {
				console.log("1 = 중복o / 0 = 중복x : "+ JSON.parse(data));							
				const result = JSON.parse(data);
				
				if (result.overLapNick == 1) {
						// 1 : 아이디가 중복되는 문구
						$("#nick_check").text("사용중인 닉네임 입니다.");
						$("#nick_check").css("color", "red");
						$("#submit").attr("disabled", true);
					} else {
						$("#nick_check").text("사용 가능한 닉네임입니다.");
						$("#nick_check").css("color", "green");
						$("#submit").attr("disabled", false);
					}
				}, error : function() {
						console.log("실패");
				}
			});
		});
	
});
</script>
</head>

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
            <div class="check_font" id="id_check"></div>
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

            </div>
            <div class="check_font" id="nick_check"></div>
      
            
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
        <button class="w-100 btn btn-lg btn-primary" type="submit" id="submit">회원가입</button>
    </form>
</div>