package com.flipkart.dao;

import com.flipkart.bean.Student;
import com.flipkart.constants.Status;
import com.flipkart.exception.InvalidStudentIdException;

public interface StudentDaoInterface {
	public Student getStudentDetails(String studentId) throws InvalidStudentIdException;
	public Status deleteStudent(String studentID) throws InvalidStudentIdException;
	public Status addStudent(Student student);
}