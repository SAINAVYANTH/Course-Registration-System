package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.bean.Notification;
import com.flipkart.constants.SQLQueryConstants;
import com.flipkart.constants.StatusConstants;
import com.flipkart.utils.DBUtils;

public class NotificationDao implements NotificationDaoInterface{
	
	private static Logger logger = Logger.getLogger(NotificationDao.class);
	private static volatile NotificationDao instance = null;
	
	private NotificationDao() {};
	
	public static NotificationDao getInstance() {
		if(instance == null) {
			synchronized(NotificationDao.class) {
				instance = new NotificationDao();
			}
		}
		return instance;
	}

	@Override
	public List<Notification> getAllNotifications(String studentId) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.GET_ALL_NOTIFICATIONS;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, studentId);
			ResultSet result =  prep_stmt.executeQuery();
			List<Notification> notifyList = new ArrayList<Notification>();
			while(result.next()) {
				notifyList.add(new Notification(result.getString(1), result.getString(2), result.getString(3)));
			}
			return notifyList;
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
		return new ArrayList<Notification>();
	}

	@Override
	public List<Notification> getUnreadNotifications(String studentId) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.GET_UNREAD_NOTIFICATIONS;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, studentId);
			prep_stmt.setString(2, Boolean.toString(true));
			ResultSet result =  prep_stmt.executeQuery();
			List<Notification> notifyList = new ArrayList<Notification>();
			while(result.next()) {
				notifyList.add(new Notification(result.getString(1), result.getString(2), result.getString(3)));
			}
			return notifyList;
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
		return new ArrayList<Notification>();
	}

	@Override
	public StatusConstants addNotification(Notification details) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.ADD_NOTIFICATION;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, details.getStudentId());
			prep_stmt.setString(2, details.getMessage());
			prep_stmt.setString(3, details.getExtras());
			prep_stmt.setString(4, Boolean.toString(true));
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
	public StatusConstants markAsRead(Notification details) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.MARK_AS_READ;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, Boolean.toString(false));
			prep_stmt.setString(2, details.getStudentId());
			prep_stmt.setString(3, details.getMessage());
			prep_stmt.setString(4, details.getExtras());
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
