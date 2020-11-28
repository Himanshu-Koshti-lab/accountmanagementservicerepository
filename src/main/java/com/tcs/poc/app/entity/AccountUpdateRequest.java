package com.tcs.poc.app.entity;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Account")
public class AccountUpdateRequest {

	@Id
    @GeneratedValue
    @Column(name="req_id")
	private int req_id;
	
	@Column(name="account_Number")
	private double accountNumber;
	
	@Column(name="userId")
	private int userId;
    
    
    @Column(name="created_date")
    private Date createdDate;
    
    @Column(name="lastModified_date")
    private Date lastModifiedDate;
    
    @Column(name="created_by")
    private String createdBy;
    
    @Column(name="modified_by")
    private String modifiedBy;

    @OneToOne
    @JoinColumn(name = "UserAccountStatusType", referencedColumnName = "id")
    private UserAccountStatusType userAccountStatusType;
    
    @OneToOne
    @JoinColumn(name = "AccountUpdateRequestStatus", referencedColumnName = "id")
    private AccountUpdateRequestStatus AccountUpdateRequestStatus;
}
