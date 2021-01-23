package com.tcs.poc.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.poc.app.entity.Account;
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
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/register-account", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AccountCreationResponse createAccountRequest(@RequestBody AccountCreationRequest user ,@AuthenticationPrincipal String emailID) throws Exception {
		
		if(user.getEmailID().equals(emailID)) {
		 	return service.accountCreationRequest(user);
		}else {
			throw new Exception("User Mismatch");
		}
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/register-accountApprovereject", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AccountCreationApproveRejectResponse verifyAccountRequestApproved(@RequestBody AccountCreationApproveRejectRequest user) throws Exception
	{
		return service.verifyAccountRequestApproved(user);
	}
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@RequestMapping(method = RequestMethod.POST, value = "/register-accountApprove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public void verifyAccountRequestApproved(@RequestBody User user) throws Exception
//	{
//		service.verifyAccountRequestApproved(user);
//	}
//
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@RequestMapping(method = RequestMethod.POST, value = "/register-accountReject", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public void verifyAccountRequestReject(@RequestBody User user) throws Exception
//	{
//		service.verifyAccountRequestReject(user);
//	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/allAccount", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<AccountResponse> verifyAccountRequestReject() throws Exception
	{
		return  service.AllAccount();
	}
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@GetMapping(value = "/AllAccs")
//	@ResponseBody
//	public List<Account> AllUsers() {
//		 return service.Allacc();	
//	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	
}
