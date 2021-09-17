package com.project.RequestTrackingSystem.serviceImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.project.RequestTrackingSystem.models.MailResponse;
import com.project.RequestTrackingSystem.models.Requests;
import com.project.RequestTrackingSystem.models.SequenceCounter;
import com.project.RequestTrackingSystem.repos.DeptRepo;
import com.project.RequestTrackingSystem.repos.RequestRepo;
import com.project.RequestTrackingSystem.repos.SeqCounterRepo;
import com.project.RequestTrackingSystem.services.RequestService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;



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
			
			Map<String, Object> model = new HashMap<>();
    		model.put("Number", req.getRequestNumber());
    		model.put("Title", req.getRequestTitle());
    		model.put("Description", req.getRequestDescription());
    		model.put("location", "Cozentus, Bhubaneswar");
    		
			this.sendEmail(model);

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
		List<Requests> allRequests = this.reqRepo.findAllByCreatedDateDesc();
		return allRequests;
	}
	
	
	public List<Requests> getReqs() {
		return this.reqRepo.findAll();
	}

	public Requests getRequestByID(int id) {
		return this.reqRepo.getById(id);
	}
	
	public long getTotalRows() {
		return this.reqRepo.count();
	}
	
	
	
//	public List<Requests> findRequestsWithSorting(String field) {
//        return  reqRepo.findAll(Sort.by(Sort.Direction.ASC,field));
//    }


    public Page<Requests> findRequestsWithPagination(int offset, int pageSize) {
        Page<Requests> request = reqRepo.findAll(PageRequest.of(offset, pageSize).withSort(Direction.DESC, "createdDate"));
        return  request;
    }
    
    
    
    public Page<Requests> findPaginated(Pageable pageable) {
    	List<Requests> req = this.getAllRequests();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Requests> list;

        if (req.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, req.size());
            list = req.subList(startItem, toIndex);
        }

        Page<Requests> reqPage
          = new PageImpl<Requests>(list, PageRequest.of(currentPage, pageSize), req.size());

        return reqPage;
    }
    
    

//    public Page<Requests> findRequestsWithPaginationAndSorting(int offset,int pageSize,String field) {
//        Page<Requests> request = reqRepo.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
//        return  request;
//    }
    
    
    
    
    
  //==================================================================================================
    
    
    @Autowired
	private JavaMailSender sender;
	
	@Autowired
	private Configuration config;
	


	//@Override
	public MailResponse sendEmail(Map<String, Object> model) {
		// TODO Auto-generated method stub
		MailResponse response = new MailResponse();
		MimeMessage message = sender.createMimeMessage();
		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			
			

			Template t = config.getTemplate("email-template.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			helper.setTo("sritik.dash51@gmail.com");
			helper.setText(html, true);
			helper.setSubject("New Request");
			//helper.setFrom(request.getFrom());
			sender.send(message);

			response.setMessage("mail send to : " + "Sritik");
			response.setStatus(Boolean.TRUE);

		} catch (MessagingException | IOException | TemplateException e) {
			response.setMessage("Mail Sending failure : "+e.getMessage());
			response.setStatus(Boolean.FALSE);
		}

		return response;
	
		
	}
    
    
    
    
    
    
    
    
    
    
}
