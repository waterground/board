<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>글 수정 | ${board.getbTitle()}</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css"/>
<link rel="stylesheet" href="//unpkg.com/bootstrap@4/dist/css/bootstrap.min.css">
<script src='//unpkg.com/jquery@3/dist/jquery.min.js'></script>
<script src='//unpkg.com/popper.js@1/dist/umd/popper.min.js'></script>
<script src='//unpkg.com/bootstrap@4/dist/js/bootstrap.min.js'></script>
</head>
<body>
	<jsp:include page="../header.jsp" flush="false"/>
	<div class="container">
		<h4 class="title">글 수정</h4>
		<form:form action="${cp}/board/modify" method="post" commandName="board">
			<form:hidden path="bId" value="${board.bId}"/>
			<div class="form-group">
				<form:label path="bTitle"> 제목 </form:label> 
				<form:input path="bTitle" class="form-control" value="${board.bTitle}"/>
			</div>
			<div class="form-group">
				<form:label path="bContent"> 내용 </form:label>
				<form:textarea path="bContent" class="form-control" rows="10" value="${board.bContent}"></form:textarea>
			</div>
			<button class="btn btn-primary float-right">완료</button>
			<a href="${cp}/board/list.do" class="btn btn-primary">목록</a>
		</form:form>
	</div>
</body>
</html>