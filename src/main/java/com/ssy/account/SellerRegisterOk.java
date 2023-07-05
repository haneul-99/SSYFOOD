package com.ssy.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssy.seller.SellerDTO;

@WebServlet("/account/sellerregisterok.do")
public class SellerRegisterOk extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      
      req.setCharacterEncoding("UTF-8");
      String id = req.getParameter("id");
      String pw= req.getParameter("pw");
      String name= req.getParameter("name");
      String ssid= req.getParameter("ssid");
      String tel= req.getParameter("tel");
      
      String store_name = req.getParameter("store_name");
      String store_info = req.getParameter("store_info");
      String store_address= req.getParameter("store_address");
      
      AccountDAO dao = new AccountDAO();
      AccountDTO adto = new AccountDTO();
      adto.setAiid(id);
      adto.setAipw(pw);
      int aResult = dao.addAccount(adto,"2");
      
      SellerDTO sdto = new SellerDTO();
      sdto.setSjumin(ssid);
      sdto.setSstore(store_name);
      sdto.setSaddress(store_address);
      sdto.setSname(name);
      sdto.setSinfo(store_info);
      sdto.setStel(tel);
      int sResult = dao.addSeller(sdto);
      
      if(aResult == 1 && sResult == 1) {
         
         resp.sendRedirect("/ssy/index.do");
         
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