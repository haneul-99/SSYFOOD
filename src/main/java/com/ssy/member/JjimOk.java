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
 * <p>회원이 찜목록에 상품을 등록하는 클래스입니다.</p>
 *
 */
@WebServlet("/member/jjimok.do")
public class JjimOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String pseq = req.getParameter("pseq");
	
		HttpSession session = req.getSession();
		String mseq = (String)session.getAttribute("mseq"); 
		
		MemberDAO dao = new MemberDAO();
		JjimDTO dto = new JjimDTO();
		
		dto.setMseq(mseq);
		dto.setPseq(pseq);
		
		dao.addJjim(dto);
		
		
		resp.sendRedirect("/ssy/product/productdetail.do?pseq=" + pseq);
	}

}
