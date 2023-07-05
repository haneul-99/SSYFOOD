package com.ssy.seller;

import lombok.Data;
/**
 * 
 * @author 유동환
 * <p>주문상품에 대한 정보를 담는 클래스입니다.</p>
 *
 */


@Data
public class OrderDTO {
	
	private String pseq; //상품번호
	private String pname; //상품이름
	private String olname; //수령인 이름
	private String oladdress; //수령인 주소
	private String olstate; //주문상태
	private String sseq;  //판매자번호
	private String sname; //판매자
	private String mname; //실제주문한 회원
	private String mtel; //주문한 회원 연락처
	private String oldate; //주문날짜
	private String odprice; //주문날짜
	private String odquantity; //주문날짜
	private String totalprice; //주문날짜
	private String olseq; //주문날짜
}
