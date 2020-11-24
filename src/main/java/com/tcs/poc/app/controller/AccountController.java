package com.tcs.poc.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.poc.app.entity.User;
import com.tcs.poc.app.model.AccountCreationRequest;
import com.tcs.poc.app.service.AccountService;


@RestController
@CrossOrigin
public class AccountController {

	@Autowired
	private AccountService service;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/register-account", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void createAccountRequest(@RequestBody AccountCreationRequest user) throws Exception {

		service.accountCreationRequest(user);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/register-accountApprove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void verifyAccountRequest(@RequestBody User user)
	{
		service.verifyAccountRequest(user);
	}
}
