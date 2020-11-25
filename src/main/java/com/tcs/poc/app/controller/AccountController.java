package com.tcs.poc.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.poc.app.entity.User;
import com.tcs.poc.app.model.AccountCreationRequest;
import com.tcs.poc.app.model.AccountStatusRequest;
import com.tcs.poc.app.model.AccountStatusResponse;
import com.tcs.poc.app.service.AccountService;


@RestController
@CrossOrigin
public class AccountController {

	@Autowired
	private AccountService service;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/register-account", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void createAccountRequest(@RequestBody AccountCreationRequest user ,@AuthenticationPrincipal String emailID) throws Exception {
		if(user.getEmailID() == emailID) {
			service.accountCreationRequest(user);
		}else {
			throw new Exception("User Mismatch");
		}
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/register-accountApprove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void verifyAccountRequestApproved(@RequestBody User user) throws Exception
	{
		service.verifyAccountRequestApproved(user);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/register-accountReject", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void verifyAccountRequestReject(@RequestBody User user) throws Exception
	{
		service.verifyAccountRequestReject(user);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/UpdateAccountStatus", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AccountStatusResponse UpdateAccountStatus(@RequestBody AccountStatusRequest user) throws Exception
	{
		return service.UpdateAccountStatus(user);
	}
	
	
	
}
