package com.ssy.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author 박민수
 * <p>판매자 개인정보와 계정정보를 자세히 보여주는 클래스</p>
 *
 */
@WebServlet("/admin/adminpagesellerdetail.do")
public class AdminPageSellerDetail extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		
		String sname = req.getParameter("sname");
		String stel = req.getParameter("stel");
		String sstore = req.getParameter("sstore");
		String sinfo = req.getParameter("sinfo");
		String saddress = req.getParameter("saddress");
		String sseq = req.getParameter("sseq");
		String aiid = req.getParameter("aiid");
		String aiseq = req.getParameter("aiseq");
		String aipw = req.getParameter("aipw");
		
	
		AdminSellerDTO dto = new AdminSellerDTO();

		dto.setSname(sname);
		dto.setStel(stel);
		dto.setSstore(sstore);
		dto.setSseq(sseq);
		dto.setSinfo(sinfo);
		dto.setSaddress(saddress);
		dto.setAiid(aiid);
		dto.setAiseq(aiseq);
		dto.setAipw(aipw);
		
		
		req.setAttribute("dto", dto);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/adminpagesellerdetail.jsp");
		dispatcher.forward(req, resp);

	}

}
