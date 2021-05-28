package com.flipkart.bean;

public class Course {
	
	public Course() {};
	
	public Course(String courseId, String courseName, String instructor) {
		super();
		this.courseName = courseName;
		this.courseId = courseId;
		this.instructor = instructor;
	}
	
	private String courseName;
	private String courseId;
	private String instructor;
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
}
