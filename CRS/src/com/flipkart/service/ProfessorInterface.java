package com.flipkart.service;

import java.util.Hashtable;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constants.Grade;
import com.flipkart.constants.Status;
import com.flipkart.exception.InvalidCourseIdException;
import com.flipkart.exception.InvalidGradeException;

public interface ProfessorInterface {
	/**
	 * Function to link professor to course
	 * @param Professor id, Course id
	 * @return Status (Success/ Fail)
	 */
	public Status teachCourse(String id, String courseId);
	
	/**
	 * Function to view courses a professor is teaching
	 * @param Professor id
	 * @return List of courses professor is teaching
	 */
	public List<Course> viewTeachingCourses(String id);
	
	/**
	 * Function to view enrolled students for a course
	 * @param Course Id
	 * @return List of students enrolled in the course
	 * @exception InvalidCourseException
	 */
	public List<Student> viewEnrolledStudents(String id, String courseId) throws InvalidCourseIdException;
	
	/**
	 * Function to give grades to students
	 * @param Professor id, Course Id, HashTable of StudentId and Grade given
	 * @exception InvalidGradeException
	 */
	public void giveGrades(String id, String courseId, Hashtable<String, Grade> grades) throws InvalidGradeException;
}
