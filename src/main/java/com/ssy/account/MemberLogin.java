package com.ssy.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author 구대현
 * <p>회원 로그인 클래스입니다.</p>
 * 
 */
@WebServlet("/account/memberlogin.do")
public class MemberLogin extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pseq = req.getParameter("pseq");
		req.setAttribute("pseq", pseq);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/account/memberlogin.jsp");
		dispatcher.forward(req, resp);
		
		
	}
}