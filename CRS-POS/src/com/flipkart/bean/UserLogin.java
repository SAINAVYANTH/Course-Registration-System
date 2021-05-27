package com.flipkart.bean;

import com.flipkart.constants.UserRoleConstants;

public class UserLogin {
	
	public UserLogin(String userId, String password, UserRoleConstants role) {
		this.userId = userId;
		this.password = password;
		this.role = role;
	}
	
	private String userId;
	private String password;
	private UserRoleConstants role;
	
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
	public UserRoleConstants getRole() {
		return role;
	}
	public void setRole(UserRoleConstants role) {
		this.role = role;
	}
}
