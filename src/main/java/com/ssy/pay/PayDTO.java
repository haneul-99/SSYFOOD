package com.ssy.pay;

import lombok.Data;


@Data
public class PayDTO {
	
	//Member
	private String mseq;
	private String maddress;
	private String mname;
	
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

	
	
}
