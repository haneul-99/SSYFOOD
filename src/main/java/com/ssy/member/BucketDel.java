package com.ssy.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/bucketdel.do")
public class BucketDel extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String bseq = req.getParameter("bseq");
		
		MemberDAO dao = new MemberDAO();
		
		HttpSession session = req.getSession();
		String mseq = (String)session.getAttribute("mseq");
		
		int result = dao.deleteBucket(bseq);
		
		if(result == 1) {
			resp.sendRedirect("/ssy/member/bucket.do?mseq=" + mseq);
			
		}
		
		

	}

}