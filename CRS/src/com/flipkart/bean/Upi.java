package com.flipkart.bean;

import com.flipkart.constants.PaymentModes;
import com.flipkart.constants.Status;

public class Upi extends Payment{
	public Upi( String amount, Status status, String upiId) {
		super(PaymentModes.UPI, amount, status);
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
