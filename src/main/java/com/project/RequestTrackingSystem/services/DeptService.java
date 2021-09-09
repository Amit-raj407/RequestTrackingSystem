package com.project.RequestTrackingSystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.RequestTrackingSystem.models.Department;
import com.project.RequestTrackingSystem.repos.DeptRepo;

@Service
public class DeptService {
	
	@Autowired
	DeptRepo deptRepo;
	
	public String save(Department dept) {
		System.out.println(dept.getDepartmentName()+dept.getDeptCode()+dept.getParentDepartmentCode()+dept.getDeptActive());
		List<Department> d;
		String msg;
		dept.setCreatedBy("Amit");
		if(this.deptRepo == null) {
			if(dept.getDeptCode().compareTo(dept.getParentDepartmentCode()) == 0) {
//				if(dept.getIsActive().compareTo("true") == 0) {
//					dept.setDeptActive(true);
//				} else {
//					dept.setDeptActive(false);
//				}
				deptRepo.save(dept);
				msg = "Department Saved Successfully";
			} else {
				msg = "Parent Department Doesn't Exist!!";
			}
		}
		d = deptRepo.findAllByDeptCode(dept.getDeptCode());
		
		if(d.isEmpty()) {
			if(dept.getDeptCode().compareTo(dept.getParentDepartmentCode()) == 0) {
				deptRepo.save(dept);
				msg = "Department Saved Successfully";
			} else {
				d = deptRepo.findAllByParentDepartmentCode(dept.getParentDepartmentCode());
				if(d.isEmpty()) {
					msg = "Parent Department Doesn't Exist!!";
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
}
