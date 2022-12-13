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

	@Override
	public String sendOtp(String userName) throws Exception {
		// TODO Auto-generated method stub
		if (userName != null) {
			System.out.println("kjhgfcgvhjkhgfdghjhgf");
			Optional<RegisterDetails> registerRepo = registerRepository.findByEmailid(userName);
			Optional<RegisterDetails> registerRepoNumber = registerRepository.findByMobilenumber(userName);
			System.out.println("kjd");

			if (registerRepo.isPresent() && registerRepo.get().getEmailid() != null) {
				System.out.println("ishgfhjkljhgjkhgf");
				String mobileNumber = registerRepo.get().getMobilenumber();
				boolean isValidMobileNumber = isValidMobileNumber(mobileNumber);
				if (isValidMobileNumber) {
					System.out.println("kjd5");
					logger.debug("Given mobileNumber: {}", isValidMobileNumber);
					RegisterDetails registerDetails = registerRepo.get();
					registerDetails.setUpdateddate(LocalDateTime.now());
					registerRepository.save(registerDetails);
//					return otpSend(mobileNumber);
				}
			} else {
				System.out.println("kjhghjkjhgfhjdhfghdjfhbfndmfn");
				if (registerRepoNumber.get().getMobilenumber().equals(userName)) {
					boolean isValidMobileNumber = isValidMobileNumber(userName);
					if (isValidMobileNumber) {
						RegisterDetails registerDetails = registerRepoNumber.get();
						registerDetails.setUpdateddate(LocalDateTime.now());
						registerRepository.save(registerDetails);
//                       return otpSend(userName);
					}
				}
			}

		}
		else {
			throw new Exception("Invalid username");
		}
		return null;

	}

	private boolean isValidMobileNumber(String mobileNumber) {
		Pattern p = Pattern
				.compile("^(\\+\\d{1,3}( )?)?(\\s*[\\-]\\s*)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
		Matcher m = p.matcher(mobileNumber);
		return (m.find() && m.group().equals(mobileNumber));
	}

	public String otpSend(String mobileNumber) {
		try {
			ResponseEntity<String> sendOtpResponse = restTemplate.exchange(otpConfig.getSendOtp() + mobileNumber,
					HttpMethod.GET, null, String.class);
			return sendOtpResponse.getBody().replaceAll(SESSION_TITLE, "$1");
		} catch (Exception e) {
			System.out.println("done");
		}

		return null;

	}

	@Override
	public void updatePassword(String email, String password) {
		// TODO Auto-generated method stub
		Optional<RegisterDetails> registerRepo = registerRepository.findByEmailid(email);
		if (registerRepo.isPresent() && registerRepo.get().getEmailid() != null) {
			RegisterDetails registerDetails = registerRepo.get();
			registerDetails.setPassword(passwordEncoder.encode(password));
			registerRepository.save(registerDetails);

		}

	}

	@Override
	public RegisterDetails emailGet(String userName) {
		// TODO Auto-generated method stub
		Optional<RegisterDetails> register=registerRepository.findByEmailid(userName);
		Optional<RegisterDetails> registerRepo=registerRepository.findByMobilenumber(userName);
		 
		if(userName!=null && userName.contains("@")) {
			RegisterDetails registerDetails  = register.get();
			return registerDetails;
		}
		else {
			RegisterDetails registerDetails = registerRepo.get();
			return registerDetails;
		
//			registerRepository.save(registerDetails);
		}
	}

	@Override
	public RegisterDetails VerifyOtp(AuthenticationRequest authenticationRequest) throws UpdatePasswordException {
		// TODO Auto-generated method stub
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
					
					throw new UpdatePasswordException("User Not available");
				}
			}else {
				
				throw new UsernameNotFoundException("Username not valid");
			}
		
		return null;
	}

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



}
