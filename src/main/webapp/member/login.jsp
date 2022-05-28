<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	label{
		display:inline-block;
		width : 75px;
	}
	*{
		margin:0;
		padding:0;
	}
	#frm{
		margin:30px;
	}
	h3{
		margin: 30px 0 0 30px;
	}
</style>
<script src="member.js"></script>
<body>
	<h2>로그인</h2>
	<form method="post" action="login.do" id="frm">
		<label for= "id">아이디</label>
		<input type="text" name="id" id="userid" autofocus="autofocus" > <br>
		<label for= "pwd">암 호</label>
		<input type="password" name="pwd" id="userpwd"> <br><br>
		<input type="submit" value="로그인" onclick="return loginCheck()">&nbsp;
		<input type="reset" value="취소">&nbsp;
		<input type="button"  value="회원가입" onclick="location.href='join.do'">&nbsp;<br>
		<span id="msg">${message}<br></span>
	</form>
			
	
</body>
</html>