package com.flipkart.utils;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.dao.CourseDao;
import com.flipkart.dao.CourseDaoInterface;

public class CourseUtils {
	public static List<Course> getCourseList() {
		CourseDaoInterface courseDao = CourseDao.getInstance();
		return courseDao.getCourseList();
	}
	
	public static Course getCourseDetails(String courseId) {
		
	}
}
