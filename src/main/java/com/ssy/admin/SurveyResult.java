package com.ssy.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssy.noticeboard.NtBoardDTO;

/**
 * 
 * @author 구대현
 * <p>설문조사 결과를 조회하는 클래스입니다.</p>
 * 
 */
@WebServlet("/admin/surveyresult.do")
public class SurveyResult extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		AdminDAO dao = new AdminDAO();
		
		ArrayList<Integer> list1 = dao.surcount(1);
		ArrayList<Integer> list2 = dao.surcount(2);
		ArrayList<Integer> list3 = dao.surcount(3);
		ArrayList<Integer> list4 = dao.surcount(4);
		ArrayList<Integer> list5 = dao.surcount(5);
		
		
		req.setAttribute("list1", list1);
		req.setAttribute("list2", list2);
		req.setAttribute("list3", list3);
		req.setAttribute("list4", list4);
		req.setAttribute("list5", list5);
		
		
		//----------------------------------------------------------
		
		String page = req.getParameter("page");
		
		int nowPage = 0;	//현재 페이지 번호(=page)
		int begin = 0;		//rnum
		int end = 0;		//rnum
		int pageSize = 10;	//한페이지 당 출력할 게시물 수
		int totalCount = 0; //총 게시물 수
		int totalPage = 0;	//총 페이지 수(총 게시물 수 / 한페이지 당 게시물 수)
		
		if (page == null || page == "") nowPage = 1;
		else nowPage = Integer.parseInt(page);
		
		
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;
		
		String column = req.getParameter("column");
		String word = req.getParameter("word");

		
		
		HashMap<String,String> map = new HashMap<String,String>();
		
		map.put("column", column);
		map.put("word", word);
		
		
		
		map.put("begin", begin + "");
		map.put("end", end + "");
		
		
		
		ArrayList<SurveyDTO> list6 = dao.surlist(map);
		
		
		//1.5
		//- 데이터 조작
		for (SurveyDTO dto : list6) {
			
			String resultq6 = dto.getResultq6();
			//내용 자르기			
			if (resultq6.length() > 40) {
				resultq6 = resultq6.substring(0, 40);
				dto.setResultq6(resultq6 + "..");
			}
			

			
		}
		

	    totalCount = dao.getTotalCount();
		totalPage = (int)Math.ceil((double)totalCount / pageSize);
		
	
		
		String pagebar = ""; //페이지바 태그
	      
	      int blockSize = 10; //한번에 보여지는 페이지 수
	      int n = 0; //출력 페이지 번호
	      int loop = 0; //루프 변수
	      
	      
	      loop = 1;
	      n = ((nowPage - 1) / blockSize) * blockSize + 1;
	    
	      if (n == 1) {
	         pagebar += String.format(" <a href='#!' style='cursor: not-allowed;'>◀</a> ");
	      } else {
	         pagebar += String.format(" <a href='/ssy/admin/surveyresult.do?page=%d'>◀</a> ",  n - 1);
	      }
	      
	      while (!(loop > blockSize || n > totalPage)) {
	    	  
	         
	         if (nowPage == n) {
	            pagebar += String.format(" <a href='#!' style='color: cornflowerblue;'>%d</a> ", n);
	         } else {
	            pagebar += String.format(" <a href='/ssy/admin/surveyresult.do?page=%d'>%d</a> "
	                                                         , n, n);
	         }
	         
	         loop++;
	         n++;
	         
	      }
	      
	      
	      if (n > totalPage) {
	         pagebar += String.format(" <a href='#!' style='cursor:not-allowed;'>▶</a> ");
	      } else {
	         pagebar += String.format(" <a href='/ssy/admin/surveyresult.do?page=%d'>▶</a> ", n);
	      }
		
		
		//2.
		req.setAttribute("list6", list6);		
		req.setAttribute("map", map);
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		
		req.setAttribute("nowPage", nowPage);
		
		req.setAttribute("pagebar", pagebar);
		

		
		//----------------------------------------------------------
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/surveyresult.jsp");
		dispatcher.forward(req, resp);

	}
}