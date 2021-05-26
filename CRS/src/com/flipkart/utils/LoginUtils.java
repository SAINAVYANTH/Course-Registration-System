
package com.flipkart.utils;

import com.flipkart.bean.UserLogin;
import com.flipkart.constants.Status;
import com.flipkart.constants.UserRole;
import com.flipkart.dao.CredentialsDAO;
import com.flipkart.dao.CredentialsDaoInterface;
import com.flipkart.exception.InvalidCredentialsException;

public class LoginUtils {
	
	public static UserLogin Login(String username, String password) {
		CredentialsDaoInterface credentialsDao = CredentialsDAO.getInstance();
		// Function call here...
		try {
			return credentialsDao.verifyCredentials(username,password);
		}
		catch(InvalidCredentialsException ex) {
			System.out.println(ex.getException());
		}
		return new UserLogin("","",UserRole.ADMIN);
	}
	
	public static Status updatePassword(String username, String password) {
		CredentialsDaoInterface credentialsDao = CredentialsDAO.getInstance();
		// Function call here...
		if(Status.FAIL== credentialsDao.updateCredentials(username,password)) {
			System.out.println("Password was not updated.Please Try again");
			return Status.FAIL;
		}
		return Status.SUCCESS;
	}
}
