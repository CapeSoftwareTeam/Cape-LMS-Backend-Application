package com.capeelectric.serviceImpl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capeelectric.config.OtpConfig;
import com.capeelectric.exception.ChangePasswordException;
import com.capeelectric.exception.UpdatePasswordException;
import com.capeelectric.model.Register;
import com.capeelectric.model.RegisterDetails;
import com.capeelectric.repository.RegisterRepo;
import com.capeelectric.request.AuthenticationRequest;
import com.capeelectric.request.ForgotPasswordRequest;
import com.capeelectric.service.LoginService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	private static final String SESSION_TITLE = ".*\"Details\":\"(.+)\".*";

	@Autowired
	private RegisterRepo registerRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private OtpConfig otpConfig;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

//	Send Otp Service Implementation
	@Override
	public String sendOtp(String userName) throws UpdatePasswordException {

		if (userName != null) {
			
			Optional<RegisterDetails> registerRepo = registerRepository.findByEmailid(userName);
			Optional<RegisterDetails> registerRepoNumber = registerRepository.findByMobilenumber(userName);
			

			if (registerRepo.isPresent() && registerRepo.get().getEmailid() != null) {
				logger.debug("user enter Email for Send Otp"+registerRepo);
				String mobileNumber = registerRepo.get().getMobilenumber();
				boolean isValidMobileNumber = isValidMobileNumber(mobileNumber);
				if (isValidMobileNumber) {
					
					logger.debug("Given mobileNumber: {}", isValidMobileNumber);
					RegisterDetails registerDetails = registerRepo.get();
					registerDetails.setUpdateddate(LocalDateTime.now());
					registerRepository.save(registerDetails);
//					return otpSend(mobileNumber);
				}
			} else if(registerRepoNumber.isPresent() && registerRepoNumber.get().getEmailid() != null) {
				logger.debug("User Enter Mobile For Send Otp"+registerRepoNumber);
				if (registerRepoNumber.get().getMobilenumber().equals(userName)) {
					boolean isValidMobileNumber = isValidMobileNumber(userName);
					if (isValidMobileNumber) {
						RegisterDetails registerDetails = registerRepoNumber.get();
						registerDetails.setUpdateddate(LocalDateTime.now());
						registerRepository.save(registerDetails);
//						return otpSend(mobileNumber);
					}
				}
			}
			else {
                logger.debug("Please Enter Register Email or Mobile Number");
				throw new UpdatePasswordException("Please Enter Register Email or Mobile Number");
			}

		}
		else {
			logger.error("Invalid Username");
			throw new UpdatePasswordException("Invalid username");
		}
		return null;

	}
