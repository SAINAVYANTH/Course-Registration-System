package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.dao.CourseDao;
import com.flipkart.dao.CourseDaoInterface;
import com.flipkart.dao.ProfessorDao;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.dao.StudentDao;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.exception.InvalidCourseIdException;
import com.flipkart.exception.InvalidProfessorIdException;
import com.flipkart.exception.InvalidStudentIdException;

public class AdminImpl implements AdminInterface{
	private static volatile AdminImpl instance = null;
	
	private AdminImpl() {};
	
	public static AdminImpl getInstance() {
		if(instance==null) {
			synchronized(AdminImpl.class) {
				instance = new AdminImpl();
			}
		}
		return instance;
	}

	@Override
	public void addCourse(Course details) {
		CourseDaoInterface courseDao = CourseDao.getInstance();
		courseDao.addNewCourse(details);	
	}

	@Override
	public void removeCourse(String courseId) throws InvalidCourseIdException{
		CourseDaoInterface courseDao = CourseDao.getInstance();
		courseDao.removeCourse(courseId);
	}

	@Override
	public void addNewProfessor(Professor details) {
		ProfessorDaoInterface professorDao = ProfessorDao.getInstance();
		professorDao.addProfessor(details);		
	}

	@Override
	public void removeStudent(String id) throws InvalidStudentIdException{
		StudentDaoInterface studentDao = StudentDao.getInstance();
		studentDao.deleteStudent(id);
	}

	@Override
	public void removeProfessor(String id) throws InvalidProfessorIdException{
		ProfessorDaoInterface professorDao = ProfessorDao.getInstance();
		professorDao.removeProfessor(id);
	}

	@Override
	public void generateReportCard(String studentId) {}

	@Override
	public void verifyStudentRegistration(String studentId) throws InvalidStudentIdException {
		StudentDaoInterface studentDao = StudentDao.getInstance();
		studentDao.changeStudentVerificationStatus(studentId);
	}
	
}
