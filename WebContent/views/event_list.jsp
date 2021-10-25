<%-- views/event_list.jsp --%>
<%@ page contentType="text/html; charset=UTF-8"%>
<div id="search">
	<div id="searchForm">
		<div>
			<label for="eventName">이벤트명</label><input type="text" id="eventName"
				class="eventName" style="height: 30px;" name="eventName">
		</div>
		<div>
			<label for="alarmUrl">알림 랜딩 주소</label><input type="text"
				id="alarmUrl" class="alarmUrl" style="height: 30px;" name="alarmUrl">
		</div>
		<div>
			<div>
				<label for="startDate">기간</label><input type="text" id="startDate"
					class="startDate" style="height: 30px;" name="startDate"><span>~</span>
				<label for="endDate">기간</label><input type="text" size="30"
					id="endDate" class="endDate" style="height: 30px;" name="endDate">
			</div>
			<div>
				<label for="eventName">추첨 게시글 수</label><input type="text"
					id="endDate" class="startDate" style="height: 30px;"
					name="startDate"><span>개</span>
			</div>
		</div>
	</div>
	<table>
		<thead>
			<tr>
				<td></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="checkbox" name="boardNo" value="0"></td>
				<td>제목1</td>
				<td class="memNo">홍길동<input type="hidden" name="memNo" value="1"></td>
				<td>2020-10-22</td>
				<td>1000</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="boardNo" value="1"></td>
				<td>제목2</td>
				<td class="memNo">홍길동2<input type="hidden" name="memNo" value="2"></td>
				<td>2020-10-23</td>
				<td>5</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5"><button type="button" id="searchBtn">알림발송</button>
				<td>
			</tr>
		</tfoot>
	</table>
</div>
<div class="result"></div>

<!--  비동기로 알림 발송 -->
<script>

$(function () {
	const getAjax = function (url, alarmType, alarmContent, alarmUrl, memNo) {
		// resolve, reject는 자바스크립트에서 지원하는 콜백 함수이다.
		return new Promise((resolve, reject) => {
			$.ajax({
				url: url,
				method: 'POST',
				dataType: 'json',
				data: {
					alarmType: alarmType,
					alarmContent: alarmContent,
					alarmUrl: alarmUrl,
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
	}
	async function requestProcess(url, alarmType, alarmContent, alarmUrl, memNo) {
		try {

			const alarms = await getAjax(url, alarmType, alarmContent, alarmUrl, memNo);

			var htmlStr = '';
			for (let i = 0; i < alarms.length; i++) {
				htmlStr += alarms[0].resultCount;
			}
			$('.result').html("성공 결과 : " + htmlStr);

		} catch (error) {
			console.log("error : ", error);
		}
	}

	$('#searchBtn').on('click', function () {
		
		const alarmType = 3;
		const eventName = $('input[name=eventName]').val();
		const alarmContent = "'" + eventName + "' 이벤트에 당첨되었습니다!";
		const alarmUrl = $('input[name=alarmUrl]').val();
		const memNo = [];

			$('input[name=boardNo]:checked').each(function () { //체크된 리스트 저장
				const mb = $(this).parent().siblings('.memNo').find('input').val();
				memNo.push(mb);
			});
			console.log(memNo);
		
		requestProcess('${pageContext.request.contextPath}/SendAlarm.do', alarmType, alarmContent, alarmUrl, memNo);
		
	});
});
</script>