//Mobile Number Pattern Validation
	private boolean isValidMobileNumber(String mobileNumber) {
		Pattern p = Pattern
				.compile("^(\\+\\d{1,3}( )?)?(\\s*[\\-]\\s*)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
		Matcher m = p.matcher(mobileNumber);
		return (m.find() && m.group().equals(mobileNumber));
	}
//Otp send Method
	public String otpSend(String mobileNumber) {
		try {
			ResponseEntity<String> sendOtpResponse = restTemplate.exchange(otpConfig.getSendOtp() + mobileNumber,
					HttpMethod.GET, null, String.class);
			return sendOtpResponse.getBody().replaceAll(SESSION_TITLE, "$1");
		} catch (Exception e) {
			
		}

		return null;

	}

//	Update PassWord Method for ForgetPassword
	@Override
	public void updatePassword(AuthenticationRequest authenticationRequest) {
		Optional<RegisterDetails> registerRepo = registerRepository.findByEmailid(authenticationRequest.getEmail());
		if (registerRepo.isPresent() && registerRepo.get().getEmailid() != null) {
			RegisterDetails registerDetails = registerRepo.get();
			registerDetails.setPassword(passwordEncoder.encode(authenticationRequest.getPassword()));
			logger.debug("Password Update Successfully");
			registerRepository.save(registerDetails);

		}

	}
//Forget PassWord Email and MobileNumber Validation
	@Override
	public RegisterDetails emailGet(String userName) throws UpdatePasswordException{
		
		Optional<RegisterDetails> register=registerRepository.findByEmailid(userName);
		Optional<RegisterDetails> registerRepo=registerRepository.findByMobilenumber(userName);
		if(userName!=null) {
		
		if(userName!=null && userName.contains("@")) {
			logger.debug("User Enter Email For Update Password"+userName);
			
			if(register.isPresent()) {
				
				RegisterDetails registerDetails  = register.get();
				
				return registerDetails;
			}else {
				logger.debug("Please Enter Registred Email");
				throw new UpdatePasswordException("Please Enter Registred Email");
			}
		
		}
		else {
			logger.debug("User Enter MobileNumber For Update Password"+userName);
			if(registerRepo.isPresent()) {
				RegisterDetails registerDetails = registerRepo.get();
				return registerDetails;
			}
			else {
				logger.debug("Please Enter Register MobileNumber");
				throw new UpdatePasswordException("Please Enter Registerd MobileNumber");
			}
		
//			registerRepository.save(registerDetails);
		}
		}
		logger.debug("Please fill Email or Mobile number to proceed further!");
		throw new UpdatePasswordException("Please fill Email or Mobile number to proceed further!");
		
	}
//Verify Otp Method
	@Override
	public RegisterDetails verifyOtp(AuthenticationRequest authenticationRequest) throws UpdatePasswordException {
		
		if (authenticationRequest.getEmail() != null) {
			RegisterDetails register = registerRepository.findByEmailid(authenticationRequest.getEmail()).get();
			if (register != null && register.getEmailid().equalsIgnoreCase(authenticationRequest.getEmail())) {
				boolean value = verifyOtpMethod(authenticationRequest);
				
				if (value) {
					
					logger.debug("Successfully Otp Verified");
				}
				else {
					logger.debug(" Otp Verified failed");
				}
				}else {
					logger.debug("User Not Available");
					throw new UpdatePasswordException("User Not available");
				}
			}else {
				logger.debug("UserName  Not Valid");
				throw new UsernameNotFoundException("Username not valid");
			}
		
		return null;
	}

// User Enter Otp Verifyication
	private boolean verifyOtpMethod(AuthenticationRequest authenticationRequest) throws UpdatePasswordException {
		boolean success = false;
		
		if(authenticationRequest.getEmail()!=null && authenticationRequest.getOtp()!=null) {
			
			Optional<RegisterDetails> registerRepo = registerRepository.findByEmailid(authenticationRequest.getEmail());
			if(registerRepo.isPresent() && registerRepo.get().getEmailid()!=null) {
				ResponseEntity<String> otpVerifyResponse = restTemplate.exchange(
						otpConfig.getVerifyOtp() +  authenticationRequest.getOtpSession() + "/" + authenticationRequest.getOtp(), HttpMethod.GET,
						null, String.class);
				
				if (!otpVerifyResponse.getBody().matches("(.*)Success(.*)")) {
					logger.error("Given OTP Mismatched:{}", authenticationRequest.getOtp());
					throw new UpdatePasswordException("OTP Mismatched");
				} else {
					success = true;
					logger.debug("Given OTP matched:{}", authenticationRequest.getOtp());
				}
				
			}
		}else {
			logger.error("Invalid Inputs");
			throw new UpdatePasswordException("Invalid Inputs");
		}

		return success;
		
	}

//	Change PassWord Service Method
	@Override
	public void changePassWord(AuthenticationRequest authenticationRequest) throws ChangePasswordException {
		
		Optional<RegisterDetails> registerRepo = registerRepository.findByEmpid(authenticationRequest.getEmpId());
		
		if(registerRepo.isPresent() && registerRepo!=null) {
			
			if(passwordEncoder.matches(authenticationRequest.getOldPassword(), registerRepo.get().getPassword())) {
	
				RegisterDetails registerDetails = registerRepo.get();
				registerDetails.setPassword(passwordEncoder.encode(authenticationRequest.getPassword()));
				registerRepository.save(registerDetails);
			}else {
				logger.debug("Enter Valid Old Password"+authenticationRequest.getOldPassword());
				throw new ChangePasswordException("Please Enter Valid Old Password");
			}
		}
		else {
			logger.debug("Invalid Inputs");
			throw new ChangePasswordException("Invalid Inputs");
		}
	}



}
