package com.tcs.poc.app.model;

import lombok.Data;

@Data
public class AccountResponse {
	
	
	private double accountNumber;
	private int userId;
	private double balance;
    private int userAccountType;
	private int userAccountStatusType;
    private int AccountRegStatusType;
}
