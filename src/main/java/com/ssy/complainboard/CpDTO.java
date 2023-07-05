package com.ssy.complainboard;

import lombok.Data;

@Data
public class CpDTO {

	private String cbseq;
	private String cbdate;
	private String cbcontent;
	private String cbtitle;
	private String mseq;
	private String mname;
	
	private String cbsecret;
	
}
