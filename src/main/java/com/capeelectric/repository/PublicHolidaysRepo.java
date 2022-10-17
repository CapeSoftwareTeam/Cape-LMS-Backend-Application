package com.capeelectric.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.PublicHolidays;

/**
 * @author Gokul
 *
 */
@Repository
public interface PublicHolidaysRepo extends CrudRepository<PublicHolidays, Integer> {

	

}
