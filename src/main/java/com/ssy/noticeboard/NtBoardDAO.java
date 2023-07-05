package com.ssy.noticeboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.ssy.DBUtil;

/**
 * 
 * @author 구대현
 * <p>공지사항 관련 데이터베이스에 접근하는 클래스입니다.</p>
 * 
 */
public class NtBoardDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public NtBoardDAO() {
		conn = DBUtil.open();
	}
	
	/**
	 * 
	 * @author 구대현
	 * <p>공지사항 리스트를 가져오는 메소드입니다.</p>
	 * 
	 */
	public ArrayList<NtBoardDTO> list(HashMap<String, String> map) {
		try {
			
			
	        String sql = String.format("select * from (select rownum as rnum, a.* from (select * from tblnotice order by nseq desc) a) where rnum between %s and %s", map.get("begin"), map.get("end"));
	        
			stat = conn.createStatement();

			rs = stat.executeQuery(sql);

	        
			ArrayList<NtBoardDTO> list = new ArrayList<NtBoardDTO>();

			while (rs.next()) {

				NtBoardDTO dto = new NtBoardDTO();

				dto.setNseq(rs.getString("nseq"));
				dto.setNname(rs.getString("nname"));
				dto.setNdate(rs.getString("ndate"));
				dto.setNreadcount(rs.getString("nreadcount"));
				dto.setFilename(rs.getString("filename"));
				
				list.add(dto);

			}

			return list;

		} catch (Exception e) {
			System.out.println("NtBoardDAO.list");
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 
	 * @author 구대현
	 * <p>공지사항 총 게시물 수를 가져오는 메소드입니다.</p>
	 * 
	 */
	public int getTotalCount() {
		try {

			String sql = "select count(*) as cnt from tblNotice";

			stat = conn.createStatement();

			rs = stat.executeQuery(sql);

			if (rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			System.out.println("NtBoardDAO.getTotalCount");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 * @author 구대현
	 * <p>조회수를 추가하는 메소드입니다.</p>
	 * 
	 */
	public void addReadCount(String seq) {
		try {
			String sql = "update tblNotice set nreadcount = nreadcount + 1 where nseq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("NtBoardDAO.addReadCount");
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @author 구대현
	 * <p>공지사항 번호를 통해 상세 정보를 가져오는 메소드입니다.</p>
	 * 
	 */
	public NtBoardDTO get(String seq) {
		try {

			String sql = "select * from tblNotice where nSeq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			if (rs.next()) {

				NtBoardDTO dto = new NtBoardDTO();

				dto.setNseq(rs.getString("nseq"));
				dto.setNname(rs.getString("nname"));
				dto.setNcontent(rs.getString("ncontent"));
				dto.setNdate(rs.getString("ndate"));
				dto.setNreadcount(rs.getString("nreadcount"));
				dto.setFilename(rs.getString("filename"));

				return dto;
			}

		} catch (Exception e) {
			System.out.println("NtBoardDAO.getList");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @author 구대현
	 * <p>작성한 공지사항을 DB에 추가하는 메소드입니다.</p>
	 * 
	 */
	public int add(NtBoardDTO dto) {

		try {
			
			String sql = "";
			String max = "select max(nseq) as max from tblnotice";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(max);
			
			int max_count = 0;
			
			if(rs.next()) {
				max_count = Integer.parseInt(rs.getString("max"))+1;				
			}
			
			sql = "insert into tblnotice(nseq, nname, ncontent, ndate, nreadcount, filename) values (?, ?, ?, default, default, ?)";

			pstat = conn.prepareStatement(sql);

			pstat.setInt(1, max_count);
			pstat.setString(2, dto.getNname());
			pstat.setString(3, dto.getNcontent());
			pstat.setString(4, dto.getFilename());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("NtBoardDAO.add");
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 
	 * @author 구대현
	 * <p>공지사항을 수정하는 메소드입니다.</p>
	 * 
	 */
	public int edit(NtBoardDTO dto) {

		try {

			String sql = "update tblNotice set nname = ?, ncontent = ?, filename = ? where nseq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getNname());
			pstat.setString(2, dto.getNcontent());
			pstat.setString(3, dto.getFilename());
			pstat.setString(4, dto.getNseq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("NtBoardDAO.edit");
			e.printStackTrace();
		}

		return 0;
	}
	
	/**
	 * 
	 * @author 구대현
	 * <p>공지사항을 삭제하는 메소드입니다.</p>
	 * 
	 */
	public int del(String seq) {

		try {

			String sql = "delete from tblNotice where nseq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("NtBoardDAO.del");
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 
	 * @author 구대현
	 * <p>선택한 공지사항 번호보다 높은 번호의 공지사항 수를 확인하는 메소드입니다.</p>
	 * 
	 */
	public boolean isHighSeq(String nseq) {

		try {

			String sql = "select count(*) from tblnotice where nseq > ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, nseq);
			
			if(pstat.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}
			
		} catch (Exception e) {
			System.out.println("NtBoardDAO.isHighSeq");
			e.printStackTrace();
		}

		return false;
	}
	
	/**
	 * 
	 * @author 구대현
	 * <p>공지사항 번호 조절 메소드입니다.</p>
	 * 
	 */
	public void updateSeq(String nseq) {
		
		try {
			
			String sql = "update tblnotice set nseq = nseq-1 where nseq > ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, nseq);
			
			pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("NtBoardDAO.updateSeq");
			e.printStackTrace();
		}
		
	}

}
