package com.ssy.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 
 * @author 유동환
 * <p>회원이 찜목록에서 상품을 삭제하는 클래스입니다.</p>
 *
 */

@WebServlet("/member/jjimdelok.do")
public class JjimDelOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	req.setCharacterEncoding("UTF-8");
		
		String jseq = req.getParameter("jseq");
		
		MemberDAO dao = new MemberDAO();
		
		HttpSession session = req.getSession();
		String mseq = (String)session.getAttribute("mseq");
		
		int result = dao.deleteJjim(jseq);
		
		if(result == 1) {
			resp.sendRedirect("/ssy/member/jjim.do?mseq=" + mseq);
			
		}

	}

}