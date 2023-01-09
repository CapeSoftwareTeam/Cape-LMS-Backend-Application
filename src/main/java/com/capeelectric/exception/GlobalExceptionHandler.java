package com.capeelectric.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capeelectric.util.ErrorMessage;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler({ UserException.class })
	public ResponseEntity<ErrorMessage> handleUserException(UserException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "402");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ ChangePasswordException.class })
	public ResponseEntity<ErrorMessage> handleChangePasswordException(ChangePasswordException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "406");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);

	}

	@ExceptionHandler({ ForgotPasswordException.class })
	public ResponseEntity<ErrorMessage> handleForgotPasswordException(ForgotPasswordException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "406");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler({ UpdatePasswordException.class })
	public ResponseEntity<ErrorMessage> handleUpdatePasswordException(UpdatePasswordException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "400");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
     
	@ExceptionHandler({LoginException.class })
	public ResponseEntity<ErrorMessage> handleLoginException(LoginException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "401");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({BadCredentialsException.class })
	public ResponseEntity<ErrorMessage> handleBadCredentialsException(BadCredentialsException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "401");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler({AuthenticationException.class })
	public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "401");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler({FileUploadException.class })
	public ResponseEntity<ErrorMessage> handleFileUploadException(FileUploadException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "404");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	

}
