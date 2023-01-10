
/**
 * 
 */
package com.capeelectric.serviceImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capeelectric.config.AWSConfig;
import com.capeelectric.exception.UserException;
import com.capeelectric.model.EmailContent;
import com.capeelectric.model.RegisterDetails;
import com.capeelectric.repository.RegisterRepo;
import com.capeelectric.service.RegisterService;

import ch.qos.logback.classic.Logger;

/**
 * @author Priyanka
 *
 **/
@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterRepo registerRepo;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${app.web.domain}")
	private String webUrl;

	@Autowired
	private AWSConfig awsConfiguration;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public void addRegisterDetails(RegisterDetails registerDetails) throws Exception,UserException {
		

		if (null != registerDetails && null != registerDetails.getEmpid()) {
			
			Optional<RegisterDetails> register = registerRepo.findByEmpid(registerDetails.getEmpid());
			Optional<RegisterDetails> register1 =registerRepo.findByEmailid(registerDetails.getEmailid());
			Optional<RegisterDetails> register2=registerRepo.findByMobilenumber(registerDetails.getMobilenumber());
			
			if (!register.isPresent() && !register1.isPresent() && !register2.isPresent()) {
				String password = registerDetails.getPassword();
				registerDetails.setPassword(passwordEncoder.encode(registerDetails.getPassword()));
				registerDetails.setStatus("Active");
				registerDetails.setCreateddate(LocalDateTime.now());

				registerDetails.setCreatedby("HR");

				registerRepo.save(registerDetails);
//				sendEmail(registerDetails.getEmailid(), "We welcome to cape electric private Limited family.\r\n"
//						+ "\r\n"
//						+ "You have been successfully registered for the Leave Management systems in Cape electric private limited.\r\n"
//						+ "\r\n"
//						+ "This application will help you in applying and managing your leave in cape electric. Kindly note your login and one time password below.\r\n"
//						+ "\r\n" + "Login:" + registerDetails.getEmailid() + "\r\n" + "Password:" + password + "\r\n"
//						+ "\r\n" + "You can change the password using this link " + webUrl + "\r\n" + "\r\n"
//						+ "Any Clarification, Please contact \"-----hr mail id-----\"\r\n" + "\r\n" + "Best Wishes\r\n"
//						+ "Admin,\r\n" + "cape electric Pvt Ltd,\r\n" + "Oragadam.");
//				System.out.println("hi");
			} else {
				if(register.isPresent()&&!register1.isPresent()&&!register2.isPresent()) {	
					throw new UserException("Employee Id Already Exist");
				}
				else if(!register.isPresent()&&register1.isPresent()&&!register2.isPresent()) {
					throw new UserException("Email Id Already Exist");
				}
				else if(!register.isPresent()&&!register1.isPresent()&&register2.isPresent()) {
					throw new UserException("Moblie Number Already Exist");
				}
				else if(register.isPresent()&&register1.isPresent()&&!register2.isPresent()) {
					throw new UserException("EmpId and Email Already Exist");
				}
				else if(!register.isPresent()&&register1.isPresent()&&register2.isPresent()){
					throw new UserException("Email and Mobile Number Already Exist");
				}
				else if(register.isPresent()&&!register1.isPresent()&&register2.isPresent()) {
					throw new UserException("EmpId and Mobile Number Alread Exist");
				}
				else {
					throw new UserException("EmpId ,Email and Mobile Number Already Exist");
				}
				
			}

		} else {
			throw new UserException("Invalid input");
		}

	}

	@Override
	public void updateRegisterDetails(RegisterDetails registerDetails) {
		registerDetails.setUpdateddate(LocalDateTime.now());
		registerDetails.setUpdatedby(registerDetails.getName());
		registerRepo.save(registerDetails);

		// TODO Auto-generated method stub

	}

	@Override
	public RegisterDetails getRegisterDetails(String empid) {
		// TODO Auto-generated method stub
		return registerRepo.findByEmpid(empid).get();
	}

	@Override
	public void deleteRegisterDetails(String empid) {
		Optional<RegisterDetails> register = registerRepo.findByEmpid(empid);
		if (register.isPresent()) {
			RegisterDetails registerData = register.get();
			registerData.setStatus("InActive");
			registerRepo.save(registerData);
		}
	}

//	@Override
//	public void sendEmail(String email,String ccEmail, String content) throws Exception {
//
//		try {
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		URI uri = new URI(awsConfiguration.getSendLmsEmail() + email +"/"+ccEmail);
//		EmailContent emailContent = new EmailContent();
//		emailContent.setContentDetails(content);
//		RequestEntity<EmailContent> requestEntity = new RequestEntity<>(emailContent, headers, HttpMethod.PUT, uri);
//		ParameterizedTypeReference<EmailContent> typeRef = new ParameterizedTypeReference<EmailContent>() {
//		};
//		ResponseEntity<EmailContent> exchange = restTemplate.exchange(requestEntity, typeRef);
//		}
//		catch(Exception e) {
//			throw new Exception("Mail sent failed");
//		}
//	}
//	
//	@Override
//	public void sendEmail(String email, String content) throws URISyntaxException {
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		URI uri = new URI(awsConfiguration.getSendLmsEmail() + email);
//		EmailContent emailContent = new EmailContent();
//		emailContent.setContentDetails(content);
//		RequestEntity<EmailContent> requestEntity = new RequestEntity<>(emailContent, headers, HttpMethod.PUT, uri);
//		ParameterizedTypeReference<EmailContent> typeRef = new ParameterizedTypeReference<EmailContent>() {
//		};
//		ResponseEntity<EmailContent> exchange = restTemplate.exchange(requestEntity, typeRef);
//	}

	@Override
	public List<RegisterDetails> getEmpidDetails() {
		// TODO Auto-generated method stub
		return (List<RegisterDetails>) registerRepo.findAll();
	}

	@Override
	public void updateRegister(String mobileNumber, String empid) {
		Optional<RegisterDetails> register = registerRepo.findByEmpid(empid);
		if (register.isPresent()) {
			register.get().setMobilenumber(mobileNumber);
			registerRepo.save(register.get());
		} else {

		}

	}

//	@Override
//	public String getMaxEmpId() {
//		 
//		Iterable<RegisterDetails> empId = registerRepo.findAll();
//		for (RegisterDetails registerDetails : empId) {
//			 for (String string : registerDetails.getEmpid().split("_")) {
//				 String[] split = string.split("_");
//		 			 if (Integer.parseInt(split[1])  > i)
//		 			 {
//							return "CAPE_"+(Integer.parseInt(split[1])+1);
////					return i;
//			 }
//		}
//	}

//}
}
