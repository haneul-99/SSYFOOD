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
 * <p>회원의 보유 포인트를 보여주는 클래스</p>
 *
 */
@WebServlet("/admin/pointselect.do")
public class PointSelect extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		 String page = req.getParameter("page");
		
	      int nowPage = 0;   //현재 페이지 번호(=page)
	      int begin = 0;      //rnum
	      int end = 0;      //rnum
	      int pageSize = 5;   //한페이지 당 출력할 게시물 수
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
	      
	      
	      
	      HashMap<String,String> pomap = new HashMap<String,String>();
	      
	      pomap.put("column", column);
	      pomap.put("word", word);
	      pomap.put("isSearch", isSearch);
	    
	      pomap.put("begin", begin + "");
	      pomap.put("end", end + "");
	   
	      
	      HttpSession session = req.getSession();
	      
	      session.setAttribute("readcount", "n");
	      
	      AdminDAO dao = new AdminDAO();
	      ArrayList<PointDTO> polist = dao.polist(pomap);
	      
	      totalCount = dao.pogetTotalCount(pomap);
	      totalPage = (int)Math.ceil((double)totalCount / pageSize);
	      String pagebar = ""; //페이지바 태그
	      
	      int blockSize = 5; //한번에 보여지는 페이지 수
	      int n = 0; //출력 페이지 번호
	      int loop = 0; //루프 변수
	      
	      
	      loop = 1;
	      n = ((nowPage - 1) / blockSize) * blockSize + 1;
	      System.out.println(n);
	    
	      if (n == 1) {
	         pagebar += String.format(" <a href='#!' style='cursor: not-allowed;'>◀</a> ");
	      } else {
	         pagebar += String.format(" <a href='/ssy/admin/pointselect.do?page=%d'>◀</a> ",  n - 1);
	      }
	      
	      while (!(loop > blockSize || n > totalPage)) {
	         
	         
	         if (nowPage == n) {
	            pagebar += String.format(" <a href='#!' style='color: cornflowerblue;'>%d</a> ", n);
	         } else {
	            pagebar += String.format(" <a href='/ssy/admin/pointselect.do?page=%d'>%d</a> "
	                                                         , n, n);
	         }
	         
	         loop++;
	         n++;
	         
	      }
	      
	      
	      if (n > totalPage) {
	         pagebar += String.format(" <a href='#!' style='cursor:not-allowed;'>▶</a> ");
	      } else {
	         pagebar += String.format(" <a href='/ssy/admin/pointselect.do?page=%d'>▶</a> ", n);
	      }
	      
	      
	      
	      
	      String mseq = req.getParameter("mseq");
	     
	     
	      PointDTO podto = dao.getPoint(mseq);
	    
	      req.setAttribute("podto", podto);
	      req.setAttribute("polist", polist); 
	      req.setAttribute("pomap", pomap);
	      req.setAttribute("totalCount", totalCount);
	      req.setAttribute("totalPage", totalPage);
	      req.setAttribute("nowPage", nowPage);
	      req.setAttribute("pagebar", pagebar);
	      

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/pointselect.jsp");
		dispatcher.forward(req, resp);

	}

}
