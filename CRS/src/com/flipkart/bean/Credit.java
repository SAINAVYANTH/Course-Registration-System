package com.flipkart.bean;

import com.flipkart.constants.PaymentModes;
import com.flipkart.constants.Status;

public class Credit extends Payment{
	private String number;
	
	public Credit(String amount, Status status, String number) {
		super(PaymentModes.CREDIT, amount, status);
		this.number = number;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
}
