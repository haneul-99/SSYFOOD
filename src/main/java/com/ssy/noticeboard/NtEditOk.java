package com.ssy.noticeboard;

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
 * @author 구대현
 * <p>공지사항 수정 결과를 저장하는 클래스입니다.</p>
 * 
 */
@WebServlet("/noticeboard/nteditok.do")
public class NtEditOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		
		String name = req.getParameter("name");
		String content = req.getParameter("content");
		String seq = req.getParameter("seq");
		String filename = req.getParameter("attach");
		
		NtBoardDTO dto = new NtBoardDTO();
		
		dto.setNname(name);
		dto.setNcontent(content);
		dto.setNseq(seq);
		dto.setFilename(filename);
		
		
		NtBoardDAO dao = new NtBoardDAO();
		
		int result = dao.edit(dto);

		if (result == 1) {
			resp.sendRedirect("/ssy/noticeboard/ntview.do?seq=" + seq);
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>");
			writer.print("alert('수정 실패!');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();			
		}
		
	}
}
