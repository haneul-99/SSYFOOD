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
 * @author 유동환
 * <p>판매자가 등록된 상품을 수정할때 사용하는 클래스입니다.</p>
 *
 */
@WebServlet("/product/productedit.do")
public class ProductEdit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		String pseq = req.getParameter("pSeq");
		String pImage = req.getParameter("pImage");
		String sseq = req.getParameter("sSeq");
		
		ProductDAO dao = new ProductDAO();
		ProductDTO dto = new ProductDTO();
		
		dto = dao.productDetail(pseq);
		ArrayList<String> hList = new ArrayList<>();
		
		hList = dao.getTag(pseq);
		
		String list = hList.toString().substring(1,hList.toString().length()-1);
		req.setAttribute("list", list);
		
		req.setAttribute("dto", dto);
		
		
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/product/productedit.jsp");
		dispatcher.forward(req, resp);
	}

}









