package com.ssy.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssy.product.ProductDAO;

/**
 * 
 * @author 유동환
 * <p>장바구니에 담은 상품들의 결제전 주문정보를 확인 클래스입니다.</p>
 *
 */

@WebServlet("/pay/bucketpay.do")
public class BucketPay extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		String totalprice = req.getParameter("totalprice").trim().replace(",", "");
		String[] pcount = req.getParameterValues("pcount");
		String[] pseq = req.getParameterValues("pseq");
		
		HttpSession session = req.getSession();
		String mseq = (String)session.getAttribute("mseq");
		
		ArrayList<String> countList = new ArrayList<>(Arrays.asList(pcount));
		ArrayList<String> productList = new ArrayList<>(Arrays.asList(pseq));
		
		
		
		MemberDAO dao = new MemberDAO();
		ProductDAO pdao = new ProductDAO();
		MemberDTO mdto = pdao.getMember(mseq); 
		req.setAttribute("mdto", mdto);
		
		ArrayList<BucketDTO> bpList = new ArrayList<BucketDTO>();
		bpList = dao.bucketPayList(mseq,countList,productList);
		req.setAttribute("bpList", bpList);
		req.setAttribute("totalprice", totalprice);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/pay/bucketpay.jsp");
		dispatcher.forward(req, resp);
	}

}