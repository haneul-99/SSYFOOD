package com.ssy.account;

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
 * @author 구대현
 * <p>로그아웃 클래스입니다.</p>
 * 
 */
@WebServlet("/account/logoutok.do")
public class LogoutOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 세션 해제
		// 피드백

		HttpSession session = req.getSession();

		session.removeAttribute("auth");
		session.removeAttribute("lv");
		session.removeAttribute("mname");
		session.removeAttribute("sname");
		session.removeAttribute("aname");
		session.removeAttribute("mseq");
		
		resp.sendRedirect("/ssy/index.do");
	}
}
