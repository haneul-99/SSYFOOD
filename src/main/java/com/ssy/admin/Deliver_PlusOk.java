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
 * <p>택배사를 추가하는 클래스</p>
 *
 */
@WebServlet("/admin/deliverplusok.do")
public class Deliver_PlusOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		
		//1.
		req.setCharacterEncoding("UTF-8");
		
		String dname = req.getParameter("dname");
		String dsmall = req.getParameter("dsmall");
		String dmidium = req.getParameter("dmidium");
		String dlarge = req.getParameter("dlarge");
		String dxlarge = req.getParameter("dxlarge");
	
		//2.
		DeliverDTO d_dto = new DeliverDTO();
		
		d_dto.setDname(dname);
		d_dto.setDsmall(dsmall);
		d_dto.setDmidium(dmidium);
		d_dto.setDlarge(dlarge);
		d_dto.setDxlarge(dxlarge);
				
		
		AdminDAO dao = new AdminDAO();

		
		
		int result = dao.plus(d_dto);
		
		
		if(result == 1) {
			resp.sendRedirect("/ssy/admin/deliver.do");
		}else{
			PrintWriter writer = resp.getWriter();
			writer.print("<script>");
			writer.print("alert('failed');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();	
		
		}
		
		

	}

}
