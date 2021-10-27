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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/reset.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/common.css">

	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-1.11.0.min.js"></script>

	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/common.js">
	</script>
</head>

<body>

	<header class="py-3 mb-4 border-bottom">
		<div class="container">
			<div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
				<a href="${pageContext.request.contextPath}/index.jsp"
					class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
					<img src="resource\img\logo.png" alt="사이트 로고" class="logo me-5">
				</a>

				<ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
					<li><a href="${pageContext.request.contextPath}/listPost.do?boardNo=1" class="nav-link px-2 link-dark">숙소리뷰</a></li>
					<li class="dropdown"><a href="#" class="nav-link px-2 link-dark dropdown-toggle"
							id="dropdownBoard1" data-bs-toggle="dropdown" aria-expanded="false">숙소톡톡</a>
						<ul class="dropdown-menu text-small" aria-labelledby="dropdownBoard1">
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/listPost.do?boardNo=2">게시판명1</a></li>
							<li><a class="dropdown-item" href="#">게시판명2</a></li>
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/listPost.do?boardNo=4">이벤트</a></li>
						</ul>
						
					</li>
				</ul>
				<c:if test="${empty sessionScope.member.id}">
					<div class="nav text-end">
						<button type="button" class="btn btn-outline-primary me-2"
							onclick="location.href='${pageContext.request.contextPath}/member_writeForm.do'">회원가입</button>
						<button type="button" class="btn btn-primary"
							onclick="location.href='${pageContext.request.contextPath}/member_login.jsp'">로그인</button>
					</div>
				</c:if>
				<c:if test="${!empty sessionScope.member.id}">
					<ul class="nav col-12 col-lg-auto mb-2 justify-content-center mb-md-0 me-4">
						<li class="dropdown"><a href="#" class="nav-link px-2 link-dark dropdown-toggle user-alarm"
								id="dropdownAlarmList" data-bs-toggle="dropdown" aria-expanded="false">알림<span
									class="notion"></span></a>
							<div id="alarm-box" class='dropdown-menu text-small' aria-labelledby='dropdownAlarmList'>
							</div>
						</li>
						<li><a href="${pageContext.request.contextPath}/Note_ReceiveList.jsp"
								class="nav-link px-2 link-dark user-note">쪽지<span class="notion"><i
									class="ico">3</i></span></a></li>
					</ul>
					<div class="dropdown text-end">
						<a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUserMenu"
							data-bs-toggle="dropdown" aria-expanded="false">
							<img src="https://github.com/mdo.png" alt="mdo" width="32" height="32"
								class="rounded-circle"><span class="user-name">${sessionScope.member.nick}</span>
						</a>
						<ul class="dropdown-menu text-small" aria-labelledby="dropdownUserMenu">
							<li><a class="dropdown-item" href="#">나의 숙소 리뷰</a></li>
							<li><a class="dropdown-item" href="#">내가 쓴 게시글</a></li>
							<li><a class="dropdown-item" href="#">등업신청</a></li>
							<li><a class="dropdown-item"
									href="${pageContext.request.contextPath}/member_detailForm.do">정보수정</a>
							</li>
							<li>
								<hr class="dropdown-divider">
							</li>
							<li><a class="dropdown-item"
									href="${pageContext.request.contextPath}/member_logout.do">로그아웃</a>
							</li>
						</ul>
					</div>
					<script>
						$(document).ready(function () {
							const memNo = "${sessionScope.member.memNo}";
							const alarmType = ['새 댓글', '등업', '이벤트 당첨', '게시글 신고', '댓글 신고', '알림']

							//******알림 목록 조회AJAX 시작*******//
							function init() {
								const getListAjax = function (url, memNo) {
									return new Promise((resolve, reject) => {
										$.ajax({
											url: url,
											method: 'POST',
											dataType: 'json',
											data: {
												memNo: memNo
											},
											success: function (data) {
												resolve(data);
											},
											error: function (e) {
												reject(e);
											}
										});
									});
								};
								async function listRequestProcess(url, memNo) {
									try {
										const alarms = await getListAjax(url, memNo);
										var htmlStr = '';
										htmlStr += "<div class='box-top'><ul class='p-0'>";
										if (alarms.length == 0) {
											htmlStr += `<li class='dropdown-item'>새로운 알림이 없습니다.</li>`;
										} else {
											for (let i = 0; i < alarms.length; i++) {
												htmlStr += "<li class='dropdown-item'><div class='alarm-top'><span>" +
													alarmType[alarms[i].alarmType - 1];
												htmlStr += "</span><button class='deleteAlarmBtn' value='" + alarms[i].no +
													"'>삭제</button></div>";
												htmlStr += "<div class='alarm-bottom'><a href='" + alarms[i].alarmUrl + "'>" +
													alarms[i].alarmContent;
												htmlStr += "</a></div></li>";
											}
										}
										htmlStr +=
											"</ul></div><div class='box-bottom'><button id='deleteAllAlarmBtn'>전체 삭제</button></div>";
										$('#alarm-box').html(htmlStr).dropdown();
									} catch (error) {
										console.log("error : ", error);
									};
								};

								listRequestProcess('${pageContext.request.contextPath}/AlarmList.do', memNo);
							};
							//******알림 목록 조회AJAX 끝*******//


							//******읽지 않은  조회AJAX 시작*******//
							function setAlarmCount() {

								const getAlarmCountAjax = function (url, memNo) {
									return new Promise((resolve, reject) => {
										$.ajax({
											url: url,
											method: 'POST',
											dataType: 'json',
											data: {
												memNo: memNo
											},
											success: function (data) {
												resolve(data);
											},
											error: function (e) {
												reject(e);
											}
										});
									});
								};

								async function AlarmCountRequestProcess(url, memNo) {
									try {
										const alarm = await getAlarmCountAjax(url, memNo);
										if (alarm[0].resultCount > 0) {
											var htmlStr = "<i class='ico'>" + alarm[0].resultCount + "</i>";
											$('.user-alarm .notion').html(htmlStr);
										}
									} catch (error) {
										console.log("error : ", error);
									};
								};
								AlarmCountRequestProcess('${pageContext.request.contextPath}/AlarmNoReadCount.do', memNo);
							};
							//******읽지 않은  조회AJAX 시작*******//


							//******알림 선택삭제 AJAX 시작*******//
							function delectAlarm(alarmNo) {
								const delectAlarmAjax = function (url, alarmNo, memNo) {
									return new Promise((resolve, reject) => {
										$.ajax({
											url: url,
											method: 'POST',
											dataType: 'json',
											data: {
												alarmNo: alarmNo,
												memNo: memNo
											},
											success: function (data) {
												resolve(data);
											},
											error: function (e) {
												reject(e);
											}
										});
									});
								};

								async function delectRequestProcess(url, alarmNo, memNo) {
									try {
										const alarms = await delectAlarmAjax(url, alarmNo, memNo);
										var htmlStr = alarms[0].result;
										if (htmlStr == "OK") {
											init();
											setAlarmCount();
										} else {
											alert("삭제에 실패했습니다.");
										}

									} catch (error) {
										console.log("error : ", error);
									};
								};
								delectRequestProcess('${pageContext.request.contextPath}/AlarmDelete.do', alarmNo, memNo);
							};
							//******알림 선택삭제 AJAX 끝*******//

							//******알림 전체삭제 AJAX 시작*******//
							function delectAllAlarm() {

								const delectAllAlarmAjax = function (url, memNo) {
									return new Promise((resolve, reject) => {
										$.ajax({
											url: url,
											method: 'POST',
											dataType: 'json',
											data: {
												memNo: memNo
											},
											success: function (data) {
												resolve(data);
											},
											error: function (e) {
												reject(e);
											}
										});
									});
								};

								async function delectAllRequestProcess(url, memNo) {
									try {
										const alarms = await delectAllAlarmAjax(url, memNo);
										var htmlStr = alarms[0].result;
										if (htmlStr == "OK") {
											init();
											setAlarmCount();
										} else {
											alert("전체 삭제에 실패했습니다.");
										}

									} catch (error) {
										console.log("error : ", error);
									};
								};
								delectAllRequestProcess('${pageContext.request.contextPath}/AlarmAllDelete.do', memNo);
							};
							//******알림 선택삭제 AJAX 끝*******//


							//******알림AJAX작동 컨트롤 시작*******//
							//읽지 않은 알림 개수 셋팅
							setAlarmCount();

							//알림 목록 조회
							$('#dropdownAlarmList').on('click', function () {
								init();
							});

							//알림 선택 삭제
							$(document).on("click", '.deleteAlarmBtn', function (e) {
								const alarmNo = this.value;
								delectAlarm(alarmNo);
							});

							//알림 전체 삭제
							$(document).on("click", '#deleteAllAlarmBtn', function (e) {
								delectAllAlarm();
							});

							//알림영역 지정
							const container = $('.user-alarm');
							const alarmModal = $('#alarm-box');
							//******알림AJAX작동 컨트롤 끝*******//
						});
					</script>
				</c:if>
			</div>
		</div>
	</header>