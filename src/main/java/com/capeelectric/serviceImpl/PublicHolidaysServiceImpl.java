package com.capeelectric.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.controller.PublicHolidaysController;
import com.capeelectric.model.HolidayHistory;
import com.capeelectric.model.PublicHolidays;
import com.capeelectric.repository.HolidayHistoryRepo;
import com.capeelectric.repository.PublicHolidaysRepo;
import com.capeelectric.service.PublicHolidaysService;

@Service
public class PublicHolidaysServiceImpl implements PublicHolidaysService  {
	
	private static final Logger logger = LoggerFactory.getLogger(PublicHolidaysServiceImpl.class);

	
	@Autowired
	private PublicHolidaysRepo publicHolidaysRepo;

	@Autowired
	private HolidayHistoryRepo holidayHistoryRepo;
	
	//Add list of public holidays Implementaion 
	@Override
	public String addPublicHolidays(List<PublicHolidays> publicHoliday) {
		publicHolidaysRepo.saveAll(publicHoliday);
		logger.debug("Add public holidays Saved successfully");
		return "Table updated!";
	}

	//Update public holidays function 
	@Override
	public void updatePublicHolidays(List<PublicHolidays> publicHolidays) {
		publicHolidaysRepo.saveAll(publicHolidays);
		logger.debug("public holidays updated successfully");
	}

	//Get list of public holidays function implementation
	@Override
	public List<PublicHolidays> getPublicHolidays() {
		logger.debug("Get public holidays successfully");
		return (List<PublicHolidays>) publicHolidaysRepo.findAll();
	}

	//Delete public holiday based on publicHolidayID function implementaion
	@Override
	public void deletePublicHolidays(Integer publicLeaveId) {
		Optional<PublicHolidays> publicHolidays = publicHolidaysRepo.findById(publicLeaveId);
		logger.debug("Public holidays deleted successfully");
		if (publicHolidays.isPresent()) {
			PublicHolidays holidays = publicHolidays.get();
			holidays.getDate();
			holidays.getDay();
			holidays.getDescription();
			holidays.getWorkLocation();
			holidays.getYear();
			HolidayHistory holidayHistory = new HolidayHistory();
			holidayHistory.setDate(holidays.getDate());
			holidayHistory.setYear(holidays.getYear());
			holidayHistory.setDay(holidays.getDay());
			holidayHistory.setDescription(holidays.getDescription());
			holidayHistory.setWorkLocation(holidays.getWorkLocation());
			holidayHistoryRepo.save(holidayHistory);
			publicHolidaysRepo.deleteById(publicLeaveId);
		}
		
		
		
	}
}
