package com.flipkart.bean;

import java.sql.Date;

public class PersonalDetails {
	
	public PersonalDetails() {};
	
	public PersonalDetails( String id, String name, Date dob, String email, String address) {
		super();
		this.name = name;
		this.id = id;
		this.dob = dob;
		this.email = email;
		this.address = address;
	}
	
	private String name;
	private String id;
	private Date dob;
	private String email;
	private String address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
