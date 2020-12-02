package com.tcs.poc.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.poc.app.entity.Account;
import com.tcs.poc.app.entity.AccountRegStatusType;
import com.tcs.poc.app.entity.UserAccountStatusType;
import com.tcs.poc.app.entity.UserAccountType;
import com.tcs.poc.app.model.AccountCreationRequest;
import com.tcs.poc.app.model.AccountCreationResponse;
import com.tcs.poc.app.repository.AccountRegStatusTypeRepo;
import com.tcs.poc.app.repository.AccountRepository;
import com.tcs.poc.app.repository.UserAccountStatusTypeRepository;
import com.tcs.poc.app.repository.UserAccountTypeRepository;

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
		if(listofaccount.size() == 2) {
			
			if(listofaccount.get(0).getAccountRegStatusType().getId() == 1 && user.getUserAccountType() ==1 ) {
				accountcreation.setMessage("User Already have salary account pending for approve");
				return accountcreation;
			}
			if(listofaccount.get(0).getAccountRegStatusType().getId() == 1 && user.getUserAccountType() ==2 ) {
				accountcreation.setMessage("User Already have current account pending for approve");
				return accountcreation;
			}
			if(listofaccount.get(1).getAccountRegStatusType().getId() == 1 && user.getUserAccountType() ==1 ) {
				accountcreation.setMessage("User Already have salary account pending for approve");
				return accountcreation;
			}
			if(listofaccount.get(1).getAccountRegStatusType().getId() == 1 && user.getUserAccountType() ==2 ) {
				accountcreation.setMessage("User Already have current account pending for approve");
				return accountcreation;
			}
		}
		
		if(listofaccount.isEmpty()) {
			//create new account
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
			accountcreation.setMessage("User Submit out first account creation request");
			return accountcreation;
		}else {
			if(user.getUserAccountType() ==1) {
				if(listofaccount.get(0).getUserAccountType().getId() == 1 && user.getUserAccountType() == 1 ) {
					accountcreation.setMessage("User Submit already have salary account");
					return accountcreation;
				}else {
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
					accountcreation.setMessage("User Submit out salary account creation request");
					return accountcreation;
				}
			}
			if(user.getUserAccountType() == 2) {
				if(listofaccount.get(0).getUserAccountType().getId() == 2 && user.getUserAccountType() == 2) {
					accountcreation.setMessage("User Submit already have current account");
					return accountcreation;
				}else {
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
					accountcreation.setMessage("User Submit out current account creation request");
					return accountcreation;
				}
			}
			accountcreation.setMessage("Unable to get request this movement of time");
			return accountcreation;			
		}
		
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		
		
		
		
		
		
		
		
		
		


//	public void verifyAccountRequestApproved(User user) throws Exception {
//
//		User tempUser = registrationRepository.findByEmailID(user.getEmailID());
//		Account tempAccount = accountRepository.findByUserId(tempUser.getId());
//
//		// accountRepository.findByAccountNumber(tempAccountNo) == null &&
//
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
//
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

}
