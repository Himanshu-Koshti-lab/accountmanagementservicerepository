package com.tcs.poc.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.poc.app.entity.UserUpdateRequest;



public interface UserUpdateReqRepository extends JpaRepository<UserUpdateRequest, Integer> {

	UserUpdateRequest findByEmailID(String emailID);
}