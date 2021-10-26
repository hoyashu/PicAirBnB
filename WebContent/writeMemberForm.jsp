<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang='ko'>

    <head>
        <meta charset='UTF-8'>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <title>writeMemberForm</title>
        <style>
            
        </style>      
    </head>

    <body>
        <article>
            <div class="container" role="main">
                <h2>회원가입</h2>
                <form action="${pageContext.request.contextPath}/writeMember.do" method="post">
                
                    <div class="mb-3">
                        <label for="id">이메일</label>
                        <input type="email" class="form-control" name="id" id="id" >
                    </div>
                    
                    <div class="mb-3">
                    	<button type="button">인증메일발송</button>
                    </div>
                   
                    <div class="mb-3">
                        <label for="certifyNo">인증번호</label>
                        <input type="text" class="form-control" name="certifyNo" id="certifyNo" >
                    </div>
                    
                    <div class="mb-3">
                    	<button type="button">인증하기</button>
                    </div>
                   
                    <div class="mb-3">
                        <label for="nick">닉네임</label>
                        <input type="text" class="form-control" name="nick" id="nick" >
                    </div>
                    
                    <div class="mb-3">
                    	<button type="button">중복 확인</button>
                    </div>
                    
                    <div class="mb-3">
                        <label for="name">이름</label>
                        <input type="text" class="form-control" name="name" id="name" >
                    </div>
                    
                    <div class="mb-3">
                        <label for="pwd">비밀번호</label>
                        <input type="password" class="form-control" name="pwd" id="pwd">
                    </div>
                                      
                    <div class="mb-3">
                        <label for="pwdCheck">비밀번호 확인</label>
                        <input type="password" class="form-control" name="pwdCheck" id="pwdCheck">
                    </div>
                              
                     <div class="mb-3">
                        <label for="gender">성별</label>
                        <input type="radio" class="form-control" name="gender" id="gender" value="M">남성
                        <input type="radio" class="form-control" name="gender" id="gender" value="F">여성
                    </div>
                    
                    <div class="mb-3">
                        <label for="hp">휴대 전화</label>
                        <input type="text" class="form-control" name="hp" id="hp">
                    </div>
                    
                    <div class="mb-3">
                        <label for="birthDay">생년월일</label>
                        <input type="date" class="form-control" name="birthDay" id="birthDay">
                    </div>
     			          
                <div>
                    <button type="submit" >회원가입</button>
                </div>
                
                </form>
            </div>
        </article>
    </body>
</html>
