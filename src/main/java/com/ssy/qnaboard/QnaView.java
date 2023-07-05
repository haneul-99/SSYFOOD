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
 * <p>판매자 문의게시판의 게시글을 상세히 보기위한 클래스</p>
 *
 */
@WebServlet("/board/qnaview.do")
public class QnaView extends HttpServlet {

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
	    
	    dto.setQtitle(qtitle);
	    dto.setQcontent(qcontent);
	    
	    
	    req.setAttribute("dto", dto);
	    req.setAttribute("pseq", pseq);
	    
	    req.setAttribute("qtitle", qtitle);
	    req.setAttribute("qcontent", qcontent);
	    req.setAttribute("qseq", qseq);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/qnaview.jsp");
		dispatcher.forward(req, resp);

	}

}
