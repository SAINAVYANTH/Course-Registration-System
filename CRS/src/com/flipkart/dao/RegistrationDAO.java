package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constants.GradeConstants;
import com.flipkart.constants.SQLQueries;
import com.flipkart.constants.Status;
import com.flipkart.exception.InvalidStudentIdException;
import com.flipkart.utils.DBUtils;

public class RegistrationDao implements RegistrationDaoInterface{
	private static volatile RegistrationDao instance = null;
	
	public static RegistrationDao getInstance() {
		if (instance == null) {
			synchronized(RegistrationDao.class) {
				instance = new RegistrationDao();
			}
		}
		return instance;	
	}

	public Status saveNewRegistration(String courseId, String studentId) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueries.SAVE_NEW_REG;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, courseId);
			prep_stmt.setString(2, studentId);
			prep_stmt.setString(3, GradeConstants.NOT_GRADED.toString());
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
	
	public Status removeRegistration(String courseId, String studentId) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueries.DROP_COURSE;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, courseId);
			prep_stmt.setString(2, studentId);
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
	
	public Hashtable<String,GradeConstants> getGrades(String studentId) throws InvalidStudentIdException{
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueries.GET_GRADES;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, studentId);
			ResultSet result = prep_stmt.executeQuery();
			Hashtable<String, GradeConstants> grades = new Hashtable<String, GradeConstants>();
			while(result.next()) {
				grades.put(result.getString(1), GradeConstants.valueOf(result.getString(2)));
			}
			return grades;
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
		throw new InvalidStudentIdException("Invalid student id " + studentId);
	}
	
	public Status addGrade(String courseID, Hashtable<String, GradeConstants> ht){
		Connection conn = null;
		PreparedStatement prep_stmt = null;
	    try {
	        conn = DBUtils.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(SQLQueries.ADD_GRADE);
	        Enumeration<String> e = ht.keys();
	        while(e.hasMoreElements()) {
	            String studentID = e.nextElement();
	            GradeConstants grade = ht.get(studentID);

	            stmt.setString(1, grade.toString());
	            stmt.setString(3, studentID);
	            stmt.setString(2, courseID);

	            stmt.executeUpdate();
	        }
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
	public int getStudentCount(String courseId) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueries.GET_STUDENT_CNT;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, courseId);
			ResultSet result = prep_stmt.executeQuery();
			result.absolute(1);
			return result.getInt(1);
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
		return 0;
	}
	
	public List<Student> viewEnrolledStudents(String courseId){
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueries.VIEW_ENROLL_STUDENTS;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, courseId);
			ResultSet result = prep_stmt.executeQuery();
			List<Student> students = new ArrayList<Student>();
			while(result.next()) {
				String studentId = result.getString(1);
				StudentDaoInterface studentDao = StudentDao.getInstance();
				Student studentDetails = studentDao.getStudentDetails(studentId);
				students.add(studentDetails);
			}
			return students;
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
		return new ArrayList<Student>();
	}

	@Override
	public List<Course> viewRegisteredCourses(String studentId) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueries.VIEW_REGIS_COURSES;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, studentId);
			ResultSet result = prep_stmt.executeQuery();
			List<Course> courses = new ArrayList<Course>();
			while(result.next()) {
				String courseId = result.getString(1);
				CourseDaoInterface courseDao = CourseDao.getInstance();
				Course courseDetails = courseDao.getCourseDetails(courseId);
				courses.add(courseDetails);
			}
			return courses;
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

	@Override
	public Status clearStudentCourses(String studentId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.prepareStatement(SQLQueries.CLEAR_STUDENT_COURSES);
	        stmt.setString(1, studentId.toString());
	        stmt.executeUpdate();
	        return Status.SUCCESS;
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
		    e.printStackTrace();
		}finally{
			try{
		    	if(stmt!=null)
		            stmt.close();
		    }catch(SQLException se2){}
		}
		return Status.FAIL;
	}
	
	
}
