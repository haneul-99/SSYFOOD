package com.ssy.eventboard;

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
 * <p>이벤트 게시판의 글을 수정하면 데이터베이스의 정보도 수정하는 클래스입니다.</p>
 *
 */
@WebServlet("/eventboard/evteditok.do")
public class EvtEditOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String ename = req.getParameter("ename");
		String econtent = req.getParameter("econtent");
		String eseq = req.getParameter("eseq");
		
		EvtBoardDTO dto = new EvtBoardDTO();
		
		dto.setEname(ename);
		dto.setEcontent(econtent);
		dto.setEseq(eseq);
		
		//System.out.println(dto);
		
		EvtBoardDAO dao = new EvtBoardDAO();
		
		int result = dao.edit(dto);
		
		//4.
		if (result == 1) {
			resp.sendRedirect("/ssy/eventboard/evtlist.do");
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
