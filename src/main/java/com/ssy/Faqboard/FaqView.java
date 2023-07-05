package com.ssy.Faqboard;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author 김하늘
 * <p>자주하는 질문의 내용을 보여주는 클래스 입니다.</p>
 *
 */

@WebServlet("/faqboard/faqview.do")
public class FaqView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//FaqView.java
	      
	    String fqseq = req.getParameter("fqseq");
	    
	    //System.out.println(fqseq);
	      
	    String isSearch = req.getParameter("isSearch");
	    String column = req.getParameter("column");
	    String word = req.getParameter("word");

	    FaqDAO dao = new FaqDAO();

	    FaqDTO dto = dao.get(fqseq);
	        
	    //System.out.println(dto);
	    
	    String fqcontent = dto.getFqcontent(); 
	    String fqtitle = dto.getFqtitle();
	        
	      
	    fqcontent = fqcontent.replace("<", "&lt;").replace(">", "&gt;");
	    fqtitle = fqtitle.replace("<", "&lt;").replace(">", "&gt;");
	        
	    dto.setFqtitle(fqtitle); 

	    fqcontent = fqcontent.replace("\r\n", "<br>");
	        
	    dto.setFqcontent(fqcontent);
	   
	    if (isSearch != null && column != null) {
	       if (isSearch.equals("y") && column.equals("content")) {
	         
	    	  fqcontent = fqcontent.replace(word, "<span style='background-color: gold; color: tomato;'>" + word + "</span>");
	          dto.setFqcontent(fqcontent);
	       }
	    }
	         
	    dto.setFqcontent(fqcontent);

	    req.setAttribute("dto", dto);
	       
	    req.setAttribute("isSearch", isSearch);
	    req.setAttribute("column", column);
	    req.setAttribute("word", word);
	      
	      
	    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/faqview.jsp");
	    dispatcher.forward(req, resp);


	}
}
