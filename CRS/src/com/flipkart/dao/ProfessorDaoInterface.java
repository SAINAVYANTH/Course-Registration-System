package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.constants.Status;
import com.flipkart.exception.InvalidProfessorIdException;

public interface ProfessorDaoInterface {
	public Status addProfessor(Professor details);
	public Professor getProfessorDetails(String id) throws InvalidProfessorIdException;
	public Status removeProfessor(String id) throws InvalidProfessorIdException;
}
