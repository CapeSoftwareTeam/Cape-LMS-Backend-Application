package com.capeelectric.service;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.capeelectric.exception.FileUploadException;
import com.capeelectric.model.FileUpload;

import com.capeelectric.repository.FileUploadRepo;

@Service
public class FileUploadService {
	
	@Autowired
	private FileUploadRepo fileUploadRepository;

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
		return fileUploadRepository.save(fileUpload).getFileId();
		
	}

	public FileUpload retrieveFileId(Integer fileId) throws FileUploadException{
		
		if (fileId != null && fileId != 0) {
			Optional<FileUpload> fileData = fileUploadRepository.findById(fileId);
			if (fileData.isPresent() && null !=fileData.get() ) {
				return fileData.get();
			}else {
				throw new FileUploadException("File Not Preset");
		} 
		}
		return null;
		
	}

	public FileUpload downloadFile(Integer fileId) throws IOException {
		if (fileId != null && fileId != 0) {
			Optional<FileUpload> fileUpload = fileUploadRepository.findById(fileId);
				if (fileUpload.isPresent() && fileUpload.get()!=null) {
					return fileUpload.get();
				} else {
					throw new IOException("File Not Preset");
				}
			} else {			
				throw new IOException("Id Not Preset");
			}
	}

	

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
				
				throw new IOException("File Update failed");
			}
		} else {
			
			throw new IOException("Id Not Preset");
		}
		
	}
	public FileUpload retrieveFileName(String componentName) throws FileUploadException {
		if (componentName != null) {
			Optional<FileUpload> fileData = fileUploadRepository.findByComponentName(componentName);
			if (fileData.isPresent() && null !=fileData.get() ) {
				return fileData.get();
			}else {
				
				throw new FileUploadException("File Not Preset");
		} 
		}
		return null;
	}

	
	
	

}
