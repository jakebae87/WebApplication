<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 확인</title>
<script src="/web/resources/js/inCheck.js"></script>
<script>
	function idOK() {
		opener.document.getElementById('id').value = '${param.id}';
		
		opener.document.getElementById('idDuplication').disabled = 'disabled';
		opener.document.getElementById('submitTag').disabled = '';

		opener.document.getElementById('id').readOnly = true;

		opener.document.getElementById('password').focus(); 

		close();
	}
</script>
</head>

<body>
	<div id="wrap">
		<h3>아이디 중복 확인</h3>
		<h4>Parameter ID 확인</h4>
		=> Parameter_ID : ${param.id }<br> 
		=> MemberDTO_ID : ${memberDTO.id }<br>
		<hr>
		<form action="idDuplication" method="get">
			User_ID : <input type="text" id="id" name="id" value="${param.id }">
			<input type="submit" value="ID중복확인" onclick="return idCheck()">
			<span id="iMessage" class="eMessage"></span>
		</form>
		<br>
		<br>

		<div id="msgBlock">
			<c:if test="${use=='T'}">
			${memberDTO.id } 사용이 가능합니다. &nbsp;&nbsp;
			<button onclick="idOK()">ID 선택</button>
			</c:if>

			<c:if test="${use=='F' }">
			${memberDTO.id } 사용이 불가능합니다. (사용중)<br>
			다시 입력하세요. <br>
				<!-- 부모창(mJoinForm, opener)에 남아있는 사용자가 입력한 id는 지워주고,  
			    현재(this)창 에서는 id 에 focus 를 주고 재입력 유도 -> script 필요
			-->
				<script>
					document.getElementById('id').focus();
					opener.document.getElementById('id').value = '';
				</script>
			</c:if>
		</div>
	</div>
</body>
</html>