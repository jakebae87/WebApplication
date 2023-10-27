<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css"
	href="/web/resources/style/style.css"/>
</head>
<body>
<h2>로그인</h2>
<form action="login" method="post">
<table>
	<tr height="30"><td><label for="id">ID</label></td>
		<td><input type="text" id="id" name="id"></td>
	</tr>
	<tr height="30"><td><label for="password">Password</label></td>
		<td><input type="password" id="password" name="password"></td>
	</tr>
	<tr height="30"><td></td>
		<td><input type="submit" value="로그인">&nbsp;&nbsp;
			<input type="reset" value="취소">
		</td>
	</tr>
</table>
</form>
<hr>
<a class="button" href="/web/">Home</a><br>
<c:if test="${not empty requestScope.message}">
	<hr>message: ${requestScope.message}
</c:if>

</body>
</html>