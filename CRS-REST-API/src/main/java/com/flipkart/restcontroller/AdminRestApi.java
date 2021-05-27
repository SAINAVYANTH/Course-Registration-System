package com.flipkart.restcontroller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.exception.InvalidCourseIdException;
import com.flipkart.exception.InvalidProfessorIdException;
import com.flipkart.exception.InvalidStudentIdException;
import com.flipkart.service.AdminImpl;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.SessionImpl;
import com.flipkart.service.SessionInterface;
import com.flipkart.utils.CourseUtils;


@Path("/admin")
public class AdminRestApi {
	
	@GET
	@Path("/{sessionid}/viewCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewCourses(@PathParam("sessionid") String sessionid){
		return CourseUtils.getCourseList();
	}
	
	@POST
	@Path("/{sessionid}/addCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(@PathParam("sessionid") String sessionid, Course newCourse) {
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			AdminInterface admin = AdminImpl.getInstance();
			admin.addCourse(newCourse);
			return Response.status(200).entity("Course successfully added!").build();
		}
		else {
			return Response.status(401).entity("Session expired! Login once again for a new session id").build();
		}
	}
	
	@POST
	@Path("/{sessionid}/removeCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeCourse(@PathParam("sessionid") String sessionid, String courseid) {
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			AdminInterface admin = AdminImpl.getInstance();
			try {
				admin.removeCourse(courseid);
			}
			catch(InvalidCourseIdException ex) {
				Response.status(400).entity("Invalid Course Id!").build();
			}
			return Response.status(200).entity("Course successfully removed!").build();
		}
		else {
			return Response.status(401).entity("Session expired! Login once again for a new session id").build();
		}
	}
	
	@POST
	@Path("/{sessionid}/addProfessor")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProfessor(@PathParam("sessionid") String sessionid, Professor newProf) {
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			AdminInterface admin = AdminImpl.getInstance();
			admin.addNewProfessor(newProf);
			return Response.status(200).entity("Professor successfully added!").build();
		}
		else {
			return Response.status(401).entity("Session expired! Login once again for a new session id").build();
		}
	}
	
	@POST
	@Path("/{sessionid}/removeProfessor")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeProfessor(@PathParam("sessionid") String sessionid, String profid) {
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			AdminInterface admin = AdminImpl.getInstance();
			try {
				admin.removeProfessor(profid);
			}
			catch(InvalidProfessorIdException ex) {
				Response.status(400).entity("Invalid Professor Id!").build();
			}
			return Response.status(200).entity("Professor successfully removed!").build();
		}
		else {
			return Response.status(401).entity("Session expired! Login once again for a new session id").build();
		}
	}
	
	@POST
	@Path("/{sessionid}/removeStudent")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeStudent(@PathParam("sessionid") String sessionid, String studentid) {
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			AdminInterface admin = AdminImpl.getInstance();
			try {
				admin.removeStudent(studentid);
			}
			catch(InvalidStudentIdException ex) {
				Response.status(400).entity("Invalid Student Id!").build();
			}
			return Response.status(200).entity("Student successfully removed!").build();
		}
		else {
			return Response.status(401).entity("Session expired! Login once again for a new session id").build();
		}
	}
	
	@POST
	@Path("/{sessionid}/verifyStudent")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response verifyStudent(@PathParam("sessionid") String sessionid, String studentid) {
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			AdminInterface admin = AdminImpl.getInstance();
			try {
				admin.verifyStudentRegistration(studentid);
			}
			catch(InvalidStudentIdException ex) {
				Response.status(400).entity("Invalid Student Id!").build();
			}
			return Response.status(200).entity("Student profile successfully verified!").build();
		}
		else {
			return Response.status(401).entity("Session expired! Login once again for a new session id").build();
		}
	}
}
