package com.cs.edcSyncAlive.entities;



import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "merchant")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Merchant implements Serializable{
	
	private static final long serialVersionUID = 4854500711886679292L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "enterprise_id")
	private Integer enterpriseId;
	
	@Column(name = "merchant_id")
	private String merchantId;
	
	@Column(name = "merchant_name_th")
	private String merchantNameTh;  
	
	@Column(name = "merchant_name_en")
	private String merchantNameEn;
	
	@Column(name = "contact_person")
	private String contactPerson;
	
	@Column(name = "address_no")
	private String addressNo;
	
	@Column(name = "floor")
	private String floor;  
	
	@Column(name = "room")
	private String room; 
	
	@Column(name = "moo")
	private String moo;
	
	@Column(name = "soi")
	private String soi;
	
	@Column(name = "road")
	private String road;
	
	@Column(name = "subdistrict_id")
	private int subdistrictId;  
	
	@Column(name = "district_id")
	private int districtId;
	
	@Column(name = "province_id")
	private int provinceId; 
	
	@Column(name = "phone_no")
	private String phoneNo;  
	
	@Column(name = "mail")
	private String mail; 
	
	@Column(name = "operation_flag")
	private boolean operationFlag;
	
	@Column(name = "reject_reason")
	private String rejectReason;
	
	@Column(name = "application_status")
	private int applicationStatus;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "import_type")
	private Integer importType;
	
	@Column(name = "approve_by_id")
	private Integer approveById;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "approve_date")
	private Date approveDate;
	
	@Column(name = "action_form")
	private String actionForm;
	
	@Column(name = "created_by_id")
	private Integer createdById;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "updated_by_id")
	private Integer updatedById;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updatedDate;


}
