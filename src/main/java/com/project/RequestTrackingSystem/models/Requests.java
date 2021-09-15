package com.project.RequestTrackingSystem.models;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="requests")

public class Requests {
	
	@Id
	@Column(name="request_id")
	private int requestId;

	@Column(name="request_number")
	private String requestNumber;
	
	@Column(name="request_title")
	private String requestTitle;
	
	@Column(name="request_description")
	private String requestDescription;
	
	@Column(name="assigned_to")
	private String assignedTo;
	
	
	
	@Column(name="initial_comments")
	private String initialComments;
	
	
	
	@Column(name="created_by")
	private String createdBy = "Amit";

	@ManyToOne(targetEntity = Department.class)
	@JoinColumn(name="request_dept")
	private Department requestDept;

	
	
	@OneToMany(mappedBy = "requestId",targetEntity = RequestsComments.class)
	List<Requests> CommentList;

	
	@Transient
	private int noOfRows;


	public int getNoOfRows() {
		return noOfRows;
	}



	@Transient
	private static String seqNum = "00001";
//	public Requests() {
//		
//	}


	
	public static String getSeqNum() {
		return seqNum;
	}
	
	public static void setSeqNum(String seqNum) {
		Requests.seqNum = seqNum;
	}
	
	
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	

	

	public String getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}
	public String getRequestTitle() {
		return requestTitle;
	}
	public void setRequestTitle(String requestTitle) {
		this.requestTitle = requestTitle;
	}
	public String getRequestDescription() {
		return requestDescription;
	}
	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	
	public String getInitialComments() {
		return initialComments;
	}
	public void setInitialComments(String initialComments) {
		this.initialComments = initialComments;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Department getRequestDept() {
		return requestDept;
	}

	public void setRequestDept(Department requestDept) {
		this.requestDept = requestDept;
	}

	



}