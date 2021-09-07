package com.project.RequestTrackingSystem.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.RequestTrackingSystem.models.User;
import com.project.RequestTrackingSystem.repos.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static boolean validateEmail(String emailStr) {
	        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
	        return matcher.find();
	}
	
	public User validate(User user) {
		User argUser;	
		String msg;
		boolean isEmail = validateEmail(user.getUserEmail());
		
		if(isEmail) {
			argUser = userRepo.findByUserEmail(user.getUserEmail());
			System.out.println(argUser);
			System.out.println(userRepo.findByUserEmail(user.getUserEmail()));
			
			if(argUser == null) {
				msg = "Email doesn't Exist";
			} else if(user.getUserPassword().compareTo(argUser.getUserPassword()) != 0) {
				msg = "Invalid Email or Password";
			} else {
				msg = "Login Successful";
			}
			
		}
		
		else {
			user.setUserName(user.getUserEmail());
			user.setUserEmail(null);
			argUser = userRepo.findByUserName(user.getUserName());
			System.out.println(argUser);
			
			System.out.println(userRepo.findByUserName(user.getUserName()));
			if(argUser == null) {
				msg = "UserName doesn't Exist";
			} else if(user.getUserPassword().compareTo(argUser.getUserPassword()) != 0) {
				msg = "Invalid UserName or Password";
			} else {
				msg = "Login Successful";
			}
		}
		if(argUser == null) {
			argUser = new User();
		}
		argUser.setMsg(msg);
		return argUser;
		
	}

}
