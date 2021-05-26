package com.flipkart.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.bean.Student;
import com.flipkart.bean.UserLogin;
import com.flipkart.constants.Status;
import com.flipkart.constants.UserRole;
import com.flipkart.exception.InvalidStudentIdException;
import com.flipkart.utils.DBUtils;

public class StudentDaoImplementation implements StudentDaoInterface {

	private static volatile StudentDaoImplementation instance=null;
	private PreparedStatement stmt = null;
	public static StudentDaoImplementation getInstance()
	{
		if(instance==null)
		{
			synchronized(StudentDaoImplementation.class){
				instance=new StudentDaoImplementation();
			}
		}
		return instance;
	}
	
	@SuppressWarnings("deprecation")
	public Status addStudent(Student student) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = "insert into students values(?,?,?,?,?,?,?,?)";
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, student.getId());
			prep_stmt.setString(2, student.getName());
			prep_stmt.setDate(3, new Date(12,3,2000));
			prep_stmt.setString(4, student.getEmail());
			prep_stmt.setString(5, student.getAddress());
			prep_stmt.setString(6, student.getRollNo());
			prep_stmt.setString(7, student.getDepartment());
			prep_stmt.setString(8, student.getYearOfJoining());
			prep_stmt.executeUpdate();
			CredentialsDaoInterface credentialsDao = CredentialsDAO.getInstance();
			credentialsDao.saveCredentials(new UserLogin(student.getId(), student.getId(), UserRole.STUDENT));
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
	
	public Student getStudentDetails(String studentId) throws InvalidStudentIdException {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = "select * from students where id=?";
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, studentId);
			ResultSet result =  prep_stmt.executeQuery();
			result.absolute(1);
			return new Student(result.getString(1), result.getString(2), result.getDate(3), result.getString(4), 
					result.getString(5), result.getString(6), result.getString(7), result.getString(8));
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
	
	public Status deleteStudent(String studentID) throws InvalidStudentIdException{
		Connection conn = null;
		PreparedStatement prep_stmt = null;
	    try{
	    	conn = DBUtils.getConnection();
	        stmt = conn.prepareStatement("DELETE FROM TABLE STUDENT WHERE ID = ?");
	        stmt.setString(1, studentID.toString());
	        stmt.executeUpdate();
	        RegistrationDaoInterface registrationDao = RegistrationDAO.getInstance();
	        return registrationDao.clearStudentCourses(studentID);
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
	    throw new InvalidStudentIdException("Invalid student id " + studentID);
	}
}