/**
 * 
 */
package com.capeelectric.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capeelectric.exception.UserException;
import com.capeelectric.model.RegisterDetails;

/**
 * @author Priyanka
 *
 */
@Service
public interface RegisterService {

	public void addRegisterDetails(RegisterDetails registerDetails) throws Exception,UserException;

	public void updateRegisterDetails(RegisterDetails registerDetails);

	public void deleteRegisterDetails(String empid);

	public RegisterDetails getRegisterDetails(String empid);

	public List<RegisterDetails> getEmpidDetails();

	public void updateRegister(String mobileNumber, String empid);

//	public void getMaxEmpId();

}
