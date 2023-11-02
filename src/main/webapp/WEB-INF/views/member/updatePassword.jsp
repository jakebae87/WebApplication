<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Password Update</title>
<link rel="stylesheet" type="text/css"
	href="/web/resources/style/style.css"/>
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
				<th bgcolor="pink">아이디</th>
				<td><input type="hidden" name="id" value="${requestScope.apple.id}">${requestScope.apple.id}</td>
			</tr>
			<tr height="40">
				<th bgcolor="pink">새로운 비밀번호</th>
				<td><input type="password" id="newPassword" name="password"></td>
			</tr>
			<tr height="40">
				<th bgcolor="pink">비밀번호 재확인</th>
				<td><input type="password" id="checkPassword"></td>
			</tr>
			<tr height="40">
				<td><input type="submit" id="submit" onclick="return passwordCheck()" value="수정">&nbsp;&nbsp;&nbsp;
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
	<a class="button" href="javascript:history.go(-1)">이전으로</a>&nbsp;
	<a class="button" href="/web/">Home</a>
</body>
<script>
	function passwordCheck() {
		let newPassword = document.getElementById('newPassword').value;
		if (newPassword.length < 4) {
			alert("비밀번호를 4자 이상 입력하세요.");
			return false
		} else if (newPassword != document.getElementById('checkPassword').value) {
			alert("비밀번호 불일치");
			return false;
		} else
			return true;
	}
</script>
</html>