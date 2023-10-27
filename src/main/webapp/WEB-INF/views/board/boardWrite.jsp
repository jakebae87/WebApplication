<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web_MVC2 Student Join **</title>
<link rel="stylesheet" type="text/css"
	href="/web/resources/style/style.css"/>
</head>
<body>
	<h2>** Web_MVC2 Student Join **</h2>
	<form action="write" method="post">
		<table>
			<tr height="40">
				<th bgcolor="Violet">I D</th>
				<td><input type="text" name="id"
					value="${sessionScope.loginID}" readonly size="20"></td>
			</tr>
			<tr height="40">
				<th bgcolor="Violet">Title</th>
				<td><input type="text" name="title" size="50"></td>
			</tr>
			<tr height="40">
				<th bgcolor="Violet">Content</th>
				<td><textarea rows="5" cols="50" name="content"></textarea></td>
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
	<a class="button" href="javascript:history.go(-1)">이전으로</a>&nbsp; &nbsp;
	<a class="button" href="/web/">Home</a>&nbsp;

</body>
</html>