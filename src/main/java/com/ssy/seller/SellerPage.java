package com.ssy.seller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssy.product.ProductDTO;


/**
 * 
 * @author 유동환
 * <p>각 판매자가 판매자페이지에서 상품을 CRUD할수 있는 클래스입니다.</p>
 *
 */
@WebServlet("/seller/sellerpage.do")
public class SellerPage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		session.getAttribute("auth");
		
		String sSeq = req.getParameter("sSeq");
		
		ArrayList<ProductDTO> sList = new ArrayList<>();
		SellerDAO dao = new SellerDAO();
		String sName = "";
		
		sList = dao.productList(sSeq);
		sName = dao.getSName(sSeq);
		
		System.out.println(sList);
			
			req.setAttribute("sList", sList);
			req.setAttribute("sName", sName);
			

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/seller/sellerpage.jsp");
		dispatcher.forward(req, resp);
	}

}















