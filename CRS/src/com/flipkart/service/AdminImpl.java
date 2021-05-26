package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDAO;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.CourseDAO;
import com.flipkart.dao.CourseDaoInterface;
import com.flipkart.dao.ProfessorDAO;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.dao.StudentDaoImplementation;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.exception.InvalidCourseIdException;
import com.flipkart.exception.InvalidProfessorIdException;
import com.flipkart.exception.InvalidStudentIdException;

public class AdminImpl implements AdminInterface{

	@Override
	public void addCourse(Course details) {
		CourseDaoInterface courseDao = CourseDAO.getInstance();
		courseDao.addNewCourse(details);	
	}

	@Override
	public void removeCourse(String courseId) throws InvalidCourseIdException{
		CourseDaoInterface courseDao = CourseDAO.getInstance();
		courseDao.removeCourse(courseId);
	}

	@Override
	public void addNewProfessor(Professor details) {
		ProfessorDaoInterface professorDao = ProfessorDAO.getInstance();
		professorDao.addProfessor(details);		
	}

	@Override
	public void addNewStudent(Student details) {
		StudentDaoInterface studentDao = StudentDaoImplementation.getInstance();
		studentDao.addStudent(details);
	}

	@Override
	public void removeStudent(String id) throws InvalidStudentIdException{
		StudentDaoInterface studentDao = StudentDaoImplementation.getInstance();
		studentDao.deleteStudent(id);
	}

	@Override
	public void removeProfessor(String id) throws InvalidProfessorIdException{
		ProfessorDaoInterface professorDao = ProfessorDAO.getInstance();
		professorDao.removeProfessor(id);
	}

	@Override
	public void generateReportCard(String studentId) {
		// TODO Auto-generated method stub
		
	}
	
}
