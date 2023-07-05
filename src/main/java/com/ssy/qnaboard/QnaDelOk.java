package com.ssy.qnaboard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author 김유진
 * <p>판매자 문의게시판에 자신이 작성한 게시글을 삭제하기 위한 클래스</p>
 *
 */

@WebServlet("/board/qnadelok.do")
public class QnaDelOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		req.setCharacterEncoding("UTF-8");
		
		String qseq = req.getParameter("qseq");
		String pseq = req.getParameter("pseq");
		
		System.out.println("qseq: " + qseq);
		QnaDAO dao = new QnaDAO();
		
		
		
		int result = dao.qnadel(qseq);
		
		
		if (result == 1) {
			resp.sendRedirect("/ssy/product/productdetail.do?pseq=" + pseq);
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>");
			writer.print("alert('failed');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();			
		}
		
		
	}

}
