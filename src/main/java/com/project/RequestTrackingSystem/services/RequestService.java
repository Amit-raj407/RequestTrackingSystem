package com.project.RequestTrackingSystem.services;

import java.util.List;



import com.project.RequestTrackingSystem.models.Requests;


public interface RequestService {
	public int saveRequest(Requests req);
	public String buildReqNumber(String deptCode,int seq);
	public List<Requests> getAllRequests();
	public Requests getRequestByID(int id);
}
