<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web_MVC2 Jo Update **</title>
<link rel="stylesheet" type="text/css"
	href="/web/resources/style/style.css"/>
</head>
<body>
	<h2>** Web_MVC2 Jo Update **</h2>
	<hr>
	<c:if test="${not empty requestScope.message}">
		${requestScope.message}
	</c:if>
	<hr>
	<form action="update" method="post">
		<table>
			<c:if test="${not empty requestScope.apple}">
				<tr height="40">
					<th bgcolor="Chocolate">조번호</th>
					<td><input type="number" name="jno" size="20"
						value="${requestScope.apple.jno }" readonly="readonly"></td>
				</tr>
				<tr height="40">
					<th bgcolor="Chocolate">조이름</th>
					<td><input type="text" name="jname" size="20"
						value="${requestScope.apple.jname }"></td>
				</tr>
				<tr height="40">
					<th bgcolor="Chocolate">조장</th>
					<td><input type="text" name="id" size="20"
						value="${requestScope.apple.id }"></td>
				</tr>
				<tr height="40">
					<th bgcolor="Chocolate">프로젝트</th>
					<td><input type="text" name="project" size="20"
						value="${requestScope.apple.project }"></td>
				</tr>
				<tr height="40">
					<th bgcolor="Chocolate">슬로건</th>
					<td><input type="text" name="slogan" size="20"
						value="${requestScope.apple.slogan }"></td>
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
			<td colspan="2">출력할 Data가 없음</td>
		</tr>
	</c:if>

	&nbsp;
	<a class="button" href="javascript:history.go(-1)">이전으로</a>&nbsp; &nbsp;
	<a class="button" href="/web/">Home</a>&nbsp;
</body>
</html>