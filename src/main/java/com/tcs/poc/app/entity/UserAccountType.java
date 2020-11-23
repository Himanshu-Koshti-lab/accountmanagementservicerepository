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
@Table(name = "userAccountType")
public class UserAccountType {

	@Id
    @GeneratedValue
    @Column(name="accountTypeId")
	private Integer accountTypeId;
	
	@Column(name="accountType")
	private String accountType;
}
