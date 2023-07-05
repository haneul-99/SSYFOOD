package com.ssy.member;

import java.io.IOException;
import java.util.ArrayList;

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
 * <p>회원의 찜목록을 출력하는 클래스입니다.</p>
 *
 */

@WebServlet("/member/jjim.do")
public class Jjim extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	
		
		MemberDAO dao = new MemberDAO();
		BucketDTO dto = new BucketDTO();
		
		HttpSession session = req.getSession();
		
		ArrayList<JjimDTO> jList = new ArrayList<JjimDTO>();
		String mseq = (String)session.getAttribute("mseq"); 
		
		
		
		
		jList = dao.jjimList(mseq);
		req.setAttribute("jList", jList);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/jjim.jsp");
		dispatcher.forward(req, resp);
	}

}