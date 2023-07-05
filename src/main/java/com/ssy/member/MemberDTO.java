package com.ssy.member;

import lombok.Data;
/**
 * 
 * @author 김동석
 * <p>멤버 페이지에 사용되는 데이터를 정의하는 클래스입니다.</p>
 *
 */
@Data
public class MemberDTO {
	private String mseq;
	private String mname;
	private String mjumin;
	private String maddress;
	private String mtel;
	private int mpoint;
	private String aiseq;
	private String pw;
	// 번호 상품명 상품이미지 금액 배송지 수령인 택배사 완료날짜
	
	private String oname;
	private String oimage;
	private String oprice;
	private String oaddress;
	private String odeliver;
	private String odate;
	
	// 상품 문의제목 문의내용
	private String pname;
	private String qtitle;
	private String qcontent;
	
	// 상품 후기제목 후기 내용
	private String rvtitle;
	private String rvcontent;
}