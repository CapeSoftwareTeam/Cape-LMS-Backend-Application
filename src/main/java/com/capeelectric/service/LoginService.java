package com.capeelectric.service;

import com.capeelectric.exception.ChangePasswordException;
import com.capeelectric.exception.UpdatePasswordException;
import com.capeelectric.model.RegisterDetails;
import com.capeelectric.request.AuthenticationRequest;


public interface LoginService {

	public String sendOtp(String userName) throws  UpdatePasswordException;

	public void updatePassword(AuthenticationRequest authenticationRequest);

	public RegisterDetails emailGet(String userName) throws UpdatePasswordException;

	public RegisterDetails verifyOtp(AuthenticationRequest authenticationRequest) throws UpdatePasswordException;

	public void changePassWord(AuthenticationRequest authenticationRequest) throws ChangePasswordException;

	


}