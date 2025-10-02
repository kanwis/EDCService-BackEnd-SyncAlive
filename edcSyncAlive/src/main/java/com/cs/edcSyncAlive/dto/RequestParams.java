package com.cs.edcSyncAlive.dto;


import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class RequestParams {
	private String transactionId;
	private String merchantId;
	private String terminalId;
	private String model;
	private Date createdDate;
	private int terminalIdPk;
	private int merchantIdPk;
	
	public RequestParams(String transactionId, String merchantId, String terminalId, String model, Date createdDate,
			int terminalIdPk, int merchantIdPk) {
		super();
		this.transactionId = transactionId;
		this.merchantId = merchantId;
		this.terminalId = terminalId;
		this.model = model;
		this.createdDate = createdDate;
		this.terminalIdPk = terminalIdPk;
		this.merchantIdPk = merchantIdPk;
	}
	
	
	

}
