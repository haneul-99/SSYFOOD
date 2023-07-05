package com.ssy.seller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssy.qnaboard.QnaDTO;

/**
 * 
 * @author 김유진
 * <p>판매자 마이페이지에서 회원이 작성한 판매자 문의게시글을 담은 클래스</p>
 *
 */

@WebServlet("/seller/sellerboardpage.do")
public class SellerBoardPage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		HttpSession session = req.getSession();
		session.getAttribute("auth");
		
		String sseq = (String)session.getAttribute("sseq");
		
		
		SellerDAO dao = new SellerDAO();
		
		
		
		ArrayList<QnaDTO> list = new ArrayList<>();
		
		
		
		list = dao.getQnaList(sseq);
		
		
		req.setAttribute("list", list);
		req.setAttribute("sseq", sseq);
		
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/seller/sellerboardpage.jsp");
		dispatcher.forward(req, resp);

	}

}
