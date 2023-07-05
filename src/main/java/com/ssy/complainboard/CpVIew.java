package com.ssy.complainboard;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author 김하늘
 * <p>작성된 문의게시판의 내용과 답변을 보여주는 클래스입니다.</p>
 *
 */

@WebServlet("/complainboard/cpview.do")
public class CpVIew extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String cbseq = req.getParameter("cbseq");
	    
	    //System.out.println(cbseq);
	      
	    String isSearch = req.getParameter("isSearch");
	    String column = req.getParameter("column");
	    String word = req.getParameter("word");
	        
	    //2. 
	    CpDAO dao = new CpDAO();

	    CpDTO dto = dao.get(cbseq);
	        
	    //System.out.println(dto);
	    
	    String cbcontent = dto.getCbcontent(); 
	    String cbtitle = dto.getCbtitle();
	    //System.out.println(dto.getCbcontent());
	      
	    cbcontent = cbcontent.replace("<", "&lt;").replace(">", "&gt;");
	    cbtitle = cbtitle.replace("<", "&lt;").replace(">", "&gt;");
	        
	    dto.setCbtitle(cbtitle); 

	    cbcontent = cbcontent.replace("\r\n", "<br>");
	        
	    dto.setCbcontent(cbcontent);
	   
	    if (isSearch != null && column != null) {
	       if (isSearch.equals("y") && column.equals("content")) {
	         
	    	  cbcontent = cbcontent.replace(word, "<span style='background-color: gold; color: tomato;'>" + word + "</span>");
	          dto.setCbcontent(cbcontent);
	       }
	    }
	         
	    dto.setCbcontent(cbcontent);
	    
	    ArrayList<CpCommentDTO> clist = dao.clist(cbseq);

	    req.setAttribute("dto", dto);
	       
	    req.setAttribute("isSearch", isSearch);
	    req.setAttribute("column", column);
	    req.setAttribute("word", word);
	    
	    req.setAttribute("clist", clist);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/cpview.jsp");
		dispatcher.forward(req, resp);

	}
}