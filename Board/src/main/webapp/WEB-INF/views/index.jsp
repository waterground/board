<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Main</title>
<link rel="stylesheet" href="//resources/css/style.css"/>
<link rel="stylesheet" href="//unpkg.com/bootstrap@4/dist/css/bootstrap.min.css">
<script src='//unpkg.com/jquery@3/dist/jquery.min.js'></script>
<script src='//unpkg.com/popper.js@1/dist/umd/popper.min.js'></script>
<script src='//unpkg.com/bootstrap@4/dist/js/bootstrap.min.js'></script>
</head>
<body>
	<jsp:include page="header.jsp" flush="false"/>
	<h1>MAIN</h1>
	
	<c:if test="${empty member}">
		<a href="${cp}/member/joinForm">JOIN</a> &nbsp;&nbsp; 
		<a href="${cp}/member/loginForm">LOGIN</a> &nbsp;&nbsp; 
		<a href="${cp}/board/list.do">Board</a> &nbsp;&nbsp; 
	</c:if>
	
	<c:if test="${!empty member}">
		<a href="${cp}/board/list">Board</a> &nbsp;&nbsp;
		<a href="${cp}/member/modifyForm">MODIFY</a> &nbsp;&nbsp; 
		<a href="${cp}/member/logout">LOGOUT</a> &nbsp;&nbsp;
		<a href="${cp}/member/removeForm">REMOVE</a> &nbsp;&nbsp; 
	</c:if>
</body>
</html>