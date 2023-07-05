package com.ssy.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author 박민수
 * <p>판매자 개인정보와 계정정보를 수정하는 클래스</p>
 *
 */
@WebServlet("/admin/adminpagesellereditok.do")
public class AdminPageSellerEditOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		   
		String sseq = req.getParameter("sseq");
		String sname = req.getParameter("sname");
		String stel = req.getParameter("stel");
		String saddress = req.getParameter("saddress");
		String sinfo = req.getParameter("sinfo");
		String sstore = req.getParameter("sstore");
		
		
		
		String aiseq =req.getParameter("aiseq");
		String aiid = req.getParameter("aiid");
		String aipw = req.getParameter("aipw");
		
		AdminSellerDTO dto = new AdminSellerDTO();

		
		dto.setSseq(sseq);
		dto.setSname(sname);
		dto.setSaddress(saddress);
		dto.setSstore(sstore);
		dto.setSinfo(sinfo);
		dto.setStel(stel);
		
		
		dto.setAiseq(aiseq);
		dto.setAiid(aiid);
		dto.setAipw(aipw);
		
	
		
		AdminDAO dao = new AdminDAO();
	
		
		
		int result  = dao.sedit(dto);

		
		if (result == 1) {
			
		resp.sendRedirect("/ssy/admin/adminpageseller.do");
			
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>");
			writer.print("alert('failed');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();			
		}

	}

	}



