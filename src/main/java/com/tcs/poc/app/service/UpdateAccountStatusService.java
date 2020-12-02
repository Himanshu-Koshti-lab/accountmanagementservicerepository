package com.tcs.poc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.poc.app.entity.Account;
import com.tcs.poc.app.entity.AccountUpdateRequest;
import com.tcs.poc.app.entity.AccountUpdateRequestStatus;
import com.tcs.poc.app.model.UpdateAccountStatusRequest;
import com.tcs.poc.app.model.UpdateAccountStatusResponse;
import com.tcs.poc.app.repository.AccountRepository;
import com.tcs.poc.app.repository.AccountUpdateRequestRepo;
import com.tcs.poc.app.repository.AccountUpdateRequestStatusRepo;

@Service
public class UpdateAccountStatusService {

	@Autowired
	public AccountRepository accountrepo;

	@Autowired
	public AccountUpdateRequestRepo accrepo;

	@Autowired
	public AccountUpdateRequestStatusRepo accUpdReqRepo;

	public UpdateAccountStatusResponse saveUserRequest(AccountUpdateRequest request) {
		List<Account> account = accountrepo.findByUserId(request.getUserId());
		UpdateAccountStatusResponse accountStatusResponse = new UpdateAccountStatusResponse();
		if (account == null) {
			accountStatusResponse.setStatus(0);
			accountStatusResponse.setMessage("Request not Submited Due to Account Not Found with this User ID");
			return accountStatusResponse;
		} else {
			// UserUpdateRequestStatus status = StatusRepo.findById(1);
			AccountUpdateRequestStatus status = accUpdReqRepo.findById(1);
			request.setAccountUpdateRequestStatus(status);
			accrepo.save(request);
			accountStatusResponse.setStatus(1);
			accountStatusResponse.setMessage("Request submited for Approval");
			return accountStatusResponse;
		}
	}

	public List<AccountUpdateRequest> getUserRequests() {
		return accrepo.findAll();
	}

//	public UpdateAccountStatusResponse UpdateAccountStatusApproved(UpdateAccountStatusRequest request) {
//		AccountUpdateRequest reqtable = accrepo.findByUserId(request.getUserid());
//		Account mainaccount = accountrepo.findByUserId(request.getUserid());
//		UpdateAccountStatusResponse response = new UpdateAccountStatusResponse();
//		if (reqtable.getUserId() == mainaccount.getUserId() && reqtable.getAccountUpdateRequestStatus().getId() == 1) {
//			AccountUpdateRequestStatus status = accUpdReqRepo.findById(2);
//			reqtable.setAccountUpdateRequestStatus(status);
//			accrepo.save(reqtable);
//			mainaccount.setUserAccountStatusType(reqtable.getUserAccountStatusType());
//			accountrepo.save(mainaccount);
//			response.setStatus(1);
//			response.setMessage("Mobile Number Updated Request Approved");
//			return response;
//		}
//		response.setStatus(0);
//		response.setMessage("User not Found in Main Table");
//		return response;
//
//	}

	public UpdateAccountStatusResponse UpdateAccountStatusApproved(UpdateAccountStatusRequest request) {
		List<AccountUpdateRequest> reqtable1 = accrepo.findAll();
		AccountUpdateRequest reqtable = new AccountUpdateRequest();
		UpdateAccountStatusResponse response = new UpdateAccountStatusResponse();
		System.out.println(reqtable1.size());
		for (int i = 0; i < reqtable1.size(); i++) {
			if (reqtable1.get(i).getAccountUpdateRequestStatus().getId() == 1
					&& reqtable1.get(i).getEmailID().equals(request.getEmailId())) {
				reqtable = reqtable1.get(i);
			}
		}
		List<Account> mainaccount = accountrepo.findByUserId(request.getUserid());
		if (reqtable == null) {
			response.setStatus(0);
			response.setMessage("User not Found in Request Table");
			return response;
		}

		if (reqtable.getUserId() == mainaccount.get(0).getUserId()) {
			AccountUpdateRequestStatus status = accUpdReqRepo.findById(2);
			reqtable.setAccountUpdateRequestStatus(status);
			accrepo.save(reqtable);
			mainaccount.get(0).setUserAccountStatusType(reqtable.getUserAccountStatusType());
			accountrepo.save(mainaccount.get(0));
			response.setStatus(1);
			response.setMessage("Mobile Number Updated Request Approved");
			return response;
		} else {
			response.setStatus(0);
			response.setMessage("User not Found in Main Table");
			return response;
		}

	}

//	public UpdateAccountStatusResponse UpdateAccountStatusRejected(UpdateAccountStatusRequest request) {
//		UpdateAccountStatusResponse response = new UpdateAccountStatusResponse();
//		AccountUpdateRequest Areqtable = accrepo.findByUserId(request.getUserid());
//		Account mainaccount = accountrepo.findByUserId(request.getUserid());
//		if (Areqtable.getUserId() == mainaccount.getUserId()
//				&& Areqtable.getAccountUpdateRequestStatus().getId() == 1) {
//			AccountUpdateRequestStatus status = accUpdReqRepo.findById(3);
//			Areqtable.setAccountUpdateRequestStatus(status);
//			accrepo.save(Areqtable);
//			response.setStatus(1);
//			response.setMessage("Mobile Number Update Request Rejected");
//			return response;
//		}
//		response.setStatus(0);
//		response.setMessage("User not Found in Main Table");
//		return response;
//	}

	public UpdateAccountStatusResponse UpdateAccountStatusRejected(UpdateAccountStatusRequest request) {
		List<AccountUpdateRequest> reqtable1 = accrepo.findAll();
		AccountUpdateRequest reqtable = new AccountUpdateRequest();
		UpdateAccountStatusResponse response = new UpdateAccountStatusResponse();
		System.out.println(reqtable1.size());
		for (int i = 0; i < reqtable1.size(); i++) {
			if (reqtable1.get(i).getAccountUpdateRequestStatus().getId() == 1
					&& reqtable1.get(i).getEmailID().equals(request.getEmailId())) {
				reqtable = reqtable1.get(i);
			}
		}
		List<Account> mainaccount = accountrepo.findByUserId(request.getUserid());
		if (reqtable == null) {
			response.setStatus(0);
			response.setMessage("User not Found in Request Table");
			return response;
		}

		if (reqtable.getUserId() == mainaccount.get(0).getUserId()) {
			AccountUpdateRequestStatus status = accUpdReqRepo.findById(3);
			reqtable.setAccountUpdateRequestStatus(status);
			accrepo.save(reqtable);
			mainaccount.get(0).setUserAccountStatusType(reqtable.getUserAccountStatusType());
			accountrepo.save(mainaccount.get(0));
			response.setStatus(1);
			response.setMessage("Mobile Number Updated Request Rejected");
			return response;
		} else {
			response.setStatus(0);
			response.setMessage("User not Found in Main Table");
			return response;
		}

	}
}
