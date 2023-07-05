package com.ssy.seller;

import lombok.Data;
/**
 * 
 * @author 유동환
 * <p>매출관련 차트에 필요한 정보를 담은 클래스입니다.</p>
 *
 */


@Data
public class ChartDTO {

	private String productname;
	private String count;
	private String price;
	
}
