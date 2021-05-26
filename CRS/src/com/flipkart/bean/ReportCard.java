package com.flipkart.bean;

import java.util.Hashtable;

import com.flipkart.constants.Grade;

public class ReportCard {
	
	public ReportCard(String studentId, Hashtable<String, Grade> grades) {
		super();
		this.studentId = studentId;
		this.grades = grades;
	}
	
	private String studentId;
	private String semester;
	private Hashtable<String, Grade> grades;
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public Hashtable<String, Grade> getGrades() {
		return grades;
	}
	public void setGrades(Hashtable<String, Grade> grades) {
		this.grades = grades;
	}
}
