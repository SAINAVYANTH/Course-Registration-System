package com.flipkart.service;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseRegistration;
import com.flipkart.bean.Payment;
import com.flipkart.bean.ReportCard;
import com.flipkart.constants.Status;
import com.flipkart.exception.GradesNotGivenException;
import com.flipkart.exception.InvalidCourseIdException;
import com.flipkart.exception.InvalidStudentIdException;
import com.flipkart.exception.RegistrationFailureException;

public interface StudentInterface {
	public Status semesterRegistration(String studentId, CourseRegistration courses) throws RegistrationFailureException;
	public Status addCourse(String studentId, String courseId) throws InvalidCourseIdException, RegistrationFailureException;
	public Status dropCourse(String studentId, String courseId) throws InvalidCourseIdException, SQLException ;
	public List<Course> viewRegisteredCourses(String studentId) throws SQLException;
	public ReportCard viewReportCard(String studentId) throws GradesNotGivenException, InvalidStudentIdException;
	public Status payFee(String studentId,Payment details);
}
