package com.ssy.admin;

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
 * <p>관리자 업무 관련 데이터베이스에 접근하는 클래스입니다.</p>
 * 
 */
public class AdminDAO {

      private Connection conn;
      private Statement stat;
      private PreparedStatement pstat;
      private ResultSet rs;
      
      public AdminDAO() {
         conn = DBUtil.open();
      }

      /**
	 * 
	 * @author 구대현
	 * <p>설문조사 결과를 DB에 추가하는 메소드입니다.</p>
	 * 
	 */
	public int saveSurvey(SurveyDTO sdto) {
		
		try {
			
			String sql = "insert into tblSurvey (surSeq,surQ1,surQ2,surQ3,surQ4,surQ5,surQ6,mSeq) values (seqSurvey.nextVal, ?, ?, ?, ?, ?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, sdto.getSurQ1());
			pstat.setString(2, sdto.getSurQ2());
			pstat.setString(3, sdto.getSurQ3());
			pstat.setString(4, sdto.getSurQ4());
			pstat.setString(5, sdto.getSurQ5());
			pstat.setString(6, sdto.getSurQ6());
			pstat.setString(7, sdto.getMseq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("AdminDAO.surSave");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 
	 * @author 구대현
	 * <p>이미 설문조사를 진행한 회원인지 확인하는 메소드입니다.</p>
	 * 
	 */
	public boolean isNewSurvey(String seq) {
		
		try {
			
			String sql = "select count(*) as cnt from tblsurvey where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("cnt").equals("0")) {
					return true;
				} else {
					return false;					
				}
			}
			
			
		} catch (Exception e) {
			System.out.println("AdminDAO.isNewSurvey");
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * 
	 * @author 구대현
	 * <p>설문조사 결과를 수정하여 제출하는 메소드입니다.</p>
	 * 
	 */
	public int updateSurvey(SurveyDTO sdto) {
		
		try {
			String del = "delete from tblsurvey where mseq = ?";
			
			pstat = conn.prepareStatement(del);
			
			pstat.setString(1, sdto.getMseq());
			
			pstat.executeUpdate();
			
			String sql = "insert into tblSurvey (surSeq,surQ1,surQ2,surQ3,surQ4,surQ5,surQ6,mSeq) values (seqSurvey.nextVal, ?, ?, ?, ?, ?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, sdto.getSurQ1());
			pstat.setString(2, sdto.getSurQ2());
			pstat.setString(3, sdto.getSurQ3());
			pstat.setString(4, sdto.getSurQ4());
			pstat.setString(5, sdto.getSurQ5());
			pstat.setString(6, sdto.getSurQ6());
			pstat.setString(7, sdto.getMseq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("AdminDAO.updateSurvey");
			e.printStackTrace();
		}

		
		return 0;
	}

	/**
	 * 
	 * @author 구대현
	 * <p>설문조사 문항별 투표수를 가져오는 메소드입니다.</p>
	 * 
	 */
	public ArrayList<Integer> surcount(int n) {
//		select surq1 as surq, count(surq1) as cnt from tblsurvey group by surq1 order by surq1;
//		select surq2 as surq, count(surq2) as cnt from tblsurvey group by surq2 order by surq2;
//		select surq3 as surq, count(surq3) as cnt from tblsurvey group by surq3 order by surq3;
//		select surq4 as surq, count(surq4) as cnt from tblsurvey group by surq4 order by surq4;
//		select surq5 as surq, count(surq5) as cnt from tblsurvey group by surq5 order by surq5;

		try {
			
			String sql = String.format("select surq%d as surq, count(surq%d) as cnt from tblsurvey group by surq%d order by surq", n, n, n );
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			while (rs.next()) {
				
				list.add(rs.getInt("cnt"));
			}
			
			
			return list;
			
		} catch (Exception e) {
			System.out.println("AdminDAO.surcount1");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 
	 * @author 구대현
	 * <p>설문조사 서술형 결과를 조회하는 메소드입니다.</p>
	 * 
	 */
	public ArrayList<SurveyDTO> surlist(HashMap<String, String> map) {
		
		try {
			
			String sql = String.format("select * from (select rownum as rnum, a.* from (select * from tblsurvey s inner join tblmember m on s.mseq = m.mseq order by surseq) a) where rnum between %s and %s", map.get("begin"), map.get("end"));
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			ArrayList<SurveyDTO> list = new ArrayList<SurveyDTO>();
			
			while(rs.next()) {
				
				SurveyDTO dto = new SurveyDTO();
				
				dto.setMname(rs.getString("mname"));
				dto.setResultq6(rs.getString("surq6"));
				
				list.add(dto);
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("AdminDAO.surlist");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 
	 * @author 구대현
	 * <p>설문조사를 진행한 총 인원 수를 가져오는 메소드입니다.</p>
	 * 
	 */
	public int getTotalCount() {
		
		try {

			String sql = "select count(*) as cnt from tblsurvey";

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
     * @author 박민수
     * 회원정보를 리스트에 담는 메소드
     * @param map
     * @return null
     */
     public ArrayList<AdminDTO> list(HashMap<String, String> map) {
        
        try {
           
           String sql = "";
           String where = "";
           
           if (map.get("isSearch").equals("y")) {
              
              if (!map.get("column").equals("all")) {
                 where = String.format("where %s like '%%' || '%s' || '%%'"
                                   , map.get("column")
                                   , map.get("word"));
              } else {
                 where = String.format("where mname like '%%' || '%s' || '%%' or aiid like '%%' || '%s' || '%%' or maddress like '%%' || '%s' || '%%' or mtel like '%%' || '%s' || '%%'"
                                   , map.get("word")
                                   , map.get("word")
                                   , map.get("word")
                                   , map.get("word"));
              }
              
           }
           
           if (map.get("tag") == null) {
              sql = String.format("select * from (select rownum as rnum, a.* from (select * from tblMember a inner join tblAccountInfo b on a.aiSeq = b.aiSeq order by mseq) a %s) where rnum between %s and %s", where, map.get("begin"), map.get("end"));
           } 
           
           pstat = conn.prepareStatement(sql);
           
           rs = pstat.executeQuery();
     
     
        
           
           
           ArrayList<AdminDTO> list = new ArrayList<AdminDTO>();
           
           while (rs.next()) {
              
              AdminDTO dto = new AdminDTO();
              
              //회원 정보
              dto.setMseq(rs.getString("mseq"));
              dto.setMname(rs.getString("mname"));
              dto.setAiid(rs.getString("aiid"));
              dto.setAipw(rs.getString("aipw"));
              dto.setMaddress(rs.getString("maddress"));
              dto.setMtel(rs.getString("mtel"));
              dto.setAiseq(rs.getString("aiseq"));
              
             
              list.add(dto);
              
           }
           
           return list;
           
        } catch (Exception e) {
           System.out.println("AdminDAO.list");
           e.printStackTrace();
        }
        
        return null;
     }
     
     /**
      *  @author 박민수
      * 회원정보의 총 명수를 나타내는 메소드
      * @param map
      * @return
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
                 where = String.format("where mname like '%%' || '%s' || '%%' or maddress like '%%' || '%s' || '%%' or mtel like '%%' || '%s' || '%%'"
                       , map.get("word")
                       , map.get("word")
                       , map.get("word"));
              }
              
           }
           
           sql = "select count(*) as cnt from tblMember " + where; 
           
           stat = conn.createStatement();
           
           rs = stat.executeQuery(sql);
           
           if (rs.next()) {
              return rs.getInt("cnt");
           }
           
        } catch (Exception e) {
           System.out.println("AdminDAO.getTotalCount");
           e.printStackTrace();
        }
        return 0;
     }

     /**
      *  @author 박민수
      * 회원정보를 리스트를 불러오는 메소드
      * @param mseq
      * @return
      */
	public AdminDTO get(String mseq) {
	try {
			
			String sql = "select tblMember.*,(select aiSeq from tblAccountInfo where aiseq = tblMember.aiseq)as cnum from tblMember where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, mseq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				AdminDTO dto = new AdminDTO();
				
				dto.setMseq(rs.getString("mseq"));
				dto.setMname(rs.getString("mname"));
				dto.setAiid(rs.getString("aiid"));
				dto.setMaddress(rs.getString("maddress"));
				dto.setMtel(rs.getString("mtel"));
				dto.setAipw(rs.getString("aipw"));
				dto.setAiseq(rs.getString("aiseq"));
				
				
				return dto;
				
			}
			
		} catch (Exception e) {
			System.out.println("AdminDAO.get");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * @author 박민수
    * 회원의 개인정보, 계정정보를 삭제하는 메소드
	 * @param mseq
	 * @return
	 */
	public int del(String mseq) {
		try {

			String sql = "delete from tblQna where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, mseq);
			
			pstat.executeUpdate();
			
			sql = "delete from tblReview where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, mseq);
			
			pstat.executeUpdate();
			
			sql = "delete from tblSurvey where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, mseq);
			
			pstat.executeUpdate();
			
			sql = "delete from tblOrderList where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, mseq);
			
			pstat.executeUpdate();

			sql = "delete from tblBucket where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, mseq);
			
			pstat.executeUpdate();
	
			sql = "delete from tblJjim where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, mseq);
			
			pstat.executeUpdate();
			
			sql = "delete from tblSellerFollower where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, mseq);
			
			pstat.executeUpdate();
			
			sql = "delete from tblComplainBoard where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, mseq);
			
			pstat.executeUpdate();
			
			sql = "delete from (select * from tblMember a inner join tblAccountInfo b on a.aiseq = b.aiseq)   where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, mseq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("AdminDAO.del");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 *  @author 박민수
    * 회원정보와 계정정보를 수정하는메소드
	 * @param dto
	 * @return 0
	 */
	public int edit(AdminDTO dto) {
		try {
			
			  String sql = "update tblMember set mname = ?,maddress = ?,mtel=? where mseq = ?";
			
			
			 pstat = conn.prepareStatement(sql);
			 
			 pstat.setString(1, dto.getMname());
			  
			 pstat.setString(2, dto.getMaddress());
			  
			 pstat.setString(3, dto.getMtel());
			 
			 pstat.setString(4, dto.getMseq());
			 
			  pstat.executeUpdate();
			 
			
			  sql = "update tblAccountInfo set aiid = ?, aipw =? where aiseq=?";
			  
			  pstat = conn.prepareStatement(sql);
			  
			  pstat.setString(1, dto.getAiid()); 
			  pstat.setString(2, dto.getAipw());
			  pstat.setString(3, dto.getAiseq());
			  
			  
			  return pstat.executeUpdate();
			 
			
		} catch (Exception e) {
			System.out.println("AdminDAO.edit");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	/*
	  		택배사 관련 정보 DAO
	  		네이밍 할때 d 추가하기
	  		
	  		 택배 회사 dseq dname dsmall dmidium dlarge dxlarge

	 */
	
	/**
	 
	 * @author 박민수
    * 택배사정보를 받아오는 메소드
	 * @param d_map
	 * @return null
	 */
	public ArrayList<DeliverDTO> d_list(HashMap<String, String> d_map) {
			try {
           
           String sql = "";
           String where = "";
           
           if (d_map.get("d_isSearch").equals("y")) {
              
              if (!d_map.get("d_column").equals("all")) {
                 where = String.format("where %s like '%%' || '%s' || '%%'"
                                   , d_map.get("d_column")
                                   , d_map.get("d_word"));
              } else {
                  where = String.format("where dname like '%%' || '%s' || '%%'"
                          , d_map.get("d_word"));
     }
              
           }
           
           if (d_map.get("tag") == null) {
              sql = String.format("select * from (select rownum as rnum, a.* from (select * from tblDeliver order by dseq) a %s) where rnum between %s and %s", where, d_map.get("d_begin"), d_map.get("d_end"));
           } 
           
           pstat = conn.prepareStatement(sql);
           
           rs = pstat.executeQuery();
     
           ArrayList<DeliverDTO> d_list = new ArrayList<DeliverDTO>();
           
           while (rs.next()) {
           	
               DeliverDTO d_dto = new DeliverDTO();
                     
              d_dto.setDseq(rs.getString("dseq"));
              d_dto.setDname(rs.getString("dname"));
              d_dto.setDsmall(rs.getString("dsmall"));
              d_dto.setDmidium(rs.getString("dmidium"));
              d_dto.setDlarge(rs.getString("dlarge"));
              d_dto.setDxlarge(rs.getString("dxlarge"));
              
              
              
              d_list.add(d_dto);
            
           }
           
           return d_list;
           
        } catch (Exception e) {
           System.out.println("AdminDAO.d_list");
           e.printStackTrace();
        }
        
        return null;
     }


	/**
	 *  @author 박민수
    * 택배사의 정보를 나타내는 메소드
	 * @param d_map
	 * @return null
	 */
	public int d_getTotalCount(HashMap<String, String> d_map) {
		 try {
	            
	            String sql = "";
	            String where = "";
	            
	            if (d_map.get("d_isSearch").equals("y")) {
	               
	               if (!d_map.get("d_column").equals("all")) {
	                  where = String.format("where %s like '%%' || '%s' || '%%'"
	                                    , d_map.get("column")
	                                    , d_map.get("word"));
	               } 
	            }
	            
	            sql = "select count(*) as cnt from tblDeliver " + where; 
	            
	            stat = conn.createStatement();
	            
	            rs = stat.executeQuery(sql);
	            
	            if (rs.next()) {
	               return rs.getInt("cnt");
	            }
	            
	         } catch (Exception e) {
	            System.out.println("AdminDAO.d_getTotalCount");
	            e.printStackTrace();
	         }
	         return 0;
	      }

	/**
	 * @author 박민수
    * 택배사정보를 나타내는 메소드
	 * @param dseq
	 * @return d_dto
	 */
	public DeliverDTO getDeliver(String dseq) {
		try {
			
			String sql = "select * from tblDeliver where dseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dseq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				DeliverDTO d_dto = new DeliverDTO();
				
			            
	               d_dto.setDseq(rs.getString("dseq"));
	               d_dto.setDname(rs.getString("dname"));
	               d_dto.setDsmall(rs.getString("dsmall"));
	               d_dto.setDmidium(rs.getString("dmidium"));
	               d_dto.setDlarge(rs.getString("dlarge"));
	               d_dto.setDxlarge(rs.getString("dxlarge"));
	               
				
				return d_dto;
				
			}
			
		} catch (Exception e) {
			System.out.println("AdminDAO.getDeliver");
			e.printStackTrace();
		}
		
		return null;
	}



	/**
	 *  @author 박민수
    * 택배사정보를 삭제하는 메소드
	 * @param d_dto
	 * @return 0
	 */
	public int edit(DeliverDTO d_dto) {
		try {
			 String sql = "update tblDeliver set dsmall = ?,dmidium = ?,dlarge=?,dxlarge=? where dseq = ?";
				
				
				 pstat = conn.prepareStatement(sql);
				 
				 pstat.setString(1, d_dto.getDsmall());
				  
				 pstat.setString(2, d_dto.getDmidium());
				  
				 pstat.setString(3, d_dto.getDlarge());
				 
				 pstat.setString(4, d_dto.getDxlarge());
				 
				 pstat.setString(5, d_dto.getDseq());
				 
				 return pstat.executeUpdate();
				 
		} catch (Exception e) {
			System.out.println("AdminDAO.d_dto");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @author 박민수
    * 새로운 택배사를 추가하는 메소드
	 * @param d_dto
	 * @return 0
	 */
	public int plus(DeliverDTO d_dto) {
		try {
			//insert into tblDeliver(dSeq, dName, dSmall, dMidium, dLarge, dXlarge) values (seqdeliver.nextval, 'CJ대한통운', 1000, 2000, 3000, 5000);
			
			String sql = "insert into tblDeliver(dSeq, dName, dSmall, dMidium, dLarge, dXlarge) values (seqdeliver.nextval, ?, ?, ?, ?, ?)";
			
			 pstat = conn.prepareStatement(sql);
			 
			 pstat.setString(1, d_dto.getDname());
			 
			 pstat.setString(2, d_dto.getDsmall());
			  
			 pstat.setString(3, d_dto.getDmidium());
			  
			 pstat.setString(4, d_dto.getDlarge());
			 
			 pstat.setString(5, d_dto.getDxlarge());
			 
			
			 
			 return pstat.executeUpdate();
			 
			 
		} catch (Exception e) {
			System.out.println("AdminDAO.plus");
			e.printStackTrace();
		}
		return 0;
	}
	/*
	  		쿠폰 정보 관련 DAO
	 
	 */

	/**
	 *  @author 박민수
    * 쿠폰 정보를 나타내는 메소드
	 * @param comap
	 * @return colist
	 */
	public ArrayList<CouponDTO> colist(HashMap<String, String> comap) {
		
		try {
			
			String sql = "";
		
			 if (comap.get("tag") == null) {
			sql = String.format("select * from (select rownum as rnum, a.* from (select * from tblCoupon order by coseq)a) where rnum between %s and %s", comap.get("cobegin"), comap.get("coend"));
			 }
			
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			
			ArrayList<CouponDTO> colist = new ArrayList<CouponDTO>();
			
			while(rs.next()) {
				
				CouponDTO codto = new CouponDTO();
				
				codto.setCoseq(rs.getString("coseq"));
				codto.setConame(rs.getString("coname"));
				codto.setCodiscount(rs.getString("codiscount"));
				codto.setCocontent(rs.getString("cocontent"));
				codto.setCodate(rs.getString("codate"));
						
				colist.add(codto);
				
			}
			
			return colist;
			
		} catch (Exception e) {
			System.out.println("AdminDAO.colist");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * @author 박민수
    * 쿠폰의 총 갯수를 나타내는 메소드
	 * @param comap
	 * @return 0
	 */
	public int cogetTotalCount(HashMap<String, String> comap) {
		try {
			
			String sql = "";
			  	sql = "select count(*) as cnt from tblCoupon "; 
	            //0
	            stat = conn.createStatement();
	            
	            rs = stat.executeQuery(sql);
	            
	            if (rs.next()) {
	               return rs.getInt("cnt");
	            }
			
		} catch (Exception e) {
			System.out.println("AdminDAO.cogetTotalCount");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @author 박민수
    * 쿠폰의 정보를  나타내는 메소드 
	 * @param coseq
	 * @return codto
	 */
	public CouponDTO getCoupon(String coseq) {
		try {
			
			String sql = "select * from tblCoupon where coseq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, coseq);
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				CouponDTO codto = new CouponDTO();
				
				codto.setCoseq(rs.getString("coseq"));
				codto.setConame(rs.getString("coname"));
				codto.setCodiscount(rs.getString("codiscount"));
				codto.setCocontent(rs.getString("cocontent"));
				codto.setCodate(rs.getString("codate"));
						
				return codto;
				
			}
			
			
		} catch (Exception e) {
			System.out.println("AdminDAO.getCoupon");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author 박민수
    * 새로운 쿠폰을 등록하는 메소드 
	 * @param codto
	 * @return pstat.executeUpdate();
	 */
	public int insert(CouponDTO codto) {
		try {

			String sql = "insert into tblCoupon(coSeq, coName, coDiscount, coContent, coDate) values (seqcoupon.nextval, ?, ?, ?, ?)";
			
			 pstat = conn.prepareStatement(sql);
			 
			 pstat.setString(1, codto.getConame());
			 
			 pstat.setString(2, codto.getCodiscount());
			  
			 pstat.setString(3, codto.getCocontent());
			 
			 pstat.setString(4, codto.getCodate());
			 System.out.println(sql);
			 return pstat.executeUpdate();
			 
			 
		} catch (Exception e) {
			System.out.println("AdminDAO.insert");
			e.printStackTrace();
		}
		return 0;
	}
	/*
	 
	  판매자
	 
	 */
	/**
	 * @author 박민수
	 * 판매자 계정정보, 회원정보를 저장하는 메소드
	 * @param smap
	 * @return slist
	 */
	public ArrayList<AdminSellerDTO> slist(HashMap<String, String> smap) {
		
		   try {
	            
	            String sql = "";
	            String where = "";
	            
	            if (smap.get("isSearch").equals("y")) {
	               
	               if (!smap.get("column").equals("all")) {
	                  where = String.format("where %s like '%%' || '%s' || '%%'"
	                                    , smap.get("column")
	                                    , smap.get("word"));
	               } else {
	                  where = String.format("where sname like '%%' || '%s' || '%%' or sstore like '%%' || '%s' || '%%' or saddress like '%%' || '%s' || '%%'"
	                                    , smap.get("word")
	                                    , smap.get("word")
	                                    , smap.get("word")
	                                   );
	               }
	               
	            }
	            
	            if (smap.get("tag") == null) {
	               sql = String.format("select * from (select rownum as rnum, a.* from (select * from tblSeller a inner join tblAccountInfo b on a.aiSeq = b.aiSeq) a %s) where rnum between %s and %s", where, smap.get("begin"), smap.get("end"));
	            } 
	            
	            pstat = conn.prepareStatement(sql);
	            
	            rs = pstat.executeQuery();
	      
	      
	         
	            
	            
	            ArrayList<AdminSellerDTO> slist = new ArrayList<AdminSellerDTO>();
	            
	            while (rs.next()) {
	               
	            	AdminSellerDTO sdto = new AdminSellerDTO();
	       
	               //판매자 정보
	            	
	            	sdto.setSseq(rs.getString("sseq"));
	            	sdto.setAiid(rs.getString("aiid"));
	            	sdto.setSname(rs.getString("sname"));
	            	sdto.setStel(rs.getString("stel"));
	            	sdto.setSstore(rs.getString("sstore"));
	            	sdto.setSinfo(rs.getString("sinfo"));
	            	sdto.setSaddress(rs.getString("saddress"));
	            	sdto.setAiseq(rs.getString("aiseq"));
	            	sdto.setAipw(rs.getString("aipw"));
	              
	               slist.add(sdto);
	               
	            }
	            
	            return slist;
	            
	         } catch (Exception e) {
	            System.out.println("AdminDAO.list");
	            e.printStackTrace();
	         }
	         
		
		return null;


	}

	/**
	 * @author 박민수
	 * 판매자의 개인정보 계정정보를 가져오는 메소드
	 * @param sseq
	 * @return
	 */
	public AdminSellerDTO sellerGet(String sseq) {
		try {
			String sql = "select * from tblSeller where sseq = ? order by sseq";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, sseq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				AdminSellerDTO sdto = new AdminSellerDTO();
				
			            
			  	sdto.setSseq(rs.getString("sseq"));
           	sdto.setAiid(rs.getString("aiid"));
           	sdto.setSname(rs.getString("sname"));
           	sdto.setStel(rs.getString("sstel"));
           	sdto.setSstore(rs.getString("sstore"));
           	sdto.setSinfo(rs.getString("sinfo"));
           	sdto.setSaddress(rs.getString("saddress"));
           	sdto.setAiseq(rs.getString("aiseq"));
             
	               
				
				return sdto;
			}
			
		} catch (Exception e) {
			System.out.println("AdminDAO.sellerGet");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author 박민수
	 * 판매자의 총 명수를 가져오는 메소드
	 * @param map
	 * @return
	 */
	public int getsTotalCount(HashMap<String, String> map) {
		try {
			String sql = "";
		  	sql = "select count(*) as cnt from tblSeller "; 
           //0
           stat = conn.createStatement();
           
           rs = stat.executeQuery(sql);
           
           if (rs.next()) {
              return rs.getInt("cnt");
           }
		} catch (Exception e) {
			System.out.println("AdminDAO.getsTotalCount");
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * @author 박민수
	 * 판매자의 계정정보 개인정보를 삭제하는 메소드
	 * @param sseq
	 * @param aiseq
	 * @return pstat.executeUpdate();
	 */
	public int sdel(String sseq, String aiseq) {
		
		try {
			
			String sql = "";
			
			sql = "delete from tblQnaAnswer where sseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, sseq);
			
		
			
			pstat.executeUpdate();
			
			sql = "delete from tblSaleState where sseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, sseq);
			
				
			pstat.executeUpdate();
			
			
			
			sql = "delete from tblProduct where sseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, sseq);
		
			
			pstat.executeUpdate();
			
			sql = "delete from tblsellerFollower where sseq = ?";
			
			
			pstat = conn.prepareStatement(sql);
		
			pstat.setString(1, sseq);
			
			pstat.executeUpdate();
			
			
			sql = "delete from (select * from tblSeller a inner join tblAccountInfo b on a.aiseq = b.aiseq) where sseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, sseq);
			
			pstat.executeUpdate();
			
			
			sql = "delete from tblAccountInfo where aiseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, aiseq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("AdminDAO.sdel");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * @author 박민수
	 * 판매자 정보를 수정하는 메소드
	 * @param dto
	 * @return
	 */
	public int sedit(AdminSellerDTO dto) {
		try {
		
			
			String sql = "update tblSeller set sname = ?, stel = ?,saddress = ?, sinfo = ?, sstore = ? where sseq = ? ";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getSname());
			pstat.setString(2, dto.getStel());
			pstat.setString(3, dto.getSaddress());
			pstat.setString(4, dto.getSinfo());
			pstat.setString(5, dto.getSstore());
			pstat.setString(6, dto.getSseq());
			
			pstat.executeUpdate();
			
			sql = "update tblAccountInfo set aiid = ?, aipw = ? where aiseq = ?";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getAiid());
			pstat.setString(2, dto.getAipw());
			pstat.setString(3, dto.getAiseq());
			
			return pstat.executeUpdate();
			
					
					
			
			
		} catch (Exception e) {
			System.out.println("AdminDAO.sedit");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @author 박민수
	 * 회원의 보유 포인트 정보를 저장하는 메소드
	 * @param pomap
	 * @return
	 */
	public ArrayList<PointDTO> polist(HashMap<String, String> pomap) {
		try {
			
			   String sql = "";
	            String where = "";
	            
	            if (pomap.get("isSearch").equals("y")) {
	               
	               if (!pomap.get("column").equals("all")) {
	                  where = String.format("where %s like '%%' || '%s' || '%%'"
	                                    , pomap.get("column")
	                                    , pomap.get("word"));
	               } else {
	                  where = String.format("where mname like '%%' || '%s' || '%%' or aiid like '%%' || '%s' || '%%' "
	                                    , pomap.get("word")
	                                    , pomap.get("word"));
	               }
	               
	            }
	            if (pomap.get("tag") == null) {
	                sql = String.format("select * from (select rownum as rnum, a.* from (select * from tblMember a inner join tblAccountInfo b on a.aiSeq = b.aiSeq order by mseq) a %s) where rnum between %s and %s", where, pomap.get("begin"), pomap.get("end"));
	             } 
	             
	             pstat = conn.prepareStatement(sql);
	             
	             rs = pstat.executeQuery();
	             
	             
	             ArrayList<PointDTO> polist = new ArrayList<PointDTO>();
	             
	             while (rs.next()) {
	                
	                PointDTO dto = new PointDTO();
	                
	                //회원 정보
	               
	                dto.setAiid(rs.getString("aiid"));
	                dto.setMpoint(rs.getString("mpoint"));
	                dto.setMname(rs.getString("mname"));
	                
	               
	                polist.add(dto);
	                
	             }
	             
	             return polist;
			
		} catch (Exception e) {
			System.out.println("AdminDAO.polist");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author 박민수
	 * 포인트를 갖고있는 회원의 총명수를 가져오는 매소드
	 * @param pomap
	 * @return
	 */
	public int pogetTotalCount(HashMap<String, String> pomap) {
		try {
			
			 String sql = "";
	         String where = "";
	         
	         if (pomap.get("isSearch").equals("y")) {
	         
	         if (!pomap.get("column").equals("all")) {
                where = String.format("where %s like '%%' || '%s' || '%%'"
                                  , pomap.get("column")
                                  , pomap.get("word"));
             } else {
                where = String.format("where mname like '%%' || '%s' || '%%' or aiid like '%%' || '%s' || '%%'"
                      , pomap.get("word")
                      , pomap.get("word")
                     );
             }
             
	         }
          
			   
           sql = "select count(*) as cnt from tblMember " + where; 
           
           stat = conn.createStatement();
           
           rs = stat.executeQuery(sql);
           
           if (rs.next()) {
              return rs.getInt("cnt");
           }
           
			
		} catch (Exception e) {
			System.out.println("AdminDAO.pogetTotalCount");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @author 박민수
	 * 회원의 포인트를 가져오는 메소드
	 * @param mseq
	 * @return
	 */
	public PointDTO getPoint(String mseq) {
		try {
			String sql = "select tblMember.*,(select aiSeq from tblAccountInfo where aiseq = tblMember.aiseq)as cnum from tblMember where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, mseq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				PointDTO dto = new PointDTO();
				
				dto.setMseq(rs.getString("mseq"));
				dto.setMname(rs.getString("mname"));
				dto.setAiid(rs.getString("aiid"));
				dto.setMpoint(rs.getString("mpoint"));
				dto.setAiseq(rs.getString("aiseq"));
				
				
				return dto;
				
			}
			
		} catch (Exception e) {
			System.out.println("AdminDAO.getPoint");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author 박민수
	 * 모든 회원에게 쿠폰을 지급하는 메소드
	 * @param coseq
	 * @param aiseq
	 * @return
	 */
	public int InsertAllCoupon(String coseq, String aiseq) {
		try {
			
			int number = 0;
			
			String sql = "select count(*) as cnt from tblMember "; 
	            
	        stat = conn.createStatement();
	            
	        rs = stat.executeQuery(sql);
	            
	        if (rs.next()) {
	        	
	        	number = rs.getInt("cnt");
	        	 
	        }
			
			
				
			for(int i = 1 ; i<=299 ; i++){
			        
				 sql = "insert into tblCouponbox (cbseq, coseq, aiseq) values (seqcouponbox.nextval,?,?)";
			        
				   	
				 
			         pstat = conn.prepareStatement(sql);
					 
					 pstat.setString(1, coseq);;
					 
					 pstat.setInt(2, i);
					 
					 pstat.executeUpdate();
					
					 
					 
			 };
			 
		
			//System.out.println(sql);
			
			
		} catch (Exception e) {
			System.out.println("AdminDAO.InsertAllCoupon");
			e.printStackTrace();
		}
		return 0;
	}




	
}

