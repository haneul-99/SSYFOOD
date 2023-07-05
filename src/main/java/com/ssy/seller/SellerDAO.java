package com.ssy.seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.ssy.DBUtil;
import com.ssy.product.ProductDTO;
import com.ssy.qnaboard.QnaDTO;

public class SellerDAO {

	Connection conn = null;
	Statement stat = null;
	PreparedStatement pstat = null;
	ResultSet rs = null;
	
	public SellerDAO() {
		conn = DBUtil.open();
	}
	
	/**
	    * @author 유동환
	    * <p>판매자가 본인이 등록한 상품들을 가져오는 메소드입니다.</p>
	    * @param  sSeq 
	    * @return ArrayList
	    */
	public ArrayList<ProductDTO> productList(String sSeq) {
		
		try {
			String sql = "select * from tblProduct p inner join tblSeller s on p.sseq = s.sseq where s.sseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, sSeq);
			rs = pstat.executeQuery();
			ArrayList<ProductDTO> list = new ArrayList<>(); 
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setPseq(rs.getString("pseq"));
				dto.setSseq(rs.getString("sseq"));
				dto.setPname(rs.getString("pname"));
				dto.setPimage(rs.getString("pimage"));
				
				dto.setPprice(rs.getString("pprice"));
				
				dto.setPorigin(rs.getString("porigin"));
				dto.setPquantity(rs.getString("pquantity"));
				dto.setPdiscount(rs.getString("pdiscount"));
				dto.setPrefundox(rs.getString("prefundOX"));
				dto.setPamount(rs.getString("pamount"));
				dto.setDseq(rs.getString("dseq"));
				dto.setSseq(rs.getString("sseq"));
				dto.setCseq(rs.getString("cseq"));
				dto.setStorename(rs.getString("sstore"));
				dto.setSname(rs.getString("sname"));
				dto.setPost(rs.getString("post"));
				list.add(dto);
			}
			return list;
			
			
		} catch (Exception e) {
			System.out.println("SellerDAO.productList");
			e.printStackTrace();
		}
		
		
		return null;
	}
	/**
	    * @author 유동환
	    * <p>판매자의 이름을 가져오는 메소드입니다.</p>
	    * @param  sSeq
	    * @return String
	    */
	public String getSName(String sSeq) {

		try {
			
			String sql = "select sname from tblSeller where sseq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sSeq);
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				return rs.getString("sname");
				
			}
			
			
		} catch (Exception e) {
			System.out.println("SellerDAO.getSName");
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	    * @author 유동환
	    * <p>판매자의 판매된 상품의 개수를 가져오는 메소드, 차트활용</p>
	    * @param  sseq
	    * @return ArrayList
	    */
	public ArrayList<ChartDTO> listChart(String sseq) {

		try {
			
			String sql = "select sum(pprice * (100-pdiscount)/100) as price,pname,count(p.pname) as cnt from tblorderlist ol inner join tblorderdetail od on ol.olseq = od.olseq inner join tblProduct p on p.pseq = od.pseq inner join tblSeller s on p.sseq = s.sseq where s.sseq = ?  group by p.pname";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sseq);
			rs = pstat.executeQuery();
			
			ArrayList<ChartDTO> list = new ArrayList<>();
			while(rs.next()) {
				ChartDTO dto = new ChartDTO();
				dto.setProductname(rs.getString("pname"));
				dto.setCount(rs.getString("cnt"));
				dto.setPrice(rs.getString("price"));
				
				list.add(dto);
			}
			
			return list;
		} catch (Exception e) {
			System.out.println("SellerDAO.listChart");
			e.printStackTrace();
		}
		
		
		
		
		return null;
	}

	
	/**
	    * @author 유동환
	    * <p>판매자가 등록한상품에 대한 주문내역 리스트를 가져오는 메소드입니다.</p>
	    * @param  
	    * @return ArrayList
	    */
	public ArrayList<OrderDTO> orderList(String sseq, HashMap<String, String> map) {

		try {
			
			String sql = "select * from (select rownum as rnum,ol.* from vworderlist ol where sseq = ? order by oldate) where rnum between ? and ? order by oldate desc";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sseq);
			pstat.setString(2, map.get("begin"));
			pstat.setString(3, map.get("end"));
			rs = pstat.executeQuery();
			
			ArrayList<OrderDTO> list = new ArrayList<>();
			while(rs.next()) {
				OrderDTO dto = new OrderDTO();
				dto.setPseq(rs.getString("pseq"));
				dto.setPname(rs.getString("pname"));
				dto.setOladdress(rs.getString("oladdress"));
				dto.setOlname(rs.getString("olname"));
				dto.setMname(rs.getString("mname"));
				dto.setMtel(rs.getString("mtel"));
				dto.setOlstate(rs.getString("olstate"));
				dto.setSname(rs.getString("sname"));
				dto.setSseq(rs.getString("sseq"));
				dto.setOldate(rs.getString("oldate"));
				dto.setOdquantity(rs.getString("odquantity"));
				dto.setOdprice(rs.getString("odprice"));
				dto.setTotalprice(rs.getString("oltotalprice"));
				dto.setOlseq(rs.getString("olseq"));
				
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
	    * @author 유동환
	    * <p>판매자 주문내역의 전체 수를 가져오는 메소드입니다.</p>
	    * @param  sseq
	    * @return int
	    */
	public int getTotalCount(String sseq) {
		try {
			
			String sql = "select count(pseq) as cnt from vworderList where sseq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sseq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				return Integer.parseInt(rs.getString("cnt"));
			}
			
		} catch (Exception e) {
			System.out.println("SellerDAO.getTotalCount");
			e.printStackTrace();
		}
		return 0;
	}
	/**
	    * @author 유동환
	    * <p>판매자가 주문내역에서 수정한 주문상태를 반영하는 메소드입니다.</p>
	    * @param  map
	    * @return int
	    */
	public int editState(HashMap<String, String> map) {

		try {
			String sql = "update tblorderlist set olstate= ? where olseq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("olstate"));
			pstat.setString(2, map.get("olseq"));
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("SellerDAO.editState");
			e.printStackTrace();
		}
		
		
		return 0;
	}

	
	/**
	    * @author 유동환
	    * <p>오늘(오늘 00시~ 익일 00시) 매출을 가져오는 메소드</p>
	    * @param  
	    * @return ArrayList
	    */
	public String todaySales(String sseq) {

try {
			
			String sql = "select odprice*odquantity as price,sname from tblorderlist ol inner join tblorderdetail od on ol.olseq = od.olseq inner join tblProduct p on p.pseq = od.pseq inner join tblSeller s on p.sseq = s.sseq where oldate between to_date(to_char(trunc(sysdate,'dd'),'yyyy/mm/dd'))  and to_date(to_char(trunc(sysdate+1,'dd'),'yyyy/mm/dd')) and s.sseq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sseq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				return rs.getString("price");
			}
			
		} catch (Exception e) {
			System.out.println("SellerDAO.getTotalCount");
			e.printStackTrace();
		}
		
		
		return null;
	}
	/**
	    * @author 유동환
	    * <p>어제(어제 00시~ 오늘 00시) 매출을 가져오는 메소드</p>
	    * @param  
	    * @return ArrayList
	    */
	public String yesterdaySales(String sseq) {

		try {
					
					String sql = "select odprice*odquantity as price,sname from tblorderlist ol inner join tblorderdetail od on ol.olseq = od.olseq inner join tblProduct p on p.pseq = od.pseq inner join tblSeller s on p.sseq = s.sseq where oldate between to_date(to_char(trunc(sysdate-1,'dd'),'yyyy/mm/dd'))  and to_date(to_char(trunc(sysdate,'dd'),'yyyy/mm/dd')) and s.sseq = ?";
					
					pstat = conn.prepareStatement(sql);
					pstat.setString(1, sseq);
					
					rs = pstat.executeQuery();
					
					if(rs.next()) {
						return rs.getString("price");
					}
					
				} catch (Exception e) {
					System.out.println("SellerDAO.getTotalCount");
					e.printStackTrace();
				}
				
				
				return null;
			}
	
	public String totalPrice(String sseq) {

	      try {
	         
	         String sql = "select sum(oltotalprice) as price,s.sseq from tblorderlist ol inner join tblorderdetail od on ol.olseq = od.olseq inner join tblProduct p on p.pseq = od.pseq inner join tblSeller s on p.sseq = s.sseq where  s.sseq = ? group by s.sseq";
	         
	         pstat = conn.prepareStatement(sql);
	         pstat.setString(1, sseq);
	         
	         rs = pstat.executeQuery();
	         
	         if(rs.next()) {
	            return rs.getString("price");
	         }
	         
	      } catch (Exception e) {
	         System.out.println("SellerDAO.getTotalCount");
	         e.printStackTrace();
	      }
	      
	      
	      
	      return null;
	   }

	/**
	 * @author 김유진
	 * <p>판매자 문의 게시글 리스트를 담은 메소드</p>
	 * @param sseq
	 * @return
	 */

	public ArrayList<QnaDTO> getQnaList(String sseq) {
		
		try {
			String sql = "select * from tblQna q inner join tblProduct p on q.pseq = p.pseq inner join tblMember m on q.mseq = m.mseq inner join tblSeller s on p.sseq = s.sseq where s.sseq = ? order by qdate";
			
			
			pstat = conn.prepareStatement(sql);

	        pstat.setString(1, sseq);
	        
	        ArrayList<QnaDTO> list = new ArrayList<QnaDTO>();
	        
	        rs = pstat.executeQuery();
	        
	        while (rs.next()) {
	        	
	        	QnaDTO dto = new QnaDTO();
	        	
	        	dto.setQseq(rs.getString("qseq"));
	        	dto.setQtitle(rs.getString("qtitle"));
	        	dto.setQcontent(rs.getString("qcontent"));
	        	dto.setQdate(rs.getString("qdate"));
	        	dto.setMname(rs.getString("mname"));
	        	dto.setPseq(rs.getString("pseq"));
	        	dto.setPname(rs.getString("pname"));
	        	
	        	
	        	list.add(dto);
	        	
	        }
	        
	        return list;
			
		} catch (Exception e) {
			System.out.println("SellerDAO.getQnaList");
			e.printStackTrace();
		}
		
		return null;
	}
}
