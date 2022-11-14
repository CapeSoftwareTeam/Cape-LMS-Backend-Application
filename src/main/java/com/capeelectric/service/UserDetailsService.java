package com.capeelectric.service;

import java.io.IOException;

import com.capeelectric.exception.ChangePasswordException;
import com.capeelectric.exception.ForgotPasswordException;
import com.capeelectric.exception.UpdatePasswordException;
import com.capeelectric.exception.UserException;
import com.capeelectric.model.RegisterDetails;

public interface UserDetailsService {
    public  RegisterDetails saveUser( RegisterDetails user);

	//public RegisterDetails findByUserName(String email) throws ForgotPasswordException, IOException;

	public RegisterDetails updatePassword(String email, String password, Integer otp) throws UpdatePasswordException;

	public RegisterDetails changePassword(String email, String oldPassword, String password)
			throws ChangePasswordException;

	public RegisterDetails retrieveUserInformation(String email) throws UserException;

	public RegisterDetails updateUserProfile(RegisterDetails user);

}
