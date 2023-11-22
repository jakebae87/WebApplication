<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member List</title>
<link rel="stylesheet" type="text/css"
	href="/web/resources/style/style.css"/>
</head>
<body>
<h2>회원목록</h2>

<hr>
<c:if test="${not empty requestScope.message}">
	${requestScope.message}
</c:if>

<table border="1" style="width:90%">
	<tr bgcolor="Lime">
		<th>아이디</th>
		<th>이름</th>
		<th>나이</th>
		<th>조번호</th>
		<th>개인정보</th>
		<th>포인트</th>
		<th>생년월일</th>
		<th>추천인</th>
		<th>삭제</th>
		<th>사진</th>
		<th>다운로드</th>
	</tr>
	
	<c:if test="${not empty requestScope.banana}">
		<c:forEach var="s" items="${requestScope.banana}">
			<tr>
				<td><a href="detail?id=${s.id }">${s.id}</a></td>
				<td>${s.name}</td>
				<td>${s.age}</td>
				<td>${s.jno}</td>
				<td>${s.info}</td>
				<td>${s.point}</td>
				<td>${s.birthday}</td>
				<td>${s.rid}</td>
				<td align="center">
					<c:if test="${sessionScope.loginID == 'admin' }">
						<a href="delete?id=${s.id}">삭제</a>
					</c:if>
				</td>
				<td>
					<img alt="picture" src="/web/${s.uploadfile }" width="100" height="100">
				</td>
				<td><a href="download?file=${s.uploadfile}">${s.uploadfile}</a></td>
			</tr>
		</c:forEach>
	</c:if>
	
	<c:if test="${empty requestScope.banana}">
		<tr>
			<td colspan="7">
				출력할 데이터가 없습니다
			</td>
		</tr>
	</c:if>
</table>

<hr>
<a class="button" href="/web/">Home</a>
</body>
</html>