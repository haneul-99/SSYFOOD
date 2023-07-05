package com.ssy.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author 구대현
 * <p>입력받은 정보로 비밀번호 재설정을 시도하는 클래스입니다.</p>
 * 
 */
@WebServlet("/account/resetpwok.do")
public class ResetPwOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		AccountDTO adto = new AccountDTO();
		
		adto.setAiid(id);
		adto.setAipw(pw);
		
		AccountDAO dao = new AccountDAO();
		
		PrintWriter writer = resp.getWriter();
		
		if(dao.getPw(id).equals(pw)) {
			writer.print("<script>");
			writer.print("alert('입력한 비밀번호가 현재 비밀번호와 같습니다.');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();	
			
		} else {
			int result = dao.resetPassword(id, pw);
			
		
		
		
		if(result == 1) {
			writer.print("<script>");
			writer.print("alert('비밀번호 변경이 완료되었습니다.');");
			writer.print("location.href='/ssy/account/index.do';");
			writer.print("</script>");
			writer.close();	
		} else {
			writer.print("<script>");
			writer.print("alert('잘못된 계정정보입니다. 다시 입력해주십시오.');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();	
		}
	}
		
	}
}
