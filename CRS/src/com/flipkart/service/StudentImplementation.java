/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseRegistration;
import com.flipkart.bean.ReportCard;
import com.flipkart.constants.Grade;
import com.flipkart.constants.Status;
import com.flipkart.dao.RegistrationDAO;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.StudentDaoImplementation;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.exception.GradesNotGivenException;
import com.flipkart.exception.InvalidCourseIdException;
import com.flipkart.exception.InvalidStudentIdException;
import com.flipkart.exception.RegistrationFailureException;

/**
 * @author Rohit
 *
 */
public class StudentImplementation implements StudentInterface {
	
	private static volatile StudentImplementation instance = null;
	
	public static StudentImplementation getInstance() {
		if (instance == null) {
			synchronized (StudentImplementation.class) {
				instance = new StudentImplementation();
			}
		}
		return instance;
	}
	StudentDaoInterface studentDaoInterface = StudentDaoImplementation.getInstance();

	@Override
	public Status semesterRegistration(String studentId, CourseRegistration courses) throws RegistrationFailureException {
		Course[] primary = courses.getPrimaryCourses();
		Course[] secondary = courses.getSecondaryCourses();
		int successes = 0;
		for(Course each : primary) {
			try {
				if(addCourse(studentId, each.getCourseId()) == Status.SUCCESS) {
					successes++;
				}
			}catch (InvalidCourseIdException | RegistrationFailureException ex) {}
		}
		if(successes < 4 && successes >=2) {
			for(Course each : secondary) {
				if(successes!=4) {
					try {
						if(addCourse(studentId, each.getCourseId()) == Status.SUCCESS) {
							successes++;
						}
					}catch (InvalidCourseIdException | RegistrationFailureException ex) {}
				}
			}
			if(successes<4) {
				RegistrationDaoInterface registrationDao = RegistrationDAO.getInstance();
		        registrationDao.clearStudentCourses(studentId);
				throw new RegistrationFailureException("Registration Failed");
			}
		}
		else {
			RegistrationDaoInterface registrationDao = RegistrationDAO.getInstance();
	        registrationDao.clearStudentCourses(studentId);
			throw new RegistrationFailureException("Registration Failed");
		}
		return Status.SUCCESS;
	}

	@Override
	public Status addCourse(String studentId, String courseId)
			throws InvalidCourseIdException, RegistrationFailureException{
		RegistrationDaoInterface registrationDao = RegistrationDAO.getInstance();
		if(registrationDao.getStudentCount(courseId) >= 10) {
			throw new RegistrationFailureException("Maximum student limit for " + courseId + " reached");
		}
		else {
			return registrationDao.saveNewRegistration(courseId, studentId);
		}
	}

	@Override
	public Status dropCourse(String studentId, String courseId) throws InvalidCourseIdException{
		RegistrationDaoInterface registrationDao = RegistrationDAO.getInstance();
		return registrationDao.removeRegistration(courseId, studentId);
	}

	@Override
	public List<Course> viewRegisteredCourses(String studentId) throws SQLException {
		RegistrationDaoInterface registrationDao = RegistrationDAO.getInstance();
		return registrationDao.viewRegisteredCourses(studentId);
	}

	@Override
	public ReportCard viewReportCard(String studentId) throws GradesNotGivenException, InvalidStudentIdException {
		RegistrationDaoInterface registrationDao = RegistrationDAO.getInstance();
		Hashtable<String, Grade> grades = registrationDao.getGrades(studentId);
		Enumeration<String> e = grades.keys();
		 while(e.hasMoreElements()) {
			 String studentID = e.nextElement();
	         Grade grade = grades.get(studentID);
	         if (grade == Grade.NOT_GRADED) {
	        	 throw new GradesNotGivenException("Grading not yet complete");
	         }
		 }
		return new ReportCard(studentId, grades);
	}
}