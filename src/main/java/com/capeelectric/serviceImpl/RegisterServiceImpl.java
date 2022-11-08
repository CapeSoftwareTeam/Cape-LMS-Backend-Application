/**
 * 
 */
package com.capeelectric.serviceImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capeelectric.config.AWSConfig;
import com.capeelectric.model.EmailContent;
import com.capeelectric.model.RegisterDetails;
import com.capeelectric.repository.RegisterRepo;
import com.capeelectric.service.RegisterService;

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

	@Override
	public void addRegisterDetails(RegisterDetails registerDetails) throws Exception {

		if (null != registerDetails && null != registerDetails.getEmpid()) {
			Optional<RegisterDetails> register = registerRepo.findByEmpid(registerDetails.getEmpid());
			if (!register.isPresent()) {
				registerDetails.setStatus("Active");
				registerDetails.setCreateddate(LocalDateTime.now());

				registerDetails.setCreatedby("HR");

				registerRepo.save(registerDetails);
				sendEmail(registerDetails.getEmailid(), "We welcome to cape electric private Limited family.\r\n"
						+ "\r\n"
						+ "You have been successfully registered for the Leave Management systems in Cape electric private limited.\r\n"
						+ "\r\n"
						+ "This application will help you in applying and managing your leave in cape electric. Kindly note your login and one time password below.\r\n"
						+ "\r\n"
						+ "Login:"+registerDetails.getEmailid()+"\r\n"
						+ "Password:"+registerDetails.getPassword()+"\r\n"
						+ "\r\n"
						+ "You can change the password using this link "+ webUrl +"\r\n"
						+ "\r\n"
						+ "Any Clarification, Please contact \"-----hr mail id-----\"\r\n"
						+ "\r\n"
						+ "Best Wishes\r\n"
						+ "Admin,\r\n"
						+ "cape electric Pvt Ltd,\r\n"
						+ "Oragadam.");
			} else {
				throw new Exception("Invalid input");
			}

		} else {
			throw new Exception(" Employee Data Already exist");
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
	public void getRegisterDetails(Integer empid) {
		registerRepo.findById(empid);
	}

	@Override
	public void deleteRegisterDetails(Integer empid) {
		Optional<RegisterDetails> register = registerRepo.findById(empid);
		if (register.isPresent()) {
			RegisterDetails registerData = register.get();
			registerData.setStatus("InActive");
			registerRepo.save(registerData);
		}
	}

	public void sendEmail(String email, String content) throws URISyntaxException {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		URI uri = new URI(awsConfiguration.getSendEmail()+email);
		EmailContent emailContent = new EmailContent();
		emailContent.setContentDetails(content);
		RequestEntity<EmailContent> requestEntity = new RequestEntity<>(emailContent, headers, HttpMethod.PUT, uri);
		ParameterizedTypeReference<EmailContent> typeRef = new ParameterizedTypeReference<EmailContent>() {};

		ResponseEntity<EmailContent> exchange = restTemplate.exchange(requestEntity, typeRef);
		System.out.println(exchange);

	}

}
