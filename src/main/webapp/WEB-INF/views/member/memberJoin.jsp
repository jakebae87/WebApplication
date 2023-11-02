<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join</title>
<link rel="stylesheet" type="text/css" href="/web/resources/style/style.css" />
<script src="/web/resources/js/joinValidation.js"></script>
<script>
"use strict"
	
	
function idDuplicationCheck(){
	if(!iCheck){
		iCheck = idCheck();	
	}else{
		let url = "idDuplication?id="+ document.getElementById('id').value;
		window.open(url,'_blank','width=250, heigth=250 ');
	}
}

let iCheck = false;
let pCheck = false;
let p2Check = false;
let nCheck = false;
let aCheck = false;
let bCheck = false;
let oCheck = false;


onload = function () {

	// 엔터키 눌렀을 때, 칸 내려가기
	let inputs = document.getElementsByTagName('input');
	
	for (let i = 0; i < inputs.length; i++) {
	    inputs[i].addEventListener('keydown', (e) => {
	        if (e.which == 13) {
	            e.preventDefault();
	            inputs[i + 1].focus();
	        }
	    })
	}

    //나이
    document.getElementById('id').addEventListener('focusout'
        , () => { iCheck = idCheck(); });

    //비밀번호
    document.getElementById('password').addEventListener('focusout'
        , () => { pCheck = passwordCheck(); });

    //비밀번호 재확인
    document.getElementById('password2').addEventListener('focusout'
        , () => { p2Check = passwordDoubleCheck(); });

    //이름
    document.getElementById('name').addEventListener('focusout'
        , () => { nCheck = nameCheck(); });

    //나이
    document.getElementById('age').addEventListener('focusout'
        , () => { aCheck = ageCheck(); });

    //생년월일
    document.getElementById('birthday').addEventListener('focusout'
        , () => { bCheck = birthdayCheck(); });

    //포인트
    document.getElementById('point').addEventListener('focusout'
        , () => { oCheck = pointCheck(); });

} //onload

function inCheck() {
    if (iCheck == false) { document.getElementById('iMessage').innerHTML = '필수입력, 아이디는 4~10 글자 입니다.'; }
    if (pCheck == false) { document.getElementById('pMessage').innerHTML = '필수입력, Password 입력 하세요.'; }
    if (p2Check == false) { document.getElementById('p2Message').innerHTML = '필수입력, Password 확인'; }
    if (nCheck == false) { document.getElementById('nMessage').innerHTML = '필수입력, Name 입력 하세요.'; }
    if (aCheck == false) { document.getElementById('aMessage').innerHTML = '필수입력, Age 입력 하세요.'; }
    if (bCheck == false) { document.getElementById('bMessage').innerHTML = '필수입력, Brthday 입력 하세요.'; }
    if (oCheck == false) { document.getElementById('oMessage').innerHTML = '필수입력, Point 입력 하세요.'; }

    if (iCheck && pCheck && p2Check && nCheck && aCheck && bCheck && oCheck) {

        if (confirm('가입하시겠습니까?')) {
            return true;
        } else {
            alert('가입이 취소 되었습니다.');
            return false;
        }
    } else { // => submit 진행불가
        return false;
    }
} //inCheck


</script>
</head>
<body>
	<h2>회원가입</h2>
	<form action="join" method="post" enctype="multipart/form-data">
		<table>
			<tr height="40">
				<th bgcolor="aqua">아이디</th>
				<td>
					<input type="text" name="id" id="id" placeholder="영어, 10글자 이내" size="20">
					<button type="button" id="idDuplication" onclick="idDuplicationCheck()">중복확인</button><br>
					<span id="iMessage" class="eMessage"></span>
				</td>
			</tr>
			<tr height="40">
				<th bgcolor="aqua">비밀번호</th>
				<td>
					<input type="password" name="password" id="password" placeholder="영어,숫자,특수문자" size="20"><br>
					<span id="pMessage" class="eMessage"></span>
				</td>
			</tr>
			<tr height="40">
				<th bgcolor="aqua">비밀번호 재확인</th>
				<td>
					<input type="password" name="password2" id="password2" placeholder="비밀번호를 다시 입력해주세요." size="20"><br>
					<span id="p2Message" class="eMessage"></span>
				</td>
			</tr>
			<tr height="40">
				<th bgcolor="aqua">이름</th>
				<td>
					<input type="text" name="name" id="name" placeholder="한글 또는 영문" size="20"><br>
					<span id="nMessage" class="eMessage"></span>
				</td>
			</tr>
			<tr height="40">
				<th bgcolor="aqua">나이</th>
				<td>
					<input type="text" name="age" id="age" placeholder="숫자입력" size="20"><br>
					<span id="aMessage" class="eMessage"></span>
				</td>
			</tr>
			<tr height="40">
				<th bgcolor="aqua">조</th>
				<td><select name="jno">
						<option value="1">1조: 119조</option>
						<option value="2">2조: 여우</option>
						<option value="3">3조: i4</option>
						<option value="4">4조: 최고조</option>
						<option value="5">5조: 오조</option>
						<option value="7">7조: 관리팀</option>
				</select></td>
			</tr>
			<tr height="40">
				<th bgcolor="aqua">자기소개</th>
				<td><input type="text" name="info" placeholder="자기소개 & 희망사항" size="20"></td>
			</tr>
			<tr height="40">
				<th bgcolor="aqua">포인트</th>
				<td>
					<input type="text" name="point" id="point" placeholder="실수입력" size="20"><br>
					<span id="oMessage" class="eMessage"></span>
				</td>
			</tr>
			<tr height="40">
				<th bgcolor="aqua">생년월일</th>
				<td>
					<input type="date" name="birthday" id="birthday" size="20"><br>
					<span id="bMessage" class="eMessage"></span>
				</td>
			</tr>
			<tr height="40">
				<th bgcolor="aqua">추천인</th>
				<td><input type="text" name="rid" size="20"></td>
			</tr>
			<tr height="40">
				<th bgcolor="aqua">사진</th>
				<td><img src="" class="selectImage" width="100" height="100"><br> <input
					type="file" name="uploadfilef" id="uploadfilef" size="20"></td>
			</tr>
			<tr height="40">
				<td><input type="submit" id="submitTag" value="가입" onclick="return inCheck()" disabled="disabled">&nbsp;&nbsp;&nbsp;
					<input type="reset" value="취소"></td>
			</tr>
		</table>
	</form>

	<hr>
	<c:if test="${not empty message}">
	=> ${message}
</c:if>

	<a class="button" href="/web/">홈으로</a>

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