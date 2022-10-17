/**
 * 
 */
package com.capeelectric.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.model.RegisterDetails;
import com.capeelectric.service.RegisterService;

/**
 * @author Priyanka
 *
 */
@RestController
public class RegisterController {
	
	@Autowired
	private RegisterService registerService;
	
	@PostMapping("/addRegister")
	public ResponseEntity<String> addRegisterDetails(@RequestBody RegisterDetails registerDetails) {
		registerService.addRegisterDetails(registerDetails);
		return new ResponseEntity<String>("Employee Registred Successfully", HttpStatus.OK);
	}

}
