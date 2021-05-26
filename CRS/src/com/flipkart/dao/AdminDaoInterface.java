package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.constants.Status;
import com.flipkart.exception.InvalidAdminIdException;

public interface AdminDaoInterface {
	
	/** Dao Function to save admin details
	 * @param Admin details
	 * */
	public Status saveAdminDetails(Admin details);
	
	/** Dao Function to fetch admin details 
	 * @param String admin id
	 * */
	public Admin getAdminDetails(String id) throws InvalidAdminIdException;
}
