package com.tcs.poc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.poc.app.entity.AccountUpdateRequestStatus;

public interface AccountUpdateRequestStatusRepo  extends JpaRepository<AccountUpdateRequestStatus, Integer> {
	 AccountUpdateRequestStatus findById(int i);
}
