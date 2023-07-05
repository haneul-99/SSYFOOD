package com.ssy.Faqboard;

import java.io.IOException;
import java.io.PrintWriter;

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
 * <p>자주하는 질문에 글을 추가할 때 사용하는 클래스입니다.</p>
 */

@WebServlet("/faqboard/faqadd.do")
public class FaqAdd extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//FaqAdd.java
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("auth") == null) {
			PrintWriter writer = resp.getWriter();
			
			writer.print("<script>");
			writer.print("alert('failed');");
			writer.print("location.href='/ssy/index.do';");
			writer.print("</script>");
			writer.close();
			return;
		} 

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/faqadd.jsp");
		dispatcher.forward(req, resp);

	}
}