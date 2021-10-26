<%-- modifyPostForm.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html lang='ko'>
<meta charset='UTF-8'>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<title>게시글 수정</title>
<style>
table {
	width: 500px;
	border-collapse: collapse;
	margin: 50px auto;
}

table, tr, th, td {
	border: 1px solid blue;
}

th, td {
	height: 30px;
	text-align: center;
}
</style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			if(${sessionScope.post.boardNo} == 1) {
				$('#reviewLiSt').show();
			} else {
				$('#reviewLiSt').hide();
			}	
		}
		
		function OnChange()	{ 
			var bordNoSelect = document.getElementById("bordNo").options[document.getElementById("bordNo").selectedIndex].value;
			if(bordNoSelect == 1) {
				$('#reviewLiSt').show();
			} else {
				$('#reviewLiSt').hide();
			}
		}
		var loadFile = function(event) {
			var image = document.getElementById('output');
			image.src = URL.createObjectURL(event.target.files[1]);
		};
		
		$(function() {
		    // Multiple images preview in browser
		    var imagesPreview = function(input, placeToInsertImagePreview) {
		        if (input.files) {
		            var filesAmount = input.files.length;
		            for (i = 0; i < filesAmount; i++) {
		                var reader = new FileReader();
		                reader.onload = function(event) {
		                    $($.parseHTML('<img>')).attr('src', event.target.result).attr('width', 100).attr('height', 100).appendTo(placeToInsertImagePreview);
		                }
		                reader.readAsDataURL(input.files[i]);
		            }
		        }
		    };
		    $('#gallery-photo-add').on('change', function() {
		        imagesPreview(this, 'div.photogallery');
		    });
		});
		
		$(function() {
		    // Multiple images preview in browser
		    var videoPreview = function(input, placeToInsertImagePreview) {
		        if (input.files) {
		            var filesAmount = input.files.length;
		            for (i = 0; i < filesAmount; i++) {
		                var reader = new FileReader();
		                reader.onload = function(event) {
		                    $($.parseHTML('<video>')).attr('src', event.target.result).attr('width', 100).attr('height', 100).attr('preload','metadata').appendTo(placeToInsertImagePreview);
		                }
		                reader.readAsDataURL(input.files[i]);
		            }
		        }
		    };
		    $('#gallery-video-add').on('change', function() {
		    	videoPreview(this, 'div.videogallery');
		    });
		});
	</script>

