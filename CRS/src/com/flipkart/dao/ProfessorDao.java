package com.flipkart.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.bean.Professor;
import com.flipkart.bean.UserLogin;
import com.flipkart.constants.SQLQueryConstants;
import com.flipkart.constants.StatusConstants;
import com.flipkart.constants.UserRoleConstants;
import com.flipkart.exception.InvalidProfessorIdException;
import com.flipkart.utils.DBUtils;

public class ProfessorDao implements ProfessorDaoInterface{
	
	private static Logger logger = Logger.getLogger(ProfessorDao.class);
	private static volatile ProfessorDao instance = null;
	
	private ProfessorDao() {};
	
	public static ProfessorDao getInstance() {
		if (instance == null) {
			synchronized (ProfessorDao.class) {
				instance = new ProfessorDao();
			}
		}
		return instance;
	}
	
	public StatusConstants addProfessor(Professor details) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.ADD_PROFESSOR;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, details.getId());
			prep_stmt.setString(2, details.getName());
			prep_stmt.setDate(3, (Date) details.getDob());
			prep_stmt.setString(4, details.getEmail());
			prep_stmt.setString(5, details.getAddress());
			prep_stmt.setString(6, details.getDepartment());
			prep_stmt.setString(7, details.getDesignation());
			prep_stmt.executeUpdate();
			CredentialsDaoInterface credentialsDao = CredentialsDao.getInstance();
			credentialsDao.saveCredentials(new UserLogin(details.getId(), details.getId(), UserRoleConstants.PROFESSOR));
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
	
	public StatusConstants removeProfessor(String id) throws InvalidProfessorIdException{
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.REMOVE_PROFESSOR;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, id);
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
		throw new InvalidProfessorIdException("Invalid id " + id);
	}
	
	public Professor getProfessorDetails(String id) throws InvalidProfessorIdException {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.GET_PROFESSOR_DETIALS;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, id);
			ResultSet result =  prep_stmt.executeQuery();
			result.absolute(1);
			return new Professor(result.getString(1), result.getString(2), result.getDate(3), result.getString(4), 
					result.getString(5), result.getString(6), result.getString(7));
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
		throw new InvalidProfessorIdException("Invalid id " + id);
	}
	
	
}
