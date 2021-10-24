<%-- memberLoginForm.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang='ko'>

    <head>
        <meta charset='UTF-8'>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <title></title>
        <style>
            
        </style>      
    </head>

    <body>
    
     	<%-- <%session.invalidate();%> --%>
     	
        <article>
            <div class="container" role="main">
                <h2>로그인</h2>
                <form action="${pageContext.request.contextPath}/loginMember.do" method="post">
                
                    <div class="mb-3">
                        <label for="id">이메일</label>
                        <input type="email" class="form-control" name="id" id="id" >
                    </div>                   
                   
                    <div class="mb-3">
                        <label for="pwd">비밀번호</label>
                        <input type="password" class="form-control" name="pwd" id="pwd" >
                    </div>
                    
                    
                    <div class="mb-3">
                        <label for="staylogin">로그인 상태 유지</label>
                        <input type="checkbox" class="form-control" name="staylogin" id="staylogin" >
                    </div>
                    
                <div>
                    <button type="submit">로그인</button>
                </div>
                
                </form>
                
                <input type="text"  value="${param.memNo}">
                
				<c:if test="${not empty sessionScope.message}">
					<span style="color : red;">${sessionScope.message}</span>
				</c:if>	
			
            </div>
        </article>
    </body>
</html>
