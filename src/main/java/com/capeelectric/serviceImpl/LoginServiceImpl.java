package com.capeelectric.serviceImpl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capeelectric.config.OtpConfig;
import com.capeelectric.model.RegisterDetails;
import com.capeelectric.repository.RegisterRepo;
import com.capeelectric.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	private static final String SESSION_TITLE = ".*\"Details\":\"(.+)\".*";

	@Autowired
	private RegisterRepo registerRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private OtpConfig otpConfig;

	@Override
	public void sendOtp(String email, String mobileNumber) {
		// TODO Auto-generated method stub
		if (email != null) {
			Optional<RegisterDetails> registerRepo = registerRepository.findByEmailid(email);
			System.out.println("kjd");
			if (registerRepo.isPresent() && registerRepo.get().getEmailid() != null) {
				System.out.println("kjd1");
				if (registerRepo.get().getMobilenumber().contains(mobileNumber)) {
					System.out.println("kjd2");
					boolean isValidMobileNumber = isValidMobileNumber(mobileNumber);
					System.out.println("kjd3");
					if (isValidMobileNumber) {
						System.out.println("kjd5");
//						logger.debug("Given mobileNumber: {}", isValidMobileNumber);
						otpSend(mobileNumber);
						RegisterDetails registerDetails = registerRepo.get();

						registerDetails.setUpdateddate(LocalDateTime.now());
//						registerDetails.setUpdatedBy(userFullName.findByUserName(userName));
						registerRepository.save(registerDetails);
					}
				}
			}
		}

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
}
