package com.ssy.noticeboard;

import lombok.Data;

/**
 * 
 * @author 구대현
 * <p>공지사항 관련 데이터 변수를 관리하는 클래스입니다.</p>
 * 
 */

@Data
public class NtBoardDTO {

	private String nseq;
	private String nname;
	private String ncontent;
	private String ndate;
	private String nreadcount;
	private String filename;
	
}
