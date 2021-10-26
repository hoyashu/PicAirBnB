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
<script>      

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
	    
	</script>
