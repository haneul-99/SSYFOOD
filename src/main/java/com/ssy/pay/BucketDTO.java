package com.ssy.pay;

import java.util.ArrayList;

import lombok.Data;

@Data
public class BucketDTO {

	private ArrayList<String> pcountList;
	private ArrayList<String> pseqList;
	private ArrayList<String> bpriceList;
	
	
	//orderList
		private String olSeq;
		private String olAddress;
		private String olDate;
		private String olName;
		private String olState;
		private int pdprice;
		
		
	//orderDetail
		private String odSeq;
		private String odPrice;
		private String totalprice;
		private int odQuantity;
		private String pSeq;
		
		//Member
		private String mseq;
		private String maddress;
		private String mname;

	
}
