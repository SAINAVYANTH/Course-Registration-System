package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.bean.UserLogin;
import com.flipkart.constants.Status;
import com.flipkart.constants.UserRole;
import com.flipkart.exception.InvalidCredentialsException;
import com.flipkart.utils.DBUtils;

public class CredentialsDAO implements CredentialsDaoInterface{
private static CredentialsDAO instance = null;
	
	private CredentialsDAO() {};
	
	public static CredentialsDAO getInstance() {
		if (instance == null) {
			instance = new CredentialsDAO();
		}
		return instance;
	}
	
	public Status saveCredentials(UserLogin details) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = "insert into credentials values(?,?,?)";
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, details.getUserId());
			prep_stmt.setString(2, details.getPassword());
			prep_stmt.setString(3, details.getRole().toString());
			prep_stmt.executeUpdate();
			return Status.SUCCESS;
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
		    e.printStackTrace();
		}finally{
			try{
		    	if(prep_stmt!=null)
		            prep_stmt.close();
		    }catch(SQLException se2){}
		}
		return Status.FAIL;
	}
	
	public UserLogin verifyCredentials(String username, String password) throws InvalidCredentialsException{
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = "select * from credentials where username=?";
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, username);
			ResultSet result =  prep_stmt.executeQuery();
			result.absolute(1);
			System.out.println("Role:" + result.getString(3));
			if (password.equals(result.getString(2))) {
				return new UserLogin(result.getString(1), result.getString(2), UserRole.valueOf(result.getString(3)));
			}
			else {
				throw new InvalidCredentialsException("Invalid password for " + username);
			}			
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
		    e.printStackTrace();
		}finally{
			try{
		    	if(prep_stmt!=null)
		            prep_stmt.close();
		    }catch(SQLException se2){}
		}
		throw new InvalidCredentialsException("Invalid username " + username);
	}
	
	public Status updateCredentials(String username, String password)  {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = "update credentials set password=? where userid=?";
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, password);
			prep_stmt.setString(2,username);
		    prep_stmt.executeUpdate();
		    return Status.SUCCESS;
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
		    e.printStackTrace();
		}finally{
			try{
		    	if(prep_stmt!=null)
		            prep_stmt.close();
		    }catch(SQLException se2){}
		}
		return Status.FAIL;
	}
}
