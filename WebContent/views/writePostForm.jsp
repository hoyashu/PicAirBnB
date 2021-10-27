<%-- writePostFrom.jsp --%>
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
<title>게시글 작성</title>
<style>

h2 {
	text-align: center;
}
</style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script type="text/javascript">
	

		function OnChange()	{ 
			var bordNoSelect = document.getElementById("bordNo").options[document.getElementById("bordNo").selectedIndex].value;
			if(bordNoSelect == 1) {
				$('#reviewLiSt').show();
			} else {
				$('#reviewLiSt').hide();
			}
		};
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
			<h2>게시글 작성</h2>
			<form action="${pageContext.request.contextPath}/uploadFile" method="POST" enctype="multipart/form-data">
				<div class="mb-3">					
					<label for="subject">제목</label> 
					<input type="text" class="form-control" name="subject" id="subject"	placeholder="제목을 입력하세요">
				</div>
				<div class="mb-3">
					<label for="bordNo">게시판 선택</label> 
					<select name="bordNo" id="bordNo" onchange="OnChange();">
						<c:forEach var="board" items ="${requestScope.boardList}">
							<c:choose>
								<c:when test="${board.key==requestScope.defaultListNo}">
									<option value="${board.key}" selected="${requestScope.defaultListNo}">${board.value}</option>
								</c:when>
								<c:otherwise>
									<option value="${board.key}"> ${board.value}</option>
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
					<label for="attach">첨부 파일</label> 
					<input type="file" class="form-control" name="attach" id="attach" multiple style="font-size: 13px;">
				</div>
				
				<div id="reviewLiSt" style="display:none;">
					
				<div class="mb-3">
					<label for="room">숙소 선택</label> 
					<select name="room">
						<c:forEach var="room" items ="${requestScope.roomList}">
							<option value="${room.no}">${room.roomAddress}</option>
						</c:forEach>
					</select>					
				</div>
				<%-- <div style="overflow:auto; width:500px; height:250px;">
					<c:forEach var="room" items ="${requestScope.roomList}">
						<input type="radio" name="room" value="${room.no}">
						<a href="https://a0.muscache.com/im/pictures/miso/Hosting-47713323/original/63e7ee4b-a5f0-4ac6-80ad-c91c20cb5a3c.jpeg?im_w=1200" target = "_blank">
						<img src = "https://a0.muscache.com/im/pictures/miso/Hosting-47713323/original/63e7ee4b-a5f0-4ac6-80ad-c91c20cb5a3c.jpeg?im_w=1200"  width = 100  width = 100 >
						</a>
						${room.roomAddress}<br><br>
					</c:forEach>
				</div> --%>
								
				<div class="mb-3">
					별점등록<br>
					<label for="rate_loc">위치</label> 
					<select name="rate_loc">
						<c:forEach var="i" begin="1" end="5">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>
					<label for="rate_clean">위생</label> 
					<select name="rate_clean">
						<c:forEach var="i" begin="1" end="5">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>
					<label for="rate_comu">의사소통</label> 
					<select name="rate_comu">
						<c:forEach var="i" begin="1" end="5">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>
					<label for="rate_chip">가성비</label> 
					<select name="rate_chip">
						<c:forEach var="i" begin="1" end="5">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>
				</div>
				<div class="mb-3">					
					<label for="visitDate">방문 날짜를 선택하세요</label> 
					<input type="date" name="visitDate" id = "visitDate" value="2021-10-25" />
				</div>				
				<div class="mb-3">					
					<label for="re_push_pl">주변 맛집과 관광지를 추천해주세요</label> 
					<input type="text" class="form-control" name="re_push_pl" id="re_push_pl">
				</div>
				<div class="mb-3">					
					<label for="re_push_npl">이런분들께는 비추에요</label> 
					<input type="text" class="form-control" name="re_push_npl" id="re_push_npl">
				</div>
				</div>
				
				<div class="mb-3">
					<label for="content">내용</label> 
					<textarea class="form-control" rows="5" name="content" id="content" placeholder="내용을 입력해 주세요"></textarea>
				</div>
				<div class="mb-3">
					<label for="tag">태그</label> 
					<input type="text" class="form-control" name="tag" id="tag" placeholder="#태그입력">
				</div>
				<div>
					<button type="submit" class="btn btn-sm btn-primary" id="saveBtn">글등록</button>
				</div>
			</form>
		</div>
	</article>
</body>
</html>


