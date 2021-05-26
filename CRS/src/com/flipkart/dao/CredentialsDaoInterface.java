package com.flipkart.dao;

import com.flipkart.bean.UserLogin;
import com.flipkart.constants.Status;
import com.flipkart.exception.InvalidCredentialsException;

public interface CredentialsDaoInterface {
	public Status saveCredentials(UserLogin details);
	public UserLogin verifyCredentials(String username, String password) throws InvalidCredentialsException;
	public Status updateCredentials(String username, String password);
}
