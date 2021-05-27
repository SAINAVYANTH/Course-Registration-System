package com.flipkart.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.bean.Admin;
import com.flipkart.bean.UserLogin;
import com.flipkart.constants.SQLQueryConstants;
import com.flipkart.constants.StatusConstants;
import com.flipkart.constants.UserRoleConstants;
import com.flipkart.exception.InvalidAdminIdException;
import com.flipkart.utils.DBUtils;

public class AdminDao implements AdminDaoInterface{
	
	private static Logger logger = Logger.getLogger(AdminDao.class);
	private static volatile AdminDao instance = null;
	
	private AdminDao() {};
	
	public static AdminDao getInstance() {
		if (instance == null) {
			synchronized (AdminDao.class) {
				instance = new AdminDao();
			}
		}
		return instance;
	}
	
	public StatusConstants saveAdminDetails(Admin details) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.SAVE_ADMIN;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, details.getId());
			prep_stmt.setString(2, details.getName());
			prep_stmt.setDate(3, (Date) details.getDob());
			prep_stmt.setString(4, details.getEmail());
			prep_stmt.setString(5, details.getAddress());
			prep_stmt.executeUpdate();
			CredentialsDaoInterface credentialsDao = CredentialsDao.getInstance();
			credentialsDao.saveCredentials(new UserLogin(details.getId(), details.getId(), UserRoleConstants.ADMIN));
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
	
	public Admin getAdminDetails(String id) throws InvalidAdminIdException{
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.GET_ADMIN;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, id);
			ResultSet result =  prep_stmt.executeQuery();
			result.absolute(1);
			return new Admin(result.getString(1), result.getString(2), result.getDate(3), result.getString(4), 
					result.getString(5));
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
		throw new InvalidAdminIdException("Invalid id " + id);
	}
}
