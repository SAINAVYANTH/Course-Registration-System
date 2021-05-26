package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.InvalidCourseIdException;
import com.flipkart.exception.InvalidProfessorIdException;
import com.flipkart.exception.InvalidStudentIdException;

public interface AdminInterface {
	
	/* function to add a course to the catalog
	 * @param Course details
	 * */
	public void addCourse(Course details);
	
	/* function to remove a course from the catalog
	 * @param String courseId
	 * @throws InvalidCourseIdException
	 * */
	public void removeCourse(String courseId) throws InvalidCourseIdException;
	
	/* function to add a new professor to the database
	 * @param Professor details
	 * */
	public void addNewProfessor(Professor details);
	
	/* function to add a new Student to the database
	 * @param Student details
	 * */
	public void addNewStudent(Student details);
	
	/* function to remove a student from database
	 * @param String id
	 * @throws InvalidStudentIdException
	 * */
	public void removeStudent(String id) throws InvalidStudentIdException;
	
	/* function to remove a professor from the database
	 * @param String id
	 * @throws InvalidProfessorIdException
	 * */
	public void removeProfessor(String id) throws InvalidProfessorIdException;
	
	/* function to generate the report card for a student
	 * @param String studentId
	 * */
	public void generateReportCard(String studentId);
}
