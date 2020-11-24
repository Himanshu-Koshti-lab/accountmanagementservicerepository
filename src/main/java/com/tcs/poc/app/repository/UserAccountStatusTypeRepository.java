package com.tcs.poc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tcs.poc.app.entity.UserAccountStatusType;

public interface UserAccountStatusTypeRepository extends JpaRepository<UserAccountStatusType, Integer> {

}
