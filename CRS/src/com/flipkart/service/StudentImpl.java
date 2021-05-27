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
import com.flipkart.bean.Payment;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;
import com.flipkart.constants.GradeConstants;
import com.flipkart.constants.StatusConstants;
import com.flipkart.dao.PaymentsDao;
import com.flipkart.dao.PaymentsDaoInterface;
import com.flipkart.dao.RegistrationDao;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.StudentDao;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.exception.GradesNotGivenException;
import com.flipkart.exception.InvalidCourseIdException;
import com.flipkart.exception.InvalidStudentIdException;
import com.flipkart.exception.RegistrationFailureException;

/**
 * @author Rohit
 *
 */

public class StudentImpl implements StudentInterface {
	
	private static volatile StudentImpl instance = null;
	
	private StudentImpl() {};
	
	public static StudentImpl getInstance() {
		if (instance == null) {
			synchronized (StudentImpl.class) {
				instance = new StudentImpl();
			}
		}
		return instance;
	}
	
	StudentDaoInterface studentDaoInterface = StudentDao.getInstance();

	@Override
	public StatusConstants semesterRegistration(String studentId, CourseRegistration courses) throws RegistrationFailureException {
		Course[] primary = courses.getPrimaryCourses();
		Course[] secondary = courses.getSecondaryCourses();
		int successes = 0;
		for(Course each : primary) {
			try {
				if(addCourse(studentId, each.getCourseId()) == StatusConstants.SUCCESS) {
					successes = successes + 1;
				}
			}catch (InvalidCourseIdException | RegistrationFailureException ex) {}
		}
		if(successes <= 4 && successes >=2) {
			for(Course each : secondary) {
				if(successes!=4) {
					try {
						if(addCourse(studentId, each.getCourseId()) == StatusConstants.SUCCESS) {
							successes = successes + 1;
						}
					}catch (InvalidCourseIdException | RegistrationFailureException ex) {}
				}
			}
			if(successes<4) {
				RegistrationDaoInterface registrationDao = RegistrationDao.getInstance();
		        registrationDao.clearStudentCourses(studentId);
				throw new RegistrationFailureException("Registration Failed");
			}
		}
		else {
			RegistrationDaoInterface registrationDao = RegistrationDao.getInstance();
	        registrationDao.clearStudentCourses(studentId);
			throw new RegistrationFailureException("Registration Failed");
		}
		return StatusConstants.SUCCESS;
	}

	@Override
	public StatusConstants addCourse(String studentId, String courseId)
			throws InvalidCourseIdException, RegistrationFailureException{
		RegistrationDaoInterface registrationDao = RegistrationDao.getInstance();
		if(registrationDao.getStudentCount(courseId) >= 10) {
			throw new RegistrationFailureException("Maximum student limit for " + courseId + " reached");
		}
		else {
			return registrationDao.saveNewRegistration(courseId, studentId);
		}
	}

	@Override
	public StatusConstants dropCourse(String studentId, String courseId) throws InvalidCourseIdException{
		RegistrationDaoInterface registrationDao = RegistrationDao.getInstance();
		return registrationDao.removeRegistration(courseId, studentId);
	}

	@Override
	public List<Course> viewRegisteredCourses(String studentId) throws SQLException {
		RegistrationDaoInterface registrationDao = RegistrationDao.getInstance();
		return registrationDao.viewRegisteredCourses(studentId);
	}

	@Override
	public ReportCard viewReportCard(String studentId) throws GradesNotGivenException, InvalidStudentIdException {
		RegistrationDaoInterface registrationDao = RegistrationDao.getInstance();
		Hashtable<String, GradeConstants> grades = registrationDao.getGrades(studentId);
		Enumeration<String> e = grades.keys();
		 while(e.hasMoreElements()) {
			 String studentID = e.nextElement();
	         GradeConstants grade = grades.get(studentID);
	         if (grade == GradeConstants.NOT_GRADED) {
	        	 throw new GradesNotGivenException("Grading not yet complete");
	         }
		 }
		return new ReportCard(studentId, grades);
	}

	@Override
	public StatusConstants payFee(String studentId, Payment details) {
		PaymentsDaoInterface paymentsDao = PaymentsDao.getInstance();
		return paymentsDao.addTransaction(studentId, details);
	}

	@Override
	public void addNewStudent(Student details) {
		StudentDaoInterface studentDao = StudentDao.getInstance();
		studentDao.addStudent(details);
	}

	@Override
	public boolean getVerificationStatus(String studentId) throws InvalidStudentIdException {
		StudentDaoInterface studentDao = StudentDao.getInstance();
		return studentDao.getVerificationStatus(studentId);
	}
}