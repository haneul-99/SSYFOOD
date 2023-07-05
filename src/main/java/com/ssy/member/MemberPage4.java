package com.ssy.member;

import java.io.IOException;

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
 * <p>멤버 페이지(개인정보 수정)를 출력하는 클래스입니다.</p>
 *
 */
@WebServlet("/member/memberpage4.do")
public class MemberPage4 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();

		MemberDAO dao = new MemberDAO();

		MemberDTO dto = dao.getMember((String)session.getAttribute("auth"));
		
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/memberpage4.jsp");
		dispatcher.forward(req, resp);

	}

}
