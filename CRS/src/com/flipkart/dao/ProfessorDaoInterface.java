package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.constants.StatusConstants;
import com.flipkart.exception.InvalidProfessorIdException;

public interface ProfessorDaoInterface {
	
	/** Dao Function to add a professor to the db
	 * @param Professor details
	 * */
	public StatusConstants addProfessor(Professor details);
	
	/** Dao Function to get details of a professor
	 * @param String professor id
	 * @throws InvalidProfessorIdException
	 * */
	public Professor getProfessorDetails(String id) throws InvalidProfessorIdException;
	
	/** Dao Function to remove a professor from the db
	 * @param String professor id
	 * @throws InvalidProfessorIdException
	 * */
	public StatusConstants removeProfessor(String id) throws InvalidProfessorIdException;
}
