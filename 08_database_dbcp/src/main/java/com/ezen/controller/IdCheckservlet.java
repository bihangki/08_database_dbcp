package com.ezen.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.dao.MemberDAO;

/**
 * Servlet implementation class IdCheckservlet
 */
@WebServlet("/idCheck.do")
public class IdCheckservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdCheckservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/idCheck.jsp";
		//화면에서 입력 파라미터를 읽어온다.
		String userid = request.getParameter("userid");
		
		//사용자 ID를 데이터베이스에서 조회
		MemberDAO mDao = MemberDAO.getInstance();		
		int status = mDao.confirmID(userid);
		
		//jsp에 넘겨주기 위한 결과를 내장객체에 저장
		request.setAttribute("userid", userid);
		request.setAttribute("result", status);
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
