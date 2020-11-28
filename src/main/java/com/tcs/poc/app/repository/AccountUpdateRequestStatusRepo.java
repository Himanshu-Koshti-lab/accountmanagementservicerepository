package com.tcs.poc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.poc.app.entity.AccountUpdateRequestStatus;
import com.tcs.poc.app.entity.UserUpdateRequestStatus;

public interface AccountUpdateRequestStatusRepo  extends JpaRepository<AccountUpdateRequestStatus, Integer> {
	 AccountUpdateRequestStatus findById(int i);
}
