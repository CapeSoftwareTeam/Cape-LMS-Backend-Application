package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.capeelectric.config.AWSConfig;

import com.capeelectric.config.OtpConfig;
import com.capeelectric.exception.UserException;
import com.capeelectric.model.Register;
import com.capeelectric.model.RegisterDetails;
import com.capeelectric.repository.RegisterRepo;
import com.capeelectric.serviceImpl.RegisterServiceImpl;
import com.capeelectric.serviceImpl.RegistrationDetailsServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class RegisterServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(RegisterServiceTest.class);
	
	@MockBean
	private OtpConfig otpConfig;
	
	@MockBean
	private RegisterRepo registerRepo;
	
	@InjectMocks
	private RegisterServiceImpl registerServiceImpl;
	
	@Mock
	private RestTemplate restTemplate;
	
	@MockBean
	private AWSConfig awsConfig;
	
	@MockBean
    private BCryptPasswordEncoder passwordEncoder;;

	private RegisterDetails register;
	
	@Value("${app.web.domain}")
	private String webUrl;
	{
		register  = new RegisterDetails();
		register.setEmpid("CEPL_687");
		register.setEmailid("raji@gmail.com");
		register.setName("Raji");
		register.setGender("Female");
		register.setCountry("India");
		register.setCity("chennai");
		register.setState("Tamilnadu");
		register.setCapeexperience("0");
		register.setOtherexperience("1");
		register.setMobilenumber("9150201701");
		register.setAlternatenumber("9150501789");
		register.setDepartment("Software");
		register.setDesignation("software developer");
		register.setMaritalstatus("unmarried");
		register.setPassword("raji@123");
		register.setManageremail("sathish@capeindia.net");
		register.setManagername("sathish dharanidharan");
		register.setTotalexperience(1);
		 
	}
		
	
	@Test
	public void testRegister() throws Exception  {
		logger.info("RegistrationServiceTest testAddRegistration_funcion Started");
		// Success flow
		registerServiceImpl.addRegisterDetails(register);
		 

	}
	

}
