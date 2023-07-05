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
 * <p>회원이 문의게시판에 글을 등록하면 데이터베이스에 정보를 추가하는 메소드입니다.</p>
 *
 */

@WebServlet("/complainboard/cpaddok.do")
public class CpAddok extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//CpAddOk.java

		req.setCharacterEncoding("UTF-8");
		
		String cbtitle = req.getParameter("cbtitle");
		String cbcontent = req.getParameter("cbcontent");
		String cbsecret = req.getParameter("cbsecret");
		
		String mseq = req.getParameter("mseq");
		//String aseq = req.getParameter("aseq");

		
		CpDAO dao = new CpDAO();

		CpDTO dto = new CpDTO();
		
		dto.setCbtitle(cbtitle);
		dto.setCbcontent(cbcontent);
		dto.setMseq(mseq);
		dto.setCbsecret(cbsecret);
		
		
		System.out.println(dto);

		int result = dao.add(dto);

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