package com.capeelectric.request;

public class ForgotPasswordRequest {
	private Integer otp;
	public ForgotPasswordRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ForgotPasswordRequest(Integer otp) {
		super();
		this.otp = otp;
	}

	public Integer getOtp() {
		return otp;
	}


	public void setOtp(Integer otp) {
		this.otp = otp;
	}

}
