package com.flipkart.service;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseRegistration;
import com.flipkart.bean.Payment;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;
import com.flipkart.constants.Status;
import com.flipkart.exception.GradesNotGivenException;
import com.flipkart.exception.InvalidCourseIdException;
import com.flipkart.exception.InvalidGradeException;
import com.flipkart.exception.InvalidStudentIdException;
import com.flipkart.exception.RegistrationFailureException;

public interface StudentInterface {
	
	
	/**
	 * Function to give grades to students
	 * @param Professor id, Course Id, HashTable of StudentId and Grade given
	 * @exception InvalidGradeException
	 */
	public Status semesterRegistration(String studentId, CourseRegistration courses) throws RegistrationFailureException;
	
	/**
	 * Function to register a student for a course
	 * @param Student Id, Course Id
	 * @exception InvalidCourseIdException, RegistrationFailureException
	 */
	public Status addCourse(String studentId, String courseId) throws InvalidCourseIdException, RegistrationFailureException;
	
	/**
	 * Function to drop course as a student
	 * @param Student ID, course Id
	 * @exception InvalidCourseIdException, SQLException
	 */
	public Status dropCourse(String studentId, String courseId) throws InvalidCourseIdException, SQLException ;
	
	/**
	 * Function to view registered courses by student
	 * @param Student ID
	 * @exception SQLException
	 */
	public List<Course> viewRegisteredCourses(String studentId) throws SQLException;
	
	/**
	 * Function view report card containing courses and grades as a student
	 * @param Student ID
	 * @exception GradesNotGivenException, InvalidStudentIdException
	 */
	public ReportCard viewReportCard(String studentId) throws GradesNotGivenException, InvalidStudentIdException;
	
	/**
	 * Function to pay fees as a student
	 * @param Student Id, Payment Details
	 */
	public Status payFee(String studentId,Payment details);
	
	/** Function to add a new Student to the database
	 * @param Student details
	 * */
	public void addNewStudent(Student details);
	
	/** Function to get the verification status of a student.
	 * @param String studentId
	 * @throws InvalidStudentIdException
	 * */
	public boolean getVerificationStatus(String studentId) throws InvalidStudentIdException;
}
