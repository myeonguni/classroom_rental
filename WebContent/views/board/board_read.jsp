<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
						<li><a href="/classroom_rental/">홈</a></li>
						<%
							/* 세션이 존재할 경우 */
							if (session.getAttribute("isAdmin") != null) { 
								/* 관리자일 경우 */
								if (session.getAttribute("isAdmin").equals("1")) {
						%>
						<li><a href="/classroom_rental/adminRentalMng.do">강의실 대여 관리</a></li>
						<li><a href="/classroom_rental/classroomRentalList.do">강의실 대여</a></li>
						<li><a href="/classroom_rental/views/admin/notice_mng.jsp">공지사항 등록</a></li>
						<li class="active"><a href="/classroom_rental/boardList.do">게시판</a></li>
						<li><a href="/classroom_rental/userRentalMng.do">나의 신청현황</a></li>
						<li><a href="/classroom_rental/logout.do">로그아웃</a></li>
						<li style="float: right;"><a href="#"><%=session.getAttribute("name")%>님 환영합니다.</a></li>
						<%
								/* 일반 사용자일 경우 */
								} else {
						%>
						<li><a href="/classroom_rental/classroomRentalList.do">강의실 대여</a></li>
						<li class="active"><a href="/classroom_rental/boardList.do">게시판</a></li>
						<li><a href="/classroom_rental/userRentalMng.do">나의 신청현황</a></li>
						<li><a href="/classroom_rental/logout.do">로그아웃</a></li>
						<li style="float: right;"><a href="#"><%=session.getAttribute("name")%>님 환영합니다.</a></li>
						<%
							}
							/* 세션이 없을 경우 */
							} else {
						%>
						<li class="active"><a href="/classroom_rental/boardList.do">게시판</a></li>
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
				<hr/><br> 
				<h4>게시글 읽기</h4>
				<hr/>
				<div id="boardPost" style="">
				<!-- 강의실 예약 신청서 양식 -->
					<form id="boardPostForm" action="/classroom_rental/boardPostForm.do" method="POST">
						<!-- 게시글 내용 -->
						<div class="form-group row">
							<label for="inputTitle" class="col-sm-2 col-form-label">제목</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputTitle" name="board_title" value="<%=request.getParameter("board_title") %>">
							</div>
						</div>
						<div class="form-group row">
							<label for="inputName" class="col-sm-2 col-form-label">작성자</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputName" disabled value="<%=request.getParameter("user_name") %>">
							</div>
						</div>
						<div class="form-group row">
							<label for="inputBody" class="col-sm-2 col-form-label">내용</label>
							<div class="col-sm-10">
								<textarea class="form-control" id="inputBody" name="board_body" rows="8" style="resize: none; wrap:hard;"><%=request.getParameter("board_body") %></textarea>
							</div>
						</div>
						<hr/>
					</form>
					
					<!-- 글 관리 버튼 -->
					<div class="text-right">
						<%
							/* 회원일 경우 */
							if (session.getAttribute("id") != null) {
								/* 글 작성자이거나 관리자일 경우 */
								if (session.getAttribute("id").equals(request.getParameter("user_id")) || session.getAttribute("isAdmin").equals("1")) { 
						%>
								<button class="btn btn-warning" type="button" onClick="editPost()" disabled>수정하기 »</button>&nbsp;&nbsp;
								<button class="btn btn-warning" type="button" onClick="deletePost()" >삭제하기 »</button>&nbsp;&nbsp;
								<a href="/classroom_rental/boardList.do"><button class="btn btn-warning">목록으로 »</button></a>
						<%
								/* 작성자가 아닐 경우 */
								} else {
						%>
								<a href="/classroom_rental/boardList.do"><button class="btn btn-warning">목록으로 »</button></a>
						<%
								}
							/* 비회원일 경우 */
							} else {
						%>
								<a href="/classroom_rental/boardList.do"><button class="btn btn-warning">목록으로 »</button></a>
						<%
							}
						%>
					</div>
					
					<!-- 글 삭제하기 Modal-->
					<div id="deletePostModal" class="modal fade" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">게시글 삭제하기</h4>
								</div>
								<div class="modal-body text-center" id="rentalDeleteModalBody">
									<div class="text-center">
										<h4>정말 삭제하시겠습니까?</h3>
										<form action="/classroom_rental/boardPostDelete.do" method="POST" id="postDeleteForm">
											<input type="hidden" name="postIndex" id="postIndex" value="">
										</form>
									</div>
								</div>
								<div class="modal-footer">
									<button type="submit" form="postDeleteForm" class="btn btn-warning">YES</button>
									<button type="button" class="btn btn-warning" data-dismiss="modal">NO</button>
									<div class="text-center">
									</div>
								</div>
							</div>
						</div>
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
		$(document).ready(function() {
			if ("${result}"=="true") {
				alert("게시글 삭제에 성공하였습니다.");
				location.href = '/classroom_rental/boardList.do';
			} else if ("${result}"=="false") {
				alert("유효하지 않은 요청입니다. 확인 후 다시 시도해주시기 바랍니다.");
				location.href = '/classroom_rental/boardList.do';
			} else { } 
		});
		
		function deletePost() {
			document.getElementById('postIndex').value = <%=request.getParameter("board_index")%>;
			$('#deletePostModal').modal('toggle');
		}
	</script>
</body>
</html>