package com.capeelectric.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capeelectric.exception.FileUploadException;
import com.capeelectric.model.FileUpload;
import com.capeelectric.model.PublicHolidays;
import com.capeelectric.service.FileUploadService;

@RestController
@RequestMapping("/api/v1")
public class FileUploadController {
	
	@Autowired 
	private FileUploadService fileUploadService;
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	
// Uplaod File Method 
    @PostMapping("/upload/{fileSize}/{componentName}")
	public ResponseEntity<Integer> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String fileSize,@PathVariable String componentName)
			throws IOException, SerialException, SQLException {
    	
		logger.debug("File Upload Start");

		logger.debug("File Upload End");
		return new ResponseEntity<Integer>(fileUploadService.uploadFile(file,fileSize,componentName), HttpStatus.OK);
	}
    
//    Retrive File for FileName Base
    @GetMapping("/retrieveFileName/{componentName}")
    public ResponseEntity<Map<String,String>> retrieveComponentName(@PathVariable String componentName) throws FileUploadException
    {
    	logger.debug("retrieve File for FileName Starts ");
    	FileUpload fileUpload =  fileUploadService.retrieveFileName(componentName);
		Map<String, String> hashMap = null ;
		if (null != fileUpload) {
			hashMap = new HashMap<String, String>();
			hashMap.put("fileId", fileUpload.getFileId().toString());
			hashMap.put("fileType", fileUpload.getFileType());
			hashMap.put("fileName", fileUpload.getFileName());
			hashMap.put("fileSize", fileUpload.getFileSize());
		 logger.debug("Retrive File  for FileName Ends");
		}
		return new ResponseEntity<Map<String, String> >(hashMap, HttpStatus.OK);
    	
    }
    	
//    Retrive File For FileId Base
    @GetMapping("/retrieveFile/{fileId}")
   	public ResponseEntity<Map<String, String>> retrieveFileName(@PathVariable Integer fileId) throws FileUploadException
   			 {
   		logger.debug("Retrive File For FileId Base Starts");
    	FileUpload fileUpload =  fileUploadService.retrieveFileId(fileId);
		Map<String, String> hashMap = null ;
		if (null != fileUpload) {
			hashMap = new HashMap<String, String>();
			hashMap.put("fileId", fileUpload.getFileId().toString());
			hashMap.put("fileType", fileUpload.getFileType());
			hashMap.put("fileName", fileUpload.getFileName());
			hashMap.put("fileSize", fileUpload.getFileSize());
		 logger.debug("Retrive File  For FileId Base Ends");
		}
		return new ResponseEntity<Map<String, String> >(hashMap, HttpStatus.OK);
   	}
    
//    Download File Method
    @GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<String> downloadFile(@PathVariable Integer fileId, HttpServletResponse response) throws IOException, SQLException,FileUploadException {
		logger.debug("DownloadFile File Start FileId : {}", fileId);
		
		FileUpload fileUpload = fileUploadService.downloadFile(fileId);
		response.setHeader("Content-Disposition", "inline; fileDB.getfileId()=\"" + fileUpload.getFileId() + "\"");
		OutputStream out = response.getOutputStream();
		response.setContentType(fileUpload.getFileName());
		org.apache.commons.io.IOUtils.copy(fileUpload.getData().getBinaryStream(), out);
		out.flush();
		out.close();
		logger.debug("Download File Ends");
		return  new ResponseEntity<String>("File download Successfully", HttpStatus.OK);
		

	}
   
//    Update File Method
    @PutMapping("/updateFile/{fileId}/{fileSize}")
	public ResponseEntity<String> updateBasicFile(@RequestParam("file") MultipartFile file,@PathVariable Integer fileId,@PathVariable String fileSize)
			throws IOException, SerialException, SQLException {
    	logger.debug("Update File Start FileId : {}", fileId);
		fileUploadService.updateFile(file, fileId,fileSize);
		logger.debug("Update File Ends");
		return new ResponseEntity<String>("File Updated Successfully", HttpStatus.OK);
    }

}
