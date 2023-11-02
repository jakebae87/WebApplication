
// 1) 아이디
function idCheck() {
  let id = document.getElementById('id').value;
  if (id.length<4 || id.length>10) { 
     document.getElementById('iMessage').innerHTML='아이디는 4~10 글자 입니다.';
     return false;

  } else if ( id.replace(/[a-z.0-9]/gi,'').length > 0 ) {
      document.getElementById('iMessage').innerHTML='아이디는 영문과 숫자만 입력하세요.';
      return false;
  }else {
     document.getElementById('iMessage').innerHTML='';
     return true;
   };
}

// 2) 비밀번호
function passwordCheck() {
  let password = document.getElementById('password').value;
  let special = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
  special = /[!-*.@]/gi;
  if (password.length<4 || password.length>10) { 
    document.getElementById('pMessage').innerHTML='비밀번호는 4~10 글자 입니다.' ;
    return false;
  } else if (password.replace(/[a-z.0-9.!-*.@]/gi,'').length > 0) {  
    document.getElementById('pMessage').innerHTML = '영문, 숫자, 특수문자로만 입력하세요.';
    return false;
  } else if (special.test(password) == false) {
    document.getElementById('pMessage').innerHTML = '특수문자가 포함되어야 합니다.';
    return false;
  }else {
    document.getElementById('pMessage').innerHTML = '';
    return true;
  }
}


// 3) 비밀번호 중복확인
function passwordDoubleCheck() {
  let password = document.getElementById('password').value;
  let password2 = document.getElementById('password2').value;
  if (password !=password2) { 
    document.getElementById('p2Message').innerHTML='비밀번호가 일치하지 않습니다.';
    return false;
  } else {
    document.getElementById('p2Message').innerHTML='';
    return true;  
  }
}


// 4) 이름
function nameCheck() {
  let name = document.getElementById('name').value;
  if (name.length<2) { 
    document.getElementById('nMessage').innerHTML='이름은 두 글자 이상 입력하세요.';
    return false;
  } else if ( name.replace(/[a-z.가-힣]/gi,'').length>0 ) {
    document.getElementById('nMessage').innerHTML='이름은 한글과 영문으로만 입력하세요.';
    return false;
  } else {
    document.getElementById('nMessage').innerHTML='';
    return true;  
  }
} 


// 5) 나이
function ageCheck() {
  let age = document.getElementById('age').value; 
  if ( age.replace(/[^0-9]/g,'').length < age.length || Number.isInteger(parseInt(age))==false ) {
      document.getElementById('aMessage').innerHTML='나이는 정수로만 입력하세요.';
      return false;
  }else {
      document.getElementById('aMessage').innerHTML='';
      return true;  
  }
}


// 6) 생년월일
function birthdayCheck() {
  let birthday = document.getElementById('birthday').value;
  if (birthday.length!=10) { 
    document.getElementById('bMessage').innerHTML='생년월일은 (yyyy-mm-dd)으로 입력해주세요';
    return false;
  } else {
    document.getElementById('bMessage').innerHTML='';
    return true;
  };
}


// 7) 포인트
function pointCheck() {
  let point = document.getElementById('point').value;
  if ( point.replace(/[^0-9./.]/g,'').length < point.length || Number.isNaN(parseFloat(point)) ) {
    document.getElementById('oMessage').innerHTML='포인트는 정수 또는 실수로 입력하세요.';
    return false;
  }else if ( parseFloat(point)<100 || parseFloat(point)>10000 ) { 
    document.getElementById('oMessage').innerHTML='포인트의 범위가 100~10,000을 벗어납니다.';
    return false;
  }else {
    document.getElementById('oMessage').innerHTML='';
    return true;  
  }
}
