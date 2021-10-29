<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, domain.NoteVo, model.dao.note.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="container">
	<script>
		$(document).ready(function () {
			$('#removeBtn').on('click', function () {
				var removePage = 1;

				$('#noteState').append("<input type='hidden' name='removePage' value='" + removePage + "'>")

				$('#noteState').attr('action', '/PicAirBnB/Note_CancelReceiveNote.do');
				$('#noteState').attr('method', 'post');
				$('#noteState').submit();
			});

			$('#saveBtn').on('click', function () {
				var removePage = 1;

				$('#noteState').append("<input type='hidden' name='removePage' value='" + removePage + "'>")

				$('#noteState').attr('action', '/PicAirBnB/Note_ModifyNoteSave.do');
				$('#noteState').attr('method', 'post');
				$('#noteState').submit();
			});
		});
	</script>
	<form action="${pageContext.request.contextPath}/Note_WriteNoteForm.do" method="GET">
		<button type="submit">쪽지 쓰기</button>
	</form>
	<form id="noteState">
		<button id="removeBtn" type="submit">삭제</button>
		<button id="saveBtn" type="submit">보관</button>
		<h1>수신 쪽지함</h1>
		<a href="${pageContext.request.contextPath}/Note_ReceiveList.do">받은 쪽지</a>
		<a href="${pageContext.request.contextPath}/Note_SendList.do">보낸 쪽지</a>
		<a href="${pageContext.request.contextPath}/Note_SaveList.do">보관함</a>

		<table class="table table-bordered table-striped table-condensed flip-content">
			<thead>
				<tr>
					<th></th>
					<th>보낸사람</th>
					<th>내용</th>
					<th>발송날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty requestScope.receiveNoteList}">
					<tr>
						<td colspan="4">받은 쪽지가 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${not empty requestScope.receiveNoteList}">
					<c:forEach var="receiveNote" items="${requestScope.receiveNoteList}" varStatus="loop">
						<%
				NoteVo sendNote = (NoteVo)pageContext.getAttribute("receiveNote");
				NoteDao notedao = NoteDao.getInstance();
				String sendMbId = notedao.selectMbId(sendNote.getNoteSendmbNo());
				pageContext.setAttribute("sendMbId", sendMbId);
			%>
						<c:url var="detailUrl" value="/Note_DetailNote.do">
							<c:param name="removePage" value="1" />
							<c:param name="noteNo" value="${pageScope.receiveNote.noteNo}" />
							<c:param name="noteGetmbNo" value="${pageScope.receiveNote.noteGetmbNo}" />
							<c:param name="noteSendmbNo" value="${pageScope.receiveNote.noteSendmbNo}" />
							<c:param name="noteCon" value="${pageScope.receiveNote.noteCon}" />
							<c:param name="noteDateTime" value="${pageScope.receiveNote.noteDateTime}" />
						</c:url>
						<tr>
							<td><input type="checkbox" name="noteNo" class="noteNo"
									value="${pageScope.receiveNote.noteNo}"></td>
							<td><a href="${detailUrl}">${pageScope.sendMbId}</a></td>
							<td><a href="${detailUrl}">${fn:substring(pageScope.receiveNote.noteCon, 0, 20)}</a></td>
							<td><a href="${detailUrl}">${pageScope.receiveNote.noteDateTime}</a></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</form>
	<%-- 페이징 처리 --%>
	<div id="paging">
		<c:set var="pageBlock" value="${requestScope.pageBlock }" scope="page" />
		<c:set var="startPage" value="${requestScope.startPage }" scope="page" />
		<c:set var="endPage" value="${requestScope.endPage }" scope="page" />
		<c:set var="totalPage" value="${requestScope.totalPage }" scope="page" />
		<c:set var="currentPage" value="${param.currentPage }" scope="page" />

		<c:if test="${startPage > pageBlock }">
			<c:url var="prevUrl" value="/Note_ReceiveList.do">
				<c:param name="currentPage" value="${startPage - pageBlock}" />
			</c:url>
			<a href="${prevUrl }">[Prev]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i == currentPage }">
				&nbsp;${i}&nbsp;
			</c:if>
			<c:if test="${i != currentPage }">
				<c:url var="url" value="/Note_ReceiveList.do">
					<c:param name="currentPage" value="${i}" />
				</c:url>
				<a href="${url}">&nbsp;${i}&nbsp;</a>
			</c:if>
		</c:forEach>
		<c:if test="${endPage < totalPage }">
			<c:url var="nextUrl" value="/Note_ReceiveList.do">
				<c:param name="currentPage" value="${endPage + 1}" />
			</c:url>
			<a href="${nextUrl }">[Next]</a>
		</c:if>
	</div>
</div>