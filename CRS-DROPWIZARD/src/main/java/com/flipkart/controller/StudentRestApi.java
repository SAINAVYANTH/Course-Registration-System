package com.flipkart.controller;

import java.sql.SQLException;
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
import com.flipkart.bean.CourseRegistration;
import com.flipkart.bean.Credit;
import com.flipkart.bean.Debit;
import com.flipkart.bean.Notification;
import com.flipkart.bean.Upi;
import com.flipkart.bean.Scholarship;
import com.flipkart.constants.StatusConstants;
import com.flipkart.exception.GradesNotGivenException;
import com.flipkart.exception.InvalidCourseIdException;
import com.flipkart.exception.InvalidStudentIdException;
import com.flipkart.exception.RegistrationFailureException;
import com.flipkart.service.NotificationImpl;
import com.flipkart.service.NotificationInterface;
import com.flipkart.service.SessionImpl;
import com.flipkart.service.SessionInterface;
import com.flipkart.service.StudentImpl;
import com.flipkart.service.StudentInterface;
import com.flipkart.utils.CourseUtils;


@Path("/student")
public class StudentRestApi {

	@GET
	@Path("/{sessionid}/viewCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewCourses(@PathParam("sessionid") String sessionid){
		return CourseUtils.getCourseList();
	}
	
