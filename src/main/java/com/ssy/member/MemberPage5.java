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
 * <p>멤버 페이지(댓글관리)를 출력하는 클래스입니다.</p>
 *
 */
@WebServlet("/member/memberpage5.do")
public class MemberPage5 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		MemberDAO dao = new MemberDAO();

		ArrayList<ArrayList> qList = dao.getQna((String)session.getAttribute("auth"));
		
		System.out.println(qList);
		
		req.setAttribute("qList", qList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/memberpage5.jsp");
		dispatcher.forward(req, resp);

	}

}
