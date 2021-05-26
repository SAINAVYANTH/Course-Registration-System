package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.constants.Status;
import com.flipkart.exception.InvalidAdminIdException;

public interface AdminDaoInterface {
	public Status saveAdminDetails(Admin details);
	public Admin getAdminDetails(String id) throws InvalidAdminIdException;
}
