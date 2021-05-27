package com.flipkart.bean;

import com.flipkart.constants.PaymentModeConstants;
import com.flipkart.constants.StatusConstants;

public class Upi extends Payment{
	public Upi( String amount, StatusConstants status, String upiId) {
		super(PaymentModeConstants.UPI, amount, status);
		this.upiId = upiId;
	}

	private String upiId;

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}
}
