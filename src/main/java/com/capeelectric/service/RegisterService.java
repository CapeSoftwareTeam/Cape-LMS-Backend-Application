/**
 * 
 */
package com.capeelectric.service;

import org.springframework.stereotype.Service;

import com.capeelectric.model.RegisterDetails;

/**
 * @author Priyanka
 *
 */
@Service
public interface RegisterService {
	
	public void addRegisterDetails(RegisterDetails registerDetails);

}
