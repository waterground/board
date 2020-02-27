<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원 탈퇴</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css"/>
<link rel="stylesheet" href="//unpkg.com/bootstrap@4/dist/css/bootstrap.min.css">
<script src='//unpkg.com/jquery@3/dist/jquery.min.js'></script>
<script src='//unpkg.com/popper.js@1/dist/umd/popper.min.js'></script>
<script src='//unpkg.com/bootstrap@4/dist/js/bootstrap.min.js'></script>
</head>
<body>
	<jsp:include page="../header.jsp" flush="false"/>
	<div class="container">
		<form:form action="${cp}/member/remove" method="post" commandName="member">
			<form:hidden path="mId" value="${member.mId}"/>
			<div class="form-group">
				<form:label path="mPw"> 비밀번호 </form:label>
				<form:password path="mPw" placeholder="비밀번호 " class="form-control"/>
			</div>
			<c:if test="${canRemove == false}">
				<small class="form-text" style="color:red;">
					잘못된 비밀번호 입니다
				</small>
			</c:if>
			<br/>
			<button>회원 탈퇴</button>
		</form:form>
	</div>
</body>
</html>