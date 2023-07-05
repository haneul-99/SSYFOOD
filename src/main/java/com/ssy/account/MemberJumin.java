package com.ssy.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author 김하늘
 * <p>회원 주민등록번호 중복검사하는 클래스입니다.</p>
 *
 */
@WebServlet("/account/memberjumin.do")
public class MemberJumin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//MemberJumin.java

		AccountDAO dao = new AccountDAO();
		
		int result = dao.checkjumin(req.getParameter("jumin"));

		resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        
        PrintWriter writer = resp.getWriter();
        
        writer.print("{");
        writer.printf("\"use\": %d", result);
        writer.print("}");
        
        System.out.println(result);
        
        writer.close();

	}
}
