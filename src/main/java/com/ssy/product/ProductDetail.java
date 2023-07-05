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
import javax.servlet.http.HttpSession;

/**
 * 
 * @author 김유진
 * <p>등록된 상품들의 상세정보 클래스</p>
 *
 */
@WebServlet("/product/productdetail.do")
public class ProductDetail extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		ProductDAO dao = new ProductDAO();
		ProductDTO dto2 = new ProductDTO();
		ProductDTO dto = new ProductDTO();
		
		String aiseq = req.getParameter("aiseq");
		dto2 = dao.getId(aiseq);
				
		String pseq = req.getParameter("pseq");
		dto = dao.ProductDetail(pseq);
		
		
		
		//페이징
		String page = req.getParameter("page");
				
		int nowPage = 0;		//현재 페이지 번호(=page)
		int begin = 0;			//rnum
		int end = 0;			//rnum
		int pageSize = 5;		//한 페이지 당 출력할 게시물 수
		int totalCount = 0;		//총 게시물 수
		int totalPage = 0;		//총 페이지 수(총 게시물 수 / 한 페이지당 게시물 수)
				
		if (page == null || page == "") nowPage = 1;
		else nowPage = Integer.parseInt(page);
				
				//list.do?page=1 > where rnum between 1 and 10
				//list.do?page=2 > where rnum between 11 and 20
				//list.do?page=3 > where rnum between 21 and 30
				
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;
		
		
		
		HashMap<String,String> map = new HashMap<String,String>();
		
		map.put("begin", begin + "");
		map.put("end", end + "");
		
		
		
		
		ArrayList<ProductDTO> qnaList = dao.getqnaList(pseq, map);
		
		for (ProductDTO pdto : qnaList) {
			
			//날짜 자르기
			String qdate = pdto.getQdate();
			
			qdate = qdate.substring(0, 10);
			
			pdto.setQdate(qdate);
			
			
			
			
		}
		
		//총 게시물 수
		//총 페이지 수
		totalCount = dao.getTotalCount(pseq);
		totalPage = (int)Math.ceil((double)totalCount / pageSize);
				
				
				String pagebar = ""; //페이지바 태그
				
				int blockSize = 10; //한번에 보여지는 페이지 수
				int n = 0;			//출력될 페이지 번호
				int loop = 0;		//루프 변수
				
				
				
		loop = 1;
		n = ((nowPage - 1) / blockSize) * blockSize + 1;
		
		
		if (n == 1) {
			pagebar += String.format(" <a href='#!' style='cursor: not-allowed;'>[이전 5페이지]</a> ");
		} else {
			pagebar += String.format(" <a href='/ssy/product/productdetail.do?page=%d&pseq=%s&aiseq=%s'>[이전 5페이지]</a> ", n - 1, pseq, aiseq);
		}
		
		while (!(loop > blockSize || n > totalPage)) {
			
			if (nowPage == n) {
				
				pagebar += String.format(" <a href='#!' style='color: cornflowerblue;'>%d</a> ", n);
			
			} else {
			
				pagebar += String.format(" <a href='/ssy/product/productdetail.do?page=%d&pseq=%s&aiseq=%s'>%d</a> ", n, pseq, aiseq ,n);
			
			}
			loop++;
			n++;
			
		}
		
		if (n > totalPage) {
			pagebar += String.format(" <a href='!#' style='cursor: not-allowed;'>[다음 5페이지]</a> ", n);
		} else {
			pagebar += String.format(" <a href='/ssy/product/productdetail.do?page=%d&pseq=%s&aiseq=%s'>[다음 5페이지]</a> ", n, pseq, aiseq);			
		}		
				
		
		
		
		
		req.setAttribute("map", map);
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("pagebar", pagebar);
		
		req.setAttribute("dto", dto);
		req.setAttribute("dto2", dto2);
		
		req.setAttribute("qnaList", qnaList);
	
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/product/productdetail.jsp");
		dispatcher.forward(req, resp);

	}

}
