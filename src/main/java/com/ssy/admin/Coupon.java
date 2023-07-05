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
 * <p>쿠폰게시판의 기능을 선택 할 수 있는 클래스</p>
 *
 */
@WebServlet("/admin/coupon.do")
public class Coupon extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/coupon.jsp");
		dispatcher.forward(req, resp);

	}

}

