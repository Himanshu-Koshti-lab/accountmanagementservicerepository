package com.tcs.poc.app.model;

import java.util.Date;

import lombok.Data;

@Data
public class AccountStatusRequest {
	
	private int AccountStatus;
	private String emailID;
}
