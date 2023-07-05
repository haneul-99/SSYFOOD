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
 * <p>추천상품 목록을 보여주는 클래스입니다.</p>
 *
 */

@WebServlet("/product/recommendlist.do")
public class RecommendList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ProductDAO dao = new ProductDAO();
		
		ArrayList<ProductDTO> relist = new ArrayList<>();
		
		
		relist = dao.recommendList();
		
		//System.out.println(relist);
			
		req.setAttribute("relist", relist);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/product/recommendlist.jsp");
		dispatcher.forward(req, resp);

	}
}