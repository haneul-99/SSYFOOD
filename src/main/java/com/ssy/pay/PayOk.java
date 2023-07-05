package com.ssy.pay;

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
 * @author 유동환
 * <p>상품을 결제하는 클래스입니다.</p>
 *
 */

@WebServlet("/pay/payok.do")
public class PayOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		String olname = req.getParameter("olname");
		String[] oladdress = req.getParameterValues("oladdress");
		int pcount= Integer.parseInt(req.getParameter("pcount"));
		String pseq = req.getParameter("pseq");
		int pdprice = Integer.parseInt(req.getParameter("pdprice"));
		
		PayDAO dao = new PayDAO();
		PayDTO dto = new PayDTO();
		
		
		
		dto.setOlAddress(oladdress[0] + " " + oladdress[1]);
		dto.setOlName(olname);
		dto.setMseq((String)session.getAttribute("mseq"));
		dto.setOdQuantity(pcount);
		dto.setPSeq(pseq);
		dto.setPdprice(pdprice);
		
		dao.payProduct(dto);
		
		String olseq = dao.getOlseq();
		
		dto.setOlSeq(olseq);
		
		
		int result = dao.addorderDetail(dto);
		
		
		if(result == 1 ) {
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			
			writer.print("<script>");
			writer.print("alert('결제가 완료되었습니다.  "
					+ "이용해주셔서 감사합니다.');");
			writer.print("location.href='/ssy/index.do';");
			writer.print("</script>");
			writer.close();
			
		} else {
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=UTF-8");
			
			PrintWriter writer = resp.getWriter();
			writer.print("<script>");
			writer.print("alert('잔액이 부족합니다.');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();
			
			
		}
		
		
		

	}

}