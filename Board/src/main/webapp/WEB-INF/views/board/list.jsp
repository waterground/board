<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록 | 게시판</title>
<link rel="stylesheet" href="${cp}/resources/css/style.css" />
<link rel="stylesheet"
	href="//unpkg.com/bootstrap@4/dist/css/bootstrap.min.css">
<script src='//unpkg.com/jquery@3/dist/jquery.min.js'></script>
<script src='//unpkg.com/popper.js@1/dist/umd/popper.min.js'></script>
<script src='//unpkg.com/bootstrap@4/dist/js/bootstrap.min.js'></script>
</head>
<body>
	<jsp:include page="../header.jsp" flush="false" />
	<div class="container">
		<h4 class="title">게시판</h4>
		<table class="table table-striped">
			<thead>
				<tr>
					<th style="width: 8%">번호</th>
					<th style="width: 15%">글쓴이</th>
					<th style="width: 40%">제목</th>
					<th style="width: 20%">날짜</th>
					<th style="width: 8%">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="board">
					<tr>
						<td>${board.bId}</td>
						<td>${board.bName}</td>
						<td><a href="${cp}/board/content.do?bId=${board.bId}">${board.bTitle}</a>
						</td>
						<td><fmt:formatDate value="${board.bDate}" pattern="yyyy.MM.dd hh:mm:ss" /></td>
						<td>${board.bHit}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="text-center" style="margin:0 auto; width: 400px;">
			<ul class="pagination">
				<c:if test="${pagination.prev eq true}">
					<li class="page-item"><a class="page-link" href="#"
						onClick="prevEvent('${pagination.curPage}', '${pagination.curRange}', '${pagination.rangeSize}')">&lt;</a></li>
				</c:if>

				<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="idx">
					<li
						class="page-item <c:out value="${pagination.curPage == idx ? 'active' : ''}"/>"><a
						class="page-link" href="#"
						onClick="pageEvent('${idx}', '${pagination.curRange}', '${pagination.rangeSize}')">
							${idx} </a></li>
				</c:forEach>
				<c:if test="${pagination.next eq true}">
					<li class="page-item"><a class="page-link" href="#"
						onClick="nextEvent('${pagination.curRange}', '${pagination.rangeSize}')">&gt;</a></li>
				</c:if>
			</ul>
		</div>
		<c:if test="${!empty member}">
			<a class="btn btn-primary float-right" href="${cp}/board/writeForm">글쓰기</a>
		</c:if>
	</div>
	<script>
		// 이전 버튼 이벤트
		function prevEvent(page, range, rangeSize) {
			var page = ((range - 1) * rangeSize);
			var range = range - 1;

			var url = "${cp}/board/list";
			url += "?page=" + page + "&range=" + range;

			location.href = url;
		}

		// 페이지 번호 버튼 이벤트
		function pageEvent(page, range, rangeSize) {
			var url = "${cp}/board/list";

			url += "?page=" + page + "&range=" + range;

			location.href = url;
		}

		// 다음 버튼 이벤트
		function nextEvent(range, rangeSize) {
			var page = parseInt(range * rangeSize) + 1;
			var range = parseInt(range) + 1;

			var url = "${cp}/board/list";
			url += "?page=" + page + "&range=" + range;

			location.href = url;
		}
	</script>
</body>
</html>