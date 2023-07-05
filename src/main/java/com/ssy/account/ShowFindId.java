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
 * <p>아이디 찾기 후 찾은 아이디를 보여주는 클래스입니다.</p>
 * 
 */
@WebServlet("/account/showfindid.do")
public class ShowFindId extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/account/showfindid.jsp");
		dispatcher.forward(req, resp);

	}
}