<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>글 보기 | ${board.bTitle}</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css" />
<link rel="stylesheet"
	href="//unpkg.com/bootstrap@4/dist/css/bootstrap.min.css">
<script src='//unpkg.com/jquery@3/dist/jquery.min.js'></script>
<script src='//unpkg.com/popper.js@1/dist/umd/popper.min.js'></script>
<script src='//unpkg.com/bootstrap@4/dist/js/bootstrap.min.js'></script>
</head>
<body>
	<jsp:include page="../header.jsp" flush="false" />
	<div class="container my-3 p-3 bg-white rounded shadow-sm">
		<h4 class="title">글 보기</h4>
		<table class="table table-bordered">
			<colgroup>
				<col width="150px">
				<col width="370px">
			</colgroup>
			<tr>
				<td>번호</td>
				<td>${board.bId}</td>
			</tr>
			<tr>
				<td>글쓴이</td>
				<td>${board.bName}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>${board.bTitle}</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${board.bHit}</td>
			</tr>
			<tr>
				<td>날짜</td>
				<td>${board.bDate}</td>
			</tr>
		</table>
		<div style="margin:15px auto;">
			${board.bContent}
		</div>
		<a href="${cp}/board/list.do" class="btn btn-primary">목록</a>
		<!-- 접속자와 작성자가 같은 경우 수정, 삭제 버튼 보임 -->
		<c:if test="${board.mId eq member.mId}"> 
			<a href="${cp}/board/remove.do?bId=${board.bId}"
				class="btn btn-primary float-right">삭제</a>
			<a href="${cp}/board/modifyForm.do?bId=${board.bId}"
				class="btn btn-primary float-right" style="margin-right: 2px;">수정</a>
		</c:if>
		<jsp:include page="reply.jsp" flush="false" />
	</div>
</body>
</html>