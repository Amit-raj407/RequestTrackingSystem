package com.project.RequestTrackingSystem.serviceImpl;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.RequestTrackingSystem.models.Requests;
import com.project.RequestTrackingSystem.models.SequenceCounter;
import com.project.RequestTrackingSystem.repos.DeptRepo;
import com.project.RequestTrackingSystem.repos.RequestRepo;
import com.project.RequestTrackingSystem.repos.SeqCounterRepo;
import com.project.RequestTrackingSystem.services.RequestService;

@Service
public class RequestServiceImpl implements RequestService {

	@Autowired
	RequestRepo reqRepo;

	@Autowired
	DeptRepo deptRepo;

	@Autowired
	SeqCounterRepo seqRepo;

	public int saveRequest(Requests req) {
		int status;

		System.out.println(req);
		System.out.print(req.getRequestTitle() + req.getRequestDept() + req.getRequestNumber());

		// deptRepo.findById(req.getRequestDept()).get().getDeptCode();

//		req.setRequestNumber(this.generateReqNumber(
//				deptRepo.findById(req.getRequestDept().getDeptId()).get().getDeptCode(), Requests.getSeqNum()));

		int seqCounter = seqRepo.getSeqNumber();
		req.setRequestNumber(this.buildReqNumber(deptRepo.findById(req.getRequestDept().getDeptId()).get().getDeptCode(), seqCounter));

		try {

			seqRepo.save(new SequenceCounter());
			reqRepo.save(req);

			status = 1;
		} catch (Exception e) {
			System.out.println(e.toString());
			status = 0;
		}

		return status;
	}

//	public String generateReqNumber(String requestDept, String seqNum) {
//		String requestNumber = "";
//
//		System.out.println("Requests Constructor");
//		if (seqNum.compareTo("100000") == 0) {
//			seqNum = "00001";
//		}
//
//		requestNumber = requestDept + seqNum;
//		System.out.println("REQ:" + requestNumber);
//
//		int index;
//		for (index = 0; index < seqNum.length(); index++) {
//			if (seqNum.charAt(index) != '0') {
//				System.out.println(index);
//				break;
//			}
//		}
//
//		String str = seqNum;
//		String substr = str.substring(index, seqNum.length());
//		String substr1 = str.substring(0, index);
//		System.out.println("range :" + substr1);
//		System.out.println("number = " + substr);
//		int val = Integer.parseInt(substr);
//		val++;
//		substr = Integer.toString(val);
//
//		seqNum = substr1 + substr;
//
//		System.out.println("SEQ:" + seqNum);
//		Requests.setSeqNum(seqNum);
//		return requestNumber;
//
//	}

	public String buildReqNumber(String deptCode,int seq) {
		DecimalFormat df = new DecimalFormat("00000");

		String reqNumber = df.format(seq);
		System.out.println("REQ NUMBER:"+reqNumber);
		return deptCode+reqNumber;
	}

	public List<Requests> getAllRequests() {
		List<Requests> allRequests = this.reqRepo.findAll();
		return allRequests;
	}

	public Requests getRequestByID(int id) {
		return this.reqRepo.getById(id);
	}
}
