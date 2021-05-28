package com.flipkart.bean;

public class CourseRegistration {
	
	public CourseRegistration() {};
	
	public CourseRegistration(String[] primaryCourses, String[] secondaryCourses) {
		super();
		this.primaryCourses = primaryCourses;
		this.secondaryCourses = secondaryCourses;
	}
	
	private String[] primaryCourses;
	private String[] secondaryCourses;
	private String semester;
	
	public String[] getPrimaryCourses() {
		return primaryCourses;
	}
	public void setPrimaryCourses(String[] primaryCourses) {
		this.primaryCourses = primaryCourses;
	}
	public String[] getSecondaryCourses() {
		return secondaryCourses;
	}
	public void setSecondaryCourses(String[] secondaryCourses) {
		this.secondaryCourses = secondaryCourses;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	
}
