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
 * <p>장바구니 클래스입니다.</p>
 *
 */

@WebServlet("/member/bucket.do")
public class Bucket extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		MemberDAO dao = new MemberDAO();
		BucketDTO dto = new BucketDTO();
		HttpSession session = req.getSession();
		
		ArrayList<BucketDTO> bList = new ArrayList<BucketDTO>();
		String mseq = (String)session.getAttribute("mseq"); 
		String pseq = (String)session.getAttribute("pseq"); 
		
		bList = dao.bucketList(mseq);
		req.setAttribute("bList", bList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/bucket.jsp");
		dispatcher.forward(req, resp);
	}

}