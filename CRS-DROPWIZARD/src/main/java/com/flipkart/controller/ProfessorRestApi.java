package com.flipkart.controller;

import java.util.Hashtable;
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
import com.flipkart.constants.GradeConstants;
import com.flipkart.exception.InvalidCourseIdException;
import com.flipkart.exception.InvalidGradeException;
import com.flipkart.service.ProfessorImpl;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.SessionImpl;
import com.flipkart.service.SessionInterface;
import com.flipkart.utils.CourseUtils;


@Path("/professor")
public class ProfessorRestApi {
	
	@GET
	@Path("/{sessionid}/viewCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewCourses(@PathParam("sessionid") String sessionid){
		return CourseUtils.getCourseList();
	}
	
	@POST
	@Path("/{sessionid}/teachCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(@PathParam("sessionid") String sessionid, String courseId) {
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			ProfessorInterface professor = ProfessorImpl.getInstance();
			String id = session.getUserFromSession(sessionid);
			professor.teachCourse(id, courseId);
			return Response.status(200).entity("Success! You are now the instructor for!" + courseId).build();
		}
		else {
			return Response.status(401).entity("Session expired! Login once again for a new session id").build();
		}
	}
	
	@GET
	@Path("/{sessionid}/viewTeachingCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewTeachingCourses(@PathParam("sessionid") String sessionid){
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			ProfessorInterface professor = ProfessorImpl.getInstance();
			String id = session.getUserFromSession(sessionid);
			return Response.status(200).entity(professor.viewTeachingCourses(id)).build();
		}
		else {
			return Response.status(401).entity("Session expired! Login once again!").build();
		}
	}
	
	@GET
	@Path("/{sessionid}/viewEnrolledStudents")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewTeachingCourses(@PathParam("sessionid") String sessionid, String courseId){
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			ProfessorInterface professor = ProfessorImpl.getInstance();
			String id = session.getUserFromSession(sessionid);
			try {
				return Response.status(200).entity(professor.viewEnrolledStudents(id, courseId)).build();
			}
			catch (InvalidCourseIdException ex){
				return Response.status(400).entity("Invalid course id!").build();
			}
		}
		else {
			return Response.status(401).entity("Session expired! Login once again!").build();
		}
	}
	
	@POST
	@Path("/{sessionid}/giveGrades/{courseid}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response giveGrades(@PathParam("sessionid") String sessionid, @PathParam("courseid") String courseId, Hashtable<String, GradeConstants> grades) {
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			ProfessorInterface professor = ProfessorImpl.getInstance();
			String id = session.getUserFromSession(sessionid);
			try {
				professor.giveGrades(id, courseId, grades);
				return Response.status(200).entity("Successfully given grades for " + courseId + "!").build();
			}
			catch(InvalidGradeException ex) {
				return Response.status(400).entity("Invalid grades! Try again.").build();
			}
		}
		else {
			return Response.status(401).entity("Session expired! Login once again for a new session id").build();
		}
	}
}
