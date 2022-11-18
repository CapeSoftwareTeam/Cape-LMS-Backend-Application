package com.capeelectric.controller;


import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.config.AWSConfig;
import com.capeelectric.config.JwtTokenUtil;
import com.capeelectric.exception.AuthenticationException;
import com.capeelectric.model.Register;
import com.capeelectric.model.RegisterDetails;
import com.capeelectric.repository.RegisterRepo;
import com.capeelectric.request.AuthenticationRequest;
import com.capeelectric.response.AuthenticationResponseRegisterDetails;
import com.capeelectric.service.LoginService;
import com.capeelectric.serviceImpl.RegistrationDetailsServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

	@Autowired
	private RegistrationDetailsServiceImpl registrationDetailsServiceImpl;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AWSConfig awsConfig;
	
	@Autowired
	private RegisterRepo registerRepo;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception, AuthenticationException {
		
		logger.debug("Create Authenticate Token starts");

		final Register register = registrationDetailsServiceImpl
				.loadUserByUsername(authenticate(authenticationRequest));

		final String token = jwtTokenUtil.generateToken(register);
		logger.debug("Create Authenticate Token ends");
		return ResponseEntity.ok(new AuthenticationResponseRegisterDetails(token, register.getRegisterDetails()));
		
	}
	
	@GetMapping("/sendotp/{email}/{mobileNumber}")
	public ResponseEntity<Void> sendOtp(@PathVariable String email,@PathVariable String mobileNumber)
			throws IOException{
		System.out.println("hi");
		loginService.sendOtp(email, mobileNumber);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	private String authenticate(AuthenticationRequest authenticationRequest)
			throws AuthenticationException {

		Optional<RegisterDetails> registerDetails = null;
		
		if (null != authenticationRequest.getEmail()) {
			registerDetails = registerRepo.findByEmailid(authenticationRequest.getEmail());
		} else if (null != authenticationRequest.getEmpId()) {
			registerDetails = registerRepo.findByEmpid(authenticationRequest.getEmpId());
		} else if (null != authenticationRequest.getMobileNumber()) {
			registerDetails = registerRepo.findByMobilenumber(authenticationRequest.getMobileNumber());
		}

		if (null !=registerDetails && registerDetails.isPresent() && null != registerDetails.get()) {

			try {
				System.out.println(registerDetails.get().getEmailid());
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
						registerDetails.get().getEmailid(), authenticationRequest.getPassword()));
				logger.debug("Authentication done sucessfully");
				return registerDetails.get().getEmailid();
			} catch (DisabledException e) {
				logger.error("Authentication failed : " + e.getMessage());
				throw new DisabledException("USER_DISABLED", e);
			} catch (BadCredentialsException e) {
				logger.error("Authentication failed : " + e.getMessage());
				throw new BadCredentialsException("INVALID_CREDENTIALS", e);
			}
		} else {
			logger.error("Invalid User");
			throw new AuthenticationException("Invalid User");
		}
	}

}

