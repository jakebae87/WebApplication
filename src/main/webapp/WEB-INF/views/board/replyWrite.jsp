<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reply Write</title>
<link rel="stylesheet" type="text/css"
	href="/web/resources/style/style.css" />
</head>
<body>
	<h2>댓글 작성</h2>
	<form action="replyWrite" method="post">
		<table>
			<tr height="40">
				<th bgcolor="Violet">아이디</th>
				<td><input type="text" name="id"
					value="${sessionScope.loginID}" readonly size="20"> <input
					type="hidden" name="post" value="${requestScope.number.post }" /></td>
			</tr>
			<tr height="40">
				<th bgcolor="Violet">댓글내용</th>
				<td><input type="text" name="content" size="20" /></td>
			</tr>
			<tr height="40">
				<td><input type="submit" value="작성">&nbsp;&nbsp; 
				<input type="reset" value="취소"></td>
			</tr>
		</table>
	</form>

	<hr>
	<c:if test="${not empty message}">
	=> ${message}
</c:if>

	<a class="button" href="javascript:history.go(-1)">이전으로</a>&nbsp;
	&nbsp;
	<a class="button" href="/web/">홈으로</a>

</body>
</html>