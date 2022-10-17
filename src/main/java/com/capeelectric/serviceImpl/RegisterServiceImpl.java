/**
 * 
 */
package com.capeelectric.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.model.RegisterDetails;
import com.capeelectric.repository.RegisterRepo;
import com.capeelectric.service.RegisterService;

/**
 * @author Priyanka
 *
 */
@Service
public class RegisterServiceImpl implements RegisterService{
	
	@Autowired
	private RegisterRepo registerRepo;
	
	@Override
	public void addRegisterDetails(RegisterDetails registerDetails) {
		registerRepo.save(registerDetails);
	}


}
