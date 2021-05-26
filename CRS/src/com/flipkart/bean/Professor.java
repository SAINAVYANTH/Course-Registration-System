package com.flipkart.bean;

import java.sql.Date;

public class Professor extends PersonalDetails{
	
	public Professor( String id, String name, Date dob, String email, String address,
			String department, String designation) {
		super(id, name, dob, email, address);
		this.department = department;
		this.designation = designation;
	}
	
	private String department;
	private String designation;
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}

}
