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
 * <p>멤버 페이지(주문내역)를 출력하는 클래스입니다.</p>
 *
 */
@WebServlet("/member/memberpage1.do")
public class MemberPage1 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		MemberDAO dao = new MemberDAO();
		ArrayList<MemberDTO> olList = dao.orderList((String)session.getAttribute("auth"));
		

		req.setAttribute("olList", olList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/memberpage1.jsp");
		dispatcher.forward(req, resp);
		

	}

}
