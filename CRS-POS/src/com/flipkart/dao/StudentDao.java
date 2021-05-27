package com.flipkart.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.bean.Student;
import com.flipkart.bean.UserLogin;
import com.flipkart.constants.SQLQueryConstants;
import com.flipkart.constants.StatusConstants;
import com.flipkart.constants.UserRoleConstants;
import com.flipkart.exception.InvalidStudentIdException;
import com.flipkart.utils.DBUtils;

public class StudentDao implements StudentDaoInterface {
	
	private static Logger logger = Logger.getLogger(StudentDao.class);
	private static volatile StudentDao instance=null;
	
	private StudentDao() {};
	
	public static StudentDao getInstance()
	{
		if(instance==null)
		{
			synchronized(StudentDao.class){
				instance=new StudentDao();
			}
		}
		return instance;
	}
	
	@SuppressWarnings("deprecation")
	public StatusConstants addStudent(Student student) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.ADD_STUDENT;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, student.getId());
			prep_stmt.setString(2, student.getName());
			prep_stmt.setDate(3, new Date(12,3,2000));
			prep_stmt.setString(4, student.getEmail());
			prep_stmt.setString(5, student.getAddress());
			prep_stmt.setString(6, student.getRollNo());
			prep_stmt.setString(7, student.getDepartment());
			prep_stmt.setString(8, student.getYearOfJoining());
			prep_stmt.setString(9, Boolean.toString(false));
			prep_stmt.executeUpdate();
			CredentialsDaoInterface credentialsDao = CredentialsDao.getInstance();
			credentialsDao.saveCredentials(new UserLogin(student.getId(), student.getId(), UserRoleConstants.STUDENT));
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
	
	public Student getStudentDetails(String studentId) throws InvalidStudentIdException {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.GET_STUDENT_DETAILS;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, studentId);
			ResultSet result =  prep_stmt.executeQuery();
			result.absolute(1);
			return new Student(result.getString(1), result.getString(2), result.getDate(3), result.getString(4), 
					result.getString(5), result.getString(6), result.getString(7), result.getString(8));
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
		throw new InvalidStudentIdException("Invalid student id " + studentId);
	}
	
	public StatusConstants deleteStudent(String studentID) throws InvalidStudentIdException{
		Connection conn = null;
		PreparedStatement prep_stmt = null;
	    try{
	    	conn = DBUtils.getConnection();
	        prep_stmt = conn.prepareStatement(SQLQueryConstants.DEL_STUDENT);
	        prep_stmt.setString(1, studentID.toString());
	        prep_stmt.executeUpdate();
	        RegistrationDaoInterface registrationDao = RegistrationDao.getInstance();
	        return registrationDao.clearStudentCourses(studentID);
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
	    throw new InvalidStudentIdException("Invalid student id " + studentID);
	}

	@Override
	public StatusConstants changeStudentVerificationStatus(String studentId) throws InvalidStudentIdException {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.CH_STUDENT_VERIFICATION;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, Boolean.toString(true));
			prep_stmt.setString(2, studentId);
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
	public boolean getVerificationStatus(String studentId) throws InvalidStudentIdException {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = SQLQueryConstants.GET_STUDENT_VERIFICATION;
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, studentId);
			ResultSet result =  prep_stmt.executeQuery();
			result.absolute(1);
			return Boolean.parseBoolean(result.getString(1));
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
		throw new InvalidStudentIdException("Invalid student id " + studentId);
	}
}