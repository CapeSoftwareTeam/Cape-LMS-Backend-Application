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


import com.capeelectric.model.FileUpload;

import com.capeelectric.repository.FileUploadRepo;

@Service
public class FileUploadService {
	
	@Autowired
	private FileUploadRepo fileUploadRepository;

	public Integer uploadFile(MultipartFile file, String fileSize) throws SerialException, SQLException, IOException {
		System.out.println("dhananananananananana");
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		System.out.println(fileName);
//		Blob blob = new javax.sql.rowset.serial.SerialBlob(IOUtils.toByteArray(file.getInputStream());
		Blob blob = new javax.sql.rowset.serial.SerialBlob(IOUtils.toByteArray(file.getInputStream()));
		
		FileUpload fileUpload = new FileUpload();
		
		fileUpload.setFileName(fileName);
		fileUpload.setData(blob);
		fileUpload.setFileType(file.getContentType());
		fileUpload.setStatus("Active");
		fileUpload.setFileSize(fileSize);
		
//		logger.debug("File Saved In DB");
		return fileUploadRepository.save(fileUpload).getFileId();
		
	}
//
	public FileUpload retrieveFileId(Integer fileId) {
		// TODO Auto-generated method stub
		
		if (fileId != null && fileId != 0) {
			Optional<FileUpload> fileData = fileUploadRepository.findById(fileId);
			if (fileData.isPresent() && null !=fileData.get() ) {
				return fileData.get();
			}
		} else {
			
		}
		return null;
	}

	public FileUpload downloadFile(Integer fileId) throws IOException {
		// TODO Auto-generated method stub
		if (fileId != null && fileId != 0) {
			Optional<FileUpload> fileUpload = fileUploadRepository.findById(fileId);
				if (fileUpload.isPresent() && fileUpload.get()!=null) {
					System.out.println(fileUpload.get().getData()+"???????????????????");
					return fileUpload.get();
				} else {
//					logger.error("File Not Preset");
					throw new IOException("File Not Preset");
				}
			} else {
//				
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

//	public List<FileUpload> retrieveFile() {
//			// TODO Auto-generated method stub
//		return  (List<FileUpload>) fileUploadRepository.findAll();
//	}
	
	
	

}
