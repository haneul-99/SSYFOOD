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
 * <p>아이디 중복검사 확인하는 클래스입니다.</p>
 *
 */
@WebServlet("/account/registerid.do")
public class RegisterId extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");

		AccountDAO dao = new AccountDAO();
		
		int result = dao.checkid(req.getParameter("id"));
		
        
        PrintWriter writer = resp.getWriter();
        
//        writer.print("{");
//        writer.printf("\"use\": %d", result);
//        writer.print("}");
        
        writer.print(result);
       
        writer.close();
	}
}