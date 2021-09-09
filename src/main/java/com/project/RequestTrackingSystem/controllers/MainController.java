package com.project.RequestTrackingSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.RequestTrackingSystem.models.ChangePassword;
import com.project.RequestTrackingSystem.models.User;
import com.project.RequestTrackingSystem.services.UserService;





@Controller
public class MainController {
	
	@Autowired
	private UserService userSvc;
	

	
	public MainController(UserService userSvc) {
		this.userSvc=userSvc;
	}
	 
	@GetMapping("/")
	public String serveLogin(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		
		return "index";
	}
	
	@PostMapping("/Login")
	public String login(@ModelAttribute("user") User user, Model model) {
		User argUser = userSvc.validate(user);
		if(argUser.getMsg().compareTo("Login Successful") == 0) {
			model.addAttribute("user",argUser);
			return "dashboard";
		} else {
			argUser.setIsInvalid(true);
			// argUser.setMsg(argUser.getMsg());
			model.addAttribute("user",argUser);
			System.out.println(argUser.getMsg()+argUser.getIsInvalid());
			return "index";
		}
		
	}
	
	
	@GetMapping("/ChangePassword/{id}")
	public ModelAndView changePassword(@PathVariable(name = "id") int id, Model model) {
		ModelAndView mav = new ModelAndView("change_password");
		ChangePassword password = new ChangePassword();
		password.setUserId(id);
		mav.addObject("password", password);
		
		return mav;
			
	}
	
	@PostMapping("/UpdatePassword")
	public String updatePassword(@ModelAttribute("password") ChangePassword password, Model model) {
		
		ChangePassword argPassword = userSvc.verifyPassword(password);
		System.out.println(argPassword.getMsg());
		model.addAttribute("message", argPassword.getMsg());
		argPassword.setVisited(true);
		model.addAttribute("password", argPassword);
		return "change_password";
	}
	
	
	@GetMapping("/ChangeAnyPassword")
	public String getChangeAnyPassword(Model model) {
		ChangePassword password = new ChangePassword();
		model.addAttribute("password" ,password);
		return "ChangeAnyPassword";
	}
	
	@PostMapping("/UpdateAnyPassword")
	public String updateAnyPassword(@ModelAttribute("password") ChangePassword password, Model model) {
		
		ChangePassword argPassword = userSvc.changeAnyPassword(password);
		System.out.println(argPassword.getMsg());
		model.addAttribute("message", argPassword.getMsg());
		model.addAttribute("password", argPassword);
		return "ChangeAnyPassword";
	}
	
	
}
