package com.ssy.qnaboard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author 김유진
 * <p>판매자 문의게시판에 게시글을 추가하는 클래스</p>
 *
 */

@WebServlet("/board/qnaaddok.do")
public class QnaAddOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//QnaAddOk.java
		//1. 인코딩 + 데이터 가져오기
		//2. DB 작업 > DAO 위임 > insert
		//3. 결과
		//4. 피드백
		
		
		HttpSession session = req.getSession();
		
		//1.
		req.setCharacterEncoding("UTF-8");
		
		String qseq = req.getParameter("qseq");
		String qtitle = req.getParameter("qtitle");
		String qcontent = req.getParameter("qcontent");
		String qdate = req.getParameter("qdate");
		String qsecret = req.getParameter("qsecret");
		
		String mseq = req.getParameter("mseq");
		String pseq = req.getParameter("pseq");
		
		
		QnaDAO dao = new QnaDAO();
		
		
		
		//2.
		QnaDTO dto = new QnaDTO();
		
		dto.setQseq(qseq);
		dto.setQtitle(qtitle);
		dto.setQcontent(qcontent);
		dto.setQdate(qdate);
		dto.setQsecret(qsecret);
		
		dto.setMseq(mseq);
		dto.setPseq(pseq);
		
		
		//System.out.println(dto);
		
		
		int result = dao.add(dto);
		
		if (result == 1) {
	         resp.sendRedirect("/ssy/product/productdetail.do?pseq=" +pseq);
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
