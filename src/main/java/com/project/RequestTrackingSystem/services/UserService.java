package com.project.RequestTrackingSystem.services;

import java.util.List;

import com.project.RequestTrackingSystem.models.ChangePassword;
import com.project.RequestTrackingSystem.models.User;
import com.project.RequestTrackingSystem.models.UserDept;



public interface UserService {
	public User validate(User user);
	public void save(User user);
	public ChangePassword verifyPassword(ChangePassword pass);
	public ChangePassword changeAnyPassword(ChangePassword pass);
	public String forgotPassword(User user);
	
	public List<UserDept> getAllUsersByDept(int deptId);
}
