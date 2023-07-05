package com.ssy.account;

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
 * @author 구대현
 * <p>입력받은 계정정보로 관리자 로그인을 시도하는 클래스입니다.</p>
 * 
 */
@WebServlet("/account/adminloginok.do")
public class AdminLoginOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
	    
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		AccountDTO dto = new AccountDTO();
		
		dto.setAiid(id);
		dto.setAipw(pw);
		
		AccountDAO dao = new AccountDAO();
		
		AccountDTO result = dao.adminLogin(dto);
		
		if(result != null) {
			
			HttpSession session = req.getSession();
			
			session.setAttribute("auth", id);
			session.setAttribute("lv", result.getAilv());
			session.setAttribute("aname", result.getAname());
			session.setAttribute("aseq", result.getAseq());
			
			//로그인기록 남기기
			dao.addLoginLog(id);
			
			//관리자, 판매자, 회원화면으로 넘기기
			resp.sendRedirect("/ssy/index.do");	
			
		}  else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>");
			writer.print("alert('잘못된 계정정보입니다. 다시 입력해주십시오.');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();	
		}
	}
}
