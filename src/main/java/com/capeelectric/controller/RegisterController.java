/**
 * 
 */
package com.capeelectric.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.CountryDetailsException;
import com.capeelectric.exception.UserException;
import com.capeelectric.model.Country;
import com.capeelectric.model.RegisterDetails;
import com.capeelectric.model.city;
import com.capeelectric.service.RegisterService;

/**
 * @author Priyanka
 *
 */
@RestController 
@RequestMapping("/api/v1")
 public class RegisterController {
	
	@Autowired
	private RegisterService registerService;
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
//	Method for adding Register details 
	 
	@PostMapping("/addRegister")
	public ResponseEntity<String> addRegisterDetails(@RequestBody RegisterDetails registerDetails) throws Exception,UserException {
		registerService.addRegisterDetails(registerDetails);
		logger.debug("registeration is addedd");

		return new ResponseEntity<String>("Employee Registred Successfully", HttpStatus.OK);
	}	
	
//	Update register
	
	@PutMapping("/updateRegister")
	public ResponseEntity<String> updateRegisterDetails(@RequestBody RegisterDetails registerDetails) {
		registerService.updateRegisterDetails(registerDetails);
		logger.debug("Register is updated");

		return new ResponseEntity<String>("Employee Updated Successfully", HttpStatus.OK);
	}
	
//	Update mobile number
	@PutMapping("/updateRegister/{mobileNumber}/{empid}")
	public ResponseEntity<String> updateRegister(@PathVariable String mobileNumber,@PathVariable String empid) {
		registerService.updateRegister(mobileNumber,empid);
		logger.debug("mobile number is updated");

		return new ResponseEntity<String>("Your mobile updated successfully", HttpStatus.OK);
	}

//	Getting a details using empid
	
	@GetMapping("/getRegister/{empid}")
	public ResponseEntity<RegisterDetails> getRegisterDetails(@PathVariable String empid){
		logger.debug("getting a details of empid");

		return new ResponseEntity<RegisterDetails>(registerService.getRegisterDetails(empid),HttpStatus.OK);	
	}
	
//	Deleting a registeration details using empid

  @DeleteMapping("/deleteRegister/{empid}")
  public ResponseEntity<String> deleteRegisterDetails(@PathVariable String empid){
	  registerService.deleteRegisterDetails(empid);
		logger.debug("employee details deleted");

	  return new ResponseEntity<String>("Employee details are deleted Successfully", HttpStatus.OK);
  }
  
//	Deleting a registeration details using empid
  @GetMapping("/getmaxempid")
  public ResponseEntity<String> getMaxEmpId(){
//	  registerService.getMaxEmpId();
		logger.debug("deleting the max empid");

	  return new ResponseEntity<String>("Employee details are deleted Successfully", HttpStatus.OK);
  }
// getting emp details using empid
  @GetMapping("/empid")
  public ResponseEntity< List<RegisterDetails> > getEmpidDetails(){
   List<RegisterDetails> registerDetails = registerService.getEmpidDetails();
	logger.debug("employee details is taken");

  	return new ResponseEntity<List<RegisterDetails> >(registerDetails,HttpStatus.OK);
  }
  
//  Getting a city from db
 @GetMapping("/getcity")
 public ResponseEntity<List<city>> getCity(){
	 List<city> cityDetails = registerService.getCity();
	

	 return new ResponseEntity<List<city>>(cityDetails,HttpStatus.OK);

  }
// Adding a city
 
 @PostMapping("/addcity")
	public ResponseEntity<String> addCity(@RequestBody city cityDetails) throws Exception,UserException {
		registerService.addCity(cityDetails);
	
		return new ResponseEntity< String>("city add successfully",HttpStatus.OK);
	}	
   
    
  
	
	
		
}
 


