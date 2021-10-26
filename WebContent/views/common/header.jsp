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
	<header>
		<div class="header-inner">
			<div class="logo">
				<a href="${pageContext.request.contextPath}/index.jsp"><img src="" alt="logo"></a>
			</div>
			<nav>
				<ul>
					<li>숙소리뷰</li>
					<li>숙소톡톡
						<div class="gnb2-box">
							<ul>
								<c:url var="url" value="/views/common/templateLeftMenu.jsp">
									<c:param name="nav" value="/views/boardLeftMenu" />
									<c:param name="content" value="/views/boardList" />
								</c:url>
								<li><a href="${url}">게시판명1</a></li>
								<li>게시판명2</li>
							</ul>
						</div>
					</li>
				</ul>
			</nav>
			<div class="util">
				<ul>
					<c:if test="${empty sessionScope.member.id}">
						<li class="join"><a
							href="${pageContext.request.contextPath}/member_writeForm.do">회원가입</a></li>
						<li class="login"><a
							href="${pageContext.request.contextPath}/member_login.jsp">로그인</a></li>

					</c:if>
					<c:if test="${!empty sessionScope.member.id}">
						<li class="user-alarm"><span id="alarmListBtn">알림</span><span
							class="notion"></span>
							<div id="alarm-box"></div></li>
						<li class="user-note">쪽지<span class="notion"><i
								class="ico">3</i></span></li>
						<li><img src="" alt="등급아이콘" class="user-ico"> <span
							class="user-name">${sessionScope.member.nick}</span> <span
							class="arrow-bottom">▼</span>
							<div>
								<ul>
									<li><a href="">나의 숙소 리뷰</a></li>
									<li><a href="">내가 쓴 게시글</a></li>
									<li><a href="">등업신청</a></li>
									<li><a
										href="${pageContext.request.contextPath}/member_detailForm.do">정보수정</a></li>
									<li><a
										href="${pageContext.request.contextPath}/member_logout.do">로그아웃</a></li>
								</ul>
							</div>
							</li>
					</c:if>
				</ul>
			</div>
		</div>
		<script>
			$(document).ready(function () {
				const memNo = ${sessionScope.member.memNo};
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
							if (alarms.length == 0) {
								htmlStr += `<li class="alarm_no">새로운 알림이 없습니다.</li>`;
							} else {
								htmlStr += "<div class='box-top'><ul>";
								for (let i = 0; i < alarms.length; i++) {
									htmlStr += "<li><div class='alarm-top'><span>" + alarmType[alarms[i].alarmType -
											1] +
										"</span><button class='deleteAlarmBtn' value='" + alarms[i].no +
										"'>삭제</button></div>";
									htmlStr +=
										"<div class='alarm-bottom'><a href='" + alarms[i].alarmUrl + "'>" + alarms[i]
										.alarmContent + "</a></div></li>";
								}
								htmlStr +=
									"</ul></div><div class='box-bottom'><button id='deleteAllAlarmBtn'>전체 삭제</button></div>";
							}
							$('#alarm-box').html(htmlStr);

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
				$('#alarmListBtn').on('click', function () {
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

				//알림 클릭시 모달창 켜지기
				$(container).on('click', function () {
					alarmModal.css('display', 'block');
				})

				//알림 외에 영역 클릭시 닫기
				$(document).click(function (e) {
					if (container.has(e.target).length === 0) {
						alarmModal.css('display', 'none');
					}

				});
				//******알림AJAX작동 컨트롤 끝*******//
			});
		</script>
	</header>