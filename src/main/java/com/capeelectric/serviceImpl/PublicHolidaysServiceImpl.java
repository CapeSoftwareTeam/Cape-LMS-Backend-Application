package com.capeelectric.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.model.HolidayHistory;
import com.capeelectric.model.PublicHolidays;
import com.capeelectric.repository.HolidayHistoryRepo;
import com.capeelectric.repository.PublicHolidaysRepo;
import com.capeelectric.service.PublicHolidaysService;

@Service
public class PublicHolidaysServiceImpl implements PublicHolidaysService  {
	
	@Autowired
	private PublicHolidaysRepo publicHolidaysRepo;

	@Autowired
	private HolidayHistoryRepo holidayHistoryRepo;
	
	@Override
	public String addPublicHolidays(List<PublicHolidays> publicHoliday) {
		publicHolidaysRepo.saveAll(publicHoliday);
		return "Table updated!";
	}

	@Override
	public void updatePublicHolidays(List<PublicHolidays> publicHolidays) {
		// TODO Auto-generated method stub
		publicHolidaysRepo.saveAll(publicHolidays);
	}

	@Override
	public List<PublicHolidays> getPublicHolidays() {
		// TODO Auto-generated method stub
		return (List<PublicHolidays>) publicHolidaysRepo.findAll();
	}

	@Override
	public void deletePublicHolidays(Integer publicLeaveId) {
		// TODO Auto-generated method stub
		Optional<PublicHolidays> publicHolidays = publicHolidaysRepo.findById(publicLeaveId);
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
