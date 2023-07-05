package com.ssy.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


/**
 * 
 * @author 유동환
 * <p>판매자의 상품등록을 완료하는 클래스입니다.</p>
 *
 */

@WebServlet("/product/registerproductok.do")
public class RegisterProductOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		
		
		 String path = req.getRealPath("/asset/images");
		 int size = 1024 * 1024 * 100;
			String filename = "";		//첨부파일명
			String orgfilename = "";	//첨부파일명
			String pName="";
			String pFile="";
			String pCategory="";
			String pPrice="";
			String pOrigin="";
			String pDiscount="";
			String sName="";
			String sTel="";
			String pRefundOx="";
			String pAmount = "";
			String pQuantity = "";
		try {
			   MultipartRequest multi = new MultipartRequest(
		               req,    
		               path,      
		               size,      
		               "UTF-8",   
		               new DefaultFileRenamePolicy() 
		            ); //업로드시켜버림
			   
			   pName = multi.getParameter("pName");
			   pCategory = multi.getParameter("pCategory");
			   pPrice = multi.getParameter("pPrice");
			   pOrigin = multi.getParameter("pOrigin");
			   pDiscount = multi.getParameter("pDiscount");
//			   sName = multi.getParameter("sName");
//			   sTel = multi.getParameter("sTel");
			   pFile = multi.getOriginalFileName("pFile");	//첨부파일명
			   pRefundOx = multi.getParameter("pRefundOx");
			   pAmount = multi.getParameter("pAmount");
			   pQuantity = multi.getParameter("pQuantity");
			   String tag = multi.getParameter("tag");
			   
			   
			   HttpSession  session = req.getSession();
			   String sSeq = (String)session.getAttribute("sseq");
			   
			   
			   
			   ProductDAO dao = new ProductDAO();
			   ProductDTO dto = new ProductDTO();
			   dto.setPname(pName);
			   dto.setCseq(pCategory);
			   dto.setPprice(pPrice);
			   dto.setPorigin(pOrigin);
			   dto.setPdiscount(pDiscount);
			   dto.setPquantity(pQuantity);
			   dto.setPimage(pFile);
			   dto.setPrefundox(pRefundOx);
			   dto.setSseq(sSeq);
			   dto.setPamount(pAmount);
			   
			   System.out.println(dto);
			   
			   int result = dao.addProduct(dto);
			   
			   
				if(tag != null && tag != "" && !tag.equals("[]")) {
				
					try {
					
						//tag: [{"value":"게시판"},{"value":"자바"},{"value":"JSP"},{"value":"서블릿"},{"value":"오라클"}]
						//키가 쌍따옴표면 JSON
						JSONParser parser = new JSONParser();
						JSONArray tlist = (JSONArray)parser.parse(tag);
						
						String pSeq = dao.getPseq();
						HashMap<String, String> map = new HashMap<String, String>();
						
						for(Object obj : tlist) {
							
							String tagName = (String)((JSONObject)obj).get("value");
							
							
							//해시태그 추가
							String hseq = "";
							if(dao.existHashTag(tagName)) {
								hseq = dao.getRHseq(tagName); //기존 태그 번호
							} else {
								dao.addHashTag(tagName); //새로운 태그
								
								//게시판 - 해시태그 연결 추가
								hseq = dao.getHseq(); //새로운 태그번호
							}
							map.put("pseq", pSeq);
							map.put("hseq", hseq);
							
							dao.addTagging(map); //연결
							
						}
					}  catch (Exception e) {
						System.out.println("RegisterProductOk.doPost");
						e.printStackTrace();
					}
				}
					
			   if(result == 1) {
			
				   resp.sendRedirect("/ssy/seller/sellerpage.do?sSeq=" + sSeq);
				   
			   } else {
				   
				   PrintWriter writer = resp.getWriter();
					writer.print("<script>");
					writer.print("alert('failed');");
					writer.print("history.back();");
					writer.print("</script>");
					writer.close();
			   }
		
		
	} catch (Exception e) {
		System.out.println("RegisterProductOk.doPost");
		e.printStackTrace();
	}
	}
}


