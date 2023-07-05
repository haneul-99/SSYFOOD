package com.ssy.admin;

import lombok.Data;


@Data
public class CouponDTO {
	/*
	 	coSeq
		coName 쿠폰이름
		coDiscount 할인율
		coContent 내용
		coDate 날짜
	 */
	
	private String coseq;
	private String coname;
	private String codiscount;
	private String cocontent;
	private String codate;
	private String aiseq;
}
