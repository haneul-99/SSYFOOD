package com.ssy.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

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
 * <p>장바구니에 있는 상품들을 결제하는 클래스입니다.</p>
 *
 */

@WebServlet("/pay/bucketpayok.do")
public class BucketPayOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String mseq = (String)session.getAttribute("mseq");
		String olname = req.getParameter("olname");
		String[] pseq = req.getParameterValues("pseq");
		String[] pcount = req.getParameterValues("pcount");
		String[] bprice = req.getParameterValues("bprice");
		String[] oladdress = req.getParameterValues("oladdress");
		String totalprice = req.getParameter("totalprice");
		
		
		
		PayDAO dao = new PayDAO();
		BucketDTO dto = new BucketDTO();
		
		dto.setOlAddress(oladdress[0] + " " + oladdress[1]);
		dto.setOlName(olname);
		dto.setMseq(mseq);
		dto.setTotalprice(totalprice);
		
		ArrayList<String> pcountList = new ArrayList<>(Arrays.asList(pcount)); 
		ArrayList<String> pseqList = new ArrayList<>(Arrays.asList(pseq)); 
		ArrayList<String> bpriceList = new ArrayList<>(Arrays.asList(bprice));
		
		dto.setPcountList(pcountList);
		dto.setPseqList(pseqList);
		dto.setBpriceList(bpriceList);
		String olseq = dao.getOlseq();
		dto.setOlSeq(olseq);
		
		//orderList
		dao.bucketPay(dto);
		
		//orderDetail
		int result = dao.bucketPayDetail(dto);
		
		//deleteBucket
		dao.deleteBucket(mseq);
		
		
		if(result == 1) {
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=UTF-8");
			
			PrintWriter writer = resp.getWriter();
			writer.print("<script>");
			writer.print("alert('결제가 완료되었습니다. 이용해주셔서 감사합니다.');");
			writer.print("location.href='/ssy/index.do';");
			writer.print("</script>");
			writer.close();

			
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