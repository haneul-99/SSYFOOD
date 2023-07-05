package com.ssy.noticeboard;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author 구대현
 * <p>공지사항 리스트에서 상세 내용을 확인하는 클래스입니다.</p>
 * 
 */
@WebServlet("/noticeboard/ntview.do")
public class NtView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		String seq = req.getParameter("seq");
		
		NtBoardDAO dao = new NtBoardDAO();

		if (session.getAttribute("nreadcount") == null || session.getAttribute("nreadcount").toString().equals("n")) {

			dao.addReadCount(seq);

			session.setAttribute("nreadcount", "y");
		}

		NtBoardDTO dto = dao.get(seq);

		String name = dto.getNname();
		String content = dto.getNcontent();
	
		
		name = name.replace("<", "&lt;").replace(">", "&gt;");
		content = content.replace("<", "&lt;").replace(">", "&gt;").replace("\r\n", "<br>");
	
		dto.setNname(name);
		dto.setNcontent(content);

		if (dto.getFilename() != null && (dto.getFilename().toLowerCase().endsWith(".jpg")
				|| dto.getFilename().toLowerCase().endsWith(".jpeg") || dto.getFilename().toLowerCase().endsWith(".gif")
				|| dto.getFilename().toLowerCase().endsWith(".png"))) {

			// 이미지 정보 획득
			BufferedImage img = ImageIO.read(new File(req.getRealPath("asset/files") + "\\" + dto.getFilename()));

			String temp = "";

			if (img.getWidth() > 620) {
				temp = "style='width:620px;'";
			}

			content = dto.getNcontent();

			content = String.format("<div style='margin-top:15px;'><img src='/ssy/asset/files/%s' %s id=\"preview\"></div>",
					dto.getFilename(), temp) + content;

			dto.setNcontent(content);
		}


		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/ntview.jsp");
		dispatcher.forward(req, resp);

	}
}
