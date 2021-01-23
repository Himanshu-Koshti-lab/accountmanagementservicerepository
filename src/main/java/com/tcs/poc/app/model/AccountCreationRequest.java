package com.tcs.poc.app.model;

import java.util.Date;

import lombok.Data;

@Data
public class AccountCreationRequest {

	private Date createdDate;
	private Date lastModifiedDate;
	private String createdBy;
	private String modifiedBy;
	private int userAccountType;
	private String emailID;
	private int user_id;
	
}
