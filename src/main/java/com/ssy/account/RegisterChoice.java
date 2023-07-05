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
 * <p>회원 회원가입과 판매자 회원가입 중 가입할 회원가입을 선택하는 클래스입니다.</p>
 *
 */
@WebServlet("/account/registerchoice.do")
public class RegisterChoice extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//RegisterChoice.java
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/account/registerchoice.jsp");
		dispatcher.forward(req, resp);

	}
}
