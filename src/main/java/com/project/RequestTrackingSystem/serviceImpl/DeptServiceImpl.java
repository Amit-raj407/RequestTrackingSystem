package com.project.RequestTrackingSystem.serviceImpl;


import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.RequestTrackingSystem.models.Department;

import com.project.RequestTrackingSystem.repos.DeptRepo;
import com.project.RequestTrackingSystem.repos.UserRepo;
import com.project.RequestTrackingSystem.services.DeptService;

@Service
public class DeptServiceImpl implements DeptService {
	
	@Autowired
	DeptRepo deptRepo;
	
	@Autowired
	UserRepo userRepo;
	
	public String save(Department dept) {
		System.out.println(dept.getDepartmentName()+dept.getDeptCode()+dept.getParentDepartmentCode()+dept.getDeptActive()+dept.getUserId());
		List<Department> d;
		String msg;
		dept.setCreatedBy(userRepo.getById(dept.getUserId()).getFirstName());
		
		if(dept.getParentDepartmentCode().compareTo("none") == 0) {
			dept.setParentDepartmentCode(dept.getDeptCode());
		}
		
		if(this.deptRepo == null) {
			if(dept.getDeptCode().compareTo(dept.getParentDepartmentCode()) == 0) {
				deptRepo.save(dept);
				msg = "Department Saved Successfully";
			} else {
				msg = "Parent Department Doesn't Exist!! ";
			}
		}
		
		d = deptRepo.findAllByDeptCode(dept.getDeptCode());
		
		if(d.isEmpty()) {
			if(dept.getDeptCode().compareTo(dept.getParentDepartmentCode()) == 0) {
				deptRepo.save(dept);
				msg = "Department Saved Successfully";
			} else {
				d = deptRepo.findAllByDeptCode(dept.getParentDepartmentCode());
				if(d.isEmpty()) {
					msg = "Parent Department Doesn't Exist!! ";
				} else {
					msg = "Department Saved Successfully";
					deptRepo.save(dept);
				}
			}
		} else {
			msg = "Department Code already Exists!!";
		}
		
		return msg;
	}
	
	public TreeMap<Integer, String> getAllDeptId() {
		
		List<Department> getAllDept = deptRepo.findAll();
		
//		To store Departments in sorted order according to DeptID
		TreeMap<Integer, String> treeMapDeptCodes = new TreeMap<Integer, String>();
		
		for(Department codes : getAllDept) {
			
			treeMapDeptCodes.put(codes.getDeptId(), codes.getDeptCode());
		}
		
		System.out.println(treeMapDeptCodes);
		return treeMapDeptCodes;
	}
}
