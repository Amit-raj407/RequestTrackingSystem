package com.project.RequestTrackingSystem.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "requests_comments")

public class RequestsComments {
	@Id
	@Column(name = "request_comment_id")
	private int requestCommentId;

	@Column(name = "request_sequence_no")
	private int requestSequenceNo;
	@Column(name = "request_comment")
	private String requestComment;
	@Column(name = "created_date")
	private LocalDate createdDate;
	@Column(name = "created_by")
	private String createdBy;

	@ManyToOne(targetEntity = Requests.class)
	@JoinColumn(name = "requestId")
	private int requestId;

	public int getRequestCommentId() {
		return requestCommentId;
	}

	public void setRequestCommentId(int requestCommentId) {
		this.requestCommentId = requestCommentId;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getRequestSequenceNo() {
		return requestSequenceNo;
	}

	public void setRequestSequenceNo(int requestSequenceNo) {
		this.requestSequenceNo = requestSequenceNo;
	}

	public String getRequestComment() {
		return requestComment;
	}

	public void setRequestComment(String requestComment) {
		this.requestComment = requestComment;
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