package com.ssy.qnaboard;

import lombok.Data;

/**
 * 
 * @author 김유진
 * <p>판매자 문의게시판 게시글에 필요한 정보를 담은 클래스</p>
 *
 */

@Data
public class QnaDTO {

	private String qseq;
	private String qtitle;
	private String qcontent;
	private String qdate;
	private String qsecret;
	
	private String pseq;
	private String pname;
	
	
	private String mseq;
	private String mname;
	
	private int thread;
	private int depth;
	
}
