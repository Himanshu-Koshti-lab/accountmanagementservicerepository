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
@Table(name = "AccountRegStatusType")
public class AccountRegStatusType {

	@Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;
	
	 @Column(name = "accountRegStatus")
	 private String accountRegStatus;
}