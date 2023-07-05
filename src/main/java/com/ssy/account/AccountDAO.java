package com.ssy.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ssy.DBUtil;
import com.ssy.RandomDTO;
import com.ssy.member.MemberDTO;
import com.ssy.seller.SellerDTO;


/**
 * 
 * @author 구대현
 * <p>계정관리 관련 데이터베이스에 접근하는 클래스입니다.</p>
 * 
 */

public class AccountDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public AccountDAO() {
		conn = DBUtil.open();
	}
	
	/**
	 * 
	 * @author 구대현
	 * <p>아이디와 비밀번호를 확인하여 회원로그인을 시도하는 메소드입니다.</p>
	 * 
	 */
	public AccountDTO memberLogin(AccountDTO dto) {
		try {

			String sql = "select a.aiSeq, a.aiLv, m.mname, m.mseq from tblaccountinfo a inner join tblmember m on a.aiseq = m.aiseq where a.aiid= ? and a.aiPw = ? and a.ailv = 1 and a.aiOX = 'Y'";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getAiid());
			pstat.setString(2, dto.getAipw());

			rs = pstat.executeQuery();

			if (rs.next()) {

				AccountDTO result = new AccountDTO();

				result.setAiseq(rs.getString("aiSeq"));
				result.setAilv(rs.getString("aiLv"));
				result.setMname(rs.getString("mName"));
				result.setMseq(rs.getString("mSeq"));

				return result;
			}

		} catch (Exception e) {
			System.out.println("AccountDAO.login");
			e.printStackTrace();
		}

		return null;
	}

	
	/**
	 * 
	 * @author 구대현
	 * <p>아이디와 비밀번호를 확인하여 판매자로그인을 시도하는 메소드입니다.</p>
	 * 
	 */
	public AccountDTO sellerLogin(AccountDTO dto) {
		try {

			String sql = "select a.aiSeq, a.aiLv, s.sname, s.sseq from tblaccountinfo a inner join tblseller s on a.aiseq = s.aiseq where a.aiid= ? and a.aiPw = ? and a.ailv = 2 and a.aiOX = 'Y'";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getAiid());
			pstat.setString(2, dto.getAipw());

			rs = pstat.executeQuery();

			if (rs.next()) {

				AccountDTO result = new AccountDTO();

				result.setAiseq(rs.getString("aiSeq"));
				result.setAilv(rs.getString("aiLv"));
				result.setSname(rs.getString("sName"));
				result.setSseq(rs.getString("sSeq"));

				return result;
			}

		} catch (Exception e) {
			System.out.println("AccountDAO.login");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @author 구대현
	 * <p>아이디와 비밀번호를 확인하여 관리자로그인을 시도하는 메소드입니다.</p>
	 * 
	 */
	public AccountDTO adminLogin(AccountDTO dto) {
		try {

			String sql = "select a.aiSeq, a.aiLv, ad.aname, ad.aseq from tblaccountinfo a inner join tbladmin ad on a.aiseq = ad.aiseq where a.aiid= ? and a.aiPw = ? and a.ailv = 3 and a.aiOX = 'Y'";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getAiid());
			pstat.setString(2, dto.getAipw());

			rs = pstat.executeQuery();

			if (rs.next()) {

				AccountDTO result = new AccountDTO();

				result.setAiseq(rs.getString("aiseq"));
				result.setAilv(rs.getString("ailv"));
				result.setAname(rs.getString("aname"));
				result.setAseq(rs.getString("aseq"));
				
				return result;
			}

		} catch (Exception e) {
			System.out.println("AccountDAO.login");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @author 구대현
	 * <p>로그인 시 로그를 기록하는 메소드입니다.</p>
	 * 
	 */
	public void addLoginLog(String id) {

		try {

			String sql = "insert into tblLoginLog(llSeq, llDate, aiSeq) values (seqLoginLog.nextVal, default, ?)";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, getSeq(id));

			pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("AccountDAO.addLoginLog");
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @author 구대현
	 * <p>아이디를 통해 계정번호를 가져오는 메소드입니다.</p>
	 * 
	 */
	public String getSeq(String id) {

		try {

			String seq = "";

			String sql = "select aiSeq from tblAccountInfo where aiId = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, id);

			rs = pstat.executeQuery();

			if (rs.next()) {
				seq = rs.getString("aiSeq");
			}

			return seq;

		} catch (Exception e) {
			System.out.println("AccountDAO.setSeq");
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 
	 * @author 구대현
	 * <p>입력한 정보에 맞는 계정 아이디를 찾는 메소드입니다.</p>
	 * 
	 */
	public String findId(MemberDTO mdto) {
		
		try {
			
			String sql = "select a.aiId from tblAccountInfo a inner join tblMember m on a.aiseq = m.aiseq where m.mname = ? and m.mjumin = ? and a.aiox='Y'";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, mdto.getMname());
			pstat.setString(2, mdto.getMjumin());
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				String result = rs.getString("aiId");
				
				return result;
			}
			
			
		} catch (Exception e) {
			System.out.println("AccountDAO.findId");
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	/**
	 * 
	 * @author 구대현
	 * <p>비밀번호 찾기에서 좀 더 정확하게 아이디를 찾는 메소드입니다.</p>
	 * 
	 */
	public String findCorrectId(MemberDTO mdto) {
		
		try {
			
			String sql = "select a.aiId from tblAccountInfo a inner join tblMember m on a.aiseq = m.aiseq where m.mname = ? and m.mjumin = ? and m.mtel = ? and a.aiox='Y'";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, mdto.getMname());
			pstat.setString(2, mdto.getMjumin());
			pstat.setString(3, mdto.getMtel());
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				String result = rs.getString("aiId");
				
				return result;
			}
			
			
		} catch (Exception e) {
			System.out.println("AccountDAO.findId");
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	/**
	 * 
	 * @author 구대현
	 * <p>아이디 찾기에서 최근 로그인 시각을 보여주는 메소드입니다.</p>
	 * 
	 */
	public String getLoginLog(String seq) {
		
		try {
			
			String sql = "select to_char(lldate,'YYYY-MM-DD HH24:MI:SS') as time from tblloginlog where llseq =(select max(llseq) from tblloginlog where aiseq = ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				String log = rs.getString("time");
				
				return log;
			}
			
			
		} catch (Exception e) {
			System.out.println("AccountDAO.getLoginLog");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 
	 * @author 구대현
	 * <p>비밀번호 찾기에서 비밀번호를 재설정하는 메소드입니다.</p>
	 * 
	 */
	public int resetPassword(String id, String pw) {
		
		try {
			
			String sql = "update tblaccountinfo set aipw = ? where aiid = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, pw);
			pstat.setString(2, id);
			
			pstat.executeQuery();
			
			return 1;
			
		} catch (Exception e) {
			System.out.println("AccountDAO.resetPassword");
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	/**
	 * 
	 * @author 구대현
	 * <p>아이디를 통해 비밀번호를 가져오는 메소드입니다.</p>
	 * 
	 */
	public String getPw(String id) {
		
		try {
			
			String sql = "select aiPw from tblAccountInfo where aiId = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, id);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				String result = rs.getString("aiPw");
				
				return result;
				
			}
			
		} catch (Exception e) {
			System.out.println("AccountDAO.getPw");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
	 * @author 구대현
	 * <p>아이디가 존재하는지 확인하는 메소드입니다.</p>
	 * 
	 */
	public boolean existId(String id) {
		
		try {
			
			String sql = "select count(*) as cnt from tblaccountInfo where aiid = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, id);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				int result = rs.getInt("cnt");
				
				if(result==1) {
					return true;
				} else {
					return false;
				}
				
			}
			
		} catch (Exception e) {
			System.out.println("AccountDAO.existId");
			e.printStackTrace();
		}
		
		
		
		
		return false;
	}
	

	/**
	 * @author 유동환
	 * <p> 회원,판매자, 관리자 구분없이 아이디와 계정,등급,활동여부를 우선적으로 추가하는 메소드입니다.</p>
	 * @param adto 받아온 계정정보
	 * @param lv 회원 1 판매자 2 관리자 3
	 * @return int
	 */
public int addAccount(AccountDTO adto, String lv) {
    try {
       
  	 String sql = "insert into tblAccountInfo (aiSeq,aiId,aiPw,aiLv,aiOx) values (seqAccountInfo.nextVal,?,?,?,default)";
       pstat = conn.prepareStatement(sql);
       
       pstat.setString(1, adto.getAiid());
       pstat.setString(2, adto.getAipw());
       pstat.setString(3, lv);
       
       return pstat.executeUpdate();
    } catch (Exception e) {
       System.out.println("AccountDAO.addAccount");
       e.printStackTrace();
    }
    
    
    
    return 0;
}

/**
 * @author 유동환
 * <p> 추가된 계정정보를 참조하여 판매자의 정보를 저장하여 판매자계정을 추가하는 메소드입니다.</p>
 * @param adto 받아온 계정정보
 * @param lv 회원 1 판매자 2 관리자 3
 * @return int
 */
public int addSeller(SellerDTO sdto) {

    try {
       
       String sql = "insert into tblSeller (sSeq,sName,sJumin,sTel,sStore,sAddress,sInfo,aiSeq) values (seqSeller.nextVal,?,?,?,?,?,?,(select max(aiseq) from tblAccountInfo))";
       pstat = conn.prepareStatement(sql);
       
       pstat.setString(1, sdto.getSname());
       pstat.setString(2, sdto.getSjumin());
       pstat.setString(3, sdto.getStel());
       pstat.setString(4, sdto.getSstore());
       pstat.setString(5, sdto.getSaddress());
       pstat.setString(6, sdto.getSinfo());
       
       return pstat.executeUpdate();
    } catch (Exception e) {
       System.out.println("AccountDAO.addAccount");
       e.printStackTrace();
    }
    
    return 0;
 }

/**
 * @author 김하늘
 * <p>추가된 회원 계정정보를 참고하여 회원의 정보를 추가하는 메소드입니다.</p>
 * @param mdto 받아온 회원계정정보
 * @return int
 */
public int addMember(MemberDTO mdto) {
	
	try {
		
		String sql = "insert into tblMember (mSeq,mName,mJumin,mAddress,mTel,mPoint,aiSeq) values (seqMember.nextVal,?,?,?,?,default,(select max(aiseq) from tblAccountInfo))";
		
         pstat = conn.prepareStatement(sql);
         
         pstat.setString(1, mdto.getMname());
         pstat.setString(2, mdto.getMjumin());
         pstat.setString(3, mdto.getMaddress());
         pstat.setString(4, mdto.getMtel());
         
         System.out.println(mdto);
         
         return pstat.executeUpdate();
		
	} catch (Exception e) {
		System.out.println("AccountDAO.addMember");
		e.printStackTrace();
	}
	
	return 0;
}

/**
 * @author 김하늘
 * <p>회원 주민등록번호 검색하여 중복여부를 판단하는 메소드입니다.</p>
 * @param jumin 회원 주민등록번호
 * @return int 
 */
public int checkjumin(String jumin) {
	
	try {
		
		
		String sql = "select count(*) as cnt from tblMember where mJumin = ?";
         
        pstat = conn.prepareStatement(sql);
         
        pstat.setString(1, jumin);
         
        rs = pstat.executeQuery();
         
        if (rs.next()) {
            
           return rs.getInt("cnt"); //1 or 0
        }
		
		
	} catch (Exception e) {
		System.out.println("AccountDAO.checkjumin");
		e.printStackTrace();
	}
	
	return 0;
}

/**
 * @author 김하늘
 * <p>판매자 주민등록번호 검색하여 중복여부를 판단하는 메소드입니다.</p>
 * @param jumin 판매자 주민등록번호
 * @return int 
 */
public int checkSellerJumin(String jumin) {
	try {
		
		String sql = "select count(*) as cnt from tblSeller where sJumin = ?";
         
        pstat = conn.prepareStatement(sql);
         
        pstat.setString(1, jumin);
         
        rs = pstat.executeQuery();
         
        if (rs.next()) {
            
           return rs.getInt("cnt"); //1 or 0
        }
		
	} catch (Exception e) {
		System.out.println("AccountDAO.checkSellerJumin");
		e.printStackTrace();
	}
	return 0;
}

/**
 * @author 김하늘
 * <p>받아온 아이디가 이미 등록된 아이디인지 확인하고 중복된 아이디가 없을 경우 저장하는 메소드입니다.</p>
 * @param id 받아온 아이디
 * @return int
 */
public int checkid(String id) {
	
	try {
		
		String sql = "select count(*) as cnt from tblAccountInfo where aiId = ?";
         
        pstat = conn.prepareStatement(sql);
         
        pstat.setString(1, id);
         
        rs = pstat.executeQuery();
         
        if (rs.next()) {
            
           return rs.getInt("cnt"); //1 or 0
        }
         
		
	} catch (Exception e) {
		System.out.println("AccountDAO.checkid");
		e.printStackTrace();
	}
	
	return 0;
}




}