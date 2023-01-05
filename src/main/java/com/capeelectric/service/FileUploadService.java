package com.capeelectric.service;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.capeelectric.exception.FileUploadException;
import com.capeelectric.model.FileUpload;

import com.capeelectric.repository.FileUploadRepo;

@Service
public class FileUploadService {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadService.class);
	
	@Autowired
	private FileUploadRepo fileUploadRepository;

//	Uplaod File Service
	public Integer uploadFile(MultipartFile file, String fileSize,String componentName) throws SerialException, SQLException, IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Blob blob = new javax.sql.rowset.serial.SerialBlob(IOUtils.toByteArray(file.getInputStream()));
		FileUpload fileUpload = new FileUpload();
		fileUpload.setFileName(fileName);
		fileUpload.setData(blob);
		fileUpload.setFileType(file.getContentType());
		fileUpload.setStatus("Active");
		fileUpload.setFileSize(fileSize);
		fileUpload.setComponentName(componentName);
		logger.debug("File Upload SuccessFully");
		return fileUploadRepository.save(fileUpload).getFileId();
		
	}
//  Retrive File Method For FileId Base
	public FileUpload retrieveFileId(Integer fileId) throws FileUploadException{
		
		if (fileId != null && fileId != 0) {
			Optional<FileUpload> fileData = fileUploadRepository.findById(fileId);
			if (fileData.isPresent() && null !=fileData.get() ) {
				return fileData.get();
			}else {
				logger.debug("Retrive Failed..File Not Present");
				throw new FileUploadException("File Not Preset");
		} 
		}
		return null;
		
	}
//  Download File Method Service
	public FileUpload downloadFile(Integer fileId) throws IOException {
		if (fileId != null && fileId != 0) {
			Optional<FileUpload> fileUpload = fileUploadRepository.findById(fileId);
				if (fileUpload.isPresent() && fileUpload.get()!=null) {
					return fileUpload.get();
				} else {
					logger.debug("Download Failed..File Not Present");
					throw new IOException("File Not Preset");
				}
			} else {
				logger.debug("File Id is Not Present");
				throw new IOException("Id Not Preset");
			}
	}

	
// Update File Method Service
	public void updateFile(MultipartFile file, Integer fileId, String fileSize) throws IOException, SerialException, SQLException {
		
		if (fileId != null && fileId != 0) {
			Optional<FileUpload> fileUpload = fileUploadRepository.findById(fileId);
			if (fileUpload.isPresent() && fileUpload.get().getFileId().equals(fileId)) {
				Blob blob = new javax.sql.rowset.serial.SerialBlob(IOUtils.toByteArray(file.getInputStream()));
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());				
				fileUpload.get().setFileName(fileName);
				fileUpload.get().setData(blob);
				fileUpload.get().setFileType(file.getContentType());
				fileUpload.get().setFileSize(fileSize);
				fileUploadRepository.save(fileUpload.get());
			} else {
				logger.debug("FileUpdate Failed..");
				throw new IOException("File Update failed");
			}
		} else {
			logger.debug("Update Failed..File Not Present");
			throw new IOException("Id Not Preset");
		}
		
	}
	
//	Retrive File For componentName Base
	public FileUpload retrieveFileName(String componentName) throws FileUploadException {
		if (componentName != null) {
			Optional<FileUpload> fileData = fileUploadRepository.findByComponentName(componentName);
			if (fileData.isPresent() && null !=fileData.get() ) {
				return fileData.get();
			}else {
				logger.debug("File Not Preset");
				throw new FileUploadException("File Not Preset");
		} 
		}
		return null;
	}

	
	
	

}
