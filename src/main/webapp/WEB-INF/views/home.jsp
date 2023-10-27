<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" type="text/css"
	href="/web/resources/style/style.css"/>
</head>
<body>
	<h1>Hello Spring</h1>

	<P>* Server Time is ${serverTime}.</P>
	<c:if test="${not empty sessionScope.loginID}">
		${sessionScope.loginName} 님 안녕하세요. <br>
	</c:if>
	<c:if test="${not empty requestScope.message }">
		message : ${requestScope.message} <br>
	</c:if>
	<hr>
	<img alt="mainImage" src="/web/resources/images/main.jpg" width="400" height="300">
	<hr>
	<!-- Before Login -->
	<c:if test="${empty sessionScope.loginID}">
		<a class="button" href="member/memberLogin">로그인</a>&nbsp;&nbsp;
		<a class="button" href="member/memberJoin">회원가입</a>
	</c:if>

	<!-- After Login -->
	<c:if test="${not empty sessionScope.loginID}">
		<a class="button" href="member/logout">로그아웃</a>&nbsp;&nbsp;
		<a class="button" href="member/detail?id=${sessionScope.loginID}">회원정보</a>&nbsp;&nbsp;
		<a class="button" href="member/memberUpdate?id=${sessionScope.loginID}">정보수정</a>&nbsp;&nbsp;
		<a class="button" href="member/delete?id=${sessionScope.loginID}">회원탈퇴</a>
	</c:if>
	<br><br>
	<a class="button" href="member/memberList">회원목록</a>&nbsp;&nbsp;
	<a class="button" href="board/boardList">글목록</a>&nbsp;&nbsp;
	<a class="button" href="jo/joList">조목록</a>
</body>
</html>
