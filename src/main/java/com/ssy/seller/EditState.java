package com.ssy.seller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 
 * @author 유동환
 * <p>판매자가 주문내역에서 주문상태를 수정할는 클래스</p>
 *
 */
@WebServlet("/seller/editstate.do")
public class EditState extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String olstate = req.getParameter("olstate");
		String olseq = req.getParameter("olseq");
		
		HttpSession session = req.getSession();
		String sseq = (String)session.getAttribute("sseq");
		HashMap<String,String> map = new HashMap<>();
		SellerDAO dao = new SellerDAO();
		map.put("olseq", olseq);
		map.put("olstate", olstate);
		int result = dao.editState(map);
		
		if(result == 1) {
			
			resp.sendRedirect("/ssy/seller/orderprocess.do?sSeq=" + sseq);
			
		}else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>");
			writer.print("alert('failed');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();
			
		}
		
		
		
	}

}