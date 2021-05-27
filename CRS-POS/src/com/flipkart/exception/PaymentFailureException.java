package com.flipkart.exception;

import com.flipkart.bean.Payment;

public class PaymentFailureException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaymentFailureException(String exception, Payment paymentDetails) {
		super();
		this.exception = exception;
		this.paymentDetails = paymentDetails;
	}
	
	private String exception;
	private Payment paymentDetails;
	
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public Payment getPaymentDetails() {
		return paymentDetails;
	}
	public void setPaymentDetails(Payment paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
}
