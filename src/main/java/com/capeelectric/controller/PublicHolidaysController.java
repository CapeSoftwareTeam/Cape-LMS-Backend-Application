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

import com.capeelectric.model.PublicHolidays;
import com.capeelectric.service.PublicHolidaysService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PublicHolidaysController {

	@Autowired
	private PublicHolidaysService publicHolidaysService;
	

    @PostMapping("/addPublicHolidays")
    public ResponseEntity<String> addPublicHolidays( @RequestBody List<PublicHolidays> publicHoliday ) {
    	publicHolidaysService.addPublicHolidays(publicHoliday);
    	return new ResponseEntity<String> ("Public Holiday added!",HttpStatus.OK);
    }
    
    @PutMapping("/updatePublicHolidays")
    public ResponseEntity<String> updatePublicHolidays(@RequestBody List<PublicHolidays> publicHolidays){
    	publicHolidaysService.updatePublicHolidays(publicHolidays);
    	return new ResponseEntity<String> ("public holiday Updated!",HttpStatus.OK);
    }

    @GetMapping("/getPublicHolidays")
    public ResponseEntity< List<PublicHolidays> > getPublicHolidays(){
     List<PublicHolidays> publicHolidays = publicHolidaysService.getPublicHolidays();
    	return new ResponseEntity<List<PublicHolidays> >(publicHolidays,HttpStatus.OK);
    }
    
    @DeleteMapping("/deletedPublicHolidays/{publicLeaveId}")
    public ResponseEntity<String> deletePublicHolidays (@PathVariable Integer publicLeaveId){
    	publicHolidaysService.deletePublicHolidays(publicLeaveId);
    	return new ResponseEntity<String>("Public holiday deleted Successfully!",HttpStatus.OK);
    }
}
