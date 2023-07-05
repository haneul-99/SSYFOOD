package com.ssy.admin;

import lombok.Data;

/**
 * 
 * @author 구대현
 * <p>설문조사 관련 데이터 변수를 관리하는 클래스입니다.</p>
 * 
 */

@Data
public class SurveyDTO {
	
	private String surSeq;
	private String surQ1;
	private String surQ2;
	private String surQ3;
	private String surQ4;
	private String surQ5;
	private String surQ6;
	private String mseq;
	
	private String mname;
	private String resultq6;
	
}
