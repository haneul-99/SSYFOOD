package com.ssy.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author 구대현
 * <p>설문조사 클래스입니다.</p>
 * 
 */
@WebServlet("/admin/survey.do")
public class Survey extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String seq = req.getParameter("mseq");
		
		AdminDAO adao = new AdminDAO();
		
		boolean newSurvey = adao.isNewSurvey(seq);
		
		
		req.setAttribute("newsurvey", newSurvey);
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/survey.jsp");
		dispatcher.forward(req, resp);

	}
}
