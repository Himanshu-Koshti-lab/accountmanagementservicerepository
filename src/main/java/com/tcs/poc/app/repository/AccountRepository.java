package com.tcs.poc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.poc.app.entity.Account;



public interface AccountRepository extends JpaRepository<Account, Integer> {

	List<Account> findByUserId(Integer id);

	Account findByAccountNumber(double tempAccountNo);

}
