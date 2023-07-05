package com.ssy.pay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.ssy.DBUtil;

public class PayDAO {

	private Connection conn;
	private PreparedStatement pstat;
	private Statement stat;
	private ResultSet rs;
	

	public PayDAO() {
		conn = DBUtil.open();
	}

	
	/**
	    * @author 유동환
	    * <p>주문 내역에 상품정보와 구매자 정보를 담는 메소드입니다.</p>
	    * @param  
	    * @return ArrayList
	    */
	public int payProduct(PayDTO dto) {

		try {
			
			String sql = "insert into tblorderList (olSeq,olAddress,olDate,olName,olState,mSeq,oltotalprice) values (seqOrderList.nextVal,?,default,?,default,?,?)";
			
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getOlAddress());
			pstat.setString(2, dto.getOlName());
			pstat.setString(3, dto.getMseq());
			pstat.setInt(4, dto.getPdprice() * dto.getOdQuantity());
			
			return pstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("PayDAO.payProduct");
			e.printStackTrace();
		}
		
		
		
		return 0;
	}

	/**
	    * @author 유동환
	    * <p>가장 최근 주문 내역번호를 가져오는 메소드입니다.</p>
	    * @param  
	    * @return ArrayList
	    */
	public String getOlseq() {

		try {
			String sql = "select max(olseq) as cnt from tblorderlist";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				
				return rs.getString("cnt");
			}
			
			
		} catch (Exception e) {
			System.out.println("PayDAO.getOlseq");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	    * @author 유동환
	    * <p>전체주문내역를 바탕으로 상세주문내역을 저장하는 메소드입니다.</p>
	    * @param  dto
	    * @return int
	    */
	public int addorderDetail(PayDTO dto) {
		
	try {
			
			String sql = "insert into tblOrderDetail (odSeq,odPrice,odQuantity,olSeq,pSeq) values (seqOrderDetail.nextVal,?,?,?,?)";
			
			
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, dto.getPdprice());
			pstat.setInt(2, dto.getOdQuantity());
			pstat.setString(3, dto.getOlSeq());
			pstat.setString(4, dto.getPSeq());
			
			
			return pstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("PayDAO.addorderDetail");
			e.printStackTrace();
		}
		
		
		
		
		return 0;
	}

	/**
	    * @author 유동환
	    * <p>장바구니를 통해 결제한 회원의 정보와 수령자의정보, 총가격을 전체주문내역에 저장하는 메소드입니다. </p>
	    * @param  dto
	    * @return void
	    */
	public void bucketPay(BucketDTO dto) {

		try {
			
			String sql = "insert into tblorderList (olSeq,olAddress,olDate,olName,olState,mSeq, oltotalprice) values (seqOrderList.nextVal,?,default,?,default,?,?)";
			
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getOlAddress());
			pstat.setString(2, dto.getOlName());
			pstat.setString(3, dto.getMseq());
			pstat.setString(4, dto.getTotalprice());
			
			pstat.executeUpdate();
			
			
			
		} catch (Exception e) {
			System.out.println("PayDAO.bucketPay");
			e.printStackTrace();
		}
		
		
		
		
	}

	/**
	    * @author 유동환
	    * <p>장바구니를 통해 주문한 고객의 전체주문 내역을 바탕으로 상세주문내역에 상품들을 저장하는 메소드입니다. </p>
	    * @param  dto
	    * @return int
	    */
	public int bucketPayDetail(BucketDTO dto) {

		try {
			
			String sql = "insert into tblOrderDetail (odSeq,odPrice,odQuantity,olSeq,pSeq) values (seqOrderDetail.nextVal,?,?,?,?)";
			
			pstat = conn.prepareStatement(sql);
			
			for(int i=0; i<dto.getPseqList().size(); i++) {
				
				pstat.setString(1, dto.getBpriceList().get(i));
				pstat.setString(2, dto.getPcountList().get(i));
				pstat.setString(3, dto.getOlSeq());
				pstat.setString(4, dto.getPseqList().get(i));
				
				
				pstat.executeUpdate();
			
			}
			
			return 1;
			
		} catch (Exception e) {
			System.out.println("PayDAO.bucketPayDetail");
			e.printStackTrace();
		}
		
		
		return 0;
	}

	/**
	    * @author 유동환
	    * <p>장바구니에 있는 상품을 결제한 회원의 장바구니 상품을 삭제하는 메소드입니다.</p>
	    * @param  mseq
	    * @return void
	    */
	public void deleteBucket(String mseq) {

		
try {
			
			String sql = "delete from tblBucket where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
				
				pstat.setString(1, mseq);
				
				
				pstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("PayDAO.deleteBucket");
			e.printStackTrace();
		}
		
		
		
		
	}



	
	
}
