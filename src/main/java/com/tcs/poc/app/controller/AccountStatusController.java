package com.tcs.poc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.poc.app.entity.Account;
import com.tcs.poc.app.entity.AccountUpdateRequest;
import com.tcs.poc.app.entity.UserUpdateRequest;
import com.tcs.poc.app.model.UpdateAccountStatusRequest;
import com.tcs.poc.app.model.UpdateAccountStatusResponse;
import com.tcs.poc.app.model.UpdateMobileRequest;
import com.tcs.poc.app.model.UpdateMobileResponse;
import com.tcs.poc.app.service.UpdateAccountStatusService;
import com.tcs.poc.app.service.UpdateMobileNoService;

@RestController
@CrossOrigin 
class AccountStatusController {
	@Autowired
	public UpdateAccountStatusService service;

	@PreAuthorize("hasRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN')")
	@PostMapping(value = "/Request")
	public UpdateAccountStatusResponse addUserUpdateReq(@RequestBody AccountUpdateRequest request,
			@AuthenticationPrincipal String emailID) throws Exception {
		UpdateAccountStatusResponse accountStatusResponse = new UpdateAccountStatusResponse();
		
		if (request.getEmailID().equals(emailID))
			return service.saveUserRequest(request);
		accountStatusResponse.setStatus(0);
		accountStatusResponse.setMessage("Request not Submited Due to Account Not Found with this User ID");
		return accountStatusResponse;

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/requestlist")
	public List<AccountUpdateRequest> findAllRequests() {
		return service.getUserRequests();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/UpdateMobileNoRequestApproved")
	public UpdateAccountStatusResponse UpdateAccountStatusResponse(@RequestBody UpdateAccountStatusRequest request) {
		System.out.println(request.getEmailId());
		return service.UpdateAccountStatusApproved(request);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/UpdateMobileNoRequestReject")
	public UpdateAccountStatusResponse UpdateAccountStatusRejected(@RequestBody UpdateAccountStatusRequest request) {
		System.out.println(request.getEmailId());
		return service.UpdateAccountStatusRejected(request);
	} 
}