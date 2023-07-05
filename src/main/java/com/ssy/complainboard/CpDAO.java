package com.ssy.complainboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.ssy.DBUtil;

public class CpDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public CpDAO() {
		conn = DBUtil.open();
	}
	
	
	/**
	 * @author 김하늘
	 * <p>검색어 유무와 검색어에 따라 목록을 보여주는 메소드입니다.</p>
	 * @param map
	 * @return ArrayList
	 */

	public ArrayList<CpDTO> list(HashMap<String, String> map) {
		
		try {
			
			String sql = "";
			String where = "";
			
			if (map.get("isSearch").equals("y")) {
				
				if (!map.get("column").equals("all")) {
					where = String.format("where %s like '%%' || '%s' || '%%'"
											, map.get("column")
											, map.get("word"));
				} else {
					where = String.format("where cbtitle like '%%' || '%s' || '%%' or cbcontent like '%%' || '%s' || '%%'"
											, map.get("word")
											, map.get("word"));

				}
				
			}
			
			
	        sql = String.format("select * from (select rownum as rnum, c.*, m.mname from tblComplainBoard c inner join tblmember m on c.mseq = m.mseq %s) where rnum between %s and %s order by cbseq", where, map.get("begin"), map.get("end"));
	         
			
			pstat = conn.prepareStatement(sql);
			
			rs = pstat.executeQuery();
			
			ArrayList<CpDTO> list = new ArrayList<CpDTO>();
			
			while (rs.next()) {
				
				CpDTO dto = new CpDTO();
				
				dto.setCbseq(rs.getString("cbseq"));
				dto.setCbdate(rs.getString("cbdate"));
				dto.setCbcontent(rs.getString("cbcontent"));
				dto.setCbtitle(rs.getString("cbtitle"));
				dto.setMname(rs.getString("mname"));
				dto.setCbsecret(rs.getString("cbsecret"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("CpDAO.list");
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
					where = String.format("where cbtitle like '%%' || '%s' || '%%' or cbcontent like '%%' || '%s' || '%%'"
											, map.get("word")
											, map.get("word"));

				}
				
			}
			
			sql = "select count(*) as cnt from tblComplainBoard " + where; 
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				return rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			System.out.println("CpDAO.getTotalCount");
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * @author 김하늘
	 * <p>문의게시판의 글을 추가하는 메소드입니다.</p>
	 * @param dto
	 * @return int
	 */

	public int add(CpDTO dto) {
		
		try {
			
			String sql = "insert into tblComplainBoard (cbseq, cbdate, cbcontent, cbtitle, mseq, cbsecret) values (seqComplainBoard.nextVal, default, ?, ?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getCbcontent());
			pstat.setString(2, dto.getCbtitle());
			pstat.setString(3, dto.getMseq());
			pstat.setString(4, dto.getCbsecret());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("CpDAO.add");
			e.printStackTrace();
		}
		
		return 0;
	}


	/**
	 * @author 김하늘
	 * <p>기존에 작성했던 내용을 가져오는 메소드입니다.</p>
	 * @param seq
	 * @return CpDTO
	 */
	
	public CpDTO get(String seq) {
		try {
			
			String sql = "select * from (select c.*, m.mname from tblComplainBoard c inner join tblmember m on c.mseq = m.mseq) where cbseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			//System.out.println(seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				CpDTO dto = new CpDTO();
				
				dto.setCbseq(rs.getString("cbseq"));
				dto.setCbdate(rs.getString("cbdate"));
				dto.setCbcontent(rs.getString("cbcontent"));
				dto.setCbtitle(rs.getString("cbtitle"));
				dto.setMname(rs.getString("mname"));
				
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("CpDAO.get");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @author 김하늘
	 * <p>문의게시판의 글을 수정하는 메소드입니다.</p>
	 * @param dto
	 * @return int
	 */

	public int edit(CpDTO dto) {
		try {
			
			String sql = "update tblComplainBoard set cbtitle = ?, cbcontent = ? where cbseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getCbtitle());
			pstat.setString(2, dto.getCbcontent());
			pstat.setString(3, dto.getCbseq());
			
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("CpDAO.edit");
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * @author 김하늘
	 * <p>문의 게시판을 삭제하는 메소드입니다.</p>
	 * @param seq
	 * @return int
	 * 
	 */

	public int del(String seq) {
		
		try {
			
			String sql = "delete from tblComplainBoard where cbseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			//System.out.println(seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("CpDAO.del");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * @author 김하늘
	 * <p>답변을 추가하는 메소드입니다.</p>
	 * @param dto
	 * @return int
	 */

	public int addComment(CpCommentDTO dto) {
		
		try {
			
			String sql = "insert into tblComplainAnswer (caseq, cadate, cacontent, cbseq, aseq) values (seqComplainAnswer.nextVal, default, ?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getCacontent());
			pstat.setString(2, dto.getCbseq());
			pstat.setString(3, dto.getAseq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("CpDAO.addComment");
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * @author 김하늘
	 * <p>작성된 답변의 목록을 보여주는 메소드입니다.</p>
	 * @param cbseq
	 * @return ArrayList
	 */

	public ArrayList<CpCommentDTO> clist(String cbseq) {
		try {
				
//				String sql = "select tblComplainAnswer.* , (select aname from tblAdmin where aseq = tblComplainAnswer.aseq) as name from tblComplainAnswer where cbseq = ?";
			
				String sql = "select * from (select c.*, a.aname from tblComplainAnswer c inner join tblAdmin a on c.aseq = a.aseq) where cbseq = ?";
				
				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, cbseq);
				
				rs = pstat.executeQuery();
				
				ArrayList<CpCommentDTO> clist = new ArrayList<CpCommentDTO>();
				
				while (rs.next()) {
					
					CpCommentDTO dto = new CpCommentDTO();
					
					dto.setCaseq(rs.getString("caseq"));
					dto.setCadate(rs.getString("cadate"));
					dto.setCacontent(rs.getString("cacontent"));
					dto.setCbseq(rs.getString("cbseq"));
					dto.setAseq(rs.getString("aseq"));
					dto.setAname(rs.getString("aname"));
					
					
					clist.add(dto);
					
				}
				
				return clist;
			
		} catch (Exception e) {
			System.out.println("CpDAO.clist");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author 김하늘
	 * <p>답변의 내용을 수정하는 메소드입니다.</p>
	 * @param dto
	 * @return int
	 */
	
	public int editComment(CpCommentDTO dto) {
		
		try {
			

			String sql = "update tblComplainAnswer set cacontent = ? where caseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getCacontent());
			pstat.setString(2, dto.getCaseq());
			
			return pstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("CpDAO.editComment");
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * @author 김하늘
	 * <p>답변을 삭제하는 메소드입니다.</p>
	 * @param caseq
	 * @return int
	 */

	public int delComment(String caseq) {
		
		try {
			
			String sql = "delete from tblComplainAnswer where caseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, caseq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("CpDAO.delComment");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @author 김하늘
	 * <p>문의게시판을 삭제할때 작성된 답변 모두를 삭제하는 메소드입니다.</p>
	 * @param cbseq
	 */
	public void delCommentAll(String cbseq) {
		
		try {
			
			String sql = "delete from tblComplainAnswer where cbseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, cbseq);
			
			pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("CpDAO.delCommentAll");
			e.printStackTrace();
		}
		
	}
}
