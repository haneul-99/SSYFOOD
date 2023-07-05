package com.ssy.member;

import lombok.Data;

/**
 * 
 * @author 유동환
 * <p>장바구니에 들어갈 상품정보를 담은 클래스입니다.</p>
 *
 */
@Data
public class BucketDTO {

	
	private String bseq;
	private String pseq;
	private String mseq;
	
	private String bprice;
	
	private String pname;
	private String pimage;
	
	private String pcount;
}
