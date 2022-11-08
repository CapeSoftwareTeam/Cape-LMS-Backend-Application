package com.capeelectric.config;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capeelectric.model.RegisterDetails;

@Service
public class JwtRegisterDetailsService implements UserDetailsService {
	@Autowired
	private com.capeelectric.repository.RegisterRepo registrationRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  Optional<RegisterDetails> optional = registrationRepository.findByEmailid(username);
		if(optional != null && optional.isPresent() && optional.get() == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(optional.get().getEmailid(), optional.get().getPassword(),
				new ArrayList<>());
	}
}


