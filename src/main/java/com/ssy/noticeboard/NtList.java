package com.ssy.noticeboard;

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

import com.ssy.admin.AdminDAO;

/**
 * 
 * @author 구대현
 * <p>공지사항 리스트를 조회하는 클래스입니다.</p>
 * 
 */
@WebServlet("/noticeboard/ntlist.do")
public class NtList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String page = req.getParameter("page");
		
		int nowPage = 0;	//현재 페이지 번호(=page)
		int begin = 0;		//rnum
		int end = 0;		//rnum
		int pageSize = 8;	//한페이지 당 출력할 게시물 수
		int totalCount = 0; //총 게시물 수
		int totalPage = 0;	//총 페이지 수(총 게시물 수 / 한페이지 당 게시물 수)
		
		if (page == null || page == "") nowPage = 1;
		else nowPage = Integer.parseInt(page);
		
		//list.do?page=1 > where rnum between 1 and 8
		//list.do?page=2 > where rnum between 9 and 16
		//list.do?page=3 > where rnum between 17 and 24
		
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;
		
		String column = req.getParameter("column");
		String word = req.getParameter("word");

		
		
		HashMap<String,String> map = new HashMap<String,String>();
		
		map.put("column", column);
		map.put("word", word);
		
		
		
		map.put("begin", begin + "");
		map.put("end", end + "");
			
		HttpSession session = req.getSession();
		
		session.setAttribute("nreadcount", "n");
		
		
		
		//1.
		NtBoardDAO dao = new NtBoardDAO();
		
		ArrayList<NtBoardDTO> list = dao.list(map);
		
		
		//1.5
		//- 데이터 조작
		for (NtBoardDTO dto : list) {
			
			//날짜 자르기
			String ndate = dto.getNdate();
			
			ndate = ndate.substring(0, 10);
			
			dto.setNdate(ndate);
			
			
			//태그 이스케이프
			String nname = dto.getNname();
			nname = nname.replace("<", "&lt;").replace(">", "&gt;");
			
			
			//제목 자르기			
			if (nname.length() > 40) {
				nname = nname.substring(0, 40);
				dto.setNname(nname + "..");
			}
			

			
		}
		
		
		
		
		
		
		//총 게시물 수
		//총 페이지 수
		
		//364 / 10 = 36
		

	    totalCount = dao.getTotalCount();
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
	         pagebar += String.format(" <a href='/ssy/noticeboard/ntlist.do?page=%d'>◀</a> ",  n - 1);
	      }
	      
	      while (!(loop > blockSize || n > totalPage)) {
	    	  
	         
	         if (nowPage == n) {
	            pagebar += String.format(" <a href='#!' style='color: cornflowerblue;'>%d</a> ", n);
	         } else {
	            pagebar += String.format(" <a href='/ssy/noticeboard/ntlist.do?page=%d'>%d</a> "
	                                                         , n, n);
	         }
	         
	         loop++;
	         n++;
	         
	      }
	      
	      
	      if (n > totalPage) {
	         pagebar += String.format(" <a href='#!' style='cursor:not-allowed;'>▶</a> ");
	      } else {
	         pagebar += String.format(" <a href='/ssy/noticeboard/ntlist.do?page=%d'>▶</a> ", n);
	      }
		
		
		//2.
		req.setAttribute("list", list);		
		req.setAttribute("map", map);
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		
		req.setAttribute("nowPage", nowPage);
		
		req.setAttribute("pagebar", pagebar);
		
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/ntlist.jsp");
		dispatcher.forward(req, resp);

	}
}