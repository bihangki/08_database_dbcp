<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ezen.dao.MemberDAO" %>
<%@ page import="java.sql.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	// DAO 객체 생성
	MemberDAO mDAO = MemberDAO.getInstance();

	// 데이터 베이스 연결 수행
	Connection conn = mDAO.getConnection();
	
	if(conn != null){
		out.print("데이터 베이스 연결 성공!!");
		
		conn.close();
	}
%>
</body>
</html>