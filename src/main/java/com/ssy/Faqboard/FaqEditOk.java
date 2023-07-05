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
 * <p>자주하는 질문의 글을 수정하면 데이터베이스의 정보도 수정하는 클래스입니다.</p>
 *
 */

@WebServlet("/faqboard/faqeditok.do")
public class FaqEditOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//FaqEditOk.java

		req.setCharacterEncoding("UTF-8");
		
		String fqtitle = req.getParameter("fqtitle");
		String fqcontent = req.getParameter("fqcontent");
		String fqseq = req.getParameter("fqseq");
		
		FaqDTO dto = new FaqDTO();
		
		dto.setFqtitle(fqtitle);
		dto.setFqcontent(fqcontent);
		dto.setFqseq(fqseq);
		
		//System.out.println(dto);
		
		FaqDAO dao = new FaqDAO();
		
		int result = dao.edit(dto);

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
