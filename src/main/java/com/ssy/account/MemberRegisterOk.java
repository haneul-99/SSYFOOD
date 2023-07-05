package com.ssy.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssy.member.MemberDTO;

/**
 * 
 * @author 김하늘
 * <p>회원 회원가입 처리를 완료하는 클래스입니다.</p>
 *
 */

@WebServlet("/account/memberregisterok.do")
public class MemberRegisterOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//RegisterOk.java
		
		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String jumin = req.getParameter("jumin");
		String tel = req.getParameter("tel");
		String address = req.getParameter("address");
		
		AccountDAO dao = new AccountDAO();
	    AccountDTO adto = new AccountDTO();
	    
	    adto.setAiid(id);
	    adto.setAipw(pw);
	    
	    int aResult = dao.addAccount(adto,"1");
	    
	    MemberDTO mdto = new MemberDTO();
	    
	    mdto.setMname(name);
	    mdto.setMjumin(jumin);
	    mdto.setMaddress(address);
	    mdto.setMtel(tel);
	    
	    System.out.println(mdto);
	    System.out.println(adto);
	    
   
	    int mResult = dao.addMember(mdto);
	    
	    if(aResult == 1 && mResult == 1) {
	         
	         resp.sendRedirect("/ssy/index.do");
	         
	    } else {
	         
	    	PrintWriter writer = resp.getWriter();
	        writer.print("<script>");
	        writer.print("alert('failed');");
	        writer.print("history.back();");
	        writer.print("</script>");
	        writer.close();
	    }
	}
}
