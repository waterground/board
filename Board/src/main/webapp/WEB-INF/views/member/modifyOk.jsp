<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원 정보 수정 완료</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css"/>
<link rel="stylesheet" href="//unpkg.com/bootstrap@4/dist/css/bootstrap.min.css">
<script src='//unpkg.com/jquery@3/dist/jquery.min.js'></script>
<script src='//unpkg.com/popper.js@1/dist/umd/popper.min.js'></script>
<script src='//unpkg.com/bootstrap@4/dist/js/bootstrap.min.js'></script>
</head>
<body>
	<jsp:include page="../header.jsp" flush="false"/>
	<div class="container text-center" style="margin:20px auto; width: 400px;">
		<p style="margin: 0 auto; text-align:center;">정보 수정이 완료되었습니다</p>
		<br/>
		<a href="${cp}/" class="btn btn-primary"
			style="text-align:center;">메인으로 가기</a>
	</div>
</body>
</html>