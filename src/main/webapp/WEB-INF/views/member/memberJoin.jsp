<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join</title>
<link rel="stylesheet" type="text/css"
	href="/web/resources/style/style.css"/>
</head>
<body>
<h2>회원가입</h2>
<form action="join" method="post">
	<table>
		<tr height="40">
			<th bgcolor="aqua">아이디</th>
			<td><input type="text" name="id" placeholder="영어, 10글자 이내" size="20"></td>
		</tr>
		<tr height="40">
			<th bgcolor="aqua">비밀번호</th>
			<td><input type="password" name="password" placeholder="영어,숫자,특수문자" size="20"></td>
		</tr>
		<tr height="40">
			<th bgcolor="aqua">이름</th>
			<td><input type="text" name="name" placeholder="한글 또는 영문" size="20"></td>
		</tr>
		<tr height="40">
			<th bgcolor="aqua">나이</th>
			<td><input type="text" name="age" placeholder="숫자입력" size="20"></td>
		</tr>
		<tr height="40">
			<th bgcolor="aqua">조</th>
				<td>
					<select name="jno">
						<option value="1">1조: 119조</option>
						<option value="2">2조: 여우</option>
						<option value="3">3조: i4</option>
						<option value="4">4조: 최고조</option>
						<option value="5">5조: 오조</option>
						<option value="7">7조: 관리팀</option>
					</select>
				</td>
		</tr>
		<tr height="40">
			<th bgcolor="aqua">자기소개</th>
			<td><input type="text" name="info" placeholder="자기소개 & 희망사항" size="20"></td>
		</tr>
		<tr height="40">
			<th bgcolor="aqua">포인트</th>
			<td><input type="text" name="point" placeholder="실수입력" size="20"></td>
		</tr>
		<tr height="40">
			<th bgcolor="aqua">생년월일</th>
			<td><input type="date" name="birthday" size="20"></td>
		</tr>
		<tr height="40">
			<th bgcolor="aqua">추천인</th>
			<td><input type="text" name="rid" size="20"></td>
		</tr>
		<tr height="40">
			<td>
				<input type="submit" value="가입">&nbsp;&nbsp;&nbsp;
				<input type="reset" value="취소">			
			</td>
		</tr>
	</table>
</form>

<hr>
<c:if test="${not empty message}">
	=> ${message}
</c:if>

<a class="button" href="/web/">홈으로</a>

</body>
</html>