package com.ssy.Faqboard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author 김하늘
 * <p>관리자가 자주하는 질문의 글을 삭제하면 데이터베이스의 정보도 삭제하는 클래스입니다.</p>
 */

@WebServlet("/faqboard/faqdelok.do")
public class FaqDelOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//FaqDelOk.java

		req.setCharacterEncoding("UTF-8");
		
		String fqseq = req.getParameter("seq");
		
		//System.out.println(fqseq);

		FaqDAO dao = new FaqDAO();
		
		int result = dao.del(fqseq);
	

		if (result == 1) {
			resp.sendRedirect("/ssy/faqboard/faqlist.do");
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