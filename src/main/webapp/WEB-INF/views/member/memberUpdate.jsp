<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Update</title>
<link rel="stylesheet" type="text/css"
	href="/web/resources/style/style.css" />
</head>
<body>
	<h2>회원정보수정</h2>
	<hr>
	<c:if test="${not empty requestScope.message}">
		${requestScope.message}
	</c:if>
	<hr>
	<form action="update" method="post" enctype="multipart/form-data">
		<table>
			<c:if test="${not empty requestScope.apple}">
				<tr height="40">
					<th bgcolor="orange">ID</th>
					<td><input type="text" name="id" size="20"
						value="${requestScope.apple.id }" readonly="readonly"></td>
				</tr>

				<tr height="40">
					<th bgcolor="orange">PW</th>
					<td><input type="password" name="password" size="20"></td>
				</tr>

				<tr height="40">
					<th bgcolor="orange">Name</th>
					<td><input type="text" name="name"
						value="${requestScope.apple.name }" size="20"></td>
				</tr>
				<tr height="40">
					<th bgcolor="orange">Age</th>
					<td><input type="text" name="age"
						value="${requestScope.apple.age }" size="20"></td>
				</tr>
				<tr height="40">
					<th bgcolor="orange">Jno</th>
					<td><select name="jno">
							<option value="1"
								<c:if test="${requestScope.apple.jno eq '1'}"> selected</c:if>>1조:
								119조</option>
							<option value="2"
								<c:if test="${requestScope.apple.jno eq '2'}">selected </c:if>>2조:
								여우</option>
							<option value="3"
								<c:if test="${requestScope.apple.jno eq '3'}"> selected</c:if>>3조:
								i4</option>
							<option value="4"
								<c:if test="${requestScope.apple.jno eq '4'}"> selected</c:if>>4조:
								최고조</option>
							<option value="5"
								<c:if test="${requestScope.apple.jno eq '5'}"> selected</c:if>>5조:
								오조</option>
							<option value="6"
								<c:if test="${requestScope.apple.jno eq '6'}"> selected</c:if>>6조:
								관리팀</option>
					</select></td>
				</tr>
				<tr height="40">
					<th bgcolor="orange">Info</th>
					<td><input type="text" name="info"
						value="${requestScope.apple.info }" size="20"></td>
				</tr>
				<tr height="40">
					<th bgcolor="orange">Point</th>
					<td><input type="text" name="point"
						value="${requestScope.apple.point }" readonly="readonly" size="20"></td>
				</tr>
				<tr height="40">
					<th bgcolor="orange">Birthday</th>
					<td><input type="date" name="birthday"
						value="${requestScope.apple.birthday}" size="20"></td>
				</tr>
				<tr height="40">
					<th bgcolor="orange">추천인</th>
					<td><input type="text" name="rid"
						value="${requestScope.apple.rid }" size="20"></td>
				</tr>
				<tr height="40">
					<th bgcolor="orange">사진</th>
					<td>
					<img alt="사진" class="selectImage" src="/web/${requestScope.apple.uploadfile }"  width="100" height="100">
					<input type="hidden" name="uploadfile" value="${requestScope.apple.uploadfile }"><br>
					<input type="file" id="uploadfilef" name="uploadfilef" size="20"></td>	<!-- 업데이트할 사진 -->
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

	<a class="button" href="/web/">Home</a>&nbsp;
</body>
<script>
	document.getElementById('uploadfilef').onchange = function(e) {
		if (this.files && this.files[0]) {
			let reader = new FileReader;
			reader.readAsDataURL(this.files[0]);
			reader.onload = function(e) { //img src 변경
				document.getElementsByClassName("selectImage")[0].src = e.target.result;
			}
		}
	};
</script>
</html>