<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Detail</title>
<link rel="stylesheet" type="text/css"
	href="/web/resources/style/style.css" />
</head>
<body>
	<h2>글 상세 페이지</h2>
	<table border="1" width="50%">
		<c:if test="${not empty requestScope.apple}">
			<tr height="40">
				<th bgcolor="Plum">글번호</th>
				<td>${requestScope.apple.seq}</td>
			</tr>
			<tr height="40">
				<th bgcolor="Plum">아이디</th>
				<td>${requestScope.apple.id}</td>
			</tr>
			<tr height="40">
				<th bgcolor="Plum">글제목</th>
				<td>${requestScope.apple.title}</td>
			</tr>
			<tr height="40">
				<th bgcolor="Plum">글내용</th>
				<td>${requestScope.apple.content}</td>
			</tr>
			<tr height="40">
				<th bgcolor="Plum">글작성일</th>
				<td>${requestScope.apple.regdate}</td>
			</tr>
			<tr height="40">
				<th bgcolor="Plum">조회수</th>
				<td>${requestScope.apple.cnt}</td>
			</tr>
		</c:if>
		<c:if test="${empty requestScope.apple}">
			<tr height="40">
				<th colspan="2">출력할 데이터가 없습니다</th>
			</tr>
		</c:if>
	</table>
	<hr>
	<!-- 댓글 -->
	<table border="1" width="50%">
		<tr height="10">
			<th width="40%" bgcolor="DarkSeaGreen">내용</th>
			<th width="15%" bgcolor="DarkSeaGreen">아이디</th>
			<th width="15%" bgcolor="DarkSeaGreen">작성시간</th>
			<th width="20%" bgcolor="DarkSeaGreen">수정</th>
			<th width="20%" bgcolor="DarkSeaGreen">삭제</th>
		</tr>
		<c:if test="${not empty requestScope.tomato}">
			<c:forEach items="${requestScope.tomato}" var="t">
				<tr height="10">
					<td>${t.content}</td>
					<td>${t.id}</td>
					<td>${t.regdate}</td>
					<c:if test="${t.id == sessionScope.loginID }">
						<td><a class="button"
							href="replyUpdate?post=${t.post}&seq=${t.seq}">수정</a></td>
						<td><a class="button"
							href="replyDelete?post=${t.post}&seq=${t.seq}">삭제</a></td>
					</c:if>
					<c:if test="${t.id != sessionScope.loginID }">
						<td>-</td>
						<td>-</td>
					</c:if>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	<hr>

	<!-- 로그인한 경우에 새글등록, 답글등록 -->
	<a class="button" href="boardWrite">글작성</a>&nbsp;&nbsp;
	<a class="button" href="replyWrite?post=${requestScope.apple.seq}">댓글작성</a>&nbsp;&nbsp;

	<!-- 로그인 id와 글쓴이 id가 동일하면 수정과 삭제 가능 -->
	<c:if test="${requestScope.apple.id == sessionScope.loginID }">
		<a class="button" href="boardUpdate?seq=${requestScope.apple.seq }">글수정</a> &nbsp;&nbsp;
		<a class="button" href="delete?seq=${requestScope.apple.seq }">글삭제</a>
	</c:if>
	<br>
	<br>
	<a class="button" href="javascript:history.go(-1)">이전으로</a> &nbsp;&nbsp;
	<a class="button" href="boardList">글목록</a> &nbsp; &nbsp;
	<a class="button" href="/web/">홈으로</a>
</body>
</html>