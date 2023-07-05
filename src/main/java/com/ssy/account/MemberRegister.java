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
 * @author 김하늘
 * <p>회원 회원가입 클래스입니다.</p>
 *
 */
@WebServlet("/account/memberregister.do")
public class MemberRegister extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//RegisterChoice.java
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/account/memberregister.jsp");
		dispatcher.forward(req, resp);

	}
}
