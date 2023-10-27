<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jo Detail</title>
<link rel="stylesheet" type="text/css"
	href="/web/resources/style/style.css" />
</head>
<body>
	<h2>조 상세페이지</h2>
	<hr>
	<c:if test="${not empty requestScope.apple}">
		<table>
			<tr height="40">
				<td bgcolor="Lavender">조번호</td>
				<td>${apple.jno}</td>
			</tr>
			<tr height="40">
				<td bgcolor="Lavender">조이름</td>
				<td>${apple.jname}</td>
			</tr>
			<tr height="40">
				<td bgcolor="Lavender">조장이름</td>
				<td>${apple.id}</td>
			</tr>
			<tr height="40">
				<td bgcolor="Lavender">프로젝트</td>
				<td>${apple.project}</td>
			</tr>
			<tr height="40">
				<td bgcolor="Lavender">슬로건</td>
				<td>${apple.slogan}</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${empty requestScope.apple}">
		<hr>조원이 없습니다.<br>
	</c:if>
	<hr>
	<h3>${apple.jname} 조원 목록</h3>
	<table width=100%>
		<tr bgcolor="LavenderBlush" height="30">
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>나이</th>
			<th>조번호</th>
			<th>개인정보</th>
			<th>포인트</th>
			<th>생년월일</th>
			<th>추천인</th>
		</tr>
		<c:if test="${not empty requestScope.banana}">
			<c:forEach var="m" items="${requestScope.banana}">
				<tr height="30">
					<td><c:if test="${sessionScope.loginID=='admin'}">
							<a href="detail?id=${m.id}">${m.id}</a>
						</c:if> 
						
						<c:if test="${sessionScope.loginID!='admin'}">
							${m.id}
						</c:if>
					</td>
					<td>${m.password}</td>
					<td>${m.name}</td>
					<td>${m.age}</td>
					<td>${m.jno}</td>
					<td>${m.info}</td>
					<td>${m.point}</td>
					<td>${m.birthday}</td>
					<td>${m.rid}</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.banana}">
			<tr>
				<td colspan="9">출력할 데이터가 없습니다.</td>
			</tr>
		</c:if>
	</table>

	<hr>
	<c:if test="${not empty message}">
		${message}<br>
	</c:if>
	<a class="button" href="joCreate">등록</a>&nbsp; &nbsp;
	<a class="button" href="update?jno=${apple.jno}">수정</a>&nbsp; &nbsp;
	<a class="button" href="delete?jno=${apple.jno}">삭제</a>
	<br><br>
	<a class="button" href="joList">목록</a>&nbsp; &nbsp;
	<a class="button" href="javascript:history.go(-1)">이전으로</a>&nbsp; &nbsp;
	<a class="button" href="/web/">홈으로</a>&nbsp;
</body>
</html>