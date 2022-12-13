package com.capeelectric.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.FileUpload;

@Repository
public interface FileUploadRepo  extends CrudRepository<FileUpload,Integer>{
	

}
  