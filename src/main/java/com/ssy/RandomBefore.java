package com.ssy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author 구대현
 * <p>메인화면에서 랜덤박스 팝업으로 이동하는 클래스입니다.</p>
 * 
 */
@WebServlet("/randombefore.do")
public class RandomBefore extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String mseq = req.getParameter("mseq");
		
		RandomDAO dao = new RandomDAO();
		
		String mpoint = dao.getPoint(mseq);
		
		req.setAttribute("mpoint", mpoint);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/randombefore.jsp");
		dispatcher.forward(req, resp);

	}
}