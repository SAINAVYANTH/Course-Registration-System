package com.flipkart.service;

import java.util.Hashtable;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constants.Grade;
import com.flipkart.constants.Status;
import com.flipkart.dao.CourseDAO;
import com.flipkart.dao.CourseDaoInterface;
import com.flipkart.dao.RegistrationDAO;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.exception.InvalidCourseIdException;
import com.flipkart.exception.InvalidGradeException;

public class ProfessorImpl implements ProfessorInterface{

	@Override
	public Status teachCourse(String id, String courseId) {
		CourseDaoInterface courseDao = CourseDAO.getInstance();
		return courseDao.updateCourseDetails(id, courseId);
	}

	@Override
	public List<Course> viewTeachingCourses(String id) {
		CourseDaoInterface courseDao = CourseDAO.getInstance();
		return courseDao.getTeachingCourse(id);
	}

	@Override
	public List<Student> viewEnrolledStudents(String id, String courseId) throws InvalidCourseIdException {
		RegistrationDaoInterface registrationDao = RegistrationDAO.getInstance();
		return registrationDao.viewEnrolledStudents(courseId);
	}

	@Override
	public void giveGrades(String id, String courseId, Hashtable<String, Grade> grades) throws InvalidGradeException {
		RegistrationDaoInterface registrationDao = RegistrationDAO.getInstance();
		registrationDao.addGrade(courseId, grades);
	}

}
