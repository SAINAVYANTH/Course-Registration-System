package com.flipkart.dao;

import com.flipkart.bean.UserLogin;
import com.flipkart.constants.Status;
import com.flipkart.exception.InvalidCredentialsException;

public interface CredentialsDaoInterface {
	
	/** Dao Function to save credentials to the db
	 * @param UserLogin details
	 * */
	public Status saveCredentials(UserLogin details);
	
	/** Dao Function to verify the credentials of a user
	 * @param String username, String password
	 * @throws InvalidCredentialsException
	 * */
	public UserLogin verifyCredentials(String username, String password) throws InvalidCredentialsException;
	
	/** Dao Function to update the credentials of a user
	 * @param String username, String password
	 * */
	public Status updateCredentials(String username, String password);
}
