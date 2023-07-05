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
@WebServlet("/member/memberpageok.do")
public class MemberPageOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// EditOk.java
		// 1. 데이터 가져오기
		// 2. DB 작업 > DAO 위임 > update
		// 3. 결과
		// 4. 피드백

		HttpSession session = req.getSession();

		// 1.
		req.setCharacterEncoding("UTF-8");

		String pw = req.getParameter("pw");
		String pwCheck = req.getParameter("pwCheck");
		String address = req.getParameter("address");
		String aiseq = req.getParameter("aiseq");

		// 2.
		if (pwCheck.equals(pw) && pwCheck.length() >= 8 && pwCheck.length() <= 16) {
			MemberDTO dto = new MemberDTO();

			dto.setMaddress(address);
			dto.setPw(pwCheck);
			dto.setAiseq(aiseq);
			

			MemberDAO dao = new MemberDAO();
			

			int result = dao.edit(dto, (String)session.getAttribute("auth"));
			int result1 = dao.edit1(dto, aiseq);

			if (result*result1 == 1) {
				resp.sendRedirect("/ssy/member/memberpage.do");
				
			} else {
				PrintWriter writer = resp.getWriter();
				writer.print("<script>");
				writer.print("alert('failed');");
				writer.print("history.back();");
				writer.print("</script>");
				writer.close();
			}

		}
		// 4.

	}

}
