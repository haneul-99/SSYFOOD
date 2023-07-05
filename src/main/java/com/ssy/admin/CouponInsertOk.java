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
 * <p>관리자가 쿠폰을 등록하는 클래스</p>
 *
 */
@WebServlet("/admin/couponinsertok.do")
public class CouponInsertOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	HttpSession session = req.getSession();
		
		
		//1.
		req.setCharacterEncoding("UTF-8");
		
		String coseq = req.getParameter("coseq");
		String coname = req.getParameter("coname");
		String codiscount = req.getParameter("codiscount");
		String cocontent = req.getParameter("cocontent");
		String codate = req.getParameter("codate");
		
		
		//2.
		CouponDTO codto = new CouponDTO();
		
		
		codto.setCoseq(coseq);
		codto.setConame(coname);
		codto.setCodiscount(codiscount);
		codto.setCocontent(cocontent);
		codto.setCodate(codate);
		
		
				
		
		AdminDAO dao = new AdminDAO();
		
		int result = dao.insert(codto);
	
		if (result == 1) {
			resp.sendRedirect("/ssy/admin/couponinsert.do");
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
