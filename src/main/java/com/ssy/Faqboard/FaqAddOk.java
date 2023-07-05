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
 * <p>관리자가 자주하는 질문에 글을 등록하면 데이터베이스에 정보를 추가하는 메소드입니다.</p>
 *
 */

@WebServlet("/faqboard/faqaddok.do")
public class FaqAddOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//FaqAddOk.java
		
		req.setCharacterEncoding("UTF-8");
		
		String fqtitle = req.getParameter("fqtitle");
		String fqcontent = req.getParameter("fqcontent");
		
		FaqDAO dao = new FaqDAO();

		FaqDTO dto = new FaqDTO();
		
		dto.setFqtitle(fqtitle);
		dto.setFqcontent(fqcontent);

		int result = dao.add(dto);

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