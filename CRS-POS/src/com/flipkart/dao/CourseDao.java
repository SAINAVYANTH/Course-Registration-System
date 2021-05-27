package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.constants.SQLQueryConstants;
import com.flipkart.constants.StatusConstants;
import com.flipkart.exception.InvalidCourseIdException;
import com.flipkart.utils.DBUtils;

public class CourseDao implements CourseDaoInterface{
	
	private static Logger logger = Logger.getLogger(CourseDao.class);
	private static volatile CourseDao instance = null;
	
	private CourseDao() {};
	
	public static CourseDao getInstance() {
		if (instance == null) {
			synchronized (CourseDao.class) {
				instance = new CourseDao();
			}
		}
		return instance;
	}
	
	
	public StatusConstants addNewCourse(Course details) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.ADD_NEW_COURSE;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, details.getCourseId());
			prep_stmt.setString(2, details.getCourseName());
			prep_stmt.setString(3, details.getInstructor());
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
	
	public Course getCourseDetails(String courseId) throws InvalidCourseIdException{
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.GET_COURSE_DETAILS; 
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, courseId);
			ResultSet result =  prep_stmt.executeQuery();
			result.absolute(1);
			return new Course(result.getString(1), result.getString(2), result.getString(3));
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
		throw new InvalidCourseIdException("Invalid course id " + courseId);
	}
	
	public StatusConstants removeCourse(String courseId) throws InvalidCourseIdException{
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.REMOVE_COURSE;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, courseId);
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
		throw new InvalidCourseIdException("Invalid course id " + courseId);
	}
	
	public List<Course> getCourseList(){
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.GET_COURSE_LIST;
			prep_stmt = conn.prepareStatement(raw_stmt);
			ResultSet result =  prep_stmt.executeQuery();
			List<Course> courseList = new ArrayList<Course>();
			while(result.next()) {
				courseList.add(new Course(result.getString(1), result.getString(2), result.getString(3)));
			}
			return courseList;
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
		return new ArrayList<Course>();
	}
	
	public StatusConstants updateCourseDetails(String id, String courseId){
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.UPDATE_COURSE_DETAILS;
			prep_stmt = conn.prepareStatement(raw_stmt);	
			prep_stmt.setString(1, id);
			prep_stmt.setString(2, courseId);
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
	public List<Course> getTeachingCourse(String id) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.GET_TEACHING_COURSES;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, id);
			ResultSet result =  prep_stmt.executeQuery();
			List<Course> courseList = new ArrayList<Course>();
			while(result.next()) {
				courseList.add(new Course(result.getString(1), result.getString(2), result.getString(3)));
			}
			return courseList;
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
		return new ArrayList<Course>();
	}
}
