package com.project.RequestTrackingSystem.controllers;

import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.RequestTrackingSystem.models.ChangePassword;
import com.project.RequestTrackingSystem.models.Department;
import com.project.RequestTrackingSystem.models.Requests;
import com.project.RequestTrackingSystem.models.User;
import com.project.RequestTrackingSystem.services.DeptService;
import com.project.RequestTrackingSystem.services.RequestService;
import com.project.RequestTrackingSystem.services.UserService;

import dto.APIResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Controller
public class MainController {

	@Autowired
	private UserService userSvc;

	@Autowired
	private DeptService deptSvc;

	@Autowired
	private RequestService reqSvc;

//	INDEX LOGIN
	@GetMapping("/")
	public String serveLogin(Model model, HttpServletRequest request) {
		User user = new User();
		model.addAttribute("user", user);

		return "index";
	}

	@GetMapping("/logout")
	public String logOut(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
//		session.setAttribute("userId", null);
//		session.setAttribute("userName", null);
		try {
			session.invalidate();
		} catch(Exception e) {
			System.out.println(e.toString());
			return "redirect:/";
		}
		
		
		System.out.println(request.getSession(false));
		return "redirect:/";
	}

	@PostMapping("/Login")
	public String login(@ModelAttribute("user") User user, Model model, HttpServletRequest request) {
		User argUser = userSvc.validate(user);
		if (argUser.getMsg().compareTo("Login Successful") == 0) {

			HttpSession session = request.getSession();
			session.setAttribute("userId", argUser.getUserId());
			session.setAttribute("userName", argUser.getFirstName());

			System.out.println(session.getAttribute("userId"));
			System.out.println(request.getSession(false));

			return "redirect:/dashboard";
		} else {
			argUser.setIsInvalid(true);
			// argUser.setMsg(argUser.getMsg());
			model.addAttribute("user", argUser);
			System.out.println(argUser.getMsg() + argUser.getIsInvalid());
			return "index";
		}
	}

	@GetMapping("/dashboard")
	public String getDashboard(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
//		System.out.println("ID" + session.getAttribute("userId"));
		if (session == null) {
			return "redirect:/";
		}
		return "dashboard";
	}

