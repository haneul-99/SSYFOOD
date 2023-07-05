package com.ssy.eventboard;

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
 * @author 김하늘
 * <p>이벤트 게시판의 글을 수정할때 사용하는 클래스입니다.</p>
 *
 */
@WebServlet("/eventboard/evtedit.do")
public class EvtEdit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String eseq = req.getParameter("seq");

		EvtBoardDAO dao = new EvtBoardDAO();
		
		EvtBoardDTO dto = dao.get(eseq);
		
		//System.out.println(eseq);
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("auth") == null) {
			PrintWriter writer = resp.getWriter();
			
			writer.print("<script>");
			writer.print("alert('failed');");
			writer.print("location.href='/ssy/index.do';");
			writer.print("</script>");
			writer.close();
			return;
		} 


		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/evtedit.jsp");
		dispatcher.forward(req, resp);

	}
}