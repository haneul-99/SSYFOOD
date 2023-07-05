package com.ssy.eventboard;

import java.io.IOException;

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
 * <p>이벤트 게시판 내용을 보여주는 클래스 입니다.</p>
 *
 */

@WebServlet("/eventboard/evtview.do")
public class EvtView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		String eseq = req.getParameter("eseq");
		    
		System.out.println(eseq);
		      
		String isSearch = req.getParameter("isSearch");
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		        
		EvtBoardDAO dao = new EvtBoardDAO();
		    
		if (session.getAttribute("ereadcount") == null || session.getAttribute("ereadcount").toString().equals("n")) {
			dao.addReadCount(eseq);
			
			session.setAttribute("ereadcount", "y");
			
		}
		      

		EvtBoardDTO dto = dao.get(eseq);
		        
		//System.out.println(dto);
		    
		String econtent = dto.getEcontent(); 
		String ename = dto.getEname();
		        
		      
		econtent = econtent.replace("<", "&lt;").replace(">", "&gt;");
		ename = ename.replace("<", "&lt;").replace(">", "&gt;");
		        
		dto.setEname(ename); 

		econtent = econtent.replace("\r\n", "<br>");
		        
		dto.setEcontent(econtent);
		   
		if (isSearch != null && column != null) {
		    if (isSearch.equals("y") && column.equals("content")) {
		         
		    	econtent = econtent.replace(word, "<span style='background-color: gold; color: tomato;'>" + word + "</span>");
		        dto.setEcontent(econtent);
		        }
		}
		
		dto.setEcontent(econtent);

		req.setAttribute("dto", dto);
		       
		req.setAttribute("isSearch", isSearch);
		req.setAttribute("column", column);
		req.setAttribute("word", word);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/evtview.jsp");
		dispatcher.forward(req, resp);

	}
}
