package com.tcs.poc.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.poc.app.entity.UserUpdateRequestStatus;




public interface UserUpdateRequestStatusRepo extends JpaRepository<UserUpdateRequestStatus, Integer> {

	UserUpdateRequestStatus findById(int i);
}