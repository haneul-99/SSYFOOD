package com.ssy.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssy.RandomDTO;
/**
 * 
 * @author 구대현
 * <p>입력받은 계정정보로 회원 로그인을 시도하는 클래스입니다.</p>
 * 
 */
@WebServlet("/account/memberloginok.do")
public class MemberLoginOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		 
		
	    
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String pseq = req.getParameter("pseq"); //김유진 작업 추가
		
		AccountDTO dto = new AccountDTO();
		
		dto.setAiid(id);
		dto.setAipw(pw);

		dto.setPseq(pseq); //김유진 작업 추가
		
		AccountDAO dao = new AccountDAO();


		PrintWriter writer = resp.getWriter();
		
		if(dao.existId(id) == false) {
			writer.print("<script>");
			writer.print("alert('존재하지 않는 아이디입니다.');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();	
		} else {
			//아이디 존재함
			if(!dao.getPw(id).equals(pw)) {
				writer.print("<script>");
				writer.print("alert('잘못된 비밀번호입니다.');");
				writer.print("history.back();");
				writer.print("</script>");
				writer.close();	
			} else {
			
				AccountDTO result = dao.memberLogin(dto);
				
				if(result != null) {
					
					HttpSession session = req.getSession();
					
					session.setAttribute("auth", id);
					session.setAttribute("lv", result.getAilv());
					session.setAttribute("mname", result.getMname());
					session.setAttribute("mseq", result.getMseq());
					
			
					//로그인기록 남기기
					dao.addLoginLog(id);
					
					//관리자, 판매자, 회원화면으로 넘기기
					if(pseq != null) {
						
						resp.sendRedirect("/ssy/product/productdetail.do?pseq=" + pseq);
					
					} else {
						
						resp.sendRedirect("/ssy/index.do");
					}
					
					
				}  else {
					writer.print("<script>");
					writer.print("alert('문제가 발생했습니다. 다시 시도해주십시오.');");
					writer.print("history.back();");
					writer.print("</script>");
					writer.close();	
				}
				
			}
		}
		
		
	}
}
