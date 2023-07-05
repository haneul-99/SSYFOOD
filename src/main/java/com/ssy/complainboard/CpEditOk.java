package com.ssy.complainboard;

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
 * <p>문의게시판의 글을 수정하면 데이터베이스의 정보도 수정하는 클래스입니다.</p>
 *
 */

@WebServlet("/complainboard/cpeditok.do")
public class CpEditOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		req.setCharacterEncoding("UTF-8");
		
		String cbtitle = req.getParameter("cbtitle");
		String cbcontent = req.getParameter("cbcontent");
		String cbseq = req.getParameter("cbseq");

		CpDTO dto = new CpDTO();
		
		dto.setCbtitle(cbtitle);
		dto.setCbcontent(cbcontent);
		dto.setCbseq(cbseq);
		
		System.out.println(dto);
		
		CpDAO dao = new CpDAO();
		
		int result = dao.edit(dto);
		
		System.out.println(cbtitle);
		
		if (result == 1) {
			resp.sendRedirect("/ssy/complainboard/cplist.do");
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