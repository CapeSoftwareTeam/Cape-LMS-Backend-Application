package com.capeelectric.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.model.PublicHolidays;
import com.capeelectric.repository.PublicHolidaysRepo;
import com.capeelectric.service.PublicHolidaysService;



@Service
public class PublicHolidaysServiceImpl implements PublicHolidaysService{

	@Autowired
	public PublicHolidaysRepo publicHolidaysRepo;

	@Override
	public String setPublicHolidays(List<PublicHolidays> publicHoliday) {
		// TODO Auto-generated method stub
		for(PublicHolidays publicHolidays:publicHoliday) {
			publicHolidaysRepo.save(publicHolidays);
			}
			return "Table updated!";
	}

	
	
}

	

	
	
	
	



