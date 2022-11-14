package com.capeelectric.response;

import com.capeelectric.model.RegisterDetails;

public class AuthenticationResponseRegisterDetails {
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final RegisterDetails registerDetails;

	public AuthenticationResponseRegisterDetails(String jwttoken, RegisterDetails registerDetails) {
		this.jwttoken = jwttoken;
		this.registerDetails = registerDetails;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public RegisterDetails getRegister() {
		return registerDetails;
	}

}
