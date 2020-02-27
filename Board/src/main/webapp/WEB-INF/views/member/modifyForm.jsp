<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원 정보 수정</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css"/>
<link rel="stylesheet" href="//unpkg.com/bootstrap@4/dist/css/bootstrap.min.css">
<script src='//unpkg.com/jquery@3/dist/jquery.min.js'></script>
<script src='//unpkg.com/popper.js@1/dist/umd/popper.min.js'></script>
<script src='//unpkg.com/bootstrap@4/dist/js/bootstrap.min.js'></script>
</head>
<body>
	<jsp:include page="../header.jsp" flush="false"/>
	<div class="container">
		<h4>회원 정보 수정</h4>
		<form:form action="${cp}/member/modify" method="post" commandName="member">
			<div class="form-group">
				<form:label path="mId"> 아이디 </form:label> 
				<form:input path="mId" value="${member.mId}" readonly="readonly" class="form-control" /> 
			</div>
			<div class="form-group">
				<form:label path="mPw"> 비밀번호 </form:label> 
				<form:password path="mPw" value="${member.mPw}" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="mName"> 이름 </form:label>
				<form:input path="mName" value="${member.mName}" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="mEmail"> 이메일 </form:label>
				<form:input path="mEmail" value="${memberm.mEmail}" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="mAddress"> 주소 </form:label>
				<form:input path="mAddress" value="${member.mAddress}" class="form-control" />
			</div>
			<button class="btn btn-primary float-right">정보 수정</button>
		</form:form>
	</div>
</body>
</html>