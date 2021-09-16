package com.project.RequestTrackingSystem.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.RequestTrackingSystem.models.Requests;


public interface RequestService {
	public int saveRequest(Requests req);
	public String buildReqNumber(String deptCode,int seq);
	public List<Requests> getAllRequests();
	public Requests getRequestByID(int id);
//	public List<Requests> findRequestsWithSorting(String field);
	public Page<Requests> findRequestsWithPagination(int offset,int pageSize);
	public long getTotalRows();
	
	
	public List<Requests> getReqs();
	
	
	public Page<Requests> findPaginated(Pageable pageable);
//	public Page<Requests> findRequestsWithPaginationAndSorting(int offset,int pageSize,String field);
}
