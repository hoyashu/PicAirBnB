<%-- member_findIdForm.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<input type="text" name="id" id="id" placeholder="아이디">
	<button type="button" id="sendCertifyCodeBtn">인증메일 발송</button>
	<input type="text" name="certifyNo" id="certifyNo" placeholder="인증번호">
	<button type="button" id="checkCertifyCodeBtn">인증번호 확인</button>


<script type="text/javascript">
$(document).ready(function () {
	var isCertification = false;
	var isClick = false;
	var code = 0;
	//******인증번호 메일 발송AJAX 시작*******//
	function sendCertifyCode(id) {
		isClick = true;
		const sendCodeAjax = function (url, id) {
			return new Promise((resolve, reject) => {
				$.ajax({
					url: url,
					method: 'POST',
					dataType :'json',
					data: {
						id: id
					},
					success: function (data) {
						resolve(data);
						isClick = false;
					},
					error: function (e) {
						reject(e);
					}
				});
			});
		};

		async function sendRequestProcess(url, id) {
			try {
				const result = await sendCodeAjax(url, id);
				if(result[0].result == 0){
					alert("인증번호가 전송되었습니다.");
					isCertification = true; //추후 인증 여부를 알기위한 값
					code = result[0].code
				}else if(result[0].result == 1){
					alert("가입되지 않은 회원입니다.");
					return false;
				}
				var htmlStr = '';
				$('#alarm-box').html(htmlStr);

			} catch (error) {
				console.log("error : ", error);
			};
		};

		sendRequestProcess('${pageContext.request.contextPath}/sendFindPwMail.do', id);
	};
	//******인증번호 메일 발송AJAX 끝*******//

	
 	//******인증번호 확인AJAX 시작*******//
	/* function sendCertifyCode(code) {
		isClick = true;
		const sendCodeAjax = function (url, code) {
			return new Promise((resolve, reject) => {
				$.ajax({
					url: url,
					method: 'POST',
					dataType :'json',
					data: {
						code: code
					},
					success: function (data) {
						resolve(data);
						isClick = false;
					},
					error: function (e) {
						reject(e);
					}
				});
			});
		};

		async function sendRequestProcess(url, code) {
			try {
				const result = await checkCodeAjax(url, id);
				console.log("result[0].code= "+result[0].code)
				if(result[0].result == 0){
					alert("인증번호가 전송되었습니다.");
					isCertification = true; //추후 인증 여부를 알기위한 값
				}else if(result[0].result == 1){
					alert("가입되지 않은 회원입니다.");
					return false;
				}
				var htmlStr = '';
				$('#alarm-box').html(htmlStr);

			} catch (error) {
				console.log("error : ", error);
			};
		};

		sendRequestProcess('${pageContext.request.contextPath}/checkFindPwMail.do', id);
	}; */
	//******인증번호 확인AJAX 끝*******//
	
	
	//인증 메일 발송
				$('#sendCertifyCodeBtn').on('click', function () {
					if(isClick == true){
						alert("발송중입니다. 기다려주세요.");
						return false;
					}
					
					if ($("#id").val().trim() == "") {
						alert("아이디를 입력하시오");
						return false;
					}
					sendCertifyCode($("#id").val().trim());
				});
	
	//
	$('#checkCertifyCodeBtn').on('click', function () {
		
		if(isCertification == false){
			alert("인증메일을 발송해주세요");
			return false;
		}
		
		if ($("#certifyNo").val().trim() == "") {
			alert("인증번호를 입력하세요");
			return false;
		}
		
		//checkCertifyCode($("#certifyNo").val().trim());
		
		if($("#certifyNo").val().trim() == code){
			alert("인증번호 확인!");
			
			
		}else{
			alert("인증번호가 올바르지 않습니다. 다시 입력하세요");
			return false;
		} 
	});
	//******알림AJAX작동 컨트롤 끝*******//
});
</script>