<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!doctype html>
<head>
	<meta charset="UTF-8">
	<title>강의실 예약 웹 어플리케이션</title>
	<!-- jquery lib load -->
	<script type="text/javascript" src="/classroom_rental/resources/lib/jquery/jquery-3.1.1.min.js"></script>
	<!-- bootstrap lib load -->
	<link rel="stylesheet" href="/classroom_rental/resources/lib/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/classroom_rental/resources/lib/bootstrap/css/bootstrap-theme.min.css">
	<script type="text/javascript" src="/classroom_rental/resources/lib/bootstrap/js/bootstrap.min.js"></script>
	<!-- main css load -->
	<link rel="stylesheet" href="/classroom_rental/resources/lib/default/main-style.css">
</head>
<body>
	<div id="wrap">
		<div class="container">
			<h1 class="page-header">
				<a href="/classroom_rental/"><img src="/classroom_rental/resources/images/logo.png" width="65px"></a>
			</h1>
			<p class="lead">강의실 예약 시스템_김원정(201403038).WebApp</p>
			<br>
			<div>
				<div class="menu">
					<ul class="nav nav-pills">
						<li class="active"><a href="/classroom_rental/">홈</a></li>
						<%
							/* 세션이 존재할 경우 */
							if (session.getAttribute("isAdmin") != null) { 
								/* 관리자일 경우 */
								if (session.getAttribute("isAdmin").equals("1")) {
						%>
						<li><a href="/classroom_rental/adminRentalMng.do">강의실 대여 관리</a></li>
						<li><a href="/classroom_rental/classroomRentalList.do">강의실 대여</a></li>
						<li><a href="/classroom_rental/noticeMng.do">공지사항 등록</a></li>
						<li><a href="/classroom_rental/views/board/board_list.jsp">게시판</a></li>
						<li><a href="/classroom_rental/userRentalMng.do">나의 신청현황</a></li>
						<li><a href="/classroom_rental/logout.do">로그아웃</a></li>
						<li style="float: right;"><a href="#"><%=session.getAttribute("name")%>님 환영합니다.</a></li>
						<%
								/* 일반 사용자일 경우 */
								} else {
						%>
						<li><a href="/classroom_rental/classroomRentalList.do">강의실 대여</a></li>
						<li><a href="/classroom_rental/views/board/board_list.jsp">게시판</a></li>
						<li><a href="/classroom_rental/userRentalMng.do">나의 신청현황</a></li>
						<li><a href="/classroom_rental/logout.do">로그아웃</a></li>
						<li style="float: right;"><a href="#"><%=session.getAttribute("name")%>님 환영합니다.</a></li>
						<%
							}
							/* 세션이 없을 경우 */
							} else {
						%>
						<li><a href="/classroom_rental/views/board/board_list.jsp">게시판</a></li>
						<li><a href="/classroom_rental/views/main/login.jsp">로그인</a></li>
						<li><a href="/classroom_rental/views/main/join.jsp">회원가입</a></li>
						<%
							}
						%>
					</ul>
				</div>
			</div>
			<div class="content">
				<!-- 구분선 삽입 -->
				<hr/>
				<!-- //구분선 삽입 -->
				<div class="row">
					<div class="col-md-4">
						<h2>강의실 대여</h2>
						<p class="text-justify">강의실을 대여해줍니다 것도 무료로 학생이면 가능합니다 하하하 간단한 신청서를 작성해야되요 강의실을 대여해줍니다 것도 무료로 학생이면 가능합니다 하하하 간단한 신청서를 작성해야되요 강의실을 대여해줍니다 것도 무료로 학생이면 가능합니다 하하하 간단한 신청요</p>
						<p><a class="btn" style="background-color:#fcf8e3;" href="/classroom_rental/classroomRentalList.do" role="button">강의실 대여하러 가기 »</a></p>
					</div>
					<div class="col-md-4">
						<h2>게시판</h2>
						<p class="text-justify">강의실을 대여해줍니다 것도 무료로 학생이면 가능합니다 하하하 간단한 신청서를 작성해야되요 강의실을 대여해줍니다 것도 무료로 학생이면 가능합니다 하하하 간단한 신청서를 작성해야되요 강의실을 대여해줍니다 것도 무료로 학생이면 가능합니다 하하하 간단한 신청요</p>
						<p><a class="btn" style="background-color:#fcf8e3;" href="/classroom_rental/views/board/board_list.jsp" role="button">게시판 가기 »</a></p>
					</div>
					<div class="col-md-4">
						<h2>사이트 문의</h2>
						<p class="text-justify">강의실을 대여해줍니다 것도 무료로 학생이면 가능합니다 하하하 간단한 신청서를 작성해야되요 강의실을 대여해줍니다 것도 무료로 학생이면 가능합니다 하하하 간단한 신청서를 작성해야되요 강의실을 대여해줍니다 것도 무료로 학생이면 가능합니다 하하하 간단한 신청요</p>
						<p><a class="btn" style="background-color:#fcf8e3;" href="#" role="button">문의하러 가기 »</a></p>
					</div>
				</div>
				<!-- 구분선 삽입 -->
				<hr/>
				<!-- //구분선 삽입 -->
				<br>
				<div id="main_area">
					<!-- Slider -->
					<div class="row">
						<div class="col-xs-12" id="slider">
							<div class="row">
								<div class="col-sm-8" id="carousel-bounding-box">
									<div class="carousel slide" id="myCarousel">
										<!-- Carousel items -->
										<div class="carousel-inner">
											<div class="active item" data-slide-number="0">
												<img src="/classroom_rental/resources/images/room1.jpg">
											</div>
											<div class="item" data-slide-number="1">
												<img src="/classroom_rental/resources/images/room2.jpg">
											</div>
											<div class="item" data-slide-number="2">
												<img src="/classroom_rental/resources/images/room3.jpg">
											</div>
											<div class="item" data-slide-number="4">
												<img src="/classroom_rental/resources/images/room4.jpg">
											</div>
											<div class="item" data-slide-number="5">
												<img src="/classroom_rental/resources/images/room5.jpg">
											</div>
											<div class="item" data-slide-number="6">
												<img src="/classroom_rental/resources/images/room6.jpg">
											</div>
										</div>
										<!-- Carousel nav -->
										<a class="left carousel-control" href="#myCarousel"
											role="button" data-slide="prev"> <span
											class="glyphicon glyphicon-chevron-left"></span>
										</a> <a class="right carousel-control" href="#myCarousel"
											role="button" data-slide="next"> <span
											class="glyphicon glyphicon-chevron-right"></span>
										</a>
									</div>
								</div>

								<div class="col-sm-4" id="carousel-text"></div>

								<div id="slide-content" style="display: none;">
									<div id="slide-content-0">
										<h2>로고스홀</h2>
										<p>» 좌석수 : 연결식 의자 659석</p>
										<p>» 이용가능 설비 : 특수조명, 빔프로젝터, 음향, 피아노</p>
										<p>» 현수막 길이 : 800 cm</p>
										<p>» 용도 : 공연, 세미나, 결혼식</p>
										<p class="sub-text">
											<a href="#">» 대여하러 가기</a>
										</p>
									</div>

									<div id="slide-content-1">
										<h2>Slider Two</h2>
										<p>Lorem Ipsum Dolor</p>
										<p class="sub-text">
											October 24 2014 - <a href="#">Read more</a>
										</p>
									</div>

									<div id="slide-content-2">
										<h2>Slider Three</h2>
										<p>Lorem Ipsum Dolor</p>
										<p class="sub-text">
											October 24 2014 - <a href="#">Read more</a>
										</p>
									</div>
									<div id="slide-content-3">
										<h2>Slider Three</h2>
										<p>Lorem Ipsum Dolor</p>
										<p class="sub-text">
											October 24 2014 - <a href="#">Read more</a>
										</p>
									</div>
									<div id="slide-content-4">
										<h2>Slider Three</h2>
										<p>Lorem Ipsum Dolor</p>
										<p class="sub-text">
											October 24 2014 - <a href="#">Read more</a>
										</p>
									</div>
									<div id="slide-content-5">
										<h2>Slider Three</h2>
										<p>Lorem Ipsum Dolor</p>
										<p class="sub-text">
											October 24 2014 - <a href="#">Read more</a>
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--/Slider-->

					<div class="row hidden-xs" id="slider-thumbs">
						<!-- Bottom switcher of slider -->
						<ul class="hide-bullets">
							<li class="col-sm-2"><a class="thumbnail"
								id="carousel-selector-0"><img
									src="/classroom_rental/resources/images/room1.jpg"></a></li>
							<li class="col-sm-2"><a class="thumbnail"
								id="carousel-selector-1"><img
									src="/classroom_rental/resources/images/room2.jpg"></a></li>
							<li class="col-sm-2"><a class="thumbnail"
								id="carousel-selector-2"><img
									src="/classroom_rental/resources/images/room3.jpg"></a></li>
							<li class="col-sm-2"><a class="thumbnail"
								id="carousel-selector-3"><img
									src="/classroom_rental/resources/images/room4.jpg"></a></li>
							<li class="col-sm-2"><a class="thumbnail"
								id="carousel-selector-4"><img
									src="/classroom_rental/resources/images/room5.jpg"></a></li>
							<li class="col-sm-2"><a class="thumbnail"
								id="carousel-selector-5"><img
									src="/classroom_rental/resources/images/room6.jpg"></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<div class="container">
			<p>
				<span>Copyright © 2016 | <a href="https://www.bible.ac.kr">bible.ac.kr</a></span>
				<span style="float: right;"><a href="https://www.bible.ac.kr">+사이트
						맵</a></span>
			</p>
		</div>
	</footer>


	<script>
		jQuery(document).ready(function($) {
			$('#myCarousel').carousel({
				interval : 5000
			});

			$('#carousel-text').html($('#slide-content-0').html());

			$('[id^=carousel-selector-]').click(function() {
				var id = this.id.substr(this.id.lastIndexOf("-") + 1);
				var id = parseInt(id);
				$('#myCarousel').carousel(id);
			});

			$('#myCarousel').on('slid.bs.carousel', function(e) {
				var id = $('.item.active').data('slide-number');
				$('#carousel-text').html($('#slide-content-' + id).html());
			});
		});
	</script>
</body>
</html>