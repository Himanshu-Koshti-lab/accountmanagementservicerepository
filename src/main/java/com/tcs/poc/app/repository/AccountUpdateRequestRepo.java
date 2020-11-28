package com.tcs.poc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.poc.app.entity.AccountUpdateRequest;

public interface AccountUpdateRequestRepo extends JpaRepository<AccountUpdateRequest, Integer>{

	AccountUpdateRequest findByUserID(int userid);

}
