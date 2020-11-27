package com.tcs.poc.app.entity;

import java.util.Date;

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
@Table(name = "userRequestCatlog")
public class UserUpdateRequest {

	@Id
	@GeneratedValue
	@Column(name = "userRequestId")
	private Integer userRequestId;

	@Column(name = "emailID")
	private String emailID;

	@Column(name = "newMobileNo")
	private long newMobileNo;

	@Column(name = "created_date")
	private Date createdDate;
	
	 @OneToOne
	 @JoinColumn(name = "UserAccountStatusType", referencedColumnName = "id")
	 private UserAccountStatusType userAccountStatusType;

	@Column(name = "lastModified_date")
	private Date lastModifiedDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_by")
	private String modifiedBy;

	@OneToOne
	@JoinColumn(name = "UserUpdateRequestStatus", referencedColumnName = "id")
	private UserUpdateRequestStatus UserRequestStatus;
}