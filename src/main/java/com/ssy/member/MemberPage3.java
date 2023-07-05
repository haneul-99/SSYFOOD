package com.ssy.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 
 * @author 김동석
 * <p>멤버 페이지(찜목록)를 출력하는 클래스입니다.</p>
 *
 */
@WebServlet("/member/memberpage3.do")
public class MemberPage3 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		MemberDAO dao = new MemberDAO();
		ArrayList<ArrayList> jList = dao.getJjim((String)session.getAttribute("auth"));
		req.setAttribute("jList", jList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/memberpage3.jsp");
		dispatcher.forward(req, resp);

	}

}
