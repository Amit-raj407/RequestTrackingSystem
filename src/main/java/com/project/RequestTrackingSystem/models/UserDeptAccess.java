package com.project.RequestTrackingSystem.models;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user_dept_access")
public class UserDeptAccess {

	@Id
	@Column(name = "user_dept_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userDeptId;
	
	
	@Column(name = "userid")
	private int userId;
	
	@Column(name = "deptid")
	private int deptId;
	
	
	@Column(name = "created_date")
	private Date createdDate = new Date();
	
	@Column(name = "created_by")
	private String createdBy;
	
	
	@Column(name = "is_admin")
	private boolean isAdmin;
	
	
	@Column(name = "is_user")
	private boolean isUser;


	public int getUserDeptId() {
		return userDeptId;
	}


	public int getUserId() {
		return userId;
	}


	public int getDeptId() {
		return deptId;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public boolean isAdmin() {
		return isAdmin;
	}


	public boolean isUser() {
		return isUser;
	}


	public void setUserDeptId(int userDeptId) {
		this.userDeptId = userDeptId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}
}
