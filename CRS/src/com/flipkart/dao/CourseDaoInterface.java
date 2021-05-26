package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.constants.Status;
import com.flipkart.exception.InvalidCourseIdException;

public interface CourseDaoInterface {
	
	/** Dao Function to add a new course
	 * @params Course details
	 * */
	public Status addNewCourse(Course details);
	
	/** Dao Function to fetch course details
	 * @param String course id
	 * @throws InvalidCourseIdException
	 * */
	public Course getCourseDetails(String courseId) throws InvalidCourseIdException;
	
	/** Dao Function to get all the courses
	 * */
	public List<Course> getCourseList();
	
	/** Dao Function to remove a course
	 * @param String course id
	 * @throws InvalidCourseIdException
	 * */
	public Status removeCourse(String courseId) throws InvalidCourseIdException;
	
	/** Dao Function to update the details of a course
	 * @params String course id
	 * */
	public Status updateCourseDetails(String id, String courseId);
	
	/** Dao Function to get courses being taught by a professor
	 * @param String professor id
	 * */
	public List<Course> getTeachingCourse(String id);
}
