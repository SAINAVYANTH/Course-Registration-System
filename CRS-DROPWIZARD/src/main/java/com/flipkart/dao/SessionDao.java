package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.apache.log4j.Logger;

import com.flipkart.constants.SQLQueryConstants;
import com.flipkart.constants.StatusConstants;
import com.flipkart.utils.DBUtils;

public class SessionDao implements SessionDaoInterface{
	
	private static Logger logger = Logger.getLogger(SessionDao.class);
	private static volatile SessionDao instance = null;
	
	private SessionDao() {};
	
	public static SessionDao getInstance() {
		if(instance==null) {
			synchronized(SessionDao.class) {
				instance = new SessionDao();
			}
		}
		return instance;
	}

	@Override
	public StatusConstants addSession(String userid, String sessionId, double expiry) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			clearUserSessions(userid);
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.ADD_SESSION;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, userid);
			prep_stmt.setString(2, sessionId);
			prep_stmt.setDouble(3, expiry);
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

	@Override
	public boolean checkSession(String sessionId) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.GET_SESSION;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, sessionId);
			ResultSet result = prep_stmt.executeQuery();
			result.absolute(1);
			double expiry = result.getDouble(3);
			long currentTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
			if((double)currentTime > expiry) {
				removeSession(sessionId);
				return false;
			}
			else {
				return true;
			}
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
		return false;
	}

	@Override
	public StatusConstants removeSession(String sessionId) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.REMOVE_SESSION;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, sessionId);
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
	
	public StatusConstants clearUserSessions(String studentId) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.CLEAR_USER_SESSION;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, studentId);
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

	@Override
	public String getUserFromSession(String sessionId) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.GET_SESSION;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, sessionId);
			ResultSet result = prep_stmt.executeQuery();
			result.absolute(1);
			return result.getString(1);
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
		return null;
	}

}
