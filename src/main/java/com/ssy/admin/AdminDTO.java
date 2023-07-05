package com.ssy.admin;

import lombok.Data;

/**
 * 
 * @author 구대현
 * <p>관리자 업무 관련 데이터 변수를 관리하는 클래스입니다.</p>
 * 
 */

@Data
public class AdminDTO {

	private String mseq;
    private String mtel;
    private String aiid;
    private String aipw;
    private String mname;
    private String maddress;
    private String aiseq;
    
}