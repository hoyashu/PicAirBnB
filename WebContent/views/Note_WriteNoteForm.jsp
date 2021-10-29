<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, domain.LogrecordVo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="container">
<h3 class="page-title">쪽지 쓰기</h3>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/Note_ReceiveList.do'" class="btn btn-secondary">받은 쪽지</button>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/Note_SendList.do'" class="btn btn-secondary">보낸 쪽지</button>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/Note_SaveList.do'" class="btn btn-secondary">보관함</button>
<form action="${pageContext.request.contextPath}/Note_WriteNote.do" method="POST">
	<div class="mb-3">
		<label for="getMbIds" class="form-label">받는 사람</label>
		<input type="text" class="form-control" name="getMbIds" id="getMbIds" placeholder="받는사람아이디">
		<p class="">받는 사람이 여러명일 경우 ,(쉼표)로 구분하여 작성할 것</p>
	</div>
	<div class="mb-3">
		<label for="getMbIds" class="form-label">내용</label>
		<textarea class="form-control" name="noteCon" cols="30" rows="6" placeholder="받는사람"></textarea>
	</div>
	<button type="submit" class="btn btn-primary">전송</button>
</form>
</div>