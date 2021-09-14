package com.project.RequestTrackingSystem.services;

import java.util.TreeMap;

import com.project.RequestTrackingSystem.models.Department;



public interface DeptService {
	public String save(Department dept);
	public TreeMap<Integer, String> getAllDeptId();
}
