
package com.flipkart.utils;

import org.apache.log4j.Logger;

import com.flipkart.bean.UserLogin;
import com.flipkart.constants.StatusConstants;
import com.flipkart.constants.UserRoleConstants;
import com.flipkart.dao.CredentialsDao;
import com.flipkart.dao.CredentialsDaoInterface;
import com.flipkart.exception.InvalidCredentialsException;

public class LoginUtils {
	private static Logger logger = Logger.getLogger(LoginUtils.class);
	
	/** Function to login to a user
	 * @param String username, String password
	 * */
	public static UserLogin Login(String username, String password) {
		CredentialsDaoInterface credentialsDao = CredentialsDao.getInstance();
		// Function call here...
		try {
			return credentialsDao.verifyCredentials(username,password);
		}
		catch(InvalidCredentialsException ex) {
			logger.info(ex.getException());
		}
		return new UserLogin("","",UserRoleConstants.ADMIN);
	}
	
	/** Function to update the password of a user
	 * @param String username, String password
	 * */
	public static StatusConstants updatePassword(String username, String password) {
		CredentialsDaoInterface credentialsDao = CredentialsDao.getInstance();
		// Function call here...
		if(StatusConstants.FAIL== credentialsDao.updateCredentials(username,password)) {
			logger.info("Password was not updated.Please Try again");
			return StatusConstants.FAIL;
		}
		return StatusConstants.SUCCESS;
	}
}
