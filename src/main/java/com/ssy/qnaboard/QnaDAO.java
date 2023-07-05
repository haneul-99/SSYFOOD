package com.ssy.qnaboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.ssy.DBUtil;


public class QnaDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public QnaDAO() {
		conn = DBUtil.open();
	}

	/**
	 * 
	 * @author 김유진
	 * <p>작성한 게시글들을 리스트에 담아 반환하는 메소드</p>
	 * @param map
	 * @return ArrayList
	 *
	 */
	public ArrayList<QnaDTO> list(HashMap<String, String> map) {
		
		try {
	         
	         String sql = "";
	         
	         
	         sql = String.format("select * from (select rownum as rnum, a.* from (select * from tblQna order by qseq)a) where rnum between %s and %s", map.get("begin"), map.get("end"));
	                  
	         
	         
	         pstat = conn.prepareStatement(sql);
	         
	         rs = pstat.executeQuery();
	         
	         ArrayList<QnaDTO> list = new ArrayList<QnaDTO>();
	         
	         while (rs.next()) {
	            
	            QnaDTO dto = new QnaDTO();
	            
	            dto.setQseq(rs.getString("qseq"));
	            dto.setQtitle(rs.getString("qtitle"));
	            dto.setQcontent(rs.getString("qcontent"));
	            dto.setQdate(rs.getString("qdate"));
	            dto.setQsecret(rs.getString("qsecret"));
	            
	            dto.setMseq(rs.getString("mseq"));
	            
	                        
	            list.add(dto);
	            
	         }
	         
	         return list;
	         
	      } catch (Exception e) {
	         System.out.println("QnaDAO.list");
	         e.printStackTrace();
	      }

		
		return null;
	}
	
	/**
	 * @author 김유진
	 * <p>작성한 게시글을 데이터베이스에 저장하는 메소드</p>
	 * @param dto
	 * @return int
	 */

	public int add(QnaDTO dto) {
		
		try {
			
			String sql = "insert into tblQna (qseq, qtitle, qcontent, qdate, qsecret, mseq, pseq) values (seqQna.nextVal, ?, ?, default, default, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getQtitle());
			pstat.setString(2, dto.getQcontent());
			
			pstat.setString(3, dto.getMseq());
			pstat.setString(4, dto.getPseq());
			
			return pstat.executeUpdate();
			
			
			
		} catch (Exception e) {
			System.out.println("QnaDAO.add");
			e.printStackTrace();
		}
		
		return 0;
	}
	/**
	 * @author 김유진
	 * <p>게시글을 작성한 회원의 계정정보를 가져오는 메소드</p>
	 * @param attribute
	 * @return QnaDTO
	 */

	public QnaDTO getMember(String attribute) {
		
		try {

	         String sql = "select * from tblAccountInfo ai inner join tblMember m on ai.aiseq=m.aiseq where ai.aiid=?";

	         pstat = conn.prepareStatement(sql);

	         pstat.setString(1, attribute);

	         rs = pstat.executeQuery();

	         if (rs.next()) {

	        	QnaDTO dto = new QnaDTO();

	        	dto.setMseq(rs.getString("mseq"));
	            
	            
	            return dto;

	         }

	      } catch (Exception e) {
	         System.out.println("MemberDAO.getMember");
	         e.printStackTrace();
	      }

		
		return null;
	}
	
	/**
	 * @author 김유진
	 * <p>자신이 작성한 게시글을 수정하고, 수정된 값을 데이터베이스에 저장하는 메소드</p>
	 * @param dto
	 * @return int
	 */

	public int qnaedit(QnaDTO dto) {
		
		try {
			
			String sql = "update tblQna set qtitle = ?, qcontent = ? where qseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getQtitle());
			pstat.setString(2, dto.getQcontent());
			pstat.setString(3, dto.getQseq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("QnaDAO.qnaedit");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * @author 김유진
	 * <p>자신이 작성한 게시글을 삭제하면, 데이터베이스에 저장된 자신의 글을 삭제하는 메소드</p>
	 * @param qseq
	 * @return int
	 */

	public int qnadel(String qseq) {
		
		try {
			
			String sql = "delete from tblQna where qseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, qseq);
			
			return pstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("QnaDAO.qnadel");
			e.printStackTrace();
		}
		
		return 0;
	}

	
	
	
	
}