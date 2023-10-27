<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Detail</title>
<link rel="stylesheet" type="text/css"
	href="/web/resources/style/style.css"/>
</head>
<body>
	<h2>회원정보</h2>
	<c:if test="${not empty requestScope.message}">
		${requestScope.message}
	</c:if>
	<hr>
	<table>
		<c:if test="${not empty requestScope.apple}">
			<tr height="40">
				<th bgcolor="pink">ID</th>
				<td>${requestScope.apple.id}</td>
			</tr>
			<tr height="40">
				<th bgcolor="pink">Password</th>
				<td>${requestScope.apple.password}</td>
			</tr>
			<tr height="40">
				<th bgcolor="pink">Name</th>
				<td>${requestScope.apple.name}</td>
			</tr>
			<tr height="40">
				<th bgcolor="pink">Age</th>
				<td>${requestScope.apple.age}</td>
			</tr>
			<tr height="40">
				<th bgcolor="pink">Jno</th>
				<td>${requestScope.apple.jno}</td>
			</tr>
			<tr height="40">
				<th bgcolor="pink">Info</th>
				<td>${requestScope.apple.info}</td>
			</tr>
			<tr height="40">
				<th bgcolor="pink">Point</th>
				<td>${requestScope.apple.point}</td>
			</tr>
			<tr height="40">
				<th bgcolor="pink">Birthday</th>
				<td>${requestScope.apple.birthday}</td>
			</tr>
			<tr height="40">
				<th bgcolor="pink">추천인</th>
				<td>${requestScope.apple.rid}</td>
			</tr>
		</c:if>

		<c:if test="${empty requestScope.apple}">
			<tr>
				<td colspan="2">출력할 Data가 없음</td>
			</tr>
		</c:if>

	</table>
	<hr>
	<a class="button" href="/web/">홈으로</a>
</body>
</html>