<%-- listPost.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*, domain.PostVo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang='ko'>
    <head>
        <title>게시글 목록보기</title>
        <style>
            table {
                width: 700px;
                border-collapse: collapse;
                margin: 50px auto;   
                font-size: 12px;             
            }

            table, tr, th, td {
                border: 1px solid blue;
                text-align: center;
            }
            
            th, td {
            	height: 35px;
            }

            h3 {
                text-align: center;
            }
            
            #paging {
            	width: 200px;
            	margin: 10px auto;
            }
            
            #writePost{
            	width: 100px;
            }

        </style>
    </head>

<body>

<center><h1>게시글 목록 조회</h1></center>

<table>
	<thead>
	<tr>
		<th>번호</th><th>게시글 목록</th><th>작성날짜</th><th>작성자</th><th>조회수</th>
	</tr>
	</thead>
	<tbody>
		<c:if test="${empty requestScope.posts}">
			<tr><td colspan="4">등록된 게시글이 없습니다.</td></tr>	
		</c:if>
		<c:if test="${not empty requestScope.posts}">			
			<c:forEach var="post" items="${requestScope.posts}" varStatus="loop">
				<c:url var="url" value="/detailPost.do">
					<c:param name="no" value="${pageScope.post.no}" />
					<c:param name="writerName" value="${pageScope.post.writerName}" />
				</c:url>
				<tr>
					<td>${requestScope.totalPostCount - (param.currentPage - 1) * requestScope.postSize - loop.index }</td>
					<td><a href="${pageScope.url}">${pageScope.post.subject}</a></td>
					<td>${pageScope.post.createDate}</td>
					<td>${pageScope.post.writerName}</td>					
					<td>${pageScope.post.views}</td>
				</tr>
			</c:forEach>
		</c:if>		
	</tbody>
</table>

<%-- 페이징 처리 --%>
<div id="paging">
  	<c:set var="pageBlock" value="${requestScope.pageBlock}" scope="page"/>		
	<c:set var="startPage" value="${requestScope.startPage}" scope="page"/>
	<c:set var="endPage" value="${requestScope.endPage}" scope="page"/>
	<c:set var="totalPage" value="${requestScope.totalPage}" scope="page"/>	
	<c:set var="currentPage" value="${param.currentPage}" scope="page"/>
	
	<c:if test="${startPage > pageBlock}">
		<c:url var="prevUrl" value="/listPost.do">
				<c:param name="currentPage" value="${startPage - pageBlock}"/>
		</c:url>
		<a href="${prevUrl}">[Prev]</a> 
	</c:if>
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<c:if test="${i == currentPage}">
			&nbsp;${i}&nbsp;
		</c:if>
		<c:if test="${i != currentPage}">
			<c:url var="url" value="/listPost.do">
				<c:param name="currentPage" value="${i}"/>
			</c:url>
			<a href="${url}">&nbsp;${i}&nbsp;</a>
		</c:if>	
	</c:forEach>
	<c:if test="${endPage < totalPage}">
		<c:url var="nextUrl" value="/listPost.do">
				<c:param name="currentPage" value="${endPage + 1}"/>
		</c:url>
		<a href="${nextUrl}">[Next]</a> 
	</c:if>
</div>

<div id="writePost">
	<c:url var="writePostUrl" value="/writePostForm.do"/>
	<a href="${writePostUrl}">글작성</a>
</div>
</body>
</html>






























