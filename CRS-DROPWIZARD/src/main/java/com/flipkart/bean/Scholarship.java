package com.flipkart.bean;

import com.flipkart.constants.PaymentModeConstants;
import com.flipkart.constants.StatusConstants;

public class Scholarship extends Payment{
	Scholarship(){
		super();
	};	
	public Scholarship(String amount, StatusConstants status, String scholarshipId) {
		super(PaymentModeConstants.SCHOLARSHIP, amount, status);
		this.scholarshipId = scholarshipId;
	}

	private String scholarshipId;

	public String getScholarshipId() {
		return scholarshipId;
	}

	public void setScholarshipId(String scholarshipId) {
		this.scholarshipId = scholarshipId;
	}
}
