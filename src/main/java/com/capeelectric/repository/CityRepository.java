package com.capeelectric.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.Country;
import com.capeelectric.model.city;
@Repository
public interface CityRepository extends CrudRepository<city, Integer>{

}
