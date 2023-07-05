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
 * <p>입력받은 정보로 아이디 찾기를 시도하는 클래스입니다.</p>
 * 
 */
@WebServlet("/account/findidok.do")
public class FindIdOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		String name = req.getParameter("name");
		String jumin = "";

		jumin += req.getParameter("frontjumin");
		jumin += "-";
		jumin += req.getParameter("backjumin");
		
		
		MemberDTO mdto = new MemberDTO();

		mdto.setMname(name);
		mdto.setMjumin(jumin);
		
		AccountDAO dao = new AccountDAO();

		String result = dao.findId(mdto);
		
		if (result != null) {

			HttpSession session = req.getSession();
			
			session.setAttribute("name", name);
			session.setAttribute("id", result);

			String log = dao.getLoginLog(dao.getSeq(result));
			
			session.setAttribute("log", log);
			
			resp.sendRedirect("/ssy/account/showfindid.do");

		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>");
			writer.print("alert('해당 정보로 회원가입한 아이디가 존재하지 않습니다.');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();
		}

	}
}
