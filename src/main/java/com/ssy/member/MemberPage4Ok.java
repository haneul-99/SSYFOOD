package com.ssy.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 
 * @author 김동석
 * <p>멤버 페이지(개인정보 수정)의 수정사항을 처리하는 클래스입니다.</p>
 *
 */
@WebServlet("/member/memberpage4ok.do")
public class MemberPage4Ok extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		MemberDAO dao = new MemberDAO();
		int result = dao.MemberX((String)session.getAttribute("auth"));
		
		
		if(result==1) {
			session.removeAttribute("auth");
			session.removeAttribute("lv");
			session.removeAttribute("mname");
			session.removeAttribute("sname");
			session.removeAttribute("aname");
			resp.sendRedirect("/ssy/index.do");
		}else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>");
			writer.print("alert('failed');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();
		}

	}

}

