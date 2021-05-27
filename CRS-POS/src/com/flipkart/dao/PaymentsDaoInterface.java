package com.flipkart.dao;

import com.flipkart.bean.Payment;
import com.flipkart.constants.StatusConstants;

public interface PaymentsDaoInterface {
	
	/** Dao Function to add a transaction detail to the db
	 * @param String student id, Payment details
	 * */
	public StatusConstants addTransaction(String studentId, Payment details);
}
