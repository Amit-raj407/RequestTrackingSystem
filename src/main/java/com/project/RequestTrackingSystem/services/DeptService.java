package com.project.RequestTrackingSystem.services;

import java.util.TreeMap;

import com.project.RequestTrackingSystem.models.Department;



public interface DeptService {
	public String save(Department dept);
	public TreeMap<Integer, String> getAllDeptId();
	public TreeMap<Integer, String> getAllParentDeptId();
	
	public Department getByDeptId(int id);

	public String edit(Department dept);

}
