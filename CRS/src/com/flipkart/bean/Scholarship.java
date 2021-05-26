package com.flipkart.bean;

import com.flipkart.constants.PaymentModes;
import com.flipkart.constants.Status;

public class Scholarship extends Payment{
	public Scholarship(String amount, Status status, String scholarshipId) {
		super(PaymentModes.SCHOLARSHIP, amount, status);
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
