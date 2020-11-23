package com.tcs.poc.app.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UserAccountStatusType")
public class UserAccountStatusType {

	@Id
    @GeneratedValue
    @Column(name="accountStatusId")
    private Integer accountStatusId;
	
	 @Column(name = "accountStatus")
	 private String accountStatus;
}
