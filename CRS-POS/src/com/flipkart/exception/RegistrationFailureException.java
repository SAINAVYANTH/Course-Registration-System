package com.flipkart.exception;

import com.flipkart.bean.CourseRegistration;

public class RegistrationFailureException extends Exception{
	public RegistrationFailureException(String exception, CourseRegistration registrationDetails) {
		super();
		this.exception = exception;
		this.registrationDetails = registrationDetails;
	}
	
	public RegistrationFailureException(String exception) {
		super();
		this.exception = exception;
		this.registrationDetails = null;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String exception;
	private CourseRegistration registrationDetails;
	
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public CourseRegistration getRegistrationDetails() {
		return registrationDetails;
	}
	public void setRegistrationDetails(CourseRegistration registrationDetails) {
		this.registrationDetails = registrationDetails;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
