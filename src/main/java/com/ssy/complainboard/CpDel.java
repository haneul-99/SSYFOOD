package com.ssy.complainboard;

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
 * <p>문의게시판의 글을 삭제할 때 사용하는 클래스입니다. </p>
 *
 */

@WebServlet("/complainboard/cpdel.do")
public class CpDel extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/cpdel.jsp");
		dispatcher.forward(req, resp);

	}
}