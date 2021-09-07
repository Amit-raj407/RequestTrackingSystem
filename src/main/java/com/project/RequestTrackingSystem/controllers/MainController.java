package com.project.RequestTrackingSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
			return "ruchi";
		} else {
			argUser.setIsInvalid(true);
			argUser.setMsg(argUser.getMsg());
			model.addAttribute("user",argUser);
			System.out.println(argUser.getMsg()+argUser.getIsInvalid());
			return "index";
		}
		
	}
	
	
	
}
