/**
 * 
 */
 function loginCheck(){
	var userid = document.getElementById("userid").value;
	var userpwd = document.getElementById("userpwd").value;
	if (userid == ""){
		alert("아이디를 입력해 주세요!");
		document.getElementById("userid").focus();
		return false;
	}else if(userid =="") {
		alert("비밀번호를 입력해주세요!");
		document.getElementById("userpwd").focus();
		return false;
	}
	
	return true;
}

function joinCheck(){
	var username = document.getElementById("name").value;
	var userid = document.getElementById("userid").value;
	var pwd = document.getElementById("pwd").value;
	var pwd_check = document.getElementById("pwd_check").value;
	var reid = document.getElementById("reid").value;
	
	if(username==""){
		alert("이름을 입력해 주세요!");
		document.getElementById("name").focus();
		return false;
	} else if (userid==""){
		alert("아이디를 입력해 주세요!");
		document.getElementById("userid").focus();
		return false;
	} else if (pwd==""){
		alert("비밀번호를 입력해 주세요!");
		document.getElementById("pwd").focus();
		return false;
	} else if (pwd_check==""){
		alert("비밀번호확인을 입력해 주세요!");
		document.getElementById("pwd_check").focus();
		return false;
	} 
	if (pwd!=pwd_check){
	alert("위의 비밀번호와 동일한 비밀번호를 입력해주세요!");
	document.getElementById("pwd_check").focus();
	return false;
	}
	if (reid==""){
	alert("아이디 중복체크를 하지 않았습니다.");
	document.getElementById("userid").focus();
	return false;
	}
	return true;
}
/*회원가입시 사용자 ID가 이미 사용중인지 확인
*/
function idCheck() {
	var userid = document.getElementById("userid").value;
	
	if (userid == "") {
		alert("사용자 아이디를 입력해 주세요.");
		document.getElementById("userid").focus();
		return false;
	}
	
	var url = "idCheck.do?userid="+userid;
	
	// 팝업창 열기
	window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
}

function  idok() {

    //opener.frm.userid.value = document.frm.userid.value;
    //opener.frm.reid.value = document.frm.userid.value;
    opener.document.getElementById("userid").value = document.getElementById("userid").value;
    opener.document.getElementById("reid").value = document.getElementById("userid").value;
    self.close();
}	