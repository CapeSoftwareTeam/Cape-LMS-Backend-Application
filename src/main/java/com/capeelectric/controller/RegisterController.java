/**
 * 
 */
package com.capeelectric.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.model.RegisterDetails;
import com.capeelectric.service.RegisterService;

/**
 * @author Priyanka
 *
 */
@RestController 
@RequestMapping("api/v1")
public class RegisterController {
	
	@Autowired
	private RegisterService registerService;
	 
	@PostMapping("/addRegister")
	public ResponseEntity<String> addRegisterDetails(@RequestBody RegisterDetails registerDetails) throws Exception {
		registerService.addRegisterDetails(registerDetails);
		return new ResponseEntity<String>("Employee Registred Successfully", HttpStatus.OK);
	}	
	
	@PutMapping("/updateRegister")
	public ResponseEntity<String> updateRegisterDetails(@RequestBody RegisterDetails registerDetails) {
		registerService.updateRegisterDetails(registerDetails);
		return new ResponseEntity<String>("Employee Updated Successfully", HttpStatus.OK);
	}
	 
   @GetMapping("/getRegister/{empid}")
  public ResponseEntity<String>getRegisterDetails(@PathVariable Integer empid){
	  registerService.getRegisterDetails(empid);
	  return new ResponseEntity<String>(" Get details Successfully", HttpStatus.OK);
	  
  }
  @DeleteMapping("/deleteRegister/{empid}")
  public ResponseEntity<String> deleteRegisterDetails(@PathVariable Integer empid){
	  registerService.deleteRegisterDetails(empid);
	  return new ResponseEntity<String>("Employee details are deleted Successfully", HttpStatus.OK);
  }
  
    
  
	
	
		
}



