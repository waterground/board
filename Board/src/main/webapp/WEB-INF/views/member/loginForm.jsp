<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α���</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css" />
<link rel="stylesheet" href="//unpkg.com/bootstrap@4/dist/css/bootstrap.min.css">
<script src='//unpkg.com/jquery@3/dist/jquery.min.js'></script>
<script src='//unpkg.com/popper.js@1/dist/umd/popper.min.js'></script>
<script src='//unpkg.com/bootstrap@4/dist/js/bootstrap.min.js'></script>
</head>
<body>
	<jsp:include page="../header.jsp" flush="false" />
	<div class="container">
		<form:form action="${cp}/member/login" method="post"
			commandName="member">
			<div class="form-group">
				<form:label path="mId"> ���̵� </form:label>
				<form:input path="mId" placeholder="���̵�" class="form-control"/>
			</div>
			<div class="form-group">
				<form:label path="mPw"> ��й�ȣ </form:label>
				<form:password path="mPw" placeholder="��й�ȣ " class="form-control"/>
			</div>
			<c:if test="${canLogin == false}">
				<small class="form-text" style="color:red;">
					�߸��� ȸ������ �Դϴ�
				</small>
			</c:if>
			<small class="form-text text-muted">
				���� ȸ���� �ƴѰ���?<a href="${cp}/member/joinForm">ȸ������</a>
			</small>
			<br/>
			<button id="loginOk">�α���</button>
		</form:form>
	</div>
</body>
</html>