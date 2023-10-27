<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Update</title>
<link rel="stylesheet" type="text/css"
	href="/web/resources/style/style.css"/>
</head>
<body>
	<h2>글수정</h2>
	<c:if test="${not empty requestScope.message}">
		${requestScope.message}
	</c:if>
	<hr>
	<form action="update" method="post">
		<table>
			<c:if test="${not empty requestScope.apple}">
				<tr height="40">
					<th bgcolor="Chocolate">Seq</th>
					<td><input type="text" name="seq" size="20"
						value="${requestScope.apple.seq }" readonly="readonly"></td>
				</tr>
				<tr height="40">
					<th bgcolor="Chocolate">ID</th>
					<td><input type="text" name="id" size="20"
						value="${requestScope.apple.id }" readonly="readonly"></td>
				</tr>
				<tr height="40">
					<th bgcolor="Chocolate">Title</th>
					<td><input type="text" name="title" size="50"
						value="${requestScope.apple.title }"></td>
				</tr>
				<tr height="40">
					<th bgcolor="Chocolate">Content</th>
					<td><textarea rows="5" cols="50" name="content">${requestScope.apple.content}</textarea></td>
				</tr>
				<tr height="40">
					<th bgcolor="Chocolate">RegDate</th>
					<td><input type="text" name="regdate" size="20"
						value="${requestScope.apple.regdate }" readonly="readonly"></td>
				</tr>
				<tr height="40">
					<th bgcolor="Chocolate">조회수</th>
					<td><input type="text" name="cnt" size="20"
						value="${requestScope.apple.cnt }" readonly="readonly"></td>
				</tr>

				<tr height="40">
					<td><input type="submit" value="수정">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="취소"></td>
				</tr>
			</c:if>
		</table>
	</form>
	<hr>
	<c:if test="${empty requestScope.apple}">
		<tr>
			<td colspan="2">출력할 데이터가 없음</td>
		</tr>
	</c:if>

	<a class="button" href="javascript:history.go(-1)">이전으로</a>&nbsp; &nbsp;
	<a class="button" href="/web/">홈으로</a>
</body>
</html>