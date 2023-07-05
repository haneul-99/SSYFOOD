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
 * 
 * @author 82104
 * <p>판매자 문의게시판에 자신이 작성한 게시글을 수정하기 위한 클래스</p>
 *
 */

@WebServlet("/board/qnaeditok.do")
public class QnaEditOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		HttpSession session = req.getSession();
		
		req.setCharacterEncoding("UTF-8");
		
		String qseq = req.getParameter("qseq");
		String qtitle = req.getParameter("qtitle");
		String qcontent = req.getParameter("qcontent");
		String qdate = req.getParameter("qdate");
		String qsecret = req.getParameter("qsecret");
		
		String mseq = req.getParameter("mseq");
		String pseq = req.getParameter("pseq");
		
		System.out.println(qcontent);
		System.out.println(qtitle);
		System.out.println(qseq);
		System.out.println(pseq);
		QnaDTO dto = new QnaDTO();
		
		dto.setQseq(qseq);
		dto.setQtitle(qtitle);
		dto.setQcontent(qcontent);
		dto.setQdate(qdate);
		dto.setQsecret(qsecret);
		
		dto.setMseq(mseq);
		dto.setPseq(pseq);
		
		QnaDAO dao = new QnaDAO();
		
		int result = dao.qnaedit(dto);
		
		
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
