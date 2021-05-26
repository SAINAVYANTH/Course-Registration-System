package com.flipkart.bean;

import com.flipkart.constants.PaymentModes;
import com.flipkart.constants.Status;

public class Debit extends Payment{
	private String number;
	
	public Debit(String amount, Status status, String number) {
		super(PaymentModes.DEBIT, amount,  status);
		this.number = number;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
}