	@POST
	@Path("/{sessionid}/courseRegistration")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response courseRegistration(@PathParam("sessionid") String sessionid ,CourseRegistration details) {
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			String id = session.getUserFromSession(sessionid);
			StudentInterface student = StudentImpl.getInstance();
			try {
				if(student.getVerificationStatus(id)) {
					try {
						student.semesterRegistration(id, details);
						return Response.status(200).entity("Semester Registration success!").build();
					}
					catch(RegistrationFailureException ex) {
						return Response.status(400).entity("Registration Failure! " + ex.getException()).build();
					}
				}
				else {
					return Response.status(200).entity("Your profile verification is still pending!").build();
				}
			}
			catch(InvalidStudentIdException ex) {
				return Response.status(400).entity("Invalid credentials").build();
			}
		}
		else {
			return Response.status(401).entity("Session expired! Login once again to get new session id.").build();
		}
	}
	
	@POST
	@Path("/{sessionid}/addCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(@PathParam("sessionid") String sessionid ,String courseId) {
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			String id = session.getUserFromSession(sessionid);
			StudentInterface student = StudentImpl.getInstance();
			try {
				if(student.getVerificationStatus(id)) {
					try {
						student.addCourse(id, courseId);
						return Response.status(200).entity("Successfully added the course!!").build();
					}
					catch(RegistrationFailureException | InvalidCourseIdException ex) {
						return Response.status(400).entity("Registration Failure!").build();
					}
				}
				else {
					return Response.status(200).entity("Your profile verification is still pending!").build();
				}
			}
			catch(InvalidStudentIdException ex) {
				return Response.status(400).entity("Invalid credentials").build();
			}
		}
		else {
			return Response.status(401).entity("Session expired! Login once again to get new session id.").build();
		}
	}
	
	@POST
	@Path("/{sessionid}/dropCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dropCourse(@PathParam("sessionid") String sessionid ,String courseId) {
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			String id = session.getUserFromSession(sessionid);
			StudentInterface student = StudentImpl.getInstance();
			try {
				if(student.getVerificationStatus(id)) {
					try {
						student.dropCourse(id, courseId);
						return Response.status(200).entity("Successfully dropped the course!!").build();
					}
					catch(SQLException | InvalidCourseIdException ex) {
						return Response.status(400).entity("Failed to drop the course!").build();
					}
				}
				else {
					return Response.status(200).entity("Your profile verification is still pending!").build();
				}
			}
			catch(InvalidStudentIdException ex) {
				return Response.status(400).entity("Invalid credentials").build();
			}
		}
		else {
			return Response.status(401).entity("Session expired! Login once again to get new session id.").build();
		}
	}
	
	@GET
	@Path("/{sessionid}/viewRegisteredCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewRegistredCourses(@PathParam("sessionid") String sessionid){
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			String id = session.getUserFromSession(sessionid);
			StudentInterface student = StudentImpl.getInstance();
			try {
				if(student.getVerificationStatus(id)) {
					try {
						return Response.status(200).entity(student.viewRegisteredCourses(id)).build();
					}
					catch(SQLException ex) {
						return Response.status(400).entity("Invalid credentials").build();
					}
				}
				else {
					return Response.status(200).entity("Your profile verification is still pending!").build();
				}
			}
			catch(InvalidStudentIdException ex) {
				return Response.status(400).entity("Invalid credentials").build();
			}
		}
		else {
			return Response.status(401).entity("Session expired! Login once again to get new session id.").build();
		}
	}
	
	@GET
	@Path("/{sessionid}/viewReportCard")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewReportCard(@PathParam("sessionid") String sessionid){
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			String id = session.getUserFromSession(sessionid);
			StudentInterface student = StudentImpl.getInstance();
			try {
				if(student.getVerificationStatus(id)) {
					try {
						return Response.status(200).entity(student.viewReportCard(id)).build();
					}
					catch(GradesNotGivenException ex) {
						return Response.status(200).entity("Grading not yet complete").build();
					}
				}
				else {
					return Response.status(200).entity("Your profile verification is still pending!").build();
				}
			}
			catch(InvalidStudentIdException ex) {
				return Response.status(400).entity("Invalid credentials").build();
			}
		}
		else {
			return Response.status(401).entity("Session expired! Login once again to get new session id.").build();
		}
	}
	
	@GET
	@Path("/{sessionid}/viewAllNotifications")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewAllNotifications(@PathParam("sessionid") String sessionid){
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			String id = session.getUserFromSession(sessionid);
			StudentInterface student = StudentImpl.getInstance();
			try {
				if(student.getVerificationStatus(id)) {
					NotificationInterface notification = NotificationImpl.getInstance();
					List<Notification> notifyList = notification.getAllNotifications(id);
					if(notifyList.size() == 0) {
						return Response.status(200).entity("No notifications!").build();
					}
					else {
						return Response.status(200).entity(notifyList).build();
					}
				}
				else {
					return Response.status(200).entity("Your profile verification is still pending!").build();
				}
			}
			catch(InvalidStudentIdException ex) {
				return Response.status(400).entity("Invalid credentials").build();
			}
		}
		else {
			return Response.status(401).entity("Session expired! Login once again to get new session id.").build();
		}
	}
	
	@GET
	@Path("/{sessionid}/viewUnreadNotifications")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewUnreadNotifications(@PathParam("sessionid") String sessionid){
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			String id = session.getUserFromSession(sessionid);
			StudentInterface student = StudentImpl.getInstance();
			try {
				if(student.getVerificationStatus(id)) {
					NotificationInterface notification = NotificationImpl.getInstance();
					List<Notification> notifyList = notification.getUnreadNotifications(id);
					if(notifyList.size() == 0) {
						return Response.status(200).entity("No new notifications for this user!").build();
					}
					else {
						return Response.status(200).entity(notifyList).build();
					}
				}
				else {
					return Response.status(200).entity("Your profile verification is still pending!").build();
				}
			}
			catch(InvalidStudentIdException ex) {
				return Response.status(400).entity("Invalid credentials").build();
			}
		}
		else {
			return Response.status(401).entity("Session expired! Login once again to get new session id.").build();
		}
	}
	
	@GET
	@Path("/{sessionid}/getPaymentOptions")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPaymentsOptions(@PathParam("sessionid") String sessionid) {
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			return Response.status(200).entity("Available methods: Credit, Debit, Scholarship, Upi").build();
		}
		else {
			return Response.status(401).entity("Session expired! Login once again to get new session id.").build();
		}
	}
	
	@POST
	@Path("/{sessionid}/payFeeCredit")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response payFeeCredit(@PathParam("sessionid") String sessionid ,Hashtable<String, String> details) {
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			String id = session.getUserFromSession(sessionid);
			StudentInterface student = StudentImpl.getInstance();
			try {
				if(student.getVerificationStatus(id)) {
					Credit card = new Credit(id,StatusConstants.SUCCESS, details.get("number"));
					student.payFee(id, card);
					return Response.status(200).entity("Successfully paid the fee!!").build();
				}
				else {
					return Response.status(200).entity("Your profile verification is still pending!").build();
				}
			}
			catch(InvalidStudentIdException ex) {
				return Response.status(400).entity("Invalid credentials").build();
			}
		}
		else {
			return Response.status(401).entity("Session expired! Login once again to get new session id.").build();
		}
	}
	
	@POST
	@Path("/{sessionid}/payFeeDebit")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response payFeeDebit(@PathParam("sessionid") String sessionid ,Hashtable<String, String> details) {
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			String id = session.getUserFromSession(sessionid);
			StudentInterface student = StudentImpl.getInstance();
			try {
				if(student.getVerificationStatus(id)) {
					Debit card = new Debit(id,StatusConstants.SUCCESS, details.get("number"));
					student.payFee(id, card);
					return Response.status(200).entity("Successfully paid the fee!!").build();
				}
				else {
					return Response.status(200).entity("Your profile verification is still pending!").build();
				}
			}
			catch(InvalidStudentIdException ex) {
				return Response.status(400).entity("Invalid credentials").build();
			}
		}
		else {
			return Response.status(401).entity("Session expired! Login once again to get new session id.").build();
		}
	}
	
	@POST
	@Path("/{sessionid}/payFeeScholarship")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response payFeeScholarship(@PathParam("sessionid") String sessionid , String schId) {
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			String id = session.getUserFromSession(sessionid);
			StudentInterface student = StudentImpl.getInstance();
			try {
				if(student.getVerificationStatus(id)) {
					Scholarship details = new Scholarship(id,StatusConstants.SUCCESS, schId);
					student.payFee(id, details);
					return Response.status(200).entity("Successfully paid the fee!!").build();
				}
				else {
					return Response.status(200).entity("Your profile verification is still pending!").build();
				}
			}
			catch(InvalidStudentIdException ex) {
				return Response.status(400).entity("Invalid credentials").build();
			}
		}
		else {
			return Response.status(401).entity("Session expired! Login once again to get new session id.").build();
		}
	}
	
	@POST
	@Path("/{sessionid}/payFeeUpi")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response payFeeUpi(@PathParam("sessionid") String sessionid , String upiId) {
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			String id = session.getUserFromSession(sessionid);
			StudentInterface student = StudentImpl.getInstance();
			try {
				if(student.getVerificationStatus(id)) {
					Upi details = new Upi(id,StatusConstants.SUCCESS, upiId);
					student.payFee(id, details);
					return Response.status(200).entity("Successfully paid the fee!!").build();
				}
				else {
					return Response.status(200).entity("Your profile verification is still pending!").build();
				}
			}
			catch(InvalidStudentIdException ex) {
				return Response.status(400).entity("Invalid credentials").build();
			}
		}
		else {
			return Response.status(401).entity("Session expired! Login once again to get new session id.").build();
		}
	}
}
