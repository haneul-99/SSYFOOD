package com.ssy.product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/product/productimage.do")
public class ProductImage extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String data = req.getParameter("data");
		
		data = "C:\\class\\code\\server\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\SSYFood\\asset\\files\\"+ data;
		
		resp.setContentType("JSON");
		
		data = String.format("<img src= \"%s\">", data);
		
		PrintWriter writer = resp.getWriter();
		writer.print("data:" + data);
		writer.close();
	}

}
