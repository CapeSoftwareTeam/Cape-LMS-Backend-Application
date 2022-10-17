/**
 * 
 */
package com.capeelectric.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capeelectric.model.RegisterDetails;



/**
 * @author Priyanka
 *
 */
@Service
public interface RegisterService {
	
	
	 public void addRegisterDetails(RegisterDetails registerDetails) throws Exception;
	 public void updateRegisterDetails(List<RegisterDetails> registerDetails);
	 public void getRegisterDetails(Integer empid);
	 public void deleteRegisterDetails(Integer empid);

	

}
