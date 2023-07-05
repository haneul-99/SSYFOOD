package com.ssy.admin;

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
 * @author 구대현
 * <p>설문조사 결과를 저장하는 클래스입니다.</p>
 * 
 */
@WebServlet("/admin/surveyok.do")
public class SurveyOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		String q1 = req.getParameter("q1");
		String q2 = req.getParameter("q2");
		String q3 = req.getParameter("q3");
		String q4 = req.getParameter("q4");
		String q5 = req.getParameter("q5");
		String q6 = req.getParameter("q6");
		String mseq = req.getParameter("mseq");
		String newSurvey = req.getParameter("newsurvey");

		SurveyDTO sdto = new SurveyDTO();

		System.out.println(newSurvey);

		sdto.setSurQ1(q1);
		sdto.setSurQ2(q2);
		sdto.setSurQ3(q3);
		sdto.setSurQ4(q4);
		sdto.setSurQ5(q5);
		sdto.setSurQ6(q6);
		sdto.setMseq(mseq);

		AdminDAO dao = new AdminDAO();

		PrintWriter writer = resp.getWriter();

		int result = 0;

		if (newSurvey.equals("false")) {
			result = dao.updateSurvey(sdto);
			System.out.println("update");
		} else {
			result = dao.saveSurvey(sdto);
			System.out.println("save");
		}

		if (result == 1) {
			writer.print("<script>");
			writer.print("alert('설문조사가 완료되었습니다.');");
			writer.print("location.href='/ssy/index.do';");
			writer.print("</script>");
			writer.close();
		} else {
			writer.print("<script>");
			writer.print("alert('문제가 발생했습니다. 다시 시도해주세요.(survey_failed)');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();
		}

	}
}
