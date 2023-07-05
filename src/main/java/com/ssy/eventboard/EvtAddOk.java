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
 * <p>관리자가 이벤트 게시판에 글을 등록하면 데이터베이스에 정보를 추가하는 메소드입니다.</p>
 *
 */

@WebServlet("/eventboard/evtaddok.do")
public class EvtAddOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String ename = req.getParameter("ename");
		String econtent = req.getParameter("econtent");
		
		EvtBoardDAO dao = new EvtBoardDAO();

		EvtBoardDTO dto = new EvtBoardDTO();
		
		dto.setEname(ename);
		dto.setEcontent(econtent);

		int result = dao.add(dto);

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