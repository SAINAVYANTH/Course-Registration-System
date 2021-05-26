package com.flipkart.utils;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.dao.CourseDao;
import com.flipkart.dao.CourseDaoInterface;
import com.flipkart.exception.InvalidCourseIdException;

public class CourseUtils {
	public static List<Course> getCourseList() {
		CourseDaoInterface courseDao = CourseDao.getInstance();
		return courseDao.getCourseList();
	}
	
	public static Course getCourseDetails(String courseId) throws InvalidCourseIdException{
		CourseDaoInterface courseDao = CourseDao.getInstance();
		return courseDao.getCourseDetails(courseId);
	}
}
