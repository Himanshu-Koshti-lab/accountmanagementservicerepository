package com.tcs.poc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.poc.app.entity.UserAccountType;

public interface UserAccountTypeRepository extends JpaRepository<UserAccountType, Integer> {

}
