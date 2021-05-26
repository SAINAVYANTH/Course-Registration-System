package com.flipkart.bean;

import com.flipkart.constants.UserRole;

public class UserLogin {
	
	public UserLogin(String userId, String password, UserRole role) {
		this.userId = userId;
		this.password = password;
		this.role = role;
	}
	
	private String userId;
	private String password;
	private UserRole role;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
}
