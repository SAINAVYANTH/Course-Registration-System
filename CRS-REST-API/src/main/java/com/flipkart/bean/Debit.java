package com.flipkart.bean;

import com.flipkart.constants.PaymentModeConstants;
import com.flipkart.constants.StatusConstants;

public class Debit extends Payment{
	public Debit() {
		super();
	};
	
	private String number;
	
	public Debit(String amount, StatusConstants status, String number) {
		super(PaymentModeConstants.DEBIT, amount,  status);
		this.number = number;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
}
