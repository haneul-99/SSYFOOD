package com.ssy.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author 유동환
 * <p>판매자가 상품을 등록하는 클래스입니다.</p>
 *
 */

@WebServlet("/product/registerproduct.do")
public class RegisterProduct extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/product/registerproduct.jsp");
		dispatcher.forward(req, resp);
	}

}