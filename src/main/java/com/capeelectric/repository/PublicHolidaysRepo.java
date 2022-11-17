package com.capeelectric.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.PublicHolidays;

@Repository
public interface PublicHolidaysRepo extends CrudRepository<PublicHolidays, Integer>{

}
