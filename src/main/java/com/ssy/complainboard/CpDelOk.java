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
 * <p>문의게시판의 글을 삭제하면 데이터베이스의 정보도 삭제하는 클래스입니다.</p>
 */

@WebServlet("/complainboard/cpdelok.do")
public class CpDelOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		req.setCharacterEncoding("UTF-8");
		
		String cbseq = req.getParameter("seq");
		
		//System.out.println(cbseq);

		CpDAO dao = new CpDAO();
		
		dao.delCommentAll(cbseq);
		
		int result = dao.del(cbseq);
	

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