<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jo Create</title>
<link rel="stylesheet" type="text/css"
	href="/web/resources/style/style.css"/>
</head>
<body>
	<h2>조생성</h2>
	<form action="create" method="post">
		<table>
			<tr height="40">
				<th bgcolor="Violet">아이디</th>
				<td><input type="text" name="id"
					value="${sessionScope.loginID}" readonly size="20"></td>
			</tr>
			<tr height="40">
				<th bgcolor="Violet">조번호</th>
				<td><input type="number" name="jno" size="20"></td>
			</tr>
			<tr height="40">
				<th bgcolor="Violet">조이름</th>
				<td><input type="text" name="jname" size="20"></td>
			</tr>
			<tr height="40">
				<th bgcolor="Violet">프로젝트</th>
				<td><input type="text" name="project" size="20"></td>
			</tr>
			<tr height="40">
				<th bgcolor="Violet">슬로건</th>
				<td><input type="text" name="slogan" size="20"></td>
			</tr>
			<tr height="40">
				<td><input type="submit" value="작성">&nbsp;&nbsp;&nbsp;
					<input type="reset" value="취소"></td>
			</tr>
		</table>
	</form>

	<hr>
	<c:if test="${not empty message}">
	=> ${message}
</c:if>

	<a class="button" href="javascript:history.go(-1)">이전으로</a>&nbsp; &nbsp;
	<a class="button" href="/web/">홈으로</a>

</body>
</html>