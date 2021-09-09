package com.project.RequestTrackingSystem.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="dept")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="dept_id")
	 private int id;
	@Column(name="dept_code")
	private String deptCode;
	@Column(name="department_name")
	private String departmentName;
	@Column(name="parent_department_code")
	private String parentDepartmentCode;
	
	@Column(name="created_by")
	private String createdBy;
	@Column(name="is_dept_active")
	private String deptActive;
	
	
	@Transient
	private int userId;
	
	
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDeptActive() {
		return deptActive;
	}
	public void setDeptActive(String deptActive) {
		this.deptActive = deptActive;
	}
	
	
	
	
	public int getId() {
		return id;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public String getParentDepartmentCode() {
		return parentDepartmentCode;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public void setParentDepartmentCode(String parentDepartmentCode) {
		this.parentDepartmentCode = parentDepartmentCode;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	

}
