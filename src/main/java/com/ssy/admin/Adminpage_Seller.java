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
 * <p>판매자 정보를 보여주는 클래스</p>
 *
 */
@WebServlet("/admin/adminpageseller.do")
public class Adminpage_Seller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


	      //페이징
	      
	      String page = req.getParameter("page");
	     
	      int nowPage = 0;   //현재 페이지 번호(=page)
	      int begin = 0;      //rnum
	      int end = 0;      //rnum
	      int pageSize = 20;   //한페이지 당 출력할 게시물 수
	      int totalCount = 0; //총 게시물 수
	      int totalPage = 0;   //총 페이지 수(총 게시물 수 / 한페이지 당 게시물 수)
	      
	      if (page == null || page == "") nowPage = 1;
	      else nowPage = Integer.parseInt(page);
	      
	      begin = ((nowPage - 1) * pageSize) + 1;
	      end = begin + pageSize - 1;
	      
	      
	      String column = req.getParameter("column");
	      String word = req.getParameter("word");
	      String isSearch = "n"; //n(목록), y(검색)
	      
	      if ((column == null || word == null) || (column == "" || word == "") ) {
	         isSearch = "n";
	      } else {
	         isSearch = "y";
	      }
	      
	      
	      HashMap<String,String> smap = new HashMap<String,String>();
	      
	      smap.put("column", column);
	      smap.put("word", word);
	      smap.put("isSearch", isSearch);
	      
	      smap.put("begin", begin + "");
	      smap.put("end", end + "");
	      
	      
	      HttpSession session = req.getSession();
	      
	      session.setAttribute("readcount", "n");
	      
	      AdminDAO sdao = new AdminDAO();
	      ArrayList<AdminSellerDTO> slist = sdao.slist(smap);
	      
	      totalCount = sdao.getsTotalCount(smap);
	      totalPage = (int)Math.ceil((double)totalCount / pageSize);
	      String pagebar = ""; //페이지바 태그
	      
	      int blockSize = 10; //한번에 보여지는 페이지 수
	      int n = 0; //출력 페이지 번호
	      int loop = 0; //루프 변수
	      
	      
	      loop = 1;
	      n = ((nowPage - 1) / blockSize) * blockSize + 1;
	    
	      if (n == 1) {
	         pagebar += String.format(" <a href='#!' style='cursor: not-allowed;'>◀</a> ");
	      } else {
	         pagebar += String.format(" <a href='/ssy/admin/adminpageseller.do?page=%d'>◀</a> ",  n - 1);
	      }
	      
	      while (!(loop > blockSize || n > totalPage)) {
	         
	         
	         if (nowPage == n) {
	            pagebar += String.format(" <a href='#!' style='color: cornflowerblue;'>%d</a> ", n);
	         } else {
	            pagebar += String.format(" <a href='/ssy/admin/adminpageseller.do?page=%d'>%d</a> "
	                                                         , n, n);
	         }
	         
	         loop++;
	         n++;
	         
	      }
	      
	      
	      if (n > totalPage) {
	         pagebar += String.format(" <a href='#!' style='cursor:not-allowed;'>▶</a> ");
	      } else {
	         pagebar += String.format(" <a href='/ssy/admin/adminpageseller.do?page=%d'>▶</a> ", n);
	      }
	      
	      String sseq = req.getParameter("sseq");
	      
	      
	      
	      AdminSellerDTO sdto = sdao.sellerGet(sseq);
	      
	      req.setAttribute("sdto", sdto);
	      
	      //페이징, 게시판, 검색 페이지
	      req.setAttribute("slist", slist);      
	      req.setAttribute("smap", smap);
	      req.setAttribute("totalCount", totalCount);
	      req.setAttribute("totalPage", totalPage);
	      req.setAttribute("nowPage", nowPage);
	      req.setAttribute("pagebar", pagebar);
	       
	      

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/adminpageseller.jsp");
		dispatcher.forward(req, resp);

	}

}
