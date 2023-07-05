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
 * <p>멤버 페이지(쿠폰함)의 쿠폰사용내역을 출력하는 클래스입니다.</p>
 *
 */
@WebServlet("/member/couponuse.do")
public class CouponUse extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		MemberDAO dao = new MemberDAO();

		ArrayList<ArrayList> coList = dao.getCoupon((String)session.getAttribute("auth"));

		req.setAttribute("coList", coList);
		System.out.println(coList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/couponuse.jsp");
		dispatcher.forward(req, resp);

	}

}

