<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang='ko'>

    <head>
        <meta charset='UTF-8'>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <title>withdrawMemberForm</title>
        <style>
            
        </style>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" 
                integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" 
                crossorigin="anonymous"></script>
        <script>
        
        function getParameter(name) {
            name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
            var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                results = regex.exec(location.search);
            return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
        }
             $(document).ready(function() {          	

                 
                 var memNoList = getParameter("memNoList");
                 var nickList = getParameter("nickList");

                 console.log("memNoList : "+memNoList);
                 //console.log("nickList : "+nickList);
                 
                 nickList = nickList.split(',');
                 console.log(nickList);
                 var htmlstr = "";
                 for (var i = 0; i < nickList.length; i++) {
                	 htmlstr += nickList[i]+"\n";
				}
                 $('#nickNames').val(htmlstr)
       				/* var val = location.href.substr(
       		                location.href.lastIndexOf('=') + 1
       		            );

       				console.log(val);
       				
       			 	$('#nickNames').val(val.split(", ")); */
       			 	
       			 	
       		
            });
             

        </script>      
    </head>

    <body>
   			 
        <article>
            <div class="container" role="main">
                <h2>강제 탈퇴 팝업</h2>
                <form action="${pageContext.request.contextPath}/withdrawMember.do" method="post">
                	
                	<div class="mb-3">
                        <label for="nickNames">강제 탈퇴 회원</label>
                        <textarea class="form-control" rows="5" name="nickNames" id="nickNames" disabled></textarea>
                    </div>   
                    
                    <div class="mb-3">
                        <label for="reason">강제 탈퇴 사유</label>
                        <input type="text" class="form-control" name="reason" id="reason" >
                    </div>
                                    
                <div>
                    <button type="submit" >확인</button>
                </div>
                
                </form>
            </div>
        </article>
    </body>
</html>
