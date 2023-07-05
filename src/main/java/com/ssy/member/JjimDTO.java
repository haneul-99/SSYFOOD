package com.ssy.member;

import lombok.Data;
/**
 * 
 * @author 유동환
 * <p>찜목록의 정보를 저장하는 클래스입니다.</p>
 *
 */

@Data
public class JjimDTO {
	private String jseq;
	private String pseq;
	private String mseq;
	
	
	private String pprice;
	private String pdiscount;
	private String pname;
	private String pimage;
}
