package com.flipkart.restcontroller;

import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationConfig extends ResourceConfig{
	public ApplicationConfig() {
		register(AdminRestApi.class);	
		register(CrsRestApi.class);	
		register(ProfessorRestApi.class);
		register(StudentRestApi.class);
	}
}
