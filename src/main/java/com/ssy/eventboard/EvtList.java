package com.ssy.eventboard;

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
 * @author 김하늘
 * <p>이벤트 게시판 목록을 보여주는 클래스입니다.</p>
 *
 */

@WebServlet("/eventboard/evtlist.do")
public class EvtList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	    String page = req.getParameter("page");
	      
	    int nowPage = 0;    
	    int begin = 0;     
	    int end = 0;     
	    int pageSize = 10;   
	    int totalCount = 0;   
	    int totalPage = 0;   
	      
	    if (page == null || page == "") nowPage = 1;
	    else nowPage = Integer.parseInt(page);
	      
	    begin = ((nowPage - 1) * pageSize) + 1;
	    end = begin + pageSize - 1;

	      
	    String column = req.getParameter("column");
	    String word = req.getParameter("word");
	    String isSearch = "n"; 
	      
	    if ((column == null || word == null) || (column == "" || word == "")) {
	       isSearch = "n";
	    } else {
	       isSearch = "y";
	    }
	      
	      
	    HashMap<String, String> map = new HashMap<String, String>();
	      
	    map.put("column", column);
	    map.put("word", word);
	    map.put("isSearch", isSearch);
	      
	    map.put("begin", begin + "");
	    map.put("end", end + "");
	      
	      
	    HttpSession session = req.getSession();
	      
	    session.setAttribute("ereadcount", "n");

	    EvtBoardDAO dao = new EvtBoardDAO();
	        
	    ArrayList<EvtBoardDTO> list = dao.list(map);

	    for (EvtBoardDTO dto : list) {
	    	
	    	
	        String edate = dto.getEdate();
	        
	        edate = edate.substring(0, 10);
	        
	        dto.setEdate(edate);
	        
	        
	        String ename = dto.getEname();
	        ename = ename.replace("<", "&lt;").replace(">", "&gt;");
	         
	        
	        if (isSearch.equals("y") && column.equals("etitle")) {
	           
	        	ename = ename.replace(word, "<span style=\"background-color: #F7FF43;\">" + word + "</span>");
	           
	          dto.setEname(ename);
	           
	        }
	        
	     }
	      
	      
	    totalCount = dao.getTotalCount(map);
	    totalPage = (int)Math.ceil((double)totalCount / pageSize);
	      
	    String pagebar = ""; 
	      
	    int blockSize = 10; 
	    int n = 0; 
	    int loop = 0; 
	      
	    loop = 1;
	    n = ((nowPage - 1) / blockSize) * blockSize + 1;
	      
	      
	    if(n == 1) {
	    	 pagebar += String.format(" <a href='#!' style='cursor: not-allowed; color: #999; font-size: 13px;'>◀</a> ");
	    } else {
	    	 pagebar += String.format(" <a href='/toy/board/list.do?page=%d'>︎︎◀</a> ", n - 1);
	    }
	      
	    while(!(loop > blockSize || n > totalPage)) {
	    	  
	    	 if (nowPage == n) {
	    	  	 pagebar += String.format(" <a href='#!' style='color: cornflowerblue;'>%d</a> ", n);
	    	 } else {
	    	     pagebar += String.format(" <a href='/toy/board/list.do?page=%d'>%d</a> ", n, n);
	    	 }
	    	 loop++;
	    	 n++;
	    }
	      
	      
	    if(n > totalPage) {
	    	 pagebar += String.format(" <a href='#!' style='cursor: not-allowed; color: #999; font-size: 13px;'>▶</a> ");
	    } else {
	    	 pagebar += String.format(" <a href='/toy/board/list.do?page=%d'>▶</a> ", n);
	    }
	      
	       
	    req.setAttribute("list", list);
	      
	    req.setAttribute("map", map);
	      
	    req.setAttribute("totalCount", totalCount);
	    req.setAttribute("totalPage", totalPage);
	      
	    req.setAttribute("nowPage", nowPage);
	      
	    req.setAttribute("pagebar", pagebar);

		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/evtlist.jsp");
		dispatcher.forward(req, resp);

	}
}
