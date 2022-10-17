package com.capeelectric.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capeelectric.model.PublicHolidays;

/**
 * @author Gokul
 *
 */
@Service
public interface PublicHolidaysService {
   

	public String setPublicHolidays(List<PublicHolidays> publicHoliday);
}
