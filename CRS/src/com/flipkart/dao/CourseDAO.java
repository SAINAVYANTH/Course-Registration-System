package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.constants.Status;
import com.flipkart.exception.InvalidCourseIdException;
import com.flipkart.utils.DBUtils;

public class CourseDAO implements CourseDaoInterface{
	private static CourseDAO instance = null;
	
	private CourseDAO() {};
	
	public static CourseDAO getInstance() {
		if (instance == null) {
			instance = new CourseDAO();
		}
		return instance;
	}
	
	
	public Status addNewCourse(Course details) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = "insert into courses values(?,?,?)";
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, details.getCourseId());
			prep_stmt.setString(2, details.getCourseName());
			prep_stmt.setString(3, details.getInstructor());
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
	
	public Course getCourseDetails(String courseId) throws InvalidCourseIdException{
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = "select * from courses where courseid=?";
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, courseId);
			ResultSet result =  prep_stmt.executeQuery();
			result.absolute(1);
			return new Course(result.getString(1), result.getString(2), result.getString(3));
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
		throw new InvalidCourseIdException("Invalid course id " + courseId);
	}
	
	public Status removeCourse(String courseId) throws InvalidCourseIdException{
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = "delete from courses where courseid=?";
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, courseId);
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
		throw new InvalidCourseIdException("Invalid course id " + courseId);
	}
	
	public List<Course> getCourseList(){
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = "select * from courses";
			prep_stmt = conn.prepareStatement(raw_stmt);
			ResultSet result =  prep_stmt.executeQuery();
			List<Course> courseList = new ArrayList<Course>();
			while(result.next()) {
				courseList.add(new Course(result.getString(1), result.getString(2), result.getString(3)));
			}
			return courseList;
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
		return new ArrayList<Course>();
	}
	
	public Status updateCourseDetails(String id, String courseId){
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = "update courses set instructor=?  where courseid=?";
			prep_stmt = conn.prepareStatement(raw_stmt);	
			prep_stmt.setString(1, id);
			prep_stmt.setString(2, courseId);
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

	@Override
	public List<Course> getTeachingCourse(String id) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = "select * from courses where instructor=?";
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, id);
			ResultSet result =  prep_stmt.executeQuery();
			List<Course> courseList = new ArrayList<Course>();
			while(result.next()) {
				courseList.add(new Course(result.getString(1), result.getString(2), result.getString(3)));
			}
			return courseList;
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
		return new ArrayList<Course>();
	}
}
