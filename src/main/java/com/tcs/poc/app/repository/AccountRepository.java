package com.tcs.poc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.poc.app.entity.Account;



public interface AccountRepository extends JpaRepository<Account, Integer> {

	Account findByUserId(Integer id);

	Account findByAccountNumber(double tempAccountNo);

}
