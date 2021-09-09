package com.project.RequestTrackingSystem.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.RequestTrackingSystem.models.Department;


@Repository
public interface DeptRepo extends JpaRepository<Department, Integer> {
	public List<Department> findAllByParentDepartmentCode(String parentDepartmentCode);
	public List<Department> findAllByDeptCode(String departmentCode);
	
}
