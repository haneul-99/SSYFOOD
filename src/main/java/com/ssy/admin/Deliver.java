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
 * <p>데이터베이스에 저장된 택배사를 보여주는 클래스</p>
 *
 */
@WebServlet("/admin/deliver.do")
public class Deliver extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		 String page = req.getParameter("page");
		
	      int nowPage = 0;   //현재 페이지 번호(=page)
	      int d_begin = 0;      //rnum
	      int d_end = 0;      //rnum
	      int pageSize = 10;   //한페이지 당 출력할 게시물 수
	      int totalCount = 0; //총 게시물 수
	      int totalPage = 0;   //총 페이지 수(총 게시물 수 / 한페이지 당 게시물 수)
	      
	      if (page == null || page == "") nowPage = 1;
	      else nowPage = Integer.parseInt(page);
	      
	      d_begin = ((nowPage - 1) * pageSize) + 1;
	      d_end = d_begin + pageSize - 1;
	      
	      
	      String d_column = req.getParameter("d_column");
	      String d_word = req.getParameter("d_word");
	      String d_isSearch = "n"; //n(목록), y(검색)
	      
	      if ((d_column == null || d_word == null) || (d_column == "" || d_word == "") ) {
	         d_isSearch = "n";
	      } else {
	         d_isSearch = "y";
	      }
	      
	      
	      HashMap<String,String> d_map = new HashMap<String,String>();
	      
	      d_map.put("d_column", d_column);
	      d_map.put("d_word", d_word);
	      d_map.put("d_isSearch", d_isSearch);
	      
	      d_map.put("d_begin", d_begin + "");
	      d_map.put("d_end", d_end + "");
	      
	      
	      HttpSession session = req.getSession();
	      
	      session.setAttribute("readcount", "n");
	      
	      AdminDAO d_dao = new AdminDAO();
	      
	      ArrayList<DeliverDTO> d_list = d_dao.d_list(d_map);
	      
	      totalCount = d_dao.d_getTotalCount(d_map);
	      
	      
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
	         pagebar += String.format(" <a href='/ssy/admin/deliver.do?page=%d'>◀</a> ",  n - 1);
	      }
	      
	      while (!(loop > blockSize || n > totalPage)) {
	         
	         
	         if (nowPage == n) {
	            pagebar += String.format(" <a href='#!' style='color: cornflowerblue;'>%d</a> ", n);
	         } else {
	            pagebar += String.format(" <a href='/ssy/admin/deliver.do?page=%d'>%d</a> "
	                                                         , n, n);
	         }
	         
	         loop++;
	         n++;
	         
	      }
	      
	      
	      if (n > totalPage) {
	         pagebar += String.format(" <a href='#!' style='cursor:not-allowed;'>▶</a> ");
	      } else {
	         pagebar += String.format(" <a href='/ssy/admin/deliver.do?page=%d'>▶</a> ", n);
	      }
	      
	      
	      
	      
	      String dseq = req.getParameter("dseq");
	     
	     
	      DeliverDTO d_dto = d_dao.getDeliver(dseq);
	    
	      req.setAttribute("d_dto", d_dto);
	      req.setAttribute("d_list", d_list); 
	      req.setAttribute("map", d_map);
	      req.setAttribute("totalCount", totalCount);
	      req.setAttribute("totalPage", totalPage);
	      req.setAttribute("nowPage", nowPage);
	      req.setAttribute("pagebar", pagebar);
	      
	   
	 
	      
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/deliver.jsp");
		dispatcher.forward(req, resp);

	}

}
