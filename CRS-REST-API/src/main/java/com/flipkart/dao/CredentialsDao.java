package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.bean.UserLogin;
import com.flipkart.constants.SQLQueryConstants;
import com.flipkart.constants.StatusConstants;
import com.flipkart.constants.UserRoleConstants;
import com.flipkart.exception.InvalidCredentialsException;
import com.flipkart.utils.DBUtils;

public class CredentialsDao implements CredentialsDaoInterface{
	
	private static Logger logger = Logger.getLogger(CredentialsDao.class);
	private static volatile CredentialsDao instance = null;
	
	private CredentialsDao() {};
	
	public static CredentialsDao getInstance() {
		if (instance == null) {
			synchronized(CredentialsDao.class) {
				instance = new CredentialsDao();
			}
		}
		return instance;
	}
	
	public StatusConstants saveCredentials(UserLogin details) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.SAVE_CREDENTIALS;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, details.getUserId());
			prep_stmt.setString(2, details.getPassword());
			prep_stmt.setString(3, details.getRole().toString());
			prep_stmt.executeUpdate();
			return StatusConstants.SUCCESS;
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(Exception e){
		    logger.error(e.getMessage());
		}finally{
			try{
		    	if(prep_stmt!=null)
		            prep_stmt.close();
		    }catch(SQLException se2){
		    	logger.error(se2.getMessage());
		    }
		}
		return StatusConstants.FAIL;
	}
	
	public UserLogin verifyCredentials(String username, String password) throws InvalidCredentialsException{
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.VERIFY_CREDENTIALS;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, username);
			ResultSet result =  prep_stmt.executeQuery();
			result.absolute(1);
			if (password.equals(result.getString(2))) {
				return new UserLogin(result.getString(1), result.getString(2), UserRoleConstants.valueOf(result.getString(3)));
			}
			else {
				throw new InvalidCredentialsException("Invalid password for " + username);
			}			
		}catch(SQLException se){
			se.printStackTrace();
			logger.error(se.getMessage());
		}catch(Exception e){
			e.printStackTrace();
		    logger.error(e.getMessage());
		}finally{
			try{
		    	if(prep_stmt!=null)
		            prep_stmt.close();
		    }catch(SQLException se2){
		    	logger.error(se2.getMessage());
		    }
		}
		throw new InvalidCredentialsException("Invalid username " + username);
	}
	
	public StatusConstants updateCredentials(String username, String password)  {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.UPDATE_CREDENTIALS;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, password);
			prep_stmt.setString(2,username);
		    prep_stmt.executeUpdate();
		    return StatusConstants.SUCCESS;
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(Exception e){
		    logger.error(e.getMessage());
		}finally{
			try{
		    	if(prep_stmt!=null)
		            prep_stmt.close();
		    }catch(SQLException se2){
		    	logger.error(se2.getMessage());
		    }
		}
		return StatusConstants.FAIL;
	}
}
