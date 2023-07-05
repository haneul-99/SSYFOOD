package com.ssy.seller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 
 * @author 유동환
 * <p>판매자의 매출관리 클래스입니다.</p>
 *
 */

@WebServlet("/seller/salesmanage.do")
public class SalesManage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		SellerDAO dao = new SellerDAO();
		
		HttpSession session = req.getSession();
		String sseq = (String)session.getAttribute("sseq");
		
		
		//총 매출
		ArrayList<ChartDTO> cList = dao.listChart(sseq);
		
		//오늘 매출
		String todaySales = dao.todaySales(sseq);
		String yesterdaySales = dao.yesterdaySales(sseq);
		
		String totalSales = dao.totalPrice(sseq);
	    req.setAttribute("totalSales", totalSales);
		
		//어제 매출
		req.setAttribute("cList", cList);
		req.setAttribute("todaySales", todaySales);
		req.setAttribute("yesterdaySales", yesterdaySales);
		
		
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/seller/salesmanage.jsp");
		dispatcher.forward(req, resp);
	}

}