<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원 가입 완료</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css"/>
<link rel="stylesheet"
	href="//unpkg.com/bootstrap@4/dist/css/bootstrap.min.css">
<script src='//unpkg.com/jquery@3/dist/jquery.min.js'></script>
<script src='//unpkg.com/popper.js@1/dist/umd/popper.min.js'></script>
<script src='//unpkg.com/bootstrap@4/dist/js/bootstrap.min.js'></script>
</head>
<body>
	<jsp:include page="../header.jsp" flush="false" />
	<div class="container text-center" style="margin:20px auto; width: 400px;">
		<p style="margin: 0 auto; text-align:center;">가입이 완료되었습니다</p>
		<br/>
		<div style="width: 80%; margin:0 auto">
			<a href="${cp}/member/loginForm" class="btn btn-primary text-center"
				style="text-align:center;">로그인 하기</a>
		</div>
	</div>
</body>
</html>