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
		Account account = new Account();
		AccountCreationResponse accountcreation = new AccountCreationResponse();
		List<Account> listofaccount = accountRepository.findAll();
		List<Account> listUseraccount = null;
		int count = 0;
		for(int i=0;i<listofaccount.size();i++) {
			if(listofaccount.get(i).getUserId() == user.getUser_id()) {				
				if(listofaccount.get(i).getUserAccountType().getId() == 1) {
					listUseraccount.add(listofaccount.get(i));
					count++;
					// user have saving account
				}
				if(listofaccount.get(i).getUserAccountType().getId() == 2) {
					listUseraccount.add(listofaccount.get(i));
					count++;
					// user have current account
				}				
			}
		}
		if(count == 2) {
			accountcreation.setMessage("User Already have both type of account");
			return accountcreation;
		}else if(listUseraccount.get(0).getUserAccountType().getId() == 1 && user.getUserAccountType() == 1){
			//user already have salary account
			accountcreation.setMessage("User Already have Salary account");
			return accountcreation;
		}else if(listofaccount.get(0).getUserAccountType().getId() == 1 && user.getUserAccountType() == 2){
			//create current account
			Optional<UserAccountType> userAccountType = userAccountTypeRepository.findById(2);
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
			System.out.println("Account creation request successfully submit");
			accountcreation.setMessage("User submit Current account creation request Successfully");
			return accountcreation;
		}else if(listUseraccount.get(0).getUserAccountType().getId() == 2 && user.getUserAccountType() == 2){
			//user already have current account
			accountcreation.setMessage("User Already have Current account");
			return accountcreation;
		}else if(listofaccount.get(0).getUserAccountType().getId() == 2 && user.getUserAccountType() == 1){
			//create salary account
			Optional<UserAccountType> userAccountType = userAccountTypeRepository.findById(1);
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
			System.out.println("Account creation request successfully submit");
			accountcreation.setMessage("User submit Salary account creation request Successfully");
			return accountcreation;
		}else {
			// First account
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
		}
		
	}	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//		User tempUser = registrationRepository.findByEmailID(user.getEmailID());
//			Optional<UserAccountType> userAccountType = userAccountTypeRepository.findById(1);
//			Optional<UserAccountStatusType> userAccountStatusType = userAccountStatusTypeRepository.findById(3);
//			Optional<AccountRegStatusType> userAccountRegStatusType = AccountRegStatusTypeRepo.findById(1);
//
//			account.setUserId(user.getUser_id());
//			account.setUserAccountType(userAccountType.get());
//			account.setUserAccountStatusType(userAccountStatusType.get());
//			account.setAccountRegStatusType(userAccountRegStatusType.get());
//			account.setCreatedDate(new Date());
//			account.setLastModifiedDate(new Date());
//			account.setCreatedBy(user.getEmailID());
//			account.setModifiedBy(user.getEmailID());
//			accountRepository.save(account);
//			System.out.println("Account creation request successfully submit");
//		}
//
//	}

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
