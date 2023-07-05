package com.ssy.noticeboard;

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
 * @author 구대현
 * <p>공지사항 삭제 결과를 저장하는 클래스입니다.</p>
 * 
 */
@WebServlet("/noticeboard/ntdelok.do")
public class NtDelOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String nseq = req.getParameter("nseq");
		
		NtBoardDAO dao = new NtBoardDAO();
		
		int result = dao.del(nseq);
		
		if(dao.isHighSeq(nseq)==true) {
			dao.updateSeq(nseq);
		}
		
		if(result == 1) {
			resp.sendRedirect("/ssy/noticeboard/ntlist.do");
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>");
			writer.print("alert('문제가 발생했습니다.');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();
		}
		
	}
}