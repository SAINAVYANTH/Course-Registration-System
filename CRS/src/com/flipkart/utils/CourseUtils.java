package com.flipkart.utils;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.dao.CourseDAO;
import com.flipkart.dao.CourseDaoInterface;

public class CourseUtils {
	public static List<Course> getCourseList() {
		CourseDaoInterface courseDao = CourseDAO.getInstance();
		return courseDao.getCourseList();
	}
	
	public static void getCourseDetails(String courseId) {
		
	}
}