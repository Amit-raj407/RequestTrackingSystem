package com.project.RequestTrackingSystem.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="requests")

public class Requests {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name="request_id")
private int requestId;
@Column(name="request_dept")
private String requestDept;
@Column(name="request_number")
private String requestNumber;
@Column(name="request_title")
private String requestTitle;
@Column(name="request_description")
private String requestDescription;
@Column(name="assigned_to")
private String assignedTo;
@Column(name="assigned_date")
private LocalDate assignedDate;
@Column(name="initial_comments")
private String initialComments;
@Column(name="created_date")
private LocalDate createdDate;
@Column(name="created_by")
private String createdBy;

@Transient
private static String seqNum = "00001";
public Requests() {
if(seqNum.compareTo("99999") == 0) {
seqNum = "00000";
}
int val = Integer.parseInt(seqNum);
val++;
seqNum = Integer.toString(val);
requestNumber = requestDept + seqNum;
}


public int getRequestId() {
return requestId;
}
public void setRequestId(int requestId) {
this.requestId = requestId;
}

public String getRequestDept() {
return requestDept;
}


public void setRequestDept(String requestDept) {
this.requestDept = requestDept;
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
public LocalDate getAssignedDate() {
return assignedDate;
}
public void setAssignedDate(LocalDate assignedDate) {
this.assignedDate = assignedDate;
}
public String getInitialComments() {
return initialComments;
}
public void setInitialComments(String initialComments) {
this.initialComments = initialComments;
}
public LocalDate getCreatedDate() {
return createdDate;
}
public void setCreatedDate(LocalDate createdDate) {
this.createdDate = createdDate;
}
public String getCreatedBy() {
return createdBy;
}
public void setCreatedBy(String createdBy) {
this.createdBy = createdBy;
}



}