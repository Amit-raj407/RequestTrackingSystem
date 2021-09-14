package com.project.RequestTrackingSystem.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="dept")
public class Department {
	
	@Id
	@Column(name="dept_id")
	private int deptId;
	
	@Column(name="dept_code")
	private String deptCode;
	
	@Column(name="department_name")
	private String departmentName;
	
	@Column(name="parent_department_code")
	private String parentDepartmentCode;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="is_dept_active")
	private boolean deptActive;
	
	@OneToMany(mappedBy = "requestDept",targetEntity = Requests.class)
	List<Requests> reqList;
	
	
	@Transient
	private int userId;
	
	
	
	
	
	
	
	
	
	
	public List<Requests> getReqList() {
		return reqList;
	}
	public void setReqList(List<Requests> reqList) {
		this.reqList = reqList;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public boolean getDeptActive() {
		return deptActive;
	}
	public void setDeptActive(boolean deptActive) {
		this.deptActive = deptActive;
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

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	
	

}
