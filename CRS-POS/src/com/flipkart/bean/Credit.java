package com.flipkart.bean;

import com.flipkart.constants.PaymentModeConstants;
import com.flipkart.constants.StatusConstants;

public class Credit extends Payment{
	private String number;
	
	public Credit(String amount, StatusConstants status, String number) {
		super(PaymentModeConstants.CREDIT, amount, status);
		this.number = number;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
}
