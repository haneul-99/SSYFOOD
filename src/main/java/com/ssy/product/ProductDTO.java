package com.ssy.product;

import lombok.Data;

/**
 * 
 * @author 유동환
 * <p>판매되는 상품에 대한 정보를 담은 클래스입니다.</p>
 *
 */

@Data
public class ProductDTO {
	
	private String pseq;
	private String pname;
	private String pimage;
	private String pprice;
	private String porigin;
	private String pquantity;
	private String pdiscount;
	private String prefundox;
	private String pamount;
	private String dseq;
	private String sseq;
	private String cseq;
	private String pregdate;
	
	private String qseq;
	private String qtitle;
	private String qcontent;
	private String qdate;
	private String mname;
	
	
	private String storename;
	private String saddress;
	private String sname;
	private String post;
	
	private String rnum;
	
	private String aiid;
	private String aiseq;
}
