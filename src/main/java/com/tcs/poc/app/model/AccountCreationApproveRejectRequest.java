package com.tcs.poc.app.model;

import lombok.Data;

@Data
public class AccountCreationApproveRejectRequest {
	private int user_id;
	private int accountregstatus;
	private int accounttype;
}
