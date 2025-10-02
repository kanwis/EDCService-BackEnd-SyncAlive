package com.cs.edcSyncAlive.entities;



import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "terminal")
@Data
@NoArgsConstructor
@ToString
public class Terminal implements Serializable {

	private static final long serialVersionUID = -7378726674731621911L;

	@Id
	@Column(name="id", length = 11)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "merchant_id")
	private int merchantId;

	@Column(name = "terminal_id")
	private String terminalId;

	@Column(name = "terminal_model_id ")
	private int terminalModelId ;
	
	@Column(name = "pos_no")
	private Integer posNo;
	
	@Column(name = "serial_id")
	private int serialId;
	
	@Column(name = "parameter_current_ver")
	private String parameterCurrentVer;
	
	@Column(name = "parameter_expect_ver")
	private String parameterExpectVer;
	
	@Column(name = "operation_flag")
	private Boolean operationFlag;
	
	@Column(name = "reject_reason")
	private String rejectReason;
	
	@Column(name = "application_status")
	private int applicationStatus;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "last_tx_date")
	private Date lastTxDate;
	
	@Column(name = "import_type")
	private int importType;
	
	@Column(name = "log_flag")
	private Boolean logFlag;
	
	@Column(name = "approve_by_id")
	private Integer approveById;
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "approve_date")
	private Date approveDate;
	
	@Column(name = "action_form")
	private String actionForm;

	@Column(name = "created_by_id")
	private int createdById;

//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by_id")
	private int updatedById;

//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "first_init")
	private Boolean firstInit;

	@Column(name = "updated_first_init_name")
	private String updatedFirstInitName;

	@Column(name = "updated_first_init_date")
	private Date updatedFirstInitDate;
	

}
