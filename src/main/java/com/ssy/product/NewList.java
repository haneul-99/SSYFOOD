package com.ssy.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author 김하늘
 * <p>신상품 목록을 보여주는 클래스입니다.</p> 
 *
 */

@WebServlet("/product/newlist.do")
public class NewList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//NewList.java
		
		ProductDAO dao = new ProductDAO();
		
		ArrayList<ProductDTO> newlist = new ArrayList<>();
		newlist = dao.newList();
			
		req.setAttribute("newlist", newlist);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/product/newlist.jsp");
		dispatcher.forward(req, resp);

	}
}