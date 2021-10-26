<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, domain.LogrecordVo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form action="${pageContext.request.contextPath}/Note_WriteNoteForm.do" method="GET">
	<button type="submit">쪽지 쓰기</button>
</form>


<a href="${pageContext.request.contextPath}/Note_ReceiveList.do">받은 쪽지</a>
<a href="${pageContext.request.contextPath}/Note_SendList.do">보낸 쪽지</a>
<a href="${pageContext.request.contextPath}/Note_SaveList.do">보관함</a>
<br>
<form action="${pageContext.request.contextPath}/Note_WriteNote.do" method="POST">
받는 사람 : <input type="text" name="getMbIds" placeholder="받는사람아이디"><br>
내용 : <textarea name="noteCon" cols="30" rows="6" placeholder="받는사람"></textarea>
<button type="submit">전송</button>
</form>