package com.tcs.poc.app.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.poc.app.entity.Account;
import com.tcs.poc.app.entity.AccountRegStatusType;
import com.tcs.poc.app.entity.User;
import com.tcs.poc.app.entity.UserAccountStatusType;
import com.tcs.poc.app.entity.UserAccountType;
import com.tcs.poc.app.model.AccountCreationRequest;
import com.tcs.poc.app.repository.AccountRegStatusTypeRepo;
import com.tcs.poc.app.repository.AccountRepository;
import com.tcs.poc.app.repository.UserAccountStatusTypeRepository;
import com.tcs.poc.app.repository.UserAccountTypeRepository;
import com.tcs.poc.app.repository.UserRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserRepository registrationRepository;

	@Autowired
	private UserAccountTypeRepository userAccountTypeRepository;

	@Autowired
	private AccountRegStatusTypeRepo AccountRegStatusTypeRepo;

	@Autowired
	private UserAccountStatusTypeRepository userAccountStatusTypeRepository;

	public void accountCreationRequest(AccountCreationRequest user) throws Exception {
		Account account = new Account();
		User tempUser = registrationRepository.findByEmailID(user.getEmailID());
		if (tempUser == null) {
			throw new Exception("");
		} else {

			Optional<UserAccountType> userAccountType = userAccountTypeRepository.findById(1);
			Optional<UserAccountStatusType> userAccountStatusType = userAccountStatusTypeRepository.findById(3);
			Optional<AccountRegStatusType> userAccountRegStatusType = AccountRegStatusTypeRepo.findById(1);

			account.setUserId(tempUser.getId());
			account.setUserAccountType(userAccountType.get());
			account.setUserAccountStatusType(userAccountStatusType.get());
			account.setAccountRegStatusType(userAccountRegStatusType.get());
			account.setCreatedDate(new Date());
			account.setLastModifiedDate(new Date());
			account.setCreatedBy(tempUser.getEmailID());
			account.setModifiedBy(tempUser.getEmailID());
			accountRepository.save(account);
			System.out.println("Account creation request successfully submit");
		}

	}

	public void verifyAccountRequestApproved(User user) throws Exception {

		User tempUser = registrationRepository.findByEmailID(user.getEmailID());
		Account tempAccount = accountRepository.findByUserId(tempUser.getId());

		// accountRepository.findByAccountNumber(tempAccountNo) == null &&

		if (tempAccount.getAccountRegStatusType().getId() == 1) {
			Optional<UserAccountStatusType> userAccountStatusType = userAccountStatusTypeRepository.findById(1);
			Optional<AccountRegStatusType> AccountRegStatusType = AccountRegStatusTypeRepo.findById(2);
			boolean x = true;
			while (x) {
				int tempAccountNo = (int) (Math.random() * 1000000);
				tempAccount.setAccountNumber(tempAccountNo);
				x = !(accountRepository.findByAccountNumber(tempAccountNo) == null);

			}
			// tempAccount.setUserAccountType(userAccountType.get());
			tempAccount.setBalance(500);
			tempAccount.setUserAccountStatusType(userAccountStatusType.get());
			tempAccount.setAccountRegStatusType(AccountRegStatusType.get());
			System.out.println(tempAccount.getAccountNumber());
			accountRepository.save(tempAccount);
		} else if (tempAccount.getAccountRegStatusType().getId() == 2) {
			throw new Exception("Already approved");
		} else {
			throw new Exception("Already Rejected");
		}

	}

	public void verifyAccountRequestReject(User user) throws Exception {

		User tempUser = registrationRepository.findByEmailID(user.getEmailID());
		Account tempAccount = accountRepository.findByUserId(tempUser.getId());
		if (tempAccount.getAccountRegStatusType().getId() == 1) {
			Optional<AccountRegStatusType> AccountRegStatusType = AccountRegStatusTypeRepo.findById(3);
			Optional<UserAccountStatusType> userAccountStatusType = userAccountStatusTypeRepository.findById(3);
			tempAccount.setUserAccountStatusType(userAccountStatusType.get());
			tempAccount.setAccountRegStatusType(AccountRegStatusType.get());
			accountRepository.save(tempAccount);
		} else if (tempAccount.getAccountRegStatusType().getId() == 2) {
			throw new Exception("Already approved");
		} else {
			throw new Exception("Already Rejected");
		}

	}

}
