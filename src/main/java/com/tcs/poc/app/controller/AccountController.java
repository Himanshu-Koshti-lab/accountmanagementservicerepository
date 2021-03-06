package com.tcs.poc.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.tcs.poc.app.model.AccountCreationApproveRejectRequest;
import com.tcs.poc.app.model.AccountCreationApproveRejectResponse;
import com.tcs.poc.app.model.AccountCreationRequest;
import com.tcs.poc.app.model.AccountCreationResponse;
import com.tcs.poc.app.model.AccountResponse;
import com.tcs.poc.app.service.AccountService;


@RestController
public class AccountController {

	@Autowired
	private AccountService service;
	
	@PostMapping(value = "/register-account")
	public AccountCreationResponse createAccountRequest(@RequestBody AccountCreationRequest user ,@AuthenticationPrincipal String emailID) throws Exception {
		
		if(user.getEmailID().equals(emailID)) {
		 	return service.accountCreationRequest(user);
		}else {
			throw new Exception("User Mismatch");
		}		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/register-accountApprovereject")
	public AccountCreationApproveRejectResponse verifyAccountRequestApproved(@RequestBody AccountCreationApproveRejectRequest user) throws Exception
	{
		return service.verifyAccountRequestApproved(user);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/allAccount")
	public List<AccountResponse> verifyAccountRequestReject() throws Exception
	{
		return  service.AllAccount();
	}	

	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
	@GetMapping(value = "/AllAccs")
	@ResponseBody
	public List<AccountResponse> Allaccs() {
		 return service.Allaccs();	
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
	@GetMapping(value = "/getAccount/{id}")
	@ResponseBody
	public AccountResponse getAccount(@PathVariable("id") double accountNumber) {
		 return service.getAccount(accountNumber);	
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
	@PostMapping(value = "/upDateBalance")
	@ResponseBody
	public void upDateBalance(@RequestBody AccountResponse account) {
		service.upDateBalance(account);	
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
	@GetMapping(value = "/getUserAccounts")
	@ResponseBody
	public List<AccountResponse> getUserAccounts(@RequestHeader("Authorization") String token) {
		 return service.getUserAccounts(token);	
	}	
}
