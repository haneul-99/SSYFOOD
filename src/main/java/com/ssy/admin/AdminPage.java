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
import javax.servlet.http.HttpSession;


/**
 * 
 * @author 박민수
 * <p>회원 개인정보와 계정정보를 보여주는 클래스</p>
 *
 */
@WebServlet("/admin/adminpage.do")
public class AdminPage extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      //페이징
      
      String page = req.getParameter("page");
     
      int nowPage = 0;   //현재 페이지 번호(=page)
      int begin = 0;      //rnum
      int end = 0;      //rnum
      int pageSize = 20;   //한페이지 당 출력할 게시물 수
      int totalCount = 0; //총 게시물 수
      int totalPage = 0;   //총 페이지 수(총 게시물 수 / 한페이지 당 게시물 수)
      
      if (page == null || page == "") nowPage = 1;
      else nowPage = Integer.parseInt(page);
      
      begin = ((nowPage - 1) * pageSize) + 1;
      end = begin + pageSize - 1;
      
      
      String column = req.getParameter("column");
      String word = req.getParameter("word");
      String isSearch = "n"; //n(목록), y(검색)
      
      if ((column == null || word == null) || (column == "" || word == "") ) {
         isSearch = "n";
      } else {
         isSearch = "y";
      }
      
      
      HashMap<String,String> map = new HashMap<String,String>();
      
      map.put("column", column);
      map.put("word", word);
      map.put("isSearch", isSearch);
      
      map.put("begin", begin + "");
      map.put("end", end + "");
      
      
      HttpSession session = req.getSession();
      
      session.setAttribute("readcount", "n");
      
      AdminDAO dao = new AdminDAO();
      ArrayList<AdminDTO> list = dao.list(map);
      
      totalCount = dao.getTotalCount(map);
      totalPage = (int)Math.ceil((double)totalCount / pageSize);
      String pagebar = ""; //페이지바 태그
      
      int blockSize = 10; //한번에 보여지는 페이지 수
      int n = 0; //출력 페이지 번호
      int loop = 0; //루프 변수
      
      
      loop = 1;
      n = ((nowPage - 1) / blockSize) * blockSize + 1;
     System.out.println(n);
      if (n == 1) {
         pagebar += String.format(" <a href='#!' style='cursor: not-allowed;'>◀</a> ");
      } else {
         pagebar += String.format(" <a href='/ssy/admin/adminpage.do?page=%d'>◀</a> ",  n - 1);
         System.out.println(n);
      }
      
      while (!(loop > blockSize || n > totalPage)) {
         
         
         if (nowPage == n) {
            pagebar += String.format(" <a href='#!' style='color: cornflowerblue;'>%d</a> ", n);
         } else {
            pagebar += String.format(" <a href='/ssy/admin/adminpage.do?page=%d'>%d</a> "
                                                         , n, n);
            System.out.println(n);
         }
         
         loop++;
         n++;
         
      }
      
      
      if (n > totalPage) {
         pagebar += String.format(" <a href='#!' style='cursor:not-allowed;'>▶</a> ");
      } else {
         pagebar += String.format(" <a href='/ssy/admin/adminpage.do?page=%d'>▶</a> ", n);
      }
      
      String mseq = req.getParameter("mseq");
      
      
      
      AdminDTO dto = dao.get(mseq);
      
      req.setAttribute("dto", dto);
      
      //페이징, 게시판, 검색 페이지
      req.setAttribute("list", list);      
      req.setAttribute("map", map);
      req.setAttribute("totalCount", totalCount);
      req.setAttribute("totalPage", totalPage);
      req.setAttribute("nowPage", nowPage);
      req.setAttribute("pagebar", pagebar);

     

      
      RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/adminpage.jsp");
      dispatcher.forward(req, resp);

      
      
   }

}