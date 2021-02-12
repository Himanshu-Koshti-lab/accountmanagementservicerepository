package com.tcs.poc.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tcs.poc.app.entity.Account;
import com.tcs.poc.app.entity.AccountRegStatusType;
import com.tcs.poc.app.entity.UserAccountStatusType;
import com.tcs.poc.app.entity.UserAccountType;
import com.tcs.poc.app.model.AccountCreationApproveRejectRequest;
import com.tcs.poc.app.model.AccountCreationApproveRejectResponse;
import com.tcs.poc.app.model.AccountCreationRequest;
import com.tcs.poc.app.model.AccountCreationResponse;
import com.tcs.poc.app.model.AccountResponse;
import com.tcs.poc.app.model.UserResponse;
import com.tcs.poc.app.repository.AccountRegStatusTypeRepo;
import com.tcs.poc.app.repository.AccountRepository;
import com.tcs.poc.app.repository.UserAccountStatusTypeRepository;
import com.tcs.poc.app.repository.UserAccountTypeRepository;
import com.tcs.poc.app.utils.BankConstants;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

//	@Autowired
//	private UserRepository registrationRepository;

	@Autowired
	private UserAccountTypeRepository userAccountTypeRepository;

	@Autowired
	private AccountRegStatusTypeRepo AccountRegStatusTypeRepo;

	@Autowired
	private UserAccountStatusTypeRepository userAccountStatusTypeRepository;

	public AccountCreationResponse accountCreationRequest(AccountCreationRequest user) throws Exception {
		List<Account> listofaccount = accountRepository.findByUserId(user.getUser_id());
		AccountCreationResponse accountcreation = new AccountCreationResponse();
		if (listofaccount.size() == 2) {

			if (listofaccount.get(0).getAccountRegStatusType().getId() == 1 && user.getUserAccountType() == 1 && user.getUserAccountType() == listofaccount.get(0).getUserAccountType().getId()) {
				accountcreation.setStatuscode(500);
				accountcreation.setMessage("User already raised request for salary account, pending for the approval.");
				return accountcreation;
			}
			if (listofaccount.get(0).getAccountRegStatusType().getId() == 1 && user.getUserAccountType() == 2 && user.getUserAccountType() == listofaccount.get(0).getUserAccountType().getId()) {
				accountcreation.setStatuscode(501);
				accountcreation.setMessage("User already raised request for current account, pending for the approval.");
				return accountcreation;
			}
			if (listofaccount.get(0).getAccountRegStatusType().getId() == 2 && user.getUserAccountType() == 1 && user.getUserAccountType() == listofaccount.get(0).getUserAccountType().getId()) {
				accountcreation.setStatuscode(500);
				accountcreation.setMessage("User already have Salary Account.");
				return accountcreation;
			}
			if (listofaccount.get(0).getAccountRegStatusType().getId() == 2 && user.getUserAccountType() == 2 && user.getUserAccountType() == listofaccount.get(0).getUserAccountType().getId()) {
				accountcreation.setStatuscode(600);
				accountcreation.setMessage("User already have Current Account.");
				return accountcreation;
			}
			if (listofaccount.get(1).getAccountRegStatusType().getId() == 1 && user.getUserAccountType() == 1 && user.getUserAccountType() == listofaccount.get(1).getUserAccountType().getId()) {
				accountcreation.setStatuscode(503);
				accountcreation.setMessage("User already raised request for salary account, pending for the approval.");
				return accountcreation;
			}
			if (listofaccount.get(1).getAccountRegStatusType().getId() == 1 && user.getUserAccountType() == 2 && user.getUserAccountType() == listofaccount.get(1).getUserAccountType().getId()) {
				accountcreation.setStatuscode(504);
				accountcreation.setMessage("User already raised request for current account, pending for the approval.");
				return accountcreation;
			}
			if (listofaccount.get(1).getAccountRegStatusType().getId() == 2 && user.getUserAccountType() == 1 && user.getUserAccountType() == listofaccount.get(1).getUserAccountType().getId()) {
				accountcreation.setStatuscode(500);
				accountcreation.setMessage("User already have Salary Account.");
				return accountcreation;
			}
			if (listofaccount.get(1).getAccountRegStatusType().getId() == 2 && user.getUserAccountType() == 2 && user.getUserAccountType() == listofaccount.get(1).getUserAccountType().getId()) {
				accountcreation.setStatuscode(601);
				accountcreation.setMessage("User already have Current Account.");
				return accountcreation;
			}
		}

		if (listofaccount.isEmpty()) {
			// create new account
			Account account = new Account();
			Optional<UserAccountType> userAccountType = userAccountTypeRepository.findById(user.getUserAccountType());
			Optional<UserAccountStatusType> userAccountStatusType = userAccountStatusTypeRepository.findById(3);
			Optional<AccountRegStatusType> userAccountRegStatusType = AccountRegStatusTypeRepo.findById(1);
			account.setUserId(user.getUser_id());
			account.setUserAccountType(userAccountType.get());
			account.setUserAccountStatusType(userAccountStatusType.get());
			account.setAccountRegStatusType(userAccountRegStatusType.get());
			account.setCreatedDate(new Date());
			account.setLastModifiedDate(new Date());
			account.setCreatedBy(user.getEmailID());
			account.setModifiedBy(user.getEmailID());
			accountRepository.save(account);
			accountcreation.setStatuscode(505);
			accountcreation.setMessage("User submit's the account creation request");
			return accountcreation;
		} else {
			if (user.getUserAccountType() == 1) {
				if (listofaccount.get(0).getUserAccountType().getId() == 1 && user.getUserAccountType() == 1 && user.getUserAccountType() == listofaccount.get(0).getUserAccountType().getId() && listofaccount.get(0).getAccountRegStatusType().getId() == 2) {
					accountcreation.setStatuscode(506);
					accountcreation.setMessage("User already have salary account");
					return accountcreation;
				}else if(listofaccount.get(0).getUserAccountType().getId() == 1 && user.getUserAccountType() == 1 && user.getUserAccountType() == listofaccount.get(0).getUserAccountType().getId() && listofaccount.get(0).getAccountRegStatusType().getId() == 3){
					Account reRegAccount = listofaccount.get(0);
					Optional<UserAccountStatusType> userAccountStatusType = userAccountStatusTypeRepository.findById(3);
					Optional<AccountRegStatusType> userAccountRegStatusType = AccountRegStatusTypeRepo.findById(1);
					reRegAccount.setUserAccountStatusType(userAccountStatusType.get());
					reRegAccount.setAccountRegStatusType(userAccountRegStatusType.get());
					accountRepository.save(reRegAccount);
					accountcreation.setStatuscode(507);
					accountcreation.setMessage("User Resubmitted salary account creation request");
					return accountcreation; 
					
				} else {
					Account account = new Account();
					Optional<UserAccountType> userAccountType = userAccountTypeRepository
							.findById(user.getUserAccountType());
					Optional<UserAccountStatusType> userAccountStatusType = userAccountStatusTypeRepository.findById(3);
					Optional<AccountRegStatusType> userAccountRegStatusType = AccountRegStatusTypeRepo.findById(1);
					account.setUserId(user.getUser_id());
					account.setUserAccountType(userAccountType.get());
					account.setUserAccountStatusType(userAccountStatusType.get());
					account.setAccountRegStatusType(userAccountRegStatusType.get());
					account.setCreatedDate(new Date());
					account.setLastModifiedDate(new Date());
					account.setCreatedBy(user.getEmailID());
					account.setModifiedBy(user.getEmailID());
					accountRepository.save(account);
					accountcreation.setStatuscode(507);
					accountcreation.setMessage("User submitted salary account creation request");
					return accountcreation;
				}
			}
			if (user.getUserAccountType() == 2) {
				if (listofaccount.get(0).getUserAccountType().getId() == 2 && user.getUserAccountType() == 2 && user.getUserAccountType() == listofaccount.get(0).getUserAccountType().getId() && listofaccount.get(0).getAccountRegStatusType().getId() == 2) {
					accountcreation.setStatuscode(508);
					accountcreation.setMessage("User already have Current account");
					return accountcreation;
				} else if(listofaccount.get(0).getUserAccountType().getId() == 2 && user.getUserAccountType() == 2 && user.getUserAccountType() == listofaccount.get(0).getUserAccountType().getId() && listofaccount.get(0).getAccountRegStatusType().getId() == 3){
					Account reRegAccount = listofaccount.get(0);
					Optional<UserAccountStatusType> userAccountStatusType = userAccountStatusTypeRepository.findById(3);
					Optional<AccountRegStatusType> userAccountRegStatusType = AccountRegStatusTypeRepo.findById(1);
					reRegAccount.setUserAccountStatusType(userAccountStatusType.get());
					reRegAccount.setAccountRegStatusType(userAccountRegStatusType.get());
					accountRepository.save(reRegAccount);
					accountcreation.setStatuscode(507);
					accountcreation.setMessage("User Resubmitted current account creation request");
					return accountcreation;
					
				}else{
					Account account = new Account();
					Optional<UserAccountType> userAccountType = userAccountTypeRepository
							.findById(user.getUserAccountType());
					Optional<UserAccountStatusType> userAccountStatusType = userAccountStatusTypeRepository.findById(3);
					Optional<AccountRegStatusType> userAccountRegStatusType = AccountRegStatusTypeRepo.findById(1);
					account.setUserId(user.getUser_id());
					account.setUserAccountType(userAccountType.get());
					account.setUserAccountStatusType(userAccountStatusType.get());
					account.setAccountRegStatusType(userAccountRegStatusType.get());
					account.setCreatedDate(new Date());
					account.setLastModifiedDate(new Date());
					account.setCreatedBy(user.getEmailID());
					account.setModifiedBy(user.getEmailID());
					accountRepository.save(account);
					accountcreation.setStatuscode(509);
					accountcreation.setMessage("User Submitted current account creation request");
					return accountcreation;
				}
			}
			accountcreation.setStatuscode(510);
			accountcreation.setMessage("Unable to get request this movement of time");
			return accountcreation;
		}

	}

	public AccountCreationApproveRejectResponse verifyAccountRequestApproved(
			AccountCreationApproveRejectRequest request) throws Exception {

		List<Account> listofUserAccounts = accountRepository.findByUserId(request.getUser_id());
		AccountCreationApproveRejectResponse response = new AccountCreationApproveRejectResponse();
		if (listofUserAccounts.isEmpty()) {
			response.setStatusCode(404);
			response.setMessage("User not found with this user Id");
			return response;
		}
		if (listofUserAccounts.size() == 2) {
			if (listofUserAccounts.get(0).getAccountRegStatusType().getId() == 2
					&& listofUserAccounts.get(0).getUserAccountType().getId() == request.getAccounttype()
					&& request.getAccounttype() == 1) {
				response.setStatusCode(111);
				response.setMessage("Salary account request already approved");
				return response;
			}
			if (listofUserAccounts.get(1).getAccountRegStatusType().getId() == 2
					&& listofUserAccounts.get(1).getUserAccountType().getId() == request.getAccounttype()
					&& request.getAccounttype() == 1) {
				response.setStatusCode(111);
				response.setMessage("Salary account request already approved");
				return response;
			}
			if (listofUserAccounts.get(0).getAccountRegStatusType().getId() == 2
					&& listofUserAccounts.get(0).getUserAccountType().getId() == request.getAccounttype()
					&& request.getAccounttype() == 2) {
				response.setStatusCode(112);
				response.setMessage("Current account request already approved");
				return response;
			}
			if (listofUserAccounts.get(1).getAccountRegStatusType().getId() == 2
					&& listofUserAccounts.get(1).getUserAccountType().getId() == request.getAccounttype()
					&& request.getAccounttype() == 2) {
				response.setStatusCode(112);
				response.setMessage("Current account request already approved");
				return response;
			}
			if (listofUserAccounts.get(0).getAccountRegStatusType().getId() == 3
					&& listofUserAccounts.get(0).getUserAccountType().getId() == request.getAccounttype()
					&& request.getAccounttype() == 1) {
				response.setStatusCode(113);
				response.setMessage("Salary account request already rejected");
				return response;
			}
			if (listofUserAccounts.get(1).getAccountRegStatusType().getId() == 3
					&& listofUserAccounts.get(1).getUserAccountType().getId() == request.getAccounttype()
					&& request.getAccounttype() == 1) {
				response.setStatusCode(113);
				response.setMessage("Salary account request already rejected");
				return response;
			}
			if (listofUserAccounts.get(0).getAccountRegStatusType().getId() == 3
					&& listofUserAccounts.get(0).getUserAccountType().getId() == request.getAccounttype()
					&& request.getAccounttype() == 2) {
				response.setStatusCode(114);
				response.setMessage("Current Account request already Rejected");
				return response;
			}
			if (listofUserAccounts.get(1).getAccountRegStatusType().getId() == 3
					&& listofUserAccounts.get(1).getUserAccountType().getId() == request.getAccounttype()
					&& request.getAccounttype() == 2) {
				response.setStatusCode(114);
				response.setMessage("Current account request already rejected");
				return response;
			}
		}
		if (request.getAccountregstatus() == 2) {
			//Salary Approved
			if (listofUserAccounts.get(0).getAccountRegStatusType().getId() == 1
					&& listofUserAccounts.get(0).getUserAccountType().getId() == request.getAccounttype() && request.getAccounttype() == 1) {
				Optional<UserAccountStatusType> userAccountStatusType = userAccountStatusTypeRepository.findById(1);
				Optional<AccountRegStatusType> AccountRegStatusType = AccountRegStatusTypeRepo.findById(2);
				boolean x = true;
				while (x) {
					int tempAccountNo = (int) (Math.random() * 1000000);
					listofUserAccounts.get(0).setAccountNumber(tempAccountNo);
					x = !(accountRepository.findByAccountNumber(tempAccountNo) == null);

				}
				listofUserAccounts.get(0).setBalance(500);
				listofUserAccounts.get(0).setUserAccountStatusType(userAccountStatusType.get());
				listofUserAccounts.get(0).setAccountRegStatusType(AccountRegStatusType.get());
				accountRepository.save(listofUserAccounts.get(0));
				response.setStatusCode(115);
				response.setMessage("Salary Approved ");
				return response;
			}
			if(listofUserAccounts.size() == 2) {
				if (listofUserAccounts.get(1).getAccountRegStatusType().getId() == 1
						&& listofUserAccounts.get(1).getUserAccountType().getId() == request.getAccounttype() && request.getAccounttype() == 1) {
					Optional<UserAccountStatusType> userAccountStatusType = userAccountStatusTypeRepository.findById(1);
					Optional<AccountRegStatusType> AccountRegStatusType = AccountRegStatusTypeRepo.findById(2);
					boolean x = true;
					while (x) {
						int tempAccountNo = (int) (Math.random() * 1000000);
						listofUserAccounts.get(1).setAccountNumber(tempAccountNo);
						x = !(accountRepository.findByAccountNumber(tempAccountNo) == null);

					}
					listofUserAccounts.get(1).setBalance(500);
					listofUserAccounts.get(1).setUserAccountStatusType(userAccountStatusType.get());
					listofUserAccounts.get(1).setAccountRegStatusType(AccountRegStatusType.get());
					accountRepository.save(listofUserAccounts.get(1));
					response.setStatusCode(115);
					response.setMessage("Salary Approved ");
					return response;
				}
			}
			
			//Current Approved
			if (listofUserAccounts.get(0).getAccountRegStatusType().getId() == 1
					&& listofUserAccounts.get(0).getUserAccountType().getId() == request.getAccounttype() && request.getAccounttype() == 2) {
				Optional<UserAccountStatusType> userAccountStatusType = userAccountStatusTypeRepository.findById(1);
				Optional<AccountRegStatusType> AccountRegStatusType = AccountRegStatusTypeRepo.findById(2);
				boolean x = true;
				while (x) {
					int tempAccountNo = (int) (Math.random() * 1000000);
					listofUserAccounts.get(0).setAccountNumber(tempAccountNo);
					x = !(accountRepository.findByAccountNumber(tempAccountNo) == null);

				}
				listofUserAccounts.get(0).setBalance(500);
				listofUserAccounts.get(0).setUserAccountStatusType(userAccountStatusType.get());
				listofUserAccounts.get(0).setAccountRegStatusType(AccountRegStatusType.get());
				accountRepository.save(listofUserAccounts.get(0));
				response.setStatusCode(116);
				response.setMessage("Current Approved ");
				return response;
			}

			if(listofUserAccounts.size() == 2) {
			if (listofUserAccounts.get(1).getAccountRegStatusType().getId() == 1
					&& listofUserAccounts.get(1).getUserAccountType().getId() == request.getAccounttype() && request.getAccounttype() == 2) {
				Optional<UserAccountStatusType> userAccountStatusType = userAccountStatusTypeRepository.findById(1);
				Optional<AccountRegStatusType> AccountRegStatusType = AccountRegStatusTypeRepo.findById(2);
				boolean x = true;
				while (x) {
					int tempAccountNo = (int) (Math.random() * 1000000);
					listofUserAccounts.get(1).setAccountNumber(tempAccountNo);
					x = !(accountRepository.findByAccountNumber(tempAccountNo) == null);

				}
				listofUserAccounts.get(1).setBalance(500);
				listofUserAccounts.get(1).setUserAccountStatusType(userAccountStatusType.get());
				listofUserAccounts.get(1).setAccountRegStatusType(AccountRegStatusType.get());
				accountRepository.save(listofUserAccounts.get(1));
				response.setStatusCode(116);
				response.setMessage("Current Approved ");
				return response;
			}
			}
		}
		if (request.getAccountregstatus() == 3) {
			if (listofUserAccounts.get(0).getAccountRegStatusType().getId() == 1
					&& listofUserAccounts.get(0).getUserAccountType().getId() == request.getAccounttype() && request.getAccounttype() == 1) {
				Optional<AccountRegStatusType> AccountRegStatusType = AccountRegStatusTypeRepo.findById(3);
				Optional<UserAccountStatusType> userAccountStatusType = userAccountStatusTypeRepository.findById(3);
				listofUserAccounts.get(0).setUserAccountStatusType(userAccountStatusType.get());
				listofUserAccounts.get(0).setAccountRegStatusType(AccountRegStatusType.get());
				accountRepository.save(listofUserAccounts.get(0));
				response.setStatusCode(116);
				response.setMessage("Salary Rejected ");
				return response;
			}
			if (listofUserAccounts.get(1).getAccountRegStatusType().getId() == 1
					&& listofUserAccounts.get(1).getUserAccountType().getId() == request.getAccounttype() && request.getAccounttype() == 1) {
				Optional<AccountRegStatusType> AccountRegStatusType = AccountRegStatusTypeRepo.findById(3);
				Optional<UserAccountStatusType> userAccountStatusType = userAccountStatusTypeRepository.findById(3);
				listofUserAccounts.get(1).setUserAccountStatusType(userAccountStatusType.get());
				listofUserAccounts.get(1).setAccountRegStatusType(AccountRegStatusType.get());
				accountRepository.save(listofUserAccounts.get(1));
				response.setStatusCode(116);
				response.setMessage("Salary Rejected ");
				return response;
			}
			if (listofUserAccounts.get(0).getAccountRegStatusType().getId() == 1
					&& listofUserAccounts.get(0).getUserAccountType().getId() == request.getAccounttype() && request.getAccounttype() == 2) {
				Optional<AccountRegStatusType> AccountRegStatusType = AccountRegStatusTypeRepo.findById(3);
				Optional<UserAccountStatusType> userAccountStatusType = userAccountStatusTypeRepository.findById(3);
				listofUserAccounts.get(0).setUserAccountStatusType(userAccountStatusType.get());
				listofUserAccounts.get(0).setAccountRegStatusType(AccountRegStatusType.get());
				accountRepository.save(listofUserAccounts.get(0));
				response.setStatusCode(117);
				response.setMessage("Current Rejected ");
				return response;
			}
			if (listofUserAccounts.get(1).getAccountRegStatusType().getId() == 1
					&& listofUserAccounts.get(1).getUserAccountType().getId() == request.getAccounttype() && request.getAccounttype() == 2) {
				Optional<AccountRegStatusType> AccountRegStatusType = AccountRegStatusTypeRepo.findById(3);
				Optional<UserAccountStatusType> userAccountStatusType = userAccountStatusTypeRepository.findById(3);
				listofUserAccounts.get(1).setUserAccountStatusType(userAccountStatusType.get());
				listofUserAccounts.get(1).setAccountRegStatusType(AccountRegStatusType.get());
				accountRepository.save(listofUserAccounts.get(1));
				response.setStatusCode(117);
				response.setMessage("Current Rejected ");
				return response;
			}
		}
		response.setStatusCode(118);
		response.setMessage("Unable to perform action at this movement of time");
		return response;
	}

	public List<AccountResponse> AllAccount() {
		List<Account> tempAcc = accountRepository.findAll();
		List<AccountResponse> temp2 = new ArrayList<>();
		for(int i= 0 ;i< tempAcc.size();i++) {
			AccountResponse temp = new AccountResponse();
			temp.setUserId(tempAcc.get(i).getUserId());
			temp.setAccountNumber(tempAcc.get(i).getAccountNumber());
			temp.setAccountRegStatusType(tempAcc.get(i).getAccountRegStatusType().getId());
			temp.setBalance(tempAcc.get(i).getBalance());
			temp.setUserAccountType(tempAcc.get(i).getUserAccountType().getId());
			temp.setUserAccountStatusType(tempAcc.get(i).getUserAccountStatusType().getId());
			temp2.add(temp);
		}
		return temp2;
	}

	public List<Account> Allacc() {
		return accountRepository.findAll();
	}
	
	public AccountResponse getAccount(double accountNumber) {
		AccountResponse accountResponse = new AccountResponse();
		Account accounts =  accountRepository.findByAccountNumber(accountNumber);
		accountResponse.setUserId(accounts.getUserId());
		accountResponse.setAccountRegStatusType(accounts.getAccountRegStatusType().getId());
		accountResponse.setAccountNumber(accounts.getAccountNumber());
		accountResponse.setBalance(accounts.getBalance());
		accountResponse.setUserAccountStatusType(accounts.getUserAccountStatusType().getId());
		accountResponse.setUserAccountType(accounts.getUserAccountType().getId());
		return accountResponse;
	}

	public List<AccountResponse> Allaccs() {
		List<AccountResponse> accountResponses = new ArrayList<AccountResponse>();
		List<Account> accounts = accountRepository.findAll();
		for(int i=0;i<accounts.size();i++) {
			AccountResponse accountResponse = new AccountResponse();
			accountResponse.setUserId(accounts.get(i).getUserId());
			accountResponse.setAccountRegStatusType(accounts.get(i).getAccountRegStatusType().getId());
			accountResponse.setAccountNumber(accounts.get(i).getAccountNumber());
			accountResponse.setBalance(accounts.get(i).getBalance());
			accountResponse.setUserAccountStatusType(accounts.get(i).getUserAccountStatusType().getId());
			accountResponse.setUserAccountType(accounts.get(i).getUserAccountType().getId());
			accountResponses.add(accountResponse);
		}
		return accountResponses;
	}

	public void upDateBalance(AccountResponse account) {
		Account account2 = accountRepository.findByAccountNumber(account.getAccountNumber());
		account2.setBalance(account.getBalance());
		accountRepository.save(account2);
	}

	public List<AccountResponse> getUserAccounts(String token) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders  headers4 = new HttpHeaders();
		headers4.set("Authorization", token);
		headers4.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity4 = new HttpEntity<String>(headers4);
		ResponseEntity<UserResponse> responseU = restTemplate.exchange(BankConstants.USER_API_URL+"/getUser", HttpMethod.GET ,entity4 , UserResponse.class);
		List<Account> tempAcc = accountRepository.findByUserId(responseU.getBody().getUser_id());
		List<AccountResponse> temp2 = new ArrayList<>();
		for(int i= 0 ;i< tempAcc.size();i++) {
			AccountResponse temp = new AccountResponse();
			temp.setUserId(tempAcc.get(i).getUserId());
			temp.setAccountNumber(tempAcc.get(i).getAccountNumber());
			temp.setAccountRegStatusType(tempAcc.get(i).getAccountRegStatusType().getId());
			temp.setBalance(tempAcc.get(i).getBalance());
			temp.setUserAccountType(tempAcc.get(i).getUserAccountType().getId());
			temp.setUserAccountStatusType(tempAcc.get(i).getUserAccountStatusType().getId());
			temp2.add(temp);
		}
		return temp2;
	}
}

