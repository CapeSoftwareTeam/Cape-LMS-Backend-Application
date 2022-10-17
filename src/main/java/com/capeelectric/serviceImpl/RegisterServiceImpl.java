/**
 * 
 */
package com.capeelectric.serviceImpl;

import java.util.List;
import java.util.Optional;

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
    public void  addRegisterDetails(RegisterDetails registerDetails) throws Exception {
		
		if (null !=registerDetails && null !=registerDetails.getEmpid()) {
			Optional<RegisterDetails> register = registerRepo.findByEmpid(registerDetails.getEmpid());
			if (!register.isPresent()) {
				registerDetails.setStatus("Active");
				registerRepo.save(registerDetails); 
			}
			else {
				throw new Exception("Data Already exist");
			}
		}
		else {
			throw new Exception("Invalid input");
		}

	}



	@Override
	public void updateRegisterDetails(List<RegisterDetails> registerDetails) {
		registerRepo.saveAll(registerDetails);
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getRegisterDetails(Integer empid) {
		registerRepo.findById(empid);
	}

	@Override
	public void deleteRegisterDetails(Integer empid  ) {
 		Optional<RegisterDetails> register = registerRepo.findById(empid);
 		if (register.isPresent()) {
 			RegisterDetails registerData = register.get();
 			registerData.setStatus("InActive");
 			registerRepo.save(registerData);
		}
	 
	}

	

}
