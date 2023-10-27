<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List</title>
<link rel="stylesheet" type="text/css"
	href="/web/resources/style/style.css" />
</head>
<body>
	<h2>글목록</h2>
	<hr>
	<c:if test="${not empty requestScope.message}">
	${requestScope.message}
</c:if>
	<table border="1" style="width: 90%">
		<tr bgcolor="DeepPink">
			<th>글번호</th>
			<th>글제목</th>
			<th>글작성자</th>
			<th>글작성일</th>
			<th>조회수</th>
			<th>삭제</th>
		</tr>

		<c:if test="${not empty requestScope.banana}">
			<c:forEach var="s" items="${requestScope.banana}">
				<tr>
					<td>${s.seq}</td>
					<!-- 로그인 여부에 따라 Title 보여주기 -->
					<c:if test="${not empty sessionScope.loginID }">
						<td><a href="boardDetail?seq=${s.seq}">${s.title}</a></td>
					</c:if>
					<c:if test="${empty sessionScope.loginID }">
						<td><a>${s.title }</a></td>
					</c:if>
					<td>${s.id}</td>
					<td>${s.regdate}</td>
					<td>${s.cnt}</td>
					<td align="center"><c:if
							test="${sessionScope.loginID == s.id }">
							<a href="delete?seq=${s.seq}">삭제</a>
						</c:if></td>
				</tr>
			</c:forEach>
		</c:if>

		<c:if test="${empty requestScope.banana}">
			<tr>
				<td colspan="7">출력할 데이터가 없습니다</td>
			</tr>
		</c:if>
	</table>

	<hr>
	<c:if test="${not empty sessionScope.loginID }">
		<a class="button" href="boardWrite">글쓰기</a>&nbsp;&nbsp;
	</c:if>
	<a class="button" href="/web/">홈으로</a>
</body>
</html>