<body>
	<article>
		<div class="container" role="main">
			<h2>게시글 수정 폼</h2>
			<form action="${pageContext.request.contextPath}/modifyPost" method="POST" enctype="multipart/form-data">
				<div class="mb-3">
					<label for="subject">제목</label> 
					<input type="text" 	class="form-control" name="subject" id="subject" value="${sessionScope.post.subject}">
				</div>
				<div class="mb-3">
					<label for="bordNo">게시판 선택</label> 
					<select name="bordNo" id="bordNo" onchange="OnChange();">
						<c:forEach var="board" items="${requestScope.boardList}">
							<c:choose>
								<c:when test="${board.key==sessionScope.post.boardNo}">
									<option value="${board.key}" selected="${sessionScope.post.boardNo}">${board.value}</option>
								</c:when>
								<c:otherwise>
									<option value="${board.key}">${board.value}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
				 <!-- <div class="mb-3">
					<label for="photo">사진</label> 					
					<input type="file" accept="image/*" class="form-control" name="photo" id="photo" onchange="loadFile(event)" multiple style="font-size: 13px;">
					<p><img id="output" width="200" /></p>
				</div> -->
				
				<div class="mb-3">
					<label for="photo">사진</label> 		
					<input type="file" accept="image/*" class="form-control" name="gallery-photo-add" id="gallery-photo-add" multiple style="font-size: 13px;">
					<div class="photogallery"></div>
				</div>
				
				<!-- <div class="mb-3">
					<label for="video">동영상</label>
					<input type="file" class="form-control" name="video" id="video" multiple style="font-size: 13px;">
				</div> -->
				
				<div class="mb-3">
					<label for="video">동영상</label>
					<input type="file" accept="video/*" class="form-control" name="gallery-video-add" id="gallery-video-add" multiple style="font-size: 13px;">
					<div class="videogallery"></div>
				</div>
				<div class="mb-3">
					<label for="attach">첨부 파일</label> <input type="file" class="form-control" name="attach" id="attach" multiple style="font-size: 13px;">
				</div>
				
				<div id="reviewLiSt" style="display:none;">
					
				<div class="mb-3">
					<label for="room">숙소 선택</label> 
					<select name="room">
						<c:forEach var="i" begin="1" end="10">
							<c:choose>
								<c:when test="${i==sessionScope.post.review.roomNo}">
									<option value="${i}" selected="${sessionScope.post.review.roomNo}">${i}</option>
								</c:when>
								<c:otherwise>
									<option value="${i}">${i}</option>
								</c:otherwise>								
							</c:choose>
						</c:forEach>
						
					</select>
				</div>
				<div class="mb-3">
					별점등록<br>
					<label for="rate_loc">위치</label> 
					<select name="rate_loc">
						<c:forEach var="i" begin="1" end="5">
							<c:choose>
								<c:when test="${i==sessionScope.post.review.rate_loc}">
									<option value="${i}" selected="${sessionScope.post.review.rate_loc}">${i}</option>
								</c:when>
								<c:otherwise>
									<option value="${i}">${i}</option>
								</c:otherwise>								
							</c:choose>
						</c:forEach>
					</select>
					<label for="rate_clean">위생</label> 
					<select name="rate_clean">
						<c:forEach var="i" begin="1" end="5">
							<c:choose>
								<c:when test="${i==sessionScope.post.review.rate_clean}">
									<option value="${i}" selected="${sessionScope.post.review.rate_clean}">${i}</option>
								</c:when>
								<c:otherwise>
									<option value="${i}">${i}</option>
								</c:otherwise>								
							</c:choose>
						</c:forEach>
					</select>
					<label for="rate_comu">의사소통</label> 
					<select name="rate_comu">
						<c:forEach var="i" begin="1" end="5">
							<c:choose>
								<c:when test="${i==sessionScope.post.review.rate_comu}">
									<option value="${i}" selected="${sessionScope.post.review.rate_comu}">${i}</option>
								</c:when>
								<c:otherwise>
									<option value="${i}">${i}</option>
								</c:otherwise>								
							</c:choose>
						</c:forEach>
					</select>
					<label for="rate_chip">가성비</label> 
					<select name="rate_chip">
						<c:forEach var="i" begin="1" end="5">
							<c:choose>
								<c:when test="${i==sessionScope.post.review.rate_chip}">
									<option value="${i}" selected="${sessionScope.post.review.rate_chip}">${i}</option>
								</c:when>
								<c:otherwise>
									<option value="${i}">${i}</option>
								</c:otherwise>								
							</c:choose>
						</c:forEach>
					</select>
				</div>
				<div class="mb-3">					
					<label for="visitDate">방문 날짜를 선택하세요</label> 
					<input type="date" name="visitDate" id = "visitDate" value="${sessionScope.post.review.visitDate}" />
				</div>				
				<div class="mb-3">					
					<label for="re_push_pl">주변 맛집과 관광지를 추천해주세요</label> 
					<input type="text" class="form-control" name="re_push_pl" id="re_push_pl" value="${sessionScope.post.review.recommendPlace}">
				</div>
				<div class="mb-3">					
					<label for="re_push_npl">이런분들께는 비추에요</label> 
					<input type="text" class="form-control" name="re_push_npl" id="re_push_npl" value="${sessionScope.post.review.notRecommendPerson}">
				</div>
				</div>
				<div class="mb-3">
					<label for="content">내용</label>
					<textarea class="form-control" rows="5" name="content" id="content">${sessionScope.post.content}</textarea>
				</div>
				<div class="mb-3">
					<label for="tag">태그</label> <input type="text" class="form-control" name="tag" id="tag" value="${sessionScope.post.tag}">
				</div>
				<div>
	                 <button type="submit" class="btn btn-sm btn-primary" id="modifyBtn"  style="font-size: 12px;">수정</button>
	            </div>       
			</form>

			<c:url var="detailUrl" value="/detailPost.do">
					<c:param name="no" value="${sessionScope.post.no}" />
					<c:param name="writerName" value="${sessionScope.post.writerName}" />
			</c:url>
			<a href="${detailUrl}">상세보기로 돌아가기</a>
		</div>
	</article>


	<c:if test="${not empty sessionScope.post.attachList}">
		<table id="tbl">
			<tr>
				<th>파일명</th>
				<th>파일크기</th>
				<th>비고</th>
			<tr>
				<c:forEach var="file" items="${sessionScope.post.attachList}">
					<c:url var="deleteAttach" value="/cancelPostAttach.do">
						<c:param name="attachNo" value="${file.no}" />
					</c:url>
					<tr>
						<td>${file.originalFileName}</td>
						<td>${file.fileSize}bytes</td>
						<td><a href="${deleteAttach}">삭제</a></td>
					<tr>
				</c:forEach>
		</table>
	</c:if>

</body>
</html>


