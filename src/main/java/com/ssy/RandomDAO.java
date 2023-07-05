package com.ssy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ssy.member.MemberDTO;


/**
 * 
 * @author 구대현
 * <p>랜덤박스 관련 데이터베이스에 접근하는 클래스입니다.</p>
 * 
 */
public class RandomDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public RandomDAO() {
		conn = DBUtil.open();
	}
	
	/**
	 * 
	 * @author 구대현
	 * <p>랜덤박스 상품을 추첨하여 보상정보를 가져오는 메소드입니다.</p>
	 * 
	 */
	public RandomDTO getReward(String rewardNum) {

		try {

			String sql = "select * from tblrandombox where rbseq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, rewardNum);

			rs = pstat.executeQuery();

			RandomDTO dto = new RandomDTO();
			
			if (rs.next()) {
				dto.setRbseq(rs.getString("rbseq"));
				dto.setRbcontent(rs.getString("rbcontent"));
			}
			
			return dto;
			
		} catch (Exception e) {
			System.out.println("RandomDAO.getReward");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @author 구대현
	 * <p>회원의 주소와 보유 포인트를 가져오는 메소드입니다.</p>
	 * 
	 */
	public MemberDTO getMember(String mseq) {
		
		try {
			
			String sql = "select * from tblmember where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, mseq);
			
			rs = pstat.executeQuery();
			
			MemberDTO mdto = new MemberDTO();
			
			if(rs.next()) {
				
				mdto.setMaddress(rs.getString("maddress"));
				mdto.setMpoint(rs.getInt("mpoint"));
			}
			
			return mdto;
			
		} catch (Exception e) {
			System.out.println("RandomDAO.getMember");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 
	 * @author 구대현
	 * <p>구매버튼을 눌렀을 때 포인트를 소모하는 메소드입니다.</p>
	 * 
	 */
	public int usePoint(String mseq) {
		
		try {
			
			String sql = "update tblMember set mPoint = mPoint-10000 where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, mseq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("RandomDAO.usePoint");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 
	 * @author 구대현
	 * <p>상품번호를 통해 상품명을 가져오는 메소드입니다.</p>
	 * 
	 */
	public String getcontent(String rbseq) {
		
		try {
			
			String sql = "select rbcontent from tblrandombox where rbseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, rbseq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				return rs.getString("rbcontent");
			}
			
		} catch (Exception e) {
			System.out.println("RandomDAO.getcontent");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 
	 * @author 구대현
	 * <p>회원번호를 통해 보유 포인트를 가져오는 메소드입니다.</p>
	 * 
	 */
	public String getPoint(String mseq) {

		try {
			
			String sql = "select mpoint from tblmember where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, mseq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				return rs.getString("mpoint");
				
			}
			
		} catch (Exception e) {
			System.out.println("RandomDAO.getPoint");
			e.printStackTrace();
		}
		
		return null;
	}

}
