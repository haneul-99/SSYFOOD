package com.ssy.Faqboard;

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
 * <p>자주하는 질문의 글을 삭제할 때 사용하는 클래스입니다. </p>
 *
 */

@WebServlet("/faqboard/faqdel.do")
public class FaqDel extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//FaqDel.java

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/faqdel.jsp");
		dispatcher.forward(req, resp);

	}
}