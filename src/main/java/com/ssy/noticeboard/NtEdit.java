package com.ssy.noticeboard;

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
 * <p>공지사항 수정 클래스입니다.</p>
 * 
 */
@WebServlet("/noticeboard/ntedit.do")
public class NtEdit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String seq = req.getParameter("nseq");
		
		
		NtBoardDAO dao = new NtBoardDAO();
		
		NtBoardDTO dto = dao.get(seq);
		
		req.setAttribute("dto", dto);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/ntedit.jsp");
		dispatcher.forward(req, resp);

	}
}
