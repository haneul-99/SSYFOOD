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

import com.ssy.qnaboard.QnaDAO;
import com.ssy.qnaboard.QnaDTO;

/**
 * 
 * @author 김유진
 * <p>판매자 문의게시글 클릭하면 보이는 내용을 담은 view 클래스</p>
 *
 */

@WebServlet("/seller/sellerboardview.do")
public class SellerBoardView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		session.getAttribute("auth");
		
		String sseq = (String)session.getAttribute("sseq");
		String pseq = req.getParameter("pseq");
	    String qtitle = req.getParameter("qtitle");
	    
	    String qcontent = req.getParameter("qcontent");
	    String pname = req.getParameter("pname");
	    String mname = req.getParameter("mname");
	    
	    String qseq = req.getParameter("qseq");
		
	    
	    
		SellerDAO dao = new SellerDAO();
		
		QnaDTO dto = new QnaDTO();
		
		
		
		req.setAttribute("dto", dto);
		req.setAttribute("pseq", pseq);
		req.setAttribute("qtitle", qtitle);
		req.setAttribute("qcontent", qcontent);
		req.setAttribute("qseq", qseq);
		req.setAttribute("pname", pname);
		req.setAttribute("mname", mname);
		
		
		ArrayList<QnaDTO> list = new ArrayList<>();
		
		
		
		list = dao.getQnaList(sseq);
		
		
		req.setAttribute("list", list);
		req.setAttribute("sseq", sseq);
		
		
		  
	    		
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/seller/sellerboardview.jsp");
		dispatcher.forward(req, resp);

	}

}
