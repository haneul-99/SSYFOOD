package com.ssy.complainboard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author 김하늘
 * <p>관리자가 문의게시판에 답변을 등록하면 데이터베이스에 정보를 추가하는 메소드입니다.</p>
 *
 */


@WebServlet("/complainboard/cpcommentaddok.do")
public class CpCommentAddOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	      req.setCharacterEncoding("UTF-8");
	      
	      String cacontent = req.getParameter("cacontent");
	      String cbseq = req.getParameter("cbseq");
	      String aseq = req.getParameter("aseq");
	      
	      CpCommentDTO dto = new CpCommentDTO();
	      
	      dto.setCacontent(cacontent);
	      dto.setCbseq(cbseq);
	      dto.setAseq(aseq);
	      
	      System.out.println(dto);
	      
	      CpDAO dao = new CpDAO();
	      
	      int result = dao.addComment(dto);
	      
	      System.out.println(dto);
	      
	      if (result == 1) {
	    	  resp.sendRedirect("/ssy/complainboard/cpview.do?cbseq=" + cbseq);
	      } else {
	         PrintWriter writer = resp.getWriter();
	         
	         writer.print("<script>");
	         writer.print("alert('failed');");
	         writer.print("history.back();");
	         writer.print("</script>");
	         writer.close();
	      }

	}
}