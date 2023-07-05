package com.ssy.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 
 * @author 박민수
 * <p>회원 개인정보와 계정정보를 삭제하는 클래스</p>
 *
 */
@WebServlet("/admin/adminpagedelok.do")
public class AdminPage_DelOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();

		String mseq = req.getParameter("mseq");
		
		
		
		//2.
		AdminDAO dao = new AdminDAO();
	
		
		int result = dao.del(mseq);
		
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

