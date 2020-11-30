package com.tcs.poc.app.model;

import lombok.Data;

@Data
public class UpdateAccountStatusRequest {

	private int newAccountStatus;
	private int userid;
	private String emailId;
	private int accounttypeid;
}