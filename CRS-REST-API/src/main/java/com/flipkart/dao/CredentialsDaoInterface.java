package com.flipkart.dao;

import com.flipkart.bean.UserLogin;
import com.flipkart.constants.StatusConstants;
import com.flipkart.exception.InvalidCredentialsException;

public interface CredentialsDaoInterface {
	
	/** Dao Function to save credentials to the db
	 * @param UserLogin details
	 * */
	public StatusConstants saveCredentials(UserLogin details);
	
	/** Dao Function to verify the credentials of a user
	 * @param String username, String password
	 * @throws InvalidCredentialsException
	 * */
	public UserLogin verifyCredentials(String username, String password) throws InvalidCredentialsException;
	
	/** Dao Function to update the credentials of a user
	 * @param String username, String password
	 * */
	public StatusConstants updateCredentials(String username, String password);
}
