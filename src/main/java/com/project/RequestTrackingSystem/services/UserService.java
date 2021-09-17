package com.project.RequestTrackingSystem.services;

import com.project.RequestTrackingSystem.models.ChangePassword;
import com.project.RequestTrackingSystem.models.User;



public interface UserService {
	public User validate(User user);
	public void save(User user);
	public ChangePassword verifyPassword(ChangePassword pass);
	public ChangePassword changeAnyPassword(ChangePassword pass);
	public String forgotPassword(User user);
}
