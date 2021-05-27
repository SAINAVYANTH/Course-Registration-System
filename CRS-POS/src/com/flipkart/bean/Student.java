package com.flipkart.bean;

import java.sql.Date;

public class Student extends PersonalDetails{
	
	public Student(String id, String name, Date dob, String email, String address, String rollNo, String department,
			String yearOfJoining) {
		super(id, name, dob, email, address);
		this.rollNo = rollNo;
		this.department = department;
		this.yearOfJoining = yearOfJoining;
	}
	
	private String rollNo;
	private String department;
	private String yearOfJoining;
	
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getYearOfJoining() {
		return yearOfJoining;
	}
	public void setYearOfJoining(String yoj) {
		this.yearOfJoining = yoj;
	}
}
