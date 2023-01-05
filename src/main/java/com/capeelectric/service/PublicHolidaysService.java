package com.capeelectric.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.capeelectric.model.PublicHolidays;

@Service
public interface PublicHolidaysService {

	public String addPublicHolidays(List<PublicHolidays> publicHoliday) ;

	public void updatePublicHolidays(List<PublicHolidays> publicHolidays);

	public List<PublicHolidays> getPublicHolidays();

	public void deletePublicHolidays(Integer publicLeaveId);

}
