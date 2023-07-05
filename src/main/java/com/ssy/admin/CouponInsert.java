package com.ssy.admin;

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
 * @author 박민수
 * <p>쿠폰을 추가하는 클래스</p>
 *
 */
@WebServlet("/admin/couponinsert.do")
public class CouponInsert extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		 String page = req.getParameter("page");
		
	      int nowPage = 0;   //현재 페이지 번호(=page)
	      int cobegin = 0;      //rnum
	      int coend = 0;      //rnum
	      int pageSize = 5;   //한페이지 당 출력할 게시물 수
	      int totalCount = 0; //총 게시물 수
	      int totalPage = 0;   //총 페이지 수(총 게시물 수 / 한페이지 당 게시물 수)
	      
	      if (page == null || page == "") nowPage = 1;
	      else nowPage = Integer.parseInt(page);
	      
	      cobegin = ((nowPage - 1) * pageSize) + 1;
	      coend = cobegin + pageSize - 1;
	      
	     
	      
	      HashMap<String,String> comap = new HashMap<String,String>();
	      
	    
	      comap.put("cobegin", cobegin + "");
	      comap.put("coend", coend + "");
	   
	      
	      HttpSession session = req.getSession();
	      
	      session.setAttribute("readcount", "n");
	      
	      AdminDAO codao = new AdminDAO();
	      ArrayList<CouponDTO> colist = codao.colist(comap);
	      
	      totalCount = codao.cogetTotalCount(comap);
	      totalPage = (int)Math.ceil((double)totalCount / pageSize);
	      String pagebar = ""; //페이지바 태그
	      
	      int blockSize = 5; //한번에 보여지는 페이지 수
	      int n = 0; //출력 페이지 번호
	      int loop = 0; //루프 변수
	      
	      
	      loop = 1;
	      n = ((nowPage - 1) / blockSize) * blockSize + 1;
	     
	    
	      if (n == 1) {
	         pagebar += String.format(" <a href='#!' style='cursor: not-allowed;'>◀</a> ");
	      } else {
	         pagebar += String.format(" <a href='/ssy/admin/couponinsert.do?page=%d'>◀</a> ",  n - 1);
	      }
	      
	      while (!(loop > blockSize || n > totalPage)) {
	         
	         
	         if (nowPage == n) {
	            pagebar += String.format(" <a href='#!' style='color: cornflowerblue;'>%d</a> ", n);
	         } else {
	            pagebar += String.format(" <a href='/ssy/admin/couponinsert.do?page=%d'>%d</a> "
	                                                         , n, n);
	         }
	         
	         loop++;
	         n++;
	         
	      }
	      
	      
	      if (n > totalPage) {
	         pagebar += String.format(" <a href='#!' style='cursor:not-allowed;'>▶</a> ");
	      } else {
	         pagebar += String.format(" <a href='/ssy/admin/couponinsert.do?page=%d'>▶</a> ", n);
	      }
	      
	      
	      
	      
	      String coseq = req.getParameter("coseq");
	     
	      String aiseq = req.getParameter("aiseq");
	     
	      CouponDTO codto = codao.getCoupon(coseq);
	      AdminDTO dto = codao.get(aiseq);
	      
	    
	      
	      
	      req.setAttribute("dto",dto);
	      req.setAttribute("codto", codto);
	      req.setAttribute("colist", colist); 
	      req.setAttribute("map", comap);
	      req.setAttribute("totalCount", totalCount);
	      req.setAttribute("totalPage", totalPage);
	      req.setAttribute("nowPage", nowPage);
	      req.setAttribute("pagebar", pagebar);
	      
	 

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/couponinsert.jsp");
		dispatcher.forward(req, resp);

	}

}
