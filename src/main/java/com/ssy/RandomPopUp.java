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

import com.ssy.member.MemberDTO;

/**
 * 
 * @author 구대현
 * <p>랜덤박스 상품을 가져오고 회원의 현재 보유 포인트를 확인해 구매처리를 하는 클래스입니다.</p>
 * 
 */
@WebServlet("/randompopup.do")
public class RandomPopUp extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");		
		
		String mseq = req.getParameter("mseq");
		String point = req.getParameter("mpoint");
		
		if(Integer.parseInt(point)>=10000) {
			req.setAttribute("enough", "Y");
		} else {
			req.setAttribute("enough", "N");
		}
		
		
		RandomDAO dao = new RandomDAO();
		RandomDTO dto = new RandomDTO();


		String[] rewardNum = { "1", "2", "3", "4", "5", "6", "7", "8", "8", "8", "8", "8", "8", "8", "8", "8", "8", "8",
				"8", "8", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9",
				"9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9",
				"9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9",
				"9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9", "9" };

		Random rnd = new Random();

		dto = dao.getReward(rewardNum[rnd.nextInt(100)]);

		MemberDTO mdto = dao.getMember(mseq);

		req.setAttribute("dto", dto);
		req.setAttribute("mdto", mdto);
		
		//---------------------------------------
		
		int mpoint = mdto.getMpoint();
		
		
		if(mpoint>=10000) {	
			dao.usePoint(mseq);		
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/randompopup.jsp");
		dispatcher.forward(req, resp);

		
	}
}