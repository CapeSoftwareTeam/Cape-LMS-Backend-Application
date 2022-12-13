package com.capeelectric.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.HolidayHistory;
@Repository
public interface HolidayHistoryRepo extends CrudRepository<HolidayHistory,Integer>{

}
