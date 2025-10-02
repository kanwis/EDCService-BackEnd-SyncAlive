package com.cs.edcSyncAlive.dto;



import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class TerminalDto {
	private int id;
	private int merchantId;
	private String terminalId;
	private int terminalModelId ;
	private Integer posNo;
	private int serialId;
	private String parameterCurrentVer;
	private String parameterExpectVer;
	private Boolean operationFlag;
	private String rejectReason;
	private int applicationStatus;
	private Date startDate;
	private Date endDate;
	private Date lastTxDate;
	private int importType;
	private Boolean logFlag;
	private Integer approveById;
	private Date approveDate;
	private String actionForm;
	private int createdById;
	private Date createdDate;
	private int updatedById;
	private Date updatedDate;
	private Boolean firstInit;
	private String updatedFirstInitName;
	private Date updatedFirstInitDate;
	public TerminalDto(int id, int merchantId, String terminalId, int terminalModelId, Integer posNo, int serialId,
			String parameterCurrentVer, String parameterExpectVer, Boolean operationFlag, String rejectReason,
			int applicationStatus, Date startDate, Date endDate, Date lastTxDate, int importType, Boolean logFlag,
			Integer approveById, Date approveDate, String actionForm, int createdById, Date createdDate,
			int updatedById, Date updatedDate, Boolean firstInit, String updatedFirstInitName,
			Date updatedFirstInitDate) {
		super();
		this.id = id;
		this.merchantId = merchantId;
		this.terminalId = terminalId;
		this.terminalModelId = terminalModelId;
		this.posNo = posNo;
		this.serialId = serialId;
		this.parameterCurrentVer = parameterCurrentVer;
		this.parameterExpectVer = parameterExpectVer;
		this.operationFlag = operationFlag;
		this.rejectReason = rejectReason;
		this.applicationStatus = applicationStatus;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lastTxDate = lastTxDate;
		this.importType = importType;
		this.logFlag = logFlag;
		this.approveById = approveById;
		this.approveDate = approveDate;
		this.actionForm = actionForm;
		this.createdById = createdById;
		this.createdDate = createdDate;
		this.updatedById = updatedById;
		this.updatedDate = updatedDate;
		this.firstInit = firstInit;
		this.updatedFirstInitName = updatedFirstInitName;
		this.updatedFirstInitDate = updatedFirstInitDate;
	}
	
	
	
}
