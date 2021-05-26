package com.flipkart.service;

import java.util.Hashtable;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constants.Grade;
import com.flipkart.constants.Status;
import com.flipkart.dao.CourseDao;
import com.flipkart.dao.CourseDaoInterface;
import com.flipkart.dao.RegistrationDao;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.exception.InvalidCourseIdException;
import com.flipkart.exception.InvalidGradeException;

public class ProfessorImpl implements ProfessorInterface{
	private static volatile ProfessorImpl instance = null;
	
	private ProfessorImpl() {};
	
	public static ProfessorImpl getInstance() {
		if(instance==null) {
			synchronized(ProfessorImpl.class) {
				instance = new ProfessorImpl();
			}
		}
		return instance;
	}

	/**
	 * Function to link professor to course
	 * @param Professor id, Course id
	 * @return Status (Success/ Fail)
	 */
	@Override
	public Status teachCourse(String id, String courseId) {
		CourseDaoInterface courseDao = CourseDao.getInstance();
		return courseDao.updateCourseDetails(id, courseId);
	}
	/**
	 * Function to view courses a professor is teaching
	 * @param Professor id
	 * @return List of courses professor is teaching
	 */
	@Override
	public List<Course> viewTeachingCourses(String id) {
		CourseDaoInterface courseDao = CourseDao.getInstance();
		return courseDao.getTeachingCourse(id);
	}

	/**
	 * Function to view enrolled students for a course
	 * @param Course Id
	 * @return List of students enrolled in the course
	 * @exception InvalidCourseException
	 */
	@Override
	public List<Student> viewEnrolledStudents(String id, String courseId) throws InvalidCourseIdException {
		RegistrationDaoInterface registrationDao = RegistrationDao.getInstance();
		return registrationDao.viewEnrolledStudents(courseId);
	}
	/**
	 * Function to give grades to students
	 * @param Professor id, Course Id, HashTable of StudentId and Grade given
	 * @exception InvalidGradeException
	 */
	@Override
	public void giveGrades(String id, String courseId, Hashtable<String, Grade> grades) throws InvalidGradeException {
		RegistrationDaoInterface registrationDao = RegistrationDao.getInstance();
		registrationDao.addGrade(courseId, grades);
	}

}
