package com.flipkart.bean;

import com.flipkart.constants.StatusConstants;

import java.util.Date;
import java.util.UUID;

import com.flipkart.constants.PaymentModeConstants;

public class Payment {
	
	public Payment() {};
	
	@SuppressWarnings("deprecation")
	public Payment( PaymentModeConstants paymentMode, String amount, StatusConstants status) {
		super();
		this.transactionId = UUID.randomUUID().toString();
		this.paymentMode = paymentMode;
		this.amount = amount;
		this.timestamp = new Date().toLocaleString();
		this.status = status;
	}
	
	private String transactionId;
	private PaymentModeConstants paymentMode;
	private String amount;
	private String timestamp;
	private StatusConstants status;
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public PaymentModeConstants getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(PaymentModeConstants paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public StatusConstants getStatus() {
		return status;
	}
	public void setStatus(StatusConstants status) {
		this.status = status;
	}
}
