package com.ssy.seller;

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
 * @author 유동환
 * <p>판매자가 본인의 상품에 대한 주문내역을 확인할 수 있는 클래스입니다.</p>
 *
 */
@WebServlet("/seller/orderprocess.do")
public class OrderProcess extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//등록된 상품 select 처리
		
		
		HttpSession session = req.getSession();
//		상품번호, 상품이름, 회원이름, 회원 주소, 주문날짜, 주문처리상태
		String sseq = (String)session.getAttribute("sseq");
		
		SellerDAO dao = new SellerDAO();
		
		
		
		//페이징관련 작업
				String page = req.getParameter("page");
				
				int nowPage = 0;		//현재 페이지 번호
				int begin = 0;			//rnum
				int end = 0;			//rnum
				int pageSize = 20;		//한페이지 당 출력할 게시물 수
				int totalCount = 0;		//총 게시물 수 
				int totalPage = 0;		//총 페이지 수(총 게시물 수 / 한 페이지 당 게시물 수)
				
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
				
				ArrayList<OrderDTO> oList = dao.orderList(sseq, map);
				
			
		
				totalCount = dao.getTotalCount(sseq);
				totalPage = (int)Math.ceil((double)totalCount / pageSize);
				
				String pagebar = ""; //페이지바 태그
				
				int blockSize = 10; //한번에 보여지는 페이지바 수
				int n = 0; //출력 페이지 번호
				int loop = 0; //루프 변수
		
				loop = 1;
				n = ((nowPage -1) / blockSize) * blockSize + 1;
		
		
				if(n == 1) {
					pagebar += String.format("<a href='#!' style='cursor:not-allowed'><i class=\"fa-solid fa-ellipsis\"></i></a>", n-1);
				} else {
					pagebar += String.format("<a href='/ssy/seller/orderprocess.do?page=%d'><i class=\"fa-solid fa-ellipsis\"></i></a>", n-1);
				}
				
				while (!(loop > blockSize || n > totalPage)) {
					
					
					if (nowPage == n) {
						
						pagebar += String.format(" <a href='#!' style='color:cornflowerblue;'>%d</a> ", n,n);
						
					} else {
						
						pagebar += String.format(" <a href='/ssy/seller/orderprocess.do?page=%d'>%d</a> ", n,n);
					
					}
					loop++;
					n++;
				}
				
				
				if(n > totalPage) {
					pagebar += String.format("<a href='#!' style='cursor:not-allowed;'><i class=\"fa-solid fa-ellipsis\"></i></a>", n);
				} else {
					pagebar += String.format("<a href='/ssy/seller/orderprocess.do?page=%d'><i class=\"fa-solid fa-ellipsis\"></i></a>", n);
					
				}
				
				
				//2.
				req.setAttribute("oList", oList);
				req.setAttribute("map", map);
				req.setAttribute("totalCount", totalCount);
				req.setAttribute("totalPage", totalPage);
				
				req.setAttribute("nowPage", nowPage);
				
				req.setAttribute("pagebar", pagebar);
				
		
		
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/seller/orderprocess.jsp");
		dispatcher.forward(req, resp);
	}

}