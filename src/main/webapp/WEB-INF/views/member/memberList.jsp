<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member List</title>
<link rel="stylesheet" type="text/css"
	href="/web/resources/style/style.css"/>
</head>
<body>
<h2>회원목록</h2>

<hr>
<c:if test="${not empty requestScope.message}">
	${requestScope.message}
</c:if>

<table border="1" style="width:90%">
	<tr bgcolor="Lime">
		<th>ID</th>
		<th>Password</th>
		<th>Name</th>
		<th>Age</th>
		<th>Jno</th>
		<th>Info</th>
		<th>Point</th>
		<th>Birthday</th>
		<th>Rid</th>
		<th>Delete</th>
	</tr>
	
	<c:if test="${not empty requestScope.banana}">
		<c:forEach var="s" items="${requestScope.banana}">
			<tr>
				<td><a href="detail?id=${s.id }">${s.id}</a></td>
				<td>${s.password}</td>
				<td>${s.name}</td>
				<td>${s.age}</td>
				<td>${s.jno}</td>
				<td>${s.info}</td>
				<td>${s.point}</td>
				<td>${s.birthday}</td>
				<td>${s.rid}</td>
				<td align="center">
					<c:if test="${sessionScope.loginID == 'admin' }">
						<a href="delete?id=${s.id}">삭제</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</c:if>
	
	<c:if test="${empty requestScope.banana}">
		<tr>
			<td colspan="7">
				출력할 Data가 1건도 없습니다
			</td>
		</tr>
	</c:if>
</table>

<hr>
<a class="button" href="/web/">Home</a>
</body>
</html>