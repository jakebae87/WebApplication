<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jo List</title>
<link rel="stylesheet" type="text/css"
	href="/web/resources/style/style.css" />
</head>
</head>
<body>
	<h2>조목록</h2>

	<hr>
	<c:if test="${not empty requestScope.message}">
	${requestScope.message}
</c:if>
	<table border="1" style="width: 90%">
		<tr bgcolor="DeepPink">
			<th>Jno</th>
			<th>조이름</th>
			<th>조장 아이디</th>
			<th>조장이름</th>
			<th>프로젝트</th>
			<th>슬로건</th>
			<th>삭제</th>
		</tr>

		<c:if test="${not empty requestScope.list}">
			<c:forEach var="s" items="${requestScope.list}">
				<tr>
					<td>${s.jno}조</td>
					<c:if test="${not empty sessionScope.loginID}">
						<td><a href="joDetail?jno=${s.jno}">${s.jname}</a></td>
					</c:if>
					<c:if test="${empty sessionScope.loginID }">
						<td><a>${s.jname }</a></td>
					</c:if>
					<td>${s.id}</td>
					<td>${s.name}</td>
					<td>${s.project}</td>
					<td>${s.slogan}</td>
					<td align="center"><c:if test="${sessionScope.loginID == 'admin'}">
							<a href="delete?jno=${s.jno}">삭제</a>
						</c:if></td>
				</tr>
			</c:forEach>
		</c:if>

		<c:if test="${empty requestScope.list}">
			<tr>
				<td colspan="7">출력할 Data가 1건도 없습니다</td>
			</tr>
		</c:if>
	</table>

	<hr>
	<c:if test="${not empty sessionScope.loginID }">
		<a class="button" href="joCreate">조생성</a>&nbsp;&nbsp;
	</c:if>
	<a class="button" href="/web/">홈으로</a>
</body>
</html>