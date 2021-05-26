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

	@Override
	public Status teachCourse(String id, String courseId) {
		CourseDaoInterface courseDao = CourseDao.getInstance();
		return courseDao.updateCourseDetails(id, courseId);
	}

	@Override
	public List<Course> viewTeachingCourses(String id) {
		CourseDaoInterface courseDao = CourseDao.getInstance();
		return courseDao.getTeachingCourse(id);
	}

	@Override
	public List<Student> viewEnrolledStudents(String id, String courseId) throws InvalidCourseIdException {
		RegistrationDaoInterface registrationDao = RegistrationDao.getInstance();
		return registrationDao.viewEnrolledStudents(courseId);
	}

	@Override
	public void giveGrades(String id, String courseId, Hashtable<String, Grade> grades) throws InvalidGradeException {
		RegistrationDaoInterface registrationDao = RegistrationDao.getInstance();
		registrationDao.addGrade(courseId, grades);
	}

}
