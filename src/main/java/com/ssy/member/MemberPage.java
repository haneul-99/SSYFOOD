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
 * <p>멤버 페이지(포인트 쿠폰함)를 출력하는 클래스입니다.</p>
 *
 */
@WebServlet("/member/memberpage.do")
public class MemberPage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		MemberDAO dao = new MemberDAO();

		MemberDTO dto = dao.getMember((String)session.getAttribute("auth"));
		ArrayList<String> sflist = dao.getFollower((String)session.getAttribute("auth"));
		ArrayList<ArrayList> coList = dao.getCoupon((String)session.getAttribute("auth"));
		
		
		req.setAttribute("dto", dto);
		req.setAttribute("list", sflist);
		req.setAttribute("coList", coList);
		req.setAttribute("listlen", coList.size());

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/memberpage.jsp");
		dispatcher.forward(req, resp);

	}

}