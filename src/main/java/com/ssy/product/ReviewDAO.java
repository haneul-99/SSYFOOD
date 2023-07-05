package com.ssy.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.ssy.DBUtil;
import com.ssy.member.MemberDAO;
import com.ssy.member.MemberDTO;

public class ReviewDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private PreparedStatement pstat1;
	private ResultSet rs;

	public ReviewDAO() {
		conn = DBUtil.open();

	}

	public ArrayList<ArrayList> getReview(String pseq) {
		
		try {

			String sql = "select * from tblReview rv inner join tblMember m on rv.mseq=m.mseq where pseq=?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, pseq);

			rs = pstat.executeQuery();
			
			ArrayList<ArrayList> list1 = new ArrayList<ArrayList>();
			
			if (rs.next()) {

				ReviewDTO dto = new ReviewDTO();
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("rseq"));
				list.add(rs.getString("rcontent"));
				list.add(rs.getString("mname"));
				list.add(rs.getString("rdate"));
				list1.add(list);

			}
			return list1;

		} catch (Exception e) {
			System.out.println("Review.getReview");
			e.printStackTrace();
		}

		return null;
		
	}
	
	

}
