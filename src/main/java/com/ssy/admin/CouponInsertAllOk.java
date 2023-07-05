package com.ssy.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * 
 * @author 박민수
 * <p> 회원에게 쿠폰을 전체 지급하는 클래스</p>
 *
 */
@WebServlet("/admin/couponinsertallok.do")
public class CouponInsertAllOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String coseq = req.getParameter("coseq");
		String aiseq = req.getParameter("aiseq");
		
		CouponDTO dto = new CouponDTO();
		
		dto.setAiseq(aiseq);
		dto.setCoseq(coseq);
		
		System.out.println(aiseq);
		
		AdminDAO dao = new AdminDAO();
		
		 int result = dao.InsertAllCoupon(coseq,aiseq);
		 
		 if (result == 0) {
				
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
