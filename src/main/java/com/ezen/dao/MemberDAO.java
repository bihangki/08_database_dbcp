package com.ezen.dao;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import com.ezen.dto.MemberVO;

public class MemberDAO {

	//싱글톤 클래스를 만들기 위해서 생성자를 private로 선언
	private MemberDAO(){		
	}
	
	// 내부적으로 사용할 객체 생성
	private static MemberDAO instance = new MemberDAO();
	
	// 내부에서 생성한 객체를 외부에 제공하는 메소드
	public static MemberDAO getInstance() {
		return instance;
	}
	
	//데이터베이스에 연결을 수행하는 메소드
	public Connection getConnection() throws Exception {
	
		//JNDI 초기화(Java Naming and Directory Interface)(JNDI객체를 사용하기 위한 객체 생성)was 구성파일 읽는 객체 초기화
		Context initContext = new InitialContext();
		// lookup() - 지정된 이름으로 등록된 서비스를 찾을 때 사용
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		// JDBC 연결 서비스 등록
		DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
		//DBCP에 등록된 오라클 연결을 할당받는다.
		Connection conn = ds.getConnection();
		
		return conn;
	}
	
	public int userCheck(String userid, String pwd) {
		Connection conn = null; //오라클 DB 연결 객체
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String sql = "SELECT pwd FROM member WHERE userid=?";
		int result = -1;
		//DB에 연결 수행
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,userid); // ?에 화면입역 id를 바인딩
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String db_pwd = rs.getString("pwd");
				if(pwd.equals(db_pwd)) {
					result = 1; //userid와 pwd 가 모두 일치
				} else {
					result = 0;
				}
			}else {
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public MemberVO getMember(String userid) {
		Connection conn = null; //오라클 DB 연결 객체
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE userid=?";
		MemberVO member = null;

		//DB에 연결 수행
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,userid); // ?에 화면입역 id를 바인딩
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setName(rs.getString("name"));
				member.setUserid(rs.getString("userid"));
				member.setPwd(rs.getString("pwd"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setAdmin(rs.getInt("admin"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return member;
	}
	
	/* 회원가입 시, 아이디 중복 체크를 하는 메소드 
	 * 아이디가 존재하면 1
	 * 존재하지 않으면 -1을 반환
	 * */
	
	public int confirmID(String userid ) {
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs =null;
		int result = -1;
		String sql = "SELECT userid FROM member WHERE userid=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
			} else {
				result = -1;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}

	/*
	 * 회원가입화며네서 전달된 데이터를 member테이블에 저장
	 */
	public int insertMember(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		String sql = "INSERT INTO member VALUES(?, ?, ?, ?, ?, ?)";
		int result = -1;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getUserid());
			pstmt.setString(3, member.getPwd());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			pstmt.setInt(6, member.getAdmin());
			
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}
	
	public int updateMember(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		String sql = "UPDATE member SET pwd=?, email=?, phone=?, admin=? WHERE userid=? ";
		int result = -1;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPwd());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhone());
			pstmt.setInt(4, member.getAdmin());
			pstmt.setString(5, member.getUserid());
			
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}
}
