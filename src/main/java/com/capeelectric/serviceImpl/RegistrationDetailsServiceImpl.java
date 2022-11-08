package com.capeelectric.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capeelectric.model.Register;
import com.capeelectric.model.RegisterDetails;
import com.capeelectric.repository.RegisterRepo;

@Service
public class RegistrationDetailsServiceImpl extends RegisterDetails implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationDetailsServiceImpl.class);

	@Autowired
	private RegisterRepo registrationRepository;

	@Override
	public Register loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("Load User By UserName starts");

		RegisterDetails register = registrationRepository.findByEmailid(username).get();

		Register registerDetails = null;
		if (register != null) {
			registerDetails = new Register(register);
		} else {
			logger.debug("Load User By UserName ends");
			throw new UsernameNotFoundException("User not exist with name : " + username);
		}
		logger.debug("Load User By UserName ends");
		return registerDetails;
	}

}
