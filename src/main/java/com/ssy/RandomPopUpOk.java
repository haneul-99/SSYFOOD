package com.ssy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssy.member.MemberDTO;

/**
 * 
 * @author 구대현
 * <p>랜덤박스 추첨 결과를 전송하는 클래스입니다.</p>
 * 
 */
@WebServlet("/randompopupok.do")
public class RandomPopUpOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String mseq = req.getParameter("mseq");
		String rbseq = req.getParameter("rbseq")
;		String maddress = req.getParameter("maddress");
		
		RandomDAO dao = new RandomDAO();
		
		String content = dao.getcontent(rbseq);
		
	
		req.setAttribute("mseq", mseq);
		req.setAttribute("content", content);
		req.setAttribute("maddress", maddress);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/randompopupok.jsp");
		dispatcher.forward(req, resp);
		
	}
}