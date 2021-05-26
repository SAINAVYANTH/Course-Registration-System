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
	public Status teachCourse(String id, String courseId);
	public List<Course> viewTeachingCourses(String id);
	public List<Student> viewEnrolledStudents(String id, String courseId) throws InvalidCourseIdException;
	public void giveGrades(String id, String courseId, Hashtable<String, Grade> grades) throws InvalidGradeException;
}
