package com.capeelectric.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.model.PublicHolidays;
import com.capeelectric.service.PublicHolidaysService;

@RestController
public class PublicHolidaysController {
	
	@Autowired
	private PublicHolidaysService publicHolidaysService;
	
    @PostMapping("/addPublicHolidays")
    public ResponseEntity<String> setPublicHolidays( @RequestBody List<PublicHolidays> publicHoliday ) {
		return new ResponseEntity<String> (publicHolidaysService.setPublicHolidays(publicHoliday),HttpStatus.OK);
    	
    }
    

}
