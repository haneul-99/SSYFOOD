package com.ssy.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.ssy.DBUtil;

public class MemberDAO {
	
	private Connection conn;
	private PreparedStatement pstat;
	private Statement stat;
	private ResultSet rs;
	

	public MemberDAO() {
		conn = DBUtil.open();
	}

	/**
	    * @author 유동환
	    * <p>장바구니에 상품을 추가하는 메소드입니다.</p>
	    * @param dto 
	    * @return int
	    */
	public int putBucket(BucketDTO dto) {

		try {
			
			String sql = "insert into tblbucket(bSeq,bPrice,pSeq,mSeq) values (seqBucket.nextVal,?,?,?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getBprice());
			pstat.setString(2, dto.getPseq());
			pstat.setString(3, dto.getMseq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("MemberDAO.putBucket");
			e.printStackTrace();
		}
		
		
		return 0;
	}

	/**
	    * @author 유동환
	    * <p>회원이 담은 장바구니 목록을 가져오는 메소드입니다.</p>
	    * @param mseq 
	    * @return ArrayList
	    */
	public ArrayList<BucketDTO> bucketList(String mseq) {

		try {
			
			String sql = "select * from tblBucket b inner join tblProduct p on b.pseq = p.pseq where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, mseq);
			
			rs = pstat.executeQuery();
			
			ArrayList<BucketDTO> list= new ArrayList<>();
			while(rs.next()) {
				
				BucketDTO dto = new BucketDTO();
				dto.setBprice(rs.getString("bprice"));
				dto.setBseq(rs.getString("bseq"));
				dto.setMseq(rs.getString("mseq"));
				dto.setPseq(rs.getString("pseq"));
				
				dto.setPimage(rs.getString("pimage"));
				dto.setPname(rs.getString("pname"));
				
				list.add(dto);
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("MemberDAO.bucketList");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	    * @author 유동환
	    * <p>회원이 담은 장바구니에서 상품을 제거하는 메소드입니다.</p>
	    * @param bseq 
	    * @return int
	    */
	public int deleteBucket(String bseq) {

		
try {
			
			String sql = "delete from tblbucket where bseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, bseq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("MemberDAO.putBucket");
			e.printStackTrace();
		}
		
		
		return 0;
	}



	/**
	    * @author 유동환
	    * <p>회원이 장바구니에 담은 상품들을 결제하는 메소드입니다..</p>
	    * @param mseq countList productList
	    * @return ArrayList
	    */
	public ArrayList<BucketDTO> bucketPayList(String mseq, ArrayList<String> countList, ArrayList<String> productList) {

		
try {
			
			String sql = "select * from tblBucket b inner join tblProduct p on b.pseq = p.pseq where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, mseq);
			
			rs = pstat.executeQuery();
			
			ArrayList<BucketDTO> list= new ArrayList<>();
			int i= 0;
			while(rs.next()) {
				
				BucketDTO dto = new BucketDTO();
				dto.setBprice(rs.getString("bprice"));
				dto.setBseq(rs.getString("bseq"));
				dto.setMseq(rs.getString("mseq"));
				dto.setPseq(productList.get(i));
				
				dto.setPimage(rs.getString("pimage"));
				dto.setPname(rs.getString("pname"));
				dto.setPcount(countList.get(i));
				list.add(dto);
				i++;
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("MemberDAO.bucketList");
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	    * @author 유동환
	    * <p>회원이 찜목록에 상품을 주가하는 메소드입니다.</p>
	    * @param dto
	    * @return void
	    */
	public void addJjim(JjimDTO dto) {

		try {
					
					String sql = "insert into tblJjim(jseq,pseq,mseq) values (seqJjim.nextVal,?,?)";
					
					pstat = conn.prepareStatement(sql);
					
					pstat.setString(1, dto.getPseq());
					pstat.setString(2, dto.getMseq());
					
					pstat.executeUpdate();
				} catch (Exception e) {
					System.out.println("MemberDAO.addJjim");
					e.printStackTrace();
				}
		
		
	}

	/**
	    * @author 유동환
	    * <p>회원의 찜목록을 가져오는 메소드입니다.</p>
	    * @param mseq
	    * @return ArrayList
	    */
	public ArrayList<JjimDTO> jjimList(String mseq) {

		try {
		String sql = "select * from tblJjim j inner join tblProduct p on j.pseq = p.pseq where mseq = ?";
		
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, mseq);
		
		rs = pstat.executeQuery();
		
		ArrayList<JjimDTO> list= new ArrayList<>();
		while(rs.next()) {
			
			JjimDTO dto = new JjimDTO();
			dto.setPname(rs.getString("pname"));
			dto.setPprice(rs.getString("pprice"));
			dto.setPdiscount(rs.getString("pdiscount"));
			dto.setJseq(rs.getString("jseq"));
			dto.setPseq(rs.getString("pseq"));
			dto.setPimage(rs.getString("pimage"));
			
			list.add(dto);
		
		}
		
		return list;
		
	} catch (Exception e) {
		System.out.println("MemberDAO.jjimList");
		e.printStackTrace();
	}
	
		
		return null;
	}

	/**
	    * @author 유동환
	    * <p>회원의 찜목록에서 상품을 삭제하는 메소드입니다.</p>
	    * @param jseq
	    * @return int
	    */
	public int deleteJjim(String jseq) {
		
try {
			
			String sql = "delete from tblJjim where jseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, jseq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("MemberDAO.putBucket");
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	/**
	    * @author 김동석
	    * <p> 전달받은 ID에 해당하는 회원정보를 DB에서 가져오는 메소드입니다.</p>
	    * @param id 받아온 ID
	    * @return MemberDTO
	    */
	public MemberDTO getMember(String id) {

		try {

			String sql = "select * from tblAccountInfo ai inner join tblMember m on ai.aiseq=m.aiseq where ai.aiid=?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, id);

			rs = pstat.executeQuery();

			if (rs.next()) {

				MemberDTO dto = new MemberDTO();

				dto.setMseq(rs.getString("mSeq"));
				dto.setMname(rs.getString("mName"));
				dto.setMjumin(rs.getString("mJumin"));
				dto.setMaddress(rs.getString("mAddress"));
				dto.setMtel(rs.getString("mTel"));
				dto.setMpoint(rs.getInt("mPoint"));
				dto.setAiseq(rs.getString("aiSeq"));
				dto.setPw(rs.getString("aiPw"));

				return dto;

			}

		} catch (Exception e) {
			System.out.println("MemberDAO.getMember");
			e.printStackTrace();
		}

		return null;
	}
	/**
	    * @author 김동석
	    * <p> 전달받은 ID에 해당하는 회원의 판매자 팔로우 정보를 DB에서 가져오는 메소드입니다.</p>
	    * @param id 받아온 ID
	    * @return ArrayList<String>
	    */
	public ArrayList<String> getFollower(String id) {

		try {

			String sql = "select * from tblAccountInfo ai left join tblMember m on ai.aiseq=m.aiseq left join tblsellerFollower sf on m.mseq = sf.mseq left join tblseller s on sf.sseq=s.sseq where ai.aiid=?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, "81");

			// pstat.setString(1, id);

			rs = pstat.executeQuery();

			ArrayList<String> list = new ArrayList<String>();

			while (rs.next()) {

				list.add(rs.getString("sname"));

			}

			return list;

		} catch (Exception e) {
			System.out.println("MemberDAO.getFollower");
			e.printStackTrace();
		}

		return null;
	}
	/**
	    * @author 김동석
	    * <p> 전달받은 ID에 해당하는 회원의 보유 쿠폰 정보를 DB에서 가져오는 메소드입니다.</p>
	    * @param id 받아온 ID
	    * @return ArrayList<ArrayList>
	    */
	public ArrayList<ArrayList> getCoupon(String id) {

		try {

			String sql = "select * from tblAccountInfo ai left join tblMember m on ai.aiseq=m.aiseq left join tblAccountInfo ai on m.aiseq = ai.aiseq  left join tblcouponbox cb on ai.aiseq = cb.aiseq  left join tblCoupon co on cb.coseq= co.coseq where ai.aiid= ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, id);

			rs = pstat.executeQuery();

			ArrayList<ArrayList> list1 = new ArrayList<ArrayList>();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("coname"));
				list.add(rs.getString("codiscount"));
				list.add(rs.getString("cocontent"));
				list.add(rs.getString("codate"));
				list1.add(list);
			}

			return list1;

		} catch (Exception e) {
			System.out.println("MemberDAO.getCoupon");
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	    * @author 김동석
	    * <p> 전달받은 ID에 해당하는 회원의 구매 내역 정보를 DB에서 가져오는 메소드입니다.</p>
	    * @param id 받아온 ID
	    * @return ArrayList<String>
	    */
	public ArrayList<ArrayList> getOrderList(String id) {

		try {

			String sql = "select * from tblOrderList ol left join tblProduct p on ol.pseq=p.pseq left join tblDeliver d on p.dseq=d.dseq where mseq=1";

			pstat = conn.prepareStatement(sql);

			// pstat.setString(1, id);

			rs = pstat.executeQuery();

			ArrayList<ArrayList> list2 = new ArrayList<ArrayList>();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("pname"));
				list.add(rs.getString("pimage"));
				list.add(rs.getString("pprice"));
				list.add(rs.getString("oladdress"));
				list.add(rs.getString("olname"));
				list.add(rs.getString("dname"));
				list.add(rs.getString("oldate"));
				list2.add(list);
			}

			return list2;

		} catch (Exception e) {
			System.out.println("MemberDAO.getOrderList");
			e.printStackTrace();
		}

		return null;

	}
	/**
	    * @author 김동석
	    * <p> 전달받은 ID에 해당하는 회원의 장바구니 정보를 DB에서 가져오는 메소드입니다.</p>
	    * @param id 받아온 ID
	    * @return ArrayList<String>
	    */
	public ArrayList<ArrayList> getBucket(String id) {

		try {

			String sql = "select * from tblBucket b inner join tblProduct p on b.pseq=p.pseq inner join tblMember m on b.mseq=m.mseq inner join tblAccountInfo ai on m.aiseq=ai.aiseq where ai.aiid=?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, id);

			rs = pstat.executeQuery();

			ArrayList<ArrayList> list2 = new ArrayList<ArrayList>();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("pname"));
				list.add(rs.getString("pimage"));
				list.add(rs.getString("pprice"));
				list.add(rs.getString("pseq"));
				list2.add(list);
			}

			return list2;

		} catch (Exception e) {
			System.out.println("MemberDAO.getBucket");
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	    * @author 김동석
	    * <p> 전달받은 ID에 해당하는 회원의 찜 목록 정보를 DB에서 가져오는 메소드입니다.</p>
	    * @param id 받아온 ID
	    * @return ArrayList<String>
	    */
	public ArrayList<ArrayList> getJjim(String id) {

		try {

			String sql = "select * from tblJjim j inner join tblProduct p on j.pseq=p.pseq inner join tblMember m on j.mseq=m.mseq inner join tblAccountInfo ai on m.aiseq= ai.aiseq where ai.aiid=?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, id);

			rs = pstat.executeQuery();

			ArrayList<ArrayList> list2 = new ArrayList<ArrayList>();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("pname"));
				list.add(rs.getString("pimage"));
				list.add(rs.getString("pseq"));
				list2.add(list);
			}

			return list2;

		} catch (Exception e) {
			System.out.println("MemberDAO.getBucket");
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	    * @author 김동석
	    * <p> 전달받은 ID에 해당하는 회원의 PW 정보를 DB에서 수정하기 위한 메소드입니다.</p>
	    * @param dto 받아온 pw
	    * @param id 받아온 ID
	    * @return int
	    */
	public int edit(MemberDTO dto, String id) {

		try {
			String sql = "update tblAccountInfo set aipw=? where aiid=?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getPw());
			pstat.setString(2, id);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.edit");
			e.printStackTrace();
		}

		return 0;
	}
	
	/**
	    * @author 김동석
	    * <p> 전달받은 ID에 해당하는 회원의 주소 정보를 DB에서 수정하기 위한 메소드입니다.</p>
	    * @param dto 받아온 주소 정보
	    * @param aiseq 받아온 회원 번호
	    * @return int
	    */
	public int edit1(MemberDTO dto, String aiseq) {

		try {
			String sql = "update tblMember set maddress=? where aiseq=?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getMaddress());
			pstat.setString(2, aiseq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.edit");
			e.printStackTrace();
		}

		return 0;
	}
	/**
	    * @author 김동석
	    * <p> 전달받은 ID에 해당하는 회원의 활동 상태를 변경하기 위한 메소드입니다.</p>
	    * @param id 받아온 ID
	    * @return int
	    */
	public int MemberX(String id) {

		try {
			String sql = "update tblAccountInfo set aiOx=? where aiid=?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, "N");
			pstat.setString(2, id);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.edit");
			e.printStackTrace();
		}

		return 0;
	}
	
	/**
	    * @author 김동석
	    * <p> 전달받은 ID에 해당하는 회원의 주문내역을 DB에서 가져오는 메소드입니다.</p>
	    * @param id 받아온 ID
	    * @return ArrayList<MemberDTO>
	    */
	public ArrayList<MemberDTO> orderList(String id) {

	      try {
	         
	         String sql = "select * from tblOrderList ol inner join tblOrderDetail od on ol.olSeq = od.olSeq inner join tblMember m on m.mseq = ol.mseq inner join tblAccountInfo ai on ai.aiseq = m.aiseq inner join tblProduct p on p.pseq = od.pseq where aiid=?";
	         pstat = conn.prepareStatement(sql);
	         pstat.setString(1, id);
	         rs = pstat.executeQuery();
	          
	         ArrayList<MemberDTO> list = new ArrayList<>();
	         while(rs.next()) {
	        	 // 번호 상품명 상품이미지 금액 배송지 수령인 택배사 완료날짜
	            MemberDTO dto = new MemberDTO();
	            dto.setOname(rs.getString("pname"));
	            dto.setOimage(rs.getString("pimage"));
	            dto.setOprice(rs.getString("odprice"));
	            dto.setOaddress(rs.getString("maddress"));
	            dto.setMname(rs.getString("mname"));
	            dto.setMaddress(rs.getString("maddress"));
	            dto.setOdate(rs.getString("olDate"));
	            
	            
	            list.add(dto);
	         }
	         
	         return list;
	         
	         
	      } catch (Exception e) {
	         System.out.println("SellerDAO.orderList");
	         e.printStackTrace();
	      }
	      
	      
	      
	      
	      return null;
	   }
	
	/**
	    * @author 김동석
	    * <p> 전달받은 ID에 해당하는 회원의 문의 게시글 내용을 DB에서 가져오는 메소드입니다.</p>
	    * @param id 받아온 ID
	    * @return ArrayList<ArrayList>
	    */
	   public ArrayList<ArrayList> getQna(String id) {

			try {

				String sql = "select * from tblQna q inner join tblMember m on q.mseq = m.mseq inner join tblAccountInfo ai on ai.aiseq = m.aiseq inner join tblProduct p on p.pseq = q.pseq where ai.aiid=?";

				pstat = conn.prepareStatement(sql);

				pstat.setString(1, id);

				rs = pstat.executeQuery();
				

				ArrayList<ArrayList> list2 = new ArrayList<ArrayList>();

				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("pname"));
					list.add(rs.getString("qtitle"));
					list.add(rs.getString("qcontent"));
					list.add(rs.getString("qdate"));
					list.add(rs.getString("pseq"));
					list2.add(list);
				}

				return list2;

			} catch (Exception e) {
				System.out.println("MemberDAO.getQna");
				e.printStackTrace();
			}

			return null;
		}
	   
	   /**
	    * @author 김동석
	    * <p> 전달받은 ID에 해당하는 회원의 후기 게시글 내용을 DB에서 가져오는 메소드입니다.</p>
	    * @param id 받아온 ID
	    * @return ArrayList<ArrayList>
	    */
	   public ArrayList<ArrayList> getReview(String id) {
		   
		   try {
			   
			   String sql = "select * from tblreview rv inner join tblMember m on rv.mseq = m.mseq inner join tblAccountInfo ai on ai.aiseq = m.aiseq inner join tblProduct p on p.pseq = rv.pseq  where ai.aiid=?";
			   
			   pstat = conn.prepareStatement(sql);

				pstat.setString(1, id);

				rs = pstat.executeQuery();
				

				ArrayList<ArrayList> list2 = new ArrayList<ArrayList>();

				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("pname"));
					list.add(rs.getString("rcontent"));
					list.add(rs.getString("rdate"));
					list2.add(list);
				}

				return list2;

			} catch (Exception e) {
				System.out.println("MemberDAO.getQna");
				e.printStackTrace();
			}

			return null;
	   }

	public void addbucket(String mseq, String pseq) {
		
		try {
			
			String sql = "";
			
			
			
		} catch (Exception e) {
			System.out.println("MemberDAO.addbucket");
			e.printStackTrace();
		}
		
	}

	
}
