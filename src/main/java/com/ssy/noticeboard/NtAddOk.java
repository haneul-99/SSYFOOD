package com.ssy.noticeboard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * 
 * @author 구대현
 * <p>공지사항 작성 결과를 저장하는 클래스입니다.</p>
 * 
 */
@WebServlet("/noticeboard/ntaddok.do")
public class NtAddOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		MultipartRequest multi = null;
		
		
		String path = req.getRealPath("/asset/files");
		int size = 1024 * 1024 * 100;
	
		multi = new MultipartRequest(
						req,
						path,
						size,
						"UTF-8",
						new DefaultFileRenamePolicy()
		);
		
		String name = multi.getParameter("name");
		String content = multi.getParameter("content");
		String filename = multi.getFilesystemName("attach");
		
		
		//2.
		NtBoardDTO dto = new NtBoardDTO();
		
		dto.setNname(name);
		dto.setNcontent(content);
		dto.setFilename(filename);

		
		NtBoardDAO dao = new NtBoardDAO();
		
		int result = dao.add(dto);

		
		//4.
		if (result == 1) {
			resp.sendRedirect("/ssy/noticeboard/ntlist.do");
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>");
			writer.print("alert('문제가 발생했습니다. 다시 시도해주세요.(add_failed)');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();			
		}
		
	}
}
