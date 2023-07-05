package com.ssy.product;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author 유동환
 * <p>판매자가 내릴 상품을 삭제하는 클래스입니다.</p>
 *
 */
@WebServlet("/product/productdelok.do")
public class ProductDelOk extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String pseq = req.getParameter("pSeq");
		String pImage = req.getParameter("pImage");
		String sseq = req.getParameter("sSeq");
		ProductDAO dao = new ProductDAO();
		
		String path = req.getRealPath("/ssy/asset/images");
		File file = new File(path + "\\" + pImage+".*");
		
		if(file.exists()) {
			
			file.delete();
		}
		
		
		dao.deletehash(pseq); //해쉬태그 지우기
		
		int result = dao.deleteProduct(pseq);
		
		if(result == 1 ) {
			
			resp.sendRedirect("/ssy/seller/sellerpage.do?sSeq="+sseq);
		}else {
			
			PrintWriter writer = resp.getWriter();
			writer.print("<script>");
			writer.print("alert('failed');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();
		}
		
		
	}

}
