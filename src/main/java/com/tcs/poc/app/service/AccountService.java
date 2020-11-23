package com.tcs.poc.app.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.poc.app.entity.Account;
import com.tcs.poc.app.entity.User;
import com.tcs.poc.app.repository.AccountRepository;
import com.tcs.poc.app.repository.UserRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserRepository registrationRepository;

	public void createAccountRequest(User user) throws Exception {
		Account account = new Account();
		com.tcs.poc.app.entity.User tempUser = registrationRepository.findByEmailID(user.getEmailID());
		if (tempUser == null) {
			throw new Exception("");
		} else {
			account.setUserId(tempUser.getId());
			account.setAccountStatusId(0);
			account.setAccountTypeId(0);
			account.setCreatedDate(new Date());
			account.setLastModifiedDate(new Date());
			account.setCreatedBy(tempUser.getEmailID());
			account.setModifiedBy(tempUser.getEmailID());
			accountRepository.save(account);
			System.out.println("Account creation request successfully submit");
		}

	}

	public void verifyAccountRequest(User user) {

		com.tcs.poc.app.entity.User tempUser = registrationRepository.findByEmailID(user.getEmailID());
		Account tempAccount = accountRepository.findByUserId(tempUser.getId());
		tempAccount.setAccountStatusId(1);
		int tempAccountNo = (int) (Math.random() * 100000);
		if (accountRepository.findByAccountNumber(tempAccountNo) == null) {
			tempAccount.setAccountNumber(tempAccountNo);
			System.out.println(tempAccount.getAccountNumber());
			accountRepository.save(tempAccount);
		}

	}
}
