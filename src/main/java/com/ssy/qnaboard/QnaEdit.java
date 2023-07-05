package com.ssy.qnaboard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 
 * 
 * @author 82104
 * <p>판매자 문의게시판에 자신이 작성한 게시글을 수정하기 위한 클래스</p>
 *
 */
@WebServlet("/board/qnaedit.do")
public class QnaEdit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
	      
	    QnaDAO dao = new QnaDAO();

	    QnaDTO dto = dao.getMember((String)session.getAttribute("auth"));
	    
	    String pseq = req.getParameter("pseq");
	    
	    
	    String qtitle = req.getParameter("qtitle");
	    
	    String qcontent = req.getParameter("qcontent");
	    
	    String qseq = req.getParameter("qseq");
	    
	    
	    req.setAttribute("dto", dto);
	    req.setAttribute("pseq", pseq);
	    
	    req.setAttribute("qtitle", qtitle);
	    req.setAttribute("qcontent", qcontent);
	    req.setAttribute("qseq", qseq);
	    
	    

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/qnaedit.jsp");
		dispatcher.forward(req, resp);

	}

}
