package com.ssy.Faqboard;

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
 * <p>자주하는 질문의 글을 수정할때 사용하는 클래스입니다.</p>
 *
 */

@WebServlet("/faqboard/faqedit.do")
public class FaqEdit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//FaqEdit.java

		String fqseq = req.getParameter("seq");

		FaqDAO dao = new FaqDAO();
		
		FaqDTO dto = dao.get(fqseq);
		
		//System.out.println(fqseq);
		
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

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/faqedit.jsp");
		dispatcher.forward(req, resp);

	}
}