package com.ssy.qnaboard;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author 김유진
 * <p>판매자 문의게시판에 게시글을 추가하는 클래스</p>
 *
 */

@WebServlet("/board/qnaadd.do")
public class QnaAdd extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
	      
	    QnaDAO dao = new QnaDAO();

	    QnaDTO dto = dao.getMember((String)session.getAttribute("auth"));
	    
	    String pseq = req.getParameter("pseq");
	    
	    
	    req.setAttribute("dto", dto);
	    req.setAttribute("pseq", pseq);
	    
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/qnaadd.jsp");
		dispatcher.forward(req, resp);

	}

}