//		if (tempAccount.getAccountRegStatusType().getId() == 1) {
//			Optional<UserAccountStatusType> userAccountStatusType = userAccountStatusTypeRepository.findById(1);
//			Optional<AccountRegStatusType> AccountRegStatusType = AccountRegStatusTypeRepo.findById(2);
//			boolean x = true;
//			while (x) {
//				int tempAccountNo = (int) (Math.random() * 1000000);
//				tempAccount.setAccountNumber(tempAccountNo);
//				x = !(accountRepository.findByAccountNumber(tempAccountNo) == null);
//
//			}
//			// tempAccount.setUserAccountType(userAccountType.get());
//			tempAccount.setBalance(500);
//			tempAccount.setUserAccountStatusType(userAccountStatusType.get());
//			tempAccount.setAccountRegStatusType(AccountRegStatusType.get());
//			System.out.println(tempAccount.getAccountNumber());
//			accountRepository.save(tempAccount);
//		} else if (tempAccount.getAccountRegStatusType().getId() == 2) {
//			throw new Exception("Already approved");
//		} else {
//			throw new Exception("Already Rejected");
//		}

//	}	

//
//	public void verifyAccountRequestReject(User user) throws Exception {
//
//		User tempUser = registrationRepository.findByEmailID(user.getEmailID());
//		Account tempAccount = accountRepository.findByUserId(tempUser.getId());
//		if (tempAccount.getAccountRegStatusType().getId() == 1) {
//			Optional<AccountRegStatusType> AccountRegStatusType = AccountRegStatusTypeRepo.findById(3);
//			Optional<UserAccountStatusType> userAccountStatusType = userAccountStatusTypeRepository.findById(3);
//			tempAccount.setUserAccountStatusType(userAccountStatusType.get());
//			tempAccount.setAccountRegStatusType(AccountRegStatusType.get());
//			accountRepository.save(tempAccount);
//		} else if (tempAccount.getAccountRegStatusType().getId() == 2) {
//			throw new Exception("Already approved");
//		} else {
//			throw new Exception("Already Rejected");
//		}
//
//	}
