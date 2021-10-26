<%-- main.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.main-title {
    letter-spacing: -2px;
    font-size: 2.3rem;
}
.main-title+.lead {
    font-size: 1rem;
}
</style>
<div class="container col-xxl-8 px-4 py-5">
	<div class="row flex-lg-row-reverse align-items-center g-5 py-5">
		<div class="col-10 col-sm-8 col-lg-6">
			<img src="resource\img\main.png" class="d-block mx-lg-auto img-fluid"
				alt="메인 이미지" width="700" height="500" loading="lazy">
		</div>
		<div class="col-lg-6">
			<h1 class="display-5 fw-bold mb-3 main-title">에어비엔비 숙소 리뷰<br>답답하셨다면 바로 검색!</h1>
			<p class="lead">게스트의 시선에서 숙소 모습과 이야기를 들어보세요.</p>
			<div class="d-grid gap-2 d-md-flex justify-content-md-start">
				<button type="button" class="btn btn-primary btn-lg px-4 me-md-2">Primary</button>
				<button type="button" class="btn btn-outline-secondary btn-lg px-4">Default</button>
			</div>
		</div>
	</div>
</div>