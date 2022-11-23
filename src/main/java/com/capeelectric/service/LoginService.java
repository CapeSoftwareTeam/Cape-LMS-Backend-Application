package com.capeelectric.service;

import com.capeelectric.exception.UpdatePasswordException;
import com.capeelectric.model.RegisterDetails;
import com.capeelectric.request.AuthenticationRequest;


public interface LoginService {

	public String sendOtp(String userName) throws Exception;

	public void updatePassword(String email, String password);

	public RegisterDetails emailGet(String userName);

	public RegisterDetails VerifyOtp(AuthenticationRequest authenticationRequest) throws UpdatePasswordException;

	


}