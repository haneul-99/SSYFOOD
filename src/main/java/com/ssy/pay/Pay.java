package com.ssy.pay;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssy.member.MemberDTO;
import com.ssy.product.ProductDAO;
import com.ssy.product.ProductDTO;

@WebServlet("/pay/pay.do")
public class Pay extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		ProductDAO dao = new ProductDAO();
		ProductDTO dto = new ProductDTO();
		
		
		String pseq = req.getParameter("pseq");
		HttpSession session = req.getSession();
		
		String mseq = (String)session.getAttribute("mseq"); 
		dto = dao.ProductDetail(pseq);
		
		MemberDTO mdto = dao.getMember(mseq); 
		
		req.setAttribute("dto", dto);
		req.setAttribute("mdto", mdto);
		
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/pay/pay.jsp");
		dispatcher.forward(req, resp);

	}

}
