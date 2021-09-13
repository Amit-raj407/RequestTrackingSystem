package com.project.RequestTrackingSystem.controllers;



import java.util.List;
import java.util.TreeMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;

import com.project.RequestTrackingSystem.models.ChangePassword;
import com.project.RequestTrackingSystem.models.Department;
import com.project.RequestTrackingSystem.models.Requests;
import com.project.RequestTrackingSystem.models.User;
import com.project.RequestTrackingSystem.services.DeptService;
import com.project.RequestTrackingSystem.services.RequestService;
import com.project.RequestTrackingSystem.services.UserService;







@Controller
public class MainController {
	
	
	private UserService userSvc;
	private DeptService deptSvc;
	private RequestService reqSvc;
	

	
	public MainController(UserService userSvc, DeptService deptSvc,RequestService reqSvc) {
		this.userSvc = userSvc;
		this.deptSvc = deptSvc;
		this.reqSvc = reqSvc;
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
	
	
	@GetMapping("/Dept/{id}")
	public String serveDepartment(@PathVariable(name = "id") int id, Model model) {
		Department dept=new Department();
		dept.setUserId(id);
		model.addAttribute("dept",dept);
		return "Department";
	}
	
	@PostMapping("/saveDept")
	public String saveDept(@ModelAttribute("dept") Department dept, Model model) {
		System.out.println("Save Dept");
		String msg = deptSvc.save(dept);
		System.out.println(msg);
		model.addAttribute("message", msg);
		return "Department";
	}
	
	@GetMapping("/CreateRequest")
	public String getCreateRequest(Model model) {
		Requests request=new Requests();
		TreeMap<Integer, String> deptIdsAndCodes = deptSvc.getAllDeptId();
		
//		for(String code : deptCodes) {
//			System.out.println(code);
//		}
		
		
		model.addAttribute("deptIds", deptIdsAndCodes);
        model.addAttribute("request",request);
        return "CreateRequest";
	}
	
	 @PostMapping("/saveRequest")
	    public String saveRqst(@ModelAttribute("request") Requests request, Model model) {
	        System.out.println("Save Request");
	        int status = this.reqSvc.saveRequest(request);
	        
	        if(status == 1) {
	        	return "redirect:/Homepage";
	        } else {
	        	String msg = "Some Error Occured!! Please try again";
	        	model.addAttribute("message",msg);
	        	return "CreateRequest";
	        }
	        
//	        System.out.println(msg);
//	        model.addAttribute("message", msg);
	        
	  }
	
	@GetMapping("/Homepage")
	public String getHomepage(Model model) {

		List<Requests> allRequests = reqSvc.getAllRequests();
		model.addAttribute("allRequests", allRequests);
		return "Homepage";
	}
	
	@GetMapping("/EditRequest/{id}")
	public ModelAndView showEditRequestPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("EditRequest");
//		Product product = service.get(id);
		TreeMap<Integer, String> deptIdsAndCodes = deptSvc.getAllDeptId();
		
		Requests request = this.reqSvc.getRequestByID(id);
		mav.addObject("deptIds", deptIdsAndCodes);
		mav.addObject("request", request);
		
		return mav;
	}
	
	
}
