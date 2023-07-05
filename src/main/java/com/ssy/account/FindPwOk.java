package com.ssy.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssy.member.MemberDTO;

/**
 * 
 * @author 구대현
 * <p>입력받은 정보로 비밀번호 찾기를 시도하는 클래스입니다.</p>
 * 
 */
@WebServlet("/account/findpwok.do")
public class FindPwOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String jumin = "";
		String tel = req.getParameter("tel");

		jumin += req.getParameter("frontjumin");
		jumin += "-";
		jumin += req.getParameter("backjumin");

		AccountDTO adto = new AccountDTO();
		MemberDTO mdto = new MemberDTO();

		adto.setAiid(id);

		mdto.setMname(name);
		mdto.setMjumin(jumin);
		mdto.setMtel(tel);

		AccountDAO dao = new AccountDAO();

		String result = dao.findCorrectId(mdto);

		if (id.equals(result)) {

			HttpSession session = req.getSession();

			session.setAttribute("id", id);

			resp.sendRedirect("/ssy/account/resetpw.do");

		} else {
			
			PrintWriter writer = resp.getWriter();
			writer.print("<script>");
			writer.print("alert('잘못된 계정정보입니다. 다시 입력해주십시오.');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();	
			
			
		}

	}
}