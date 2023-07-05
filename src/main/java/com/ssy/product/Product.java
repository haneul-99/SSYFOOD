package com.ssy.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author 유동환
 * <p>등록된 상품들을 나열하는 클래스입니다.</p>
 *
 */
@WebServlet("/product/product.do")
public class Product extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		//검색
		
		String word = req.getParameter("word").trim();
		
		
		String cSeq = req.getParameter("cSeq");
		ProductDAO dao = new ProductDAO();
		
		HashMap<String,String> map = new HashMap<>();
		
		map.put("cSeq", cSeq);
		
		map.put("word", word);
		
		ArrayList<ProductDTO> pList = new ArrayList<>();
		pList = dao.productList(map);
		

		req.setAttribute("pList", pList);
		req.setAttribute("cSeq", cSeq);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/product/product.jsp");
		dispatcher.forward(req, resp);
	}
}
