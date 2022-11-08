/**
 * 
 */
package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.RegisterDetails;

/**
 * @author Priyanka
 *
 */
@Repository
public interface RegisterRepo extends CrudRepository<RegisterDetails, Integer>{

	public Optional<RegisterDetails> findByEmpid(String empid);

	public Optional<RegisterDetails> findByEmailid(String username);

}
