<%-- template.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>PicAirBnB</title>
<link rel="stylesheet"
   href="${pageContext.request.contextPath}/resource/css/reset.css">
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
   integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
   crossorigin="anonymous">

<link rel="stylesheet"
   href="${pageContext.request.contextPath}/resource/css/common.css">

<script type="text/javascript"
   src="${pageContext.request.contextPath}/resource/js/jquery-1.11.0.min.js"></script>

<script type="text/javascript"
   src="${pageContext.request.contextPath}/resource/js/common.js">
   </script>
</head>


    <body>
        <article>
            <div class="container" role="main">
                <h2>정보수정</h2>
                <form action="${pageContext.request.contextPath}/member_modify.do" method="post">
                
                    <div class="mb-3">
                        <label for="id">이메일</label>
                        <input type="email" class="form-control" name="id" id="id" value="${modifyId}">
                    </div>
                    
                    <div class="mb-3">
                    	<button type="button">인증메일발송</button>
                    </div>
                   
                    <div class="mb-3">
                        <label for="certifyNo">인증번호</label>
                        <input type="text" class="form-control" name="certifyNo" id="certifyNo">
                    </div>
                    
                    <div class="mb-3">
                    	<button type="button">인증하기</button>
                    </div>
                   
                    <div class="mb-3">
                        <label for="nick">닉네임</label>
                        <input type="text" class="form-control" name="nick" id="nick" value="${modifyNick}" >
                    </div>
                    
                    <div class="mb-3">
                    	<button type="button">중복 확인</button>
                    </div>
                    
                    <div class="mb-3">
                        <label for="name">이름</label>
                        <input type="text" class="form-control" name="name" id="name" value="${modifyName}" >
                    </div>
                    
                    <div class="mb-3">
                        <label for="pwd">현재 비밀번호</label>
                        <input type="password" class="form-control" name="pwd" id="pwd">
                    </div>
                    
                    <div class="mb-3">
                        <label for="pwdCheck">새 비밀번호</label>
                        <input type="password" class="form-control" name="changePwd" id="changePwd">
                    </div>
                                      
                    <div class="mb-3">
                        <label for="pwdCheck">새 비밀번호 확인</label>
                        <input type="password" class="form-control" name="changePwdCheck" id="changePwdCheck">
                    </div>
                    
                    <div class="mb-3">
                        <label for="gender">성별</label>
                        <input type="radio" class="form-control" name="gender" id="gender" value="M">남성
                        <input type="radio" class="form-control" name="gender" id="gender" value="F">여성
                    </div>
                    
                    <div class="mb-3">
                        <label for="hp">휴대 전화</label>
                        <input type="text" class="form-control" name="hp" id="hp" value="${modifyHp}" >
                    </div>
                    
                    <div class="mb-3">
                        <label for="birthDay">생년월일</label>
                        <input type="date" class="form-control" name="birthDay" id="birthDay" value="${modifyBrith}">
                    </div>
     			          
                <div>
                    <button type="submit">수정하기</button>
                </div>
               
                </form>
            </div>
        </article>
            
        <!-- <script>      

        const getAjax = function(url, keyfield, keyword) {
            // resolve, reject는 자바스크립트에서 지원하는 콜백 함수이다.
            return new Promise( (resolve, reject) => {
                $.ajax({                        
                    url: url,
                    method: 'POST',
                    dataType: 'json',
                    data: {
                     	keyfield: keyfield,
                    	keyword: keyword
                    },
                    success: function(data) {                    	
                        resolve(data);
                    }, 
                    error: function(e) {                    	
                        reject(e);
                    }
                });
            });
        }   
        

        async function requestProcess(url, keyfield, keyword) {
            try {
                const boards = await getAjax(url, keyfield, keyword);
                
                console.log('length : ', boards.length);
                
                var htmlStr = '';
                for (let i = 0; i < boards.length; i++) {
                	htmlStr += '<tr id="' + boards[i].no + '">';
                	htmlStr += '<td>' + (boards.length - i) + '</td>';
                	htmlStr += '<td>' + boards[i].subject + '</td>';
                	htmlStr += '<td>' + boards[i].writer + '</td>';
                	htmlStr += '<td>' + boards[i].writedate + '</td>';
                	htmlStr += '<td>' + boards[i].hitcount + '</td>';
                }
                
                $('body > table > tbody').html(htmlStr);
                  
            } catch (error) {
                console.log("error : ", error);   
            }
        }
        
        
        
	    $('#searchBtn').on('click', function() {				
			const keyfield = $('#keyfield option:selected').val();
	    	const keyword =	$('#keyword').val();
	    	//requestProcess('/helloWebApp/searchBoard.do?keyfield=' + keyfield + "&keyword=" + keyword);
	    	requestProcess('/helloWebApp/searchBoard.do', keyfield, keyword);
	   
	    });
	    
	    
	   //동적으로 생성된 요소에 이벤트 추가
	  
	   $('table > tbody').on('click', 'tr', function() {		
		   $(this).siblings().removeClass('highlight');
		   $(this).toggleClass("highlight");	
		   
		   location.href = '/helloWebApp/detailBoard.do?no=' + this.id;
		   
	   });
	    
	</script> -->
    </body> 
</html>
