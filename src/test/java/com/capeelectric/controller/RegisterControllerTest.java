package com.capeelectric.controller;

import java.sql.Date;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.capeelectric.model.Register;
import com.capeelectric.repository.RegisterRepo;
import com.capeelectric.service.RegisterService;


@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class RegisterControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(RegisterControllerTest.class);
	@InjectMocks
	private RegisterController registrationController;

	@MockBean
	private RegisterService registrationService;

	
	@MockBean
	private RegisterRepo registerRepository;

	private Register register;
	
	private String resetUrl = "http:localhost";
	
	{
		register = new Register();
		register.setEmpid("CEPL_001");
		register.setEmailid("priyankapalanivel@gmail.com");
		register.setName("Priyanka");
		register.setGender("Female");
		
		register.setCountry("India");
		register.setCity("chennai");
		register.setState("Tamilnadu");
		register.setCapeexperience("0");
		register.setOtherexperience("1");
		register.setMobilenumber("9150201706");
		register.setAlternatenumber("9150501709");
		register.setDepartment("Software");
		register.setDesignation("software developer");
		register.setMaritalstatus("unmarried");
		register.setPassword("priya@123");
		register.setManageremail("sathish@capeindia.net");
		register.setManagername("sathish dharanidharan");
		register.setTotalexperience(1);

	}

}
