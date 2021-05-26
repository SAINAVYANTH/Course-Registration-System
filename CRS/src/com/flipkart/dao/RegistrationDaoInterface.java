package com.flipkart.dao;

import java.util.Hashtable;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constants.GradeConstants;
import com.flipkart.constants.Status;
import com.flipkart.exception.InvalidStudentIdException;

public interface RegistrationDaoInterface {
	
	/** Dao Function to save new registration entry
	 * @param String course id, String student id
	 * */
	public Status saveNewRegistration(String courseId, String studentId);
	
	/** Dao Function to remove a registration entry
	 * @param String course id, String student id
	 * */
	public Status removeRegistration(String courseId, String studentId);
	
	/** Dao Function to get the grades of a student
	 * @param String student id
	 * @throws InvalidStudentIdException
	 * */
	public Hashtable<String,GradeConstants> getGrades(String studentId) throws InvalidStudentIdException;
	
	/** Dao Function to get the count of students registered for a course
	 * @param String course id
	 * */
	public int getStudentCount(String courseId);
	
	/** Dao Function to add grade for a given course and student
	 * @param String course id and String student id
	 * */
	public Status addGrade(String courseID, Hashtable<String, GradeConstants> ht);
	
	/** Dao Function to view the students enrolled in a course
	 * @param String course id
	 * */
	public List<Student> viewEnrolledStudents(String courseId);
	
	/** Dao Function to veiw the courses registered by a student
	 * @param String student id
	 * */
	public List<Course> viewRegisteredCourses(String studentId);
	
	/** Dao Function to clear all the courses corrresponding to a student
	 * @param String student id
	 * */
	public Status clearStudentCourses(String studentId);
}
