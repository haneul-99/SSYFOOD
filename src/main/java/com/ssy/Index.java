package com.ssy;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author 유동환
 * 
 * <p>사이트의 첫화면으로 카테고리를 선택하는 화면입니다. </p>
 *
 */
@WebServlet("/index.do")
public class Index extends HttpServlet {
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(req, resp);
	}

}
