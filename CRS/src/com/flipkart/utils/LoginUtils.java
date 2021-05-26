
package com.flipkart.utils;

import org.apache.log4j.Logger;

import com.flipkart.bean.UserLogin;
import com.flipkart.constants.Status;
import com.flipkart.constants.UserRole;
import com.flipkart.dao.CredentialsDao;
import com.flipkart.dao.CredentialsDaoInterface;
import com.flipkart.exception.InvalidCredentialsException;

public class LoginUtils {
	private static Logger logger = Logger.getLogger(LoginUtils.class);
	
	public static UserLogin Login(String username, String password) {
		CredentialsDaoInterface credentialsDao = CredentialsDao.getInstance();
		// Function call here...
		try {
			return credentialsDao.verifyCredentials(username,password);
		}
		catch(InvalidCredentialsException ex) {
			logger.info(ex.getException());
		}
		return new UserLogin("","",UserRole.ADMIN);
	}
	
	public static Status updatePassword(String username, String password) {
		CredentialsDaoInterface credentialsDao = CredentialsDao.getInstance();
		// Function call here...
		if(Status.FAIL== credentialsDao.updateCredentials(username,password)) {
			logger.info("Password was not updated.Please Try again");
			return Status.FAIL;
		}
		return Status.SUCCESS;
	}
}
