<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar mr-auto navbar-expand-lg navbar-light bg-light ">
	<a class="navbar-brand" href="${cp}/">Board</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<c:choose>
			<c:when test="${not empty member && member.mId != null}">
				<ul class="navbar navbar-nav">
					<li class="nav-item"><a class="nav-link"
						href="${cp}/member/modifyForm">MODIFY</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${cp}/member/removeForm">REMOVE</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${cp}/member/logout">LOGOUT</a></li>
				</ul>
				<ul class="navbar navbar-nav mr-auto">
					<li class="nav-item"><span
						class="navbar-text nav navbar-nav navbar-right"> 안녕하세요
							${member.getmName()} 님 </span></li>
				</ul>
			</c:when>
			<c:otherwise>
				<ul class="navbar navbar-nav">
					<li class="nav-item"><a class="nav-link"
						href="${cp}/member/loginForm">LOGIN</a></li>
				</ul>
			</c:otherwise>
		</c:choose>
	</div>
</nav>
