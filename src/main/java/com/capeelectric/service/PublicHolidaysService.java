package com.capeelectric.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capeelectric.model.PublicHolidays;

@Service
public interface PublicHolidaysService {

	public String addPublicHolidays(List<PublicHolidays> publicHoliday);

	void updatePublicHolidays(List<PublicHolidays> publicHolidays);

	List<PublicHolidays> getPublicHolidays();

	void deletePublicHolidays(Integer publicLeaveId);

}
