<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, domain.MemberVo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h1>회원 목록 조회</h1>

<div>
	<button type="button" name="withdrawMemberBtn" id="withdrawMemberBtn">회원 탈퇴</button>
</div>
<table>

	<thead>
		<tr>
			<th></th>
			<th>회원번호</th>
			<th>닉네임</th>
			<th>등급</th>
			<th>가입일</th>
			<th>방문수</th>
			<th>게시글수</th>
			<th>댓글수</th>
			<th>성별</th>
		</tr>
	</thead>
	<tbody>

		<c:if test="${empty requestScope.members}">
			<tr>
				<td colspan="8">등록된 사용자가 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${not empty requestScope.members}">

			<c:forEach var="member" items="${requestScope.members}" varStatus="loop">

				<c:url var="url" value="/member_modify.do">
					<c:param name="memNo" value="${pageScope.member.memNo}"></c:param>
				</c:url>

				<tr>
					<td><input type="checkbox" value="${pageScope.member.memNo}-${pageScope.member.nick}"
							class="checkbox" name="checkbox"></td>
					<td>${pageScope.member.memNo}</td>
					<td><a href="${pageScope.url}">${pageScope.member.nick}</a></td>
					<td>${pageScope.member.grade}</td>
					<td>${pageScope.member.joinDate}</td>
					<td>${pageScope.member.visitCount}</td>
					<td>${pageScope.member.boardCount}</td>
					<td>${pageScope.member.commentCount}</td>
					<td>${pageScope.member.gender}</td>
				</tr>

			</c:forEach>
		</c:if>
	</tbody>
</table>

<%-- 페이징 처리 --%>
<div id="paging">
	<c:set var="pageBlock" value="${requestScope.pageBlock}" scope="page" />
	<c:set var="startPage" value="${requestScope.startPage}" scope="page" />
	<c:set var="endPage" value="${requestScope.endPage}" scope="page" />
	<c:set var="totalPage" value="${requestScope.totalPage}" scope="page" />
	<c:set var="currentPage" value="${param.currentPage}" scope="page" />

	<c:if test="${startPage > pageBlock}">
		<c:url var="prevUrl" value="/member_allList.do">
			<c:param name="currentPage" value="${startPage - pageBlock}" />
		</c:url>
		<a href="${prevUrl}">[Prev]</a>
	</c:if>
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<c:if test="${i == currentPage}">
			&nbsp;${i}&nbsp;
		</c:if>
		<c:if test="${i != currentPage}">
			<c:url var="url" value="/member_allList.do">
				<c:param name="currentPage" value="${i}" />
			</c:url>
			<a href="${url}">&nbsp;${i}&nbsp;</a>
		</c:if>
	</c:forEach>
	<c:if test="${endPage < totalPage}">
		<c:url var="nextUrl" value="/member_allList.do">
			<c:param name="currentPage" value="${endPage + 1}" />
		</c:url>
		<a href="${nextUrl}">[Next]</a>
	</c:if>
</div>
<script>
	$(document).ready(function () {
		$('#withdrawMemberBtn').on('click', function () {
			let memNo_arr = [];
			let nick_arr = [];
			$('input[name=checkbox]:checked').each(function () {
				let temp = $(this).val().split("-");
				memNo_arr.push(temp[0]);
				nick_arr.push(temp[1]);
			});
			location.href = '${pageContext.request.contextPath}/member_withdrawForm.do?memNoList=' +
				memNo_arr.join(",") + "&nickList=" + nick_arr.join(",");
		});

	});
</script>
