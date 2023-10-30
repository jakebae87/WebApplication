<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web_MVC2 Reply Write **</title>
<link rel="stylesheet" type="text/css"
	href="/web/resources/style/style.css" />
</head>
<body>
	<h2>** Web_MVC2 Reply Write **</h2>
	<form action="replyUpdate" method="post">
		<table>
			<tr height="40">
				<th bgcolor="Violet">댓글번호</th>
				<td><input type="text" name="seq" size="20"
					value="${requestScope.banana.seq }" readonly="readonly"/></td>
			</tr>
			<tr height="40">
				<th bgcolor="Violet">I D</th>
				<td><input type="text" name="id"
					value="${sessionScope.loginID}" readonly size="20"> <input
					type="hidden" name="post" value="${requestScope.banana.post }"
					readonly="readonly" /></td>
			</tr>
			<tr height="40">
				<th bgcolor="Violet">Content</th>
				<td><input type="text" name="content" size="20"
					value="${requestScope.banana.content }" /></td>
			</tr>
			<tr height="40">
				<th></th>
				<td><input type="submit" value="작성">&nbsp;&nbsp;&nbsp;
					<input type="reset" value="취소"></td>
			</tr>
		</table>
	</form>

	<hr>
	<c:if test="${not empty message}">
	=> ${message}
</c:if>

	&nbsp;
	<a class="button" href="javascript:history.go(-1)">이전으로</a>&nbsp;
	&nbsp;
	<a class="button" href="/web/">Home</a>&nbsp;

</body>
</html>