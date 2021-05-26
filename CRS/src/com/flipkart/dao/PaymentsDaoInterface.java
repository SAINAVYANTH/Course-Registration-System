package com.flipkart.dao;

import com.flipkart.bean.Payment;
import com.flipkart.constants.Status;

public interface PaymentsDaoInterface {
	public Status addTransaction(String studentId, Payment details);
}
