<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, domain.NoteVo, model.dao.note.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
	$(document).ready(function(){
		$('#removeBtn').on('click',function(){
			
			$('#noteState').attr('action','/PicAirBnB/Note_CancelReceiveNote.do');
			$('#noteState').attr('method','post');
			$('#noteState').submit();
		});
		
		$('#saveBtn').on('click',function(){
			
			$('#noteState').attr('action','/PicAirBnB/Note_ModifyNoteSave.do');
			$('#noteState').attr('method','post');
			$('#noteState').submit();
		});
	});
</script>
<form action="${pageContext.request.contextPath}/Note_WriteNoteForm.do" method="GET">
	<button type="submit">쪽지 쓰기</button>
</form>

<form id="noteState">
<input type="hidden" name="removePage" value="${requestScope.removePage}">
<input type="hidden" name="noteNo" value="${requestScope.noteNo}">
<button id="removeBtn" type="submit">삭제</button>
<button id="saveBtn" type="submit">보관</button>
</form>

<h1>쪽지 상세보기</h1>
<a href="${pageContext.request.contextPath}/Note_ReceiveList.do">받은 쪽지</a>
<a href="${pageContext.request.contextPath}/Note_SendList.do">보낸 쪽지</a>
<a href="${pageContext.request.contextPath}/Note_SaveList.do">보관함</a>

<br><br>
<%
int getMbNo = (Integer)(request.getAttribute("noteGetmbNo"));
NoteDao notedao = NoteDao.getInstance();
String getMbId = notedao.selectMbId(getMbNo);
pageContext.setAttribute("getMbId", getMbId);

int sendMbNo = (Integer)(request.getAttribute("noteSendmbNo"));
String sendMbId = notedao.selectMbId(sendMbNo);
pageContext.setAttribute("sendMbId", sendMbId);
%>
보낸 사람 : ${pageScope.sendMbId} <br>
내용 : ${requestScope.noteCon } <br>
받는 사람 : ${pageScope.getMbId } <br>
발송 날짜 : ${requestScope.noteDateTime } <br>















