package com.ssy.admin;

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
 * @author 박민수
 * <p>회원 개인정보와 계정정보를 수정하는 클래스</p>
 *
 */
@WebServlet("/admin/adminpageeditok.do")
public class AdminPage_EditOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		

		HttpSession session = req.getSession();
		
		
		//1.
		req.setCharacterEncoding("UTF-8");
		
		String mseq = req.getParameter("mseq");
		String mname = req.getParameter("mname");
		String maddress = req.getParameter("maddress");
		String aiid = req.getParameter("aiid");
		String aipw = req.getParameter("aipw");
		String aiseq = req.getParameter("aiseq");
		String mtel = req.getParameter("mtel");
		
		//2.
		AdminDTO dto = new AdminDTO();
		
		
		dto.setMseq(mseq);
		dto.setMname(mname);
		dto.setMaddress(maddress);
		dto.setAiid(aiid);
		dto.setAipw(aipw);
		dto.setAiseq(aiseq);
		dto.setMtel(mtel);
				
		
		AdminDAO dao = new AdminDAO();
		
		int result = dao.edit(dto);
	
		if (result == 1) {
			resp.sendRedirect("/ssy/admin/adminpage.do");
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
