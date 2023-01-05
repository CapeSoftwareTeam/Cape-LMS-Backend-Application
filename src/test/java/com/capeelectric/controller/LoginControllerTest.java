package com.capeelectric.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.config.JwtTokenUtil;
import com.capeelectric.exception.AuthenticationException;
import com.capeelectric.exception.ChangePasswordException;
import com.capeelectric.exception.UpdatePasswordException;
import com.capeelectric.model.Register;
import com.capeelectric.model.RegisterDetails;
import com.capeelectric.repository.RegisterRepo;
import com.capeelectric.request.AuthenticationRequest;
import com.capeelectric.serviceImpl.LoginServiceImpl;
import com.capeelectric.serviceImpl.RegistrationDetailsServiceImpl;


//import com.capeelectric.serviceImpl.RegistrationDetailsServiceImpl;



@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {
	
	@InjectMocks
	private LoginController loginController;
	
	@MockBean
	private RegistrationDetailsServiceImpl registrationDetailsServiceImpl;
	
	@MockBean
	private RegisterRepo registrationRepository;
		
	@MockBean
	private AuthenticationRequest authenticationRequest;
	
	@MockBean
	private AuthenticationManager authenticationManager;
	
	@MockBean
	private JwtTokenUtil jwtTokenUtil;

	@MockBean
	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
	
	@MockBean
	private LoginServiceImpl loginServiceImpl;
		
    private RegisterDetails registerDetails;
    
    {
    	registerDetails =new RegisterDetails();
    	registerDetails.setEmpid("CEPL-001");
    	registerDetails.setEmailid("LMS@gamil.com");
    	registerDetails.setPassword("Xyz@12345");
    	registerDetails.setStatus("Active");
    	registerDetails.setRegisterid(1);
    	registerDetails.setMobilenumber("6374053802");
    	
    }
	
	
	@Test
	public void authenticateTest() throws AuthenticationException, Exception {
		Register register = new Register();
		register.setEmpid("CEPL-001");
		register.setPassword("Cape@1234");
		register.setEmailid("LMS@gamil.com");
		register.setRegister(register);
		AuthenticationRequest authenticationRequest = new AuthenticationRequest();
		authenticationRequest.setEmpId("CEPL-001");	
		authenticationRequest.setPassword("Cape@1234");
		register.setStatus("Active");
		
		when(registrationDetailsServiceImpl.loadUserByUsername("LMS@gamil.com")).thenReturn(register); 
		when(registrationRepository.findByEmpid("CEPL-001")).thenReturn(Optional.of(registerDetails));
		ResponseEntity<?> token = loginController.createAuthenticationToken(authenticationRequest);
		assertNotNull(token);

	}
	
	@Test
	public void updatePassWordTest()throws UpdatePasswordException{
		AuthenticationRequest authenticationRequest = new AuthenticationRequest();
		authenticationRequest.setEmail("LMS@gamil.com");
		authenticationRequest.setPassword("Cape@1234");
		when(registrationRepository.findByEmailid(authenticationRequest.getEmail())).thenReturn(Optional.of(registerDetails));
		ResponseEntity<String> updatePassword=loginController.updatePassword(authenticationRequest);
		assertEquals(updatePassword.getBody(),"Password  Updated!");
		
		
	}
	
	@Test
	public void changePasswordTest()throws ChangePasswordException{
		AuthenticationRequest authenticationRequest = new AuthenticationRequest();
		authenticationRequest.setEmpId("CEPL-001");
		authenticationRequest.setOldPassword("Cape@1234");
		authenticationRequest.setPassword("Cape1234");
		when(registrationRepository.findByEmpid(authenticationRequest.getEmpId())).thenReturn(Optional.of(registerDetails));
		ResponseEntity<String> changePassword=loginController.changePassword(authenticationRequest);
		assertEquals(changePassword.getBody(),"New PassWord Updated Successfully!");
	}

}
