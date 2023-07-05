package com.ssy.member;

import java.io.IOException;

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
 * <p>장바구니에 상품추가를 완료하는 클래스입니다.</p>
 *
 */
@WebServlet("/member/bucketok.do")
public class BucketOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		
		String mseq = (String)session.getAttribute("mseq");
		String pseq = req.getParameter("pseq");
		String cseq = req.getParameter("cseq");
		String jseq = req.getParameter("jseq");

		String bprice = req.getParameter("bprice");
		MemberDAO dao = new MemberDAO();
		BucketDTO dto = new BucketDTO();
		dto.setBprice(bprice);
		dto.setMseq(mseq);
		dto.setPseq(pseq);
		
		int result = dao.putBucket(dto);
		
		if(result == 1 && jseq == null) {
			
			resp.sendRedirect("/ssy/product/product.do?cSeq="+cseq +"&word=");
		} else if(result == 1 && jseq != null){
			
			resp.sendRedirect("/ssy/member/jjim.do?mSeq="+mseq);
		}
		
		
	}

}