	@GetMapping("/ChangePassword")
	public ModelAndView changePassword(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("change_password");

		HttpSession session = request.getSession(false);
		System.out.println("In Change Password: " + session.getAttribute("userId"));

//		if(session.getAttribute("userId") == null) {
//			ModelAndView login = new ModelAndView("index");
//			User user = new User();
//			mav.addObject("user", user);
//			return login;
//		}

		ChangePassword password = new ChangePassword();
		password.setUserId((int) session.getAttribute("userId"));

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
		model.addAttribute("password", password);
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

	@GetMapping("/Dept")
	public String serveDepartment(Model model, HttpServletRequest request) {

		Department dept = new Department();

		HttpSession session = request.getSession(false);

		if (session == null) {
			return "redirect:/";
		}

		System.out.println("In Dept: " + session.getAttribute("userId"));

		dept.setUserId((int) session.getAttribute("userId"));

//		Get All Dept Codes to display in parent deptCode
		TreeMap<Integer, String> deptIdsAndCodes = deptSvc.getAllParentDeptId();

		model.addAttribute("deptIds", deptIdsAndCodes);
		model.addAttribute("dept", dept);
		return "Department";
	}

	@GetMapping("/EditDept/{id}")
	public ModelAndView showEditDeptPage(@PathVariable(name = "id") int id, HttpServletRequest request) {

		HttpSession session = request.getSession(false);

		ModelAndView mav = new ModelAndView("EditDepartment");
//		Product product = service.get(id);
		TreeMap<Integer, String> deptIdsAndCodes = deptSvc.getAllParentDeptId();

		Department dept = this.deptSvc.getByDeptId(id);
		dept.setUserId((int) session.getAttribute("userId"));
		mav.addObject("deptIds", deptIdsAndCodes);
		mav.addObject("dept", dept);

		return mav;
	}

	@PostMapping("/editDept")
	public String editDept(@ModelAttribute("dept") Department dept, Model model) {
		System.out.println("Edit Dept" + dept.getDeptId());
		String msg = deptSvc.edit(dept);
		System.out.println(msg);

		TreeMap<Integer, String> deptIdsAndCodes = deptSvc.getAllParentDeptId();

		model.addAttribute("deptIds", deptIdsAndCodes);
		model.addAttribute("dept", dept);
		model.addAttribute("message", msg);
		return "Department";
	}

	@PostMapping("/saveDept")
	public String saveDept(@ModelAttribute("dept") Department dept, Model model) {
		System.out.println("Save Dept" + dept.getDeptId());
		String msg = deptSvc.save(dept);
		System.out.println(msg);

		TreeMap<Integer, String> deptIdsAndCodes = deptSvc.getAllParentDeptId();

		model.addAttribute("deptIds", deptIdsAndCodes);
		model.addAttribute("dept", dept);
		model.addAttribute("message", msg);
		return "Department";
	}

	@GetMapping("/CreateRequest")
	public String getCreateRequest(Model model, HttpServletRequest req) {

		HttpSession session = req.getSession(false);

		if (session == null) {
			return "redirect:/";
		}

		Requests request = new Requests();
		TreeMap<Integer, String> deptIdsAndCodes = deptSvc.getAllDeptId();

		model.addAttribute("deptIds", deptIdsAndCodes);
		model.addAttribute("request", request);
		return "CreateRequest";
	}

	@PostMapping("/saveRequest")
	public String saveRqst(@ModelAttribute("request") Requests request, Model model) {
		System.out.println("Save Request");
		int status = this.reqSvc.saveRequest(request);

		if (status == 1) {
			return "redirect:/Homepage";
		} else {
			String msg = "Some Error Occured!! Please try again";
			model.addAttribute("message", msg);
			model.addAttribute("request", request);
			return "CreateRequest";
		}

//	        System.out.println(msg);
//	        model.addAttribute("message", msg);

	}

//	@GetMapping("/Homepage")
//	public String getHomepage(Model model) {
//
//		List<Requests> allRequests = reqSvc.getAllRequests();
//		model.addAttribute("allRequests", allRequests);
//		long totalRowCount = reqSvc.getTotalRows();
//		model.addAttribute("totalRows", totalRowCount);
//		return "Homepage";
//	}

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

//    @GetMapping("/{field}")
//    private String getRequestsWithSort(@PathVariable String field, Model model) {
//        List<Requests> allRequests = reqSvc.findRequestsWithSorting(field);
//        model.addAttribute("allRequests", allRequests);
//        return "Homepage";
//    }

//	=============================Original Code======================================
//	=============================Try with dropdown==================================
//    @GetMapping("/Homepage/{offset}/{pageSize}")
//    private String getRequestsWithPagination(@PathVariable int offset, @PathVariable int pageSize, Model model) {
//        Page<Requests> allRequests = reqSvc.findRequestsWithPagination(offset, pageSize);
//        model.addAttribute("allRequests", allRequests);
//        return "Homepage";
//    }

//	@GetMapping("/Homepage/{offset}")
//	private String getRequestsWithPagination(@PathVariable int offset, Model model) {
//		Page<Requests> allRequests = reqSvc.findRequestsWithPagination(offset - 1, 5);
//		model.addAttribute("allRequests", allRequests);
//		return "Homepage";
//	}

//    @GetMapping("/Homepage/{offset}/{pageSize}/{field}")
//    private String getRequestsWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field, Model model) {
//        Page<Requests> allRequests = reqSvc.findRequestsWithPaginationAndSorting(offset, pageSize, field);
//        model.addAttribute("allRequests", allRequests);
//        return "Homepage";
//    }

	@GetMapping(value = "/Homepage")
	public String listBooks(HttpServletRequest request, Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return "redirect:/";
		}

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(8);

		Page<Requests> reqPage = this.reqSvc.findPaginated(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("reqPage", reqPage);

		int totalPages = reqPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "Homepage";
	}

	@GetMapping("/dtoGetReq")
	private String getAllRequests(Model model) {
		List<Requests> requests = this.reqSvc.getReqs();
//	        return new APIResponse<>(requests.size(), requests);
		APIResponse<Object> api = new APIResponse<Object>(requests.size(), requests);
		System.out.println(api.getRecordCount());

		model.addAttribute("req", api.getResponse());

		return "DTO";
	}

	// ========================================================================================

	@GetMapping("/reset")
	public String serveResetPassword(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "resetPassword";
	}

	@PostMapping("/reset")
	public String resetPassword(@ModelAttribute("user") User user, Model model) {

		String msg = this.userSvc.forgotPassword(user);
		model.addAttribute("user", user);
		model.addAttribute("message",msg);
		return "resetPassword";
	}
	
	
	@GetMapping("/ManageUser")
	public String manageUser(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);

		if (session == null) {
			return "redirect:/";
		}
		
		User user = new User();
		model.addAttribute("user", user);
		return "ManageUser";
	}

}
