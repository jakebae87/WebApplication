<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Password Update</title>
</head>
<body>
	<h2>비밀번호 수정</h2>
	<hr>
	<c:if test="${not empty requestScope.message}">
		${requestScope.message}
	</c:if>
	<hr>
	<form action="updatePassword" method="post">
		<table>
			<tr height="40">
				<th bgcolor="pink">ID</th>
				<td><input type="hidden" name="id" value="${requestScope.apple.id}">${requestScope.apple.id}</td>
			</tr>
			<tr height="40">
				<th bgcolor="pink">new Password</th>
				<td><input type="password" id="newPassword" name="password"></td>
			</tr>
			<tr height="40">
				<th bgcolor="pink">check Password</th>
				<td><input type="password" id="checkPassword"></td>
			</tr>
			<tr height="40">
				<td><input type="submit" id="submit" value="수정">&nbsp;&nbsp;&nbsp;
					<input type="reset" value="취소"></td>
			</tr>
		</table>
	</form>
	<c:if test="${empty requestScope.apple}">
		<tr>
			<td colspan="2">출력할 Data가 없음</td>
		</tr>
	</c:if>
	<hr>
	&nbsp;
	<a class="button" href="javascript:history.go(-1)">이전으로</a>&nbsp; &nbsp;
	<a class="button" href="/web/">Home</a>&nbsp;
</body>
<script>
	document.getElementById("submit").addEventListener('click',function(){
		if(document.getElementById("newPassword") != document.getElementById("checkPassword")){
			alert("비밀번호 불일치");
		}
	})
</script>
</html>