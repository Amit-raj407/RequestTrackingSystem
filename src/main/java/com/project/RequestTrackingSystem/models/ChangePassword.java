package com.project.RequestTrackingSystem.models;

import com.sun.istack.NotNull;

import lombok.Data;


@Data

public class ChangePassword {
	
	@NotNull
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private int userId;
	private String msg = "";
	private boolean isVisited = false;
	
	public boolean getVisited() {
		return isVisited;
	}
	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	public int getUserId() {
		return userId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
}
