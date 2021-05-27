package com.flipkart.utils;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.dao.CourseDao;
import com.flipkart.dao.CourseDaoInterface;
import com.flipkart.exception.InvalidCourseIdException;

public class CourseUtils {
	
	/** Function to retrieve the list of all the courses offered.
	 * */
	public static List<Course> getCourseList() {
		CourseDaoInterface courseDao = CourseDao.getInstance();
		return courseDao.getCourseList();
	}
	
	/** Function to get the details of a given course
	 * @param String course id
	 * */
	public static Course getCourseDetails(String courseId) throws InvalidCourseIdException{
		CourseDaoInterface courseDao = CourseDao.getInstance();
		return courseDao.getCourseDetails(courseId);
	}
}
