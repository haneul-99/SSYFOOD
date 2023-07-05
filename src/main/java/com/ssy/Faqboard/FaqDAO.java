package com.ssy.Faqboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.ssy.DBUtil;

public class FaqDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public FaqDAO() {
		conn = DBUtil.open();
	}
	
	/**
	 * @author 김하늘
	 * <p>검색어 유무와 검색어에 따라 목록을 보여주는 메소드입니다.</p>
	 * @param map
	 * @return ArrayList
	 */

	public ArrayList<FaqDTO> list(HashMap<String, String> map) {
		
		try {
			
			String sql = "";
			String where = "";
			
			if (map.get("isSearch").equals("y")) {
				
				if (!map.get("column").equals("all")) {
					where = String.format("where %s like '%%' || '%s' || '%%'"
											, map.get("column")
											, map.get("word"));
				} else {
					where = String.format("where fqtitle like '%%' || '%s' || '%%' or fqcontent like '%%' || '%s' || '%%'"
											, map.get("word")
											, map.get("word"));

				}
				
			}
			
			
	        sql = String.format("select * from (select rownum as rnum, f.* from tblFaq f %s) where rnum between %s and %s", where, map.get("begin"), map.get("end"));
	         
			
			pstat = conn.prepareStatement(sql);
			
			rs = pstat.executeQuery();
			
			ArrayList<FaqDTO> list = new ArrayList<FaqDTO>();
			
			while (rs.next()) {
				
				FaqDTO dto = new FaqDTO();
				
				dto.setFqseq(rs.getString("fqseq"));
				dto.setFqtitle(rs.getString("fqtitle"));
				dto.setFqcontent(rs.getString("fqcontent"));
				dto.setFqdate(rs.getString("fqdate"));
								
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("FaqDAO.list");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @author 김하늘
	 * <p>검색어 유무와 검색어에 따라 총 페이지 수를 가져오는 메소드입니다.</p>
	 * @param map
	 * @return int
	 */
	
	public int getTotalCount(HashMap<String, String> map) {
		
		try {
			
			String sql = "";
			String where = "";
			
if (map.get("isSearch").equals("y")) {
				
				if (!map.get("column").equals("all")) {
					where = String.format("where %s like '%%' || '%s' || '%%'"
											, map.get("column")
											, map.get("word"));
				} else {
					where = String.format("where fqtitle like '%%' || '%s' || '%%' or fqcontent like '%%' || '%s' || '%%'"
											, map.get("word")
											, map.get("word"));

				}
				
			}
			
			sql = "select count(*) as cnt from tblFaq " + where; 
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				return rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			System.out.println("FaqDAO.getTotalCount");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @author 김하늘
	 * <p>기존에 작성된 내용을 가져오는 메소드입니다.</p>
	 * @param seq
	 * @return FaqDTO
	 */
	
	public FaqDTO get(String seq) {
		
		try {
			
			String sql = "select * from tblFaq where fqseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				FaqDTO dto = new FaqDTO();
				
				dto.setFqseq(rs.getString("fqseq"));
				dto.setFqtitle(rs.getString("fqtitle"));
				dto.setFqcontent(rs.getString("fqcontent"));
				dto.setFqdate(rs.getString("fqdate"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("FaqDAO.get");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @author 김하늘
	 * <p>자주하는 질문의 글을 삭제하는 메소드입니다.</p>
	 * @param seq
	 * @return int
	 */

	public int del(String seq) {
		
		try {
			
			String sql = "delete from tblFaq where fqseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			System.out.println(seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("FaqDAO.del");
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * @author 김하늘
	 * <p>자주하는 질문에 글을 추가하는 메소드입니다.</p>
	 * @param dto
	 * @return int
	 */
	
	public int add(FaqDTO dto) {
		try {
			
			String sql = "insert into tblFaq (fqseq, fqtitle, fqcontent, fqdate) values (seqFaq.nextVal, ?, ?, default)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getFqtitle());
			pstat.setString(2, dto.getFqcontent());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("FaqDAO.add");
			e.printStackTrace();
		}
		
		return 0;
	
	}
	
	/**
	 * @author 김하늘
	 * <p>등록한 자주하는 질문의 글을 수정하는 메소드입니다.</p>
	 * @param dto
	 * @return int
	 */

	public int edit(FaqDTO dto) {
		
		try {

			String sql = "update tblFaq set fqtitle = ?, fqcontent = ? where fqseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getFqtitle());
			pstat.setString(2, dto.getFqcontent());
			pstat.setString(3, dto.getFqseq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("FaqDAO.edit");
			e.printStackTrace();
		}
		return 0;
	}

}
