package com.ssy.eventboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.ssy.DBUtil;

public class EvtBoardDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public EvtBoardDAO() {
		conn = DBUtil.open();
	}
	
	/**
	 * @author 김하늘
	 * <p>검색어 유무와 검색어에 따라 목록을 보여주는 메소드입니다.</p>
	 * @param map
	 * @return ArrayList
	 */

	public ArrayList<EvtBoardDTO> list(HashMap<String, String> map) {
		
		try {
			
			String sql = "";
			String where = "";
			
			if (map.get("isSearch").equals("y")) {
				
				if (!map.get("column").equals("all")) {
					where = String.format("where %s like '%%' || '%s' || '%%'"
											, map.get("column")
											, map.get("word"));
				} else {
					where = String.format("where ename like '%%' || '%s' || '%%' or econtent like '%%' || '%s' || '%%'"
											, map.get("word")
											, map.get("word"));

				}
				
			}
			

	        sql = String.format("select * from (select rownum as rnum, e.* from tblEvent e %s) where rnum between %s and %s", where, map.get("begin"), map.get("end"));
	         
			
			pstat = conn.prepareStatement(sql);
			
			rs = pstat.executeQuery();
			
			ArrayList<EvtBoardDTO> list = new ArrayList<EvtBoardDTO>();
			
			while (rs.next()) {
				
				EvtBoardDTO dto = new EvtBoardDTO();
				
				dto.setEseq(rs.getString("eseq"));
				dto.setEname(rs.getString("ename"));
				dto.setEcontent(rs.getString("econtent"));
				dto.setEdate(rs.getString("edate"));
				dto.setEreadcount(rs.getString("ereadcount"));
								
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("EvtBoardDAO.list");
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
					where = String.format("where ename like '%%' || '%s' || '%%' or econtent like '%%' || '%s' || '%%'"
											, map.get("word")
											, map.get("word"));

				}
				
			}
			
			sql = "select count(*) as cnt from tblEvent " + where; 
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				return rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			System.out.println("EvtBoardDAO.getTotalCount");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @author 김하늘
	 * <p>이벤트 게시판을 조회할 때마다 조회수가 증가하는 메소드입니다.</p>
	 * @param eseq
	 */
	
	public void addReadCount(String eseq) {
		
		try {
			
			String sql = "update tblEvent set ereadcount = ereadcount + 1 where eseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, eseq);
			
			pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("EvtBoardDAO.addReadCount");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @author 김하늘
	 * <p>게시판을 수정할 때 기존에 작성했던 내용을 가져오는 메소드입니다.</p>
	 * @param eseq
	 * @return EvtBoardDTO
	 */

	public EvtBoardDTO get(String eseq) {
		
		try {
			
			String sql = "select * from tblEvent where eseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, eseq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				EvtBoardDTO dto = new EvtBoardDTO();
				
				dto.setEseq(rs.getString("eseq"));
				dto.setEname(rs.getString("ename"));
				dto.setEcontent(rs.getString("econtent"));
				dto.setEdate(rs.getString("edate"));
				dto.setEreadcount(rs.getString("ereadcount"));
				
				return dto;
			}
			
			
		} catch (Exception e) {
			System.out.println("EvtBoardDAO.get");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @author 김하늘
	 * <p>이벤트 게시판의 글을 추가하는 메소드입니다.</p>
	 * @param dto
	 * @return int
	 */

	public int add(EvtBoardDTO dto) {
		
		try {
			String sql = "insert into tblEvent (eseq, ename, econtent, edate, ereadcount) values (seqEvent.nextVal, ?, ?, default, default)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getEname());
			pstat.setString(2, dto.getEcontent());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("EvtBoardDAO.add");
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * @author 김하늘
	 * <p>이벤트 게시판의 글을 삭제하는 메소드입니다.</p>
	 * @param eseq
	 * @return int 
	 */

	public int del(String eseq) {
		
		try {
			
			String sql = "delete from tblEvent where eseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, eseq);
			
			//System.out.println(eseq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("EvtBoardDAO.del");
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * @author 김하늘
	 * <p>이벤트 게시판의 글을 수정하는 메소드입니다.</p>
	 * @param dto
	 * @return int 
	 */

	public int edit(EvtBoardDTO dto) {
		
		try {
			
			String sql = "update tblEvent set ename = ?, econtent = ? where eseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getEname());
			pstat.setString(2, dto.getEcontent());
			pstat.setString(3, dto.getEseq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("EvtBoardDAO.edit");
			e.printStackTrace();
		}
		return 0;
	}

}
