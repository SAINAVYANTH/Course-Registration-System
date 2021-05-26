package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.constants.Status;
import com.flipkart.exception.InvalidCourseIdException;

public interface CourseDaoInterface {
	public Status addNewCourse(Course details);
	public Course getCourseDetails(String courseId) throws InvalidCourseIdException;
	public List<Course> getCourseList();
	public Status removeCourse(String courseId) throws InvalidCourseIdException;
	public Status updateCourseDetails(String id, String courseId);
	public List<Course> getTeachingCourse(String id);
}
