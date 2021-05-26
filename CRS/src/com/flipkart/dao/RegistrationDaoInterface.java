package com.flipkart.dao;

import java.util.Hashtable;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constants.Grade;
import com.flipkart.constants.Status;
import com.flipkart.exception.InvalidStudentIdException;

public interface RegistrationDaoInterface {
	public Status saveNewRegistration(String courseId, String studentId);
	public Status removeRegistration(String courseId, String studentId);
	public Hashtable<String,Grade> getGrades(String studentId) throws InvalidStudentIdException;
	public int getStudentCount(String courseId);
	public Status addGrade(String courseID, Hashtable<String, Grade> ht);
	public List<Student> viewEnrolledStudents(String courseId);
	public List<Course> viewRegisteredCourses(String studentId);
	public Status clearStudentCourses(String studentId);
}
