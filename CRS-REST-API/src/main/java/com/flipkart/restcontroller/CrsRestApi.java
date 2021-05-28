package com.flipkart.restcontroller;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Student;
import com.flipkart.exception.InvalidCredentialsException;
import com.flipkart.service.SessionImpl;
import com.flipkart.service.SessionInterface;
import com.flipkart.service.StudentImpl;
import com.flipkart.service.StudentInterface;
import com.flipkart.utils.LoginUtils;


@Path("/home")
public class CrsRestApi {
	
	@POST
	@Path("/login/{username}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@PathParam("username") String username, String password) {
		try {
			System.out.println("Recieved" + username + " " + password);
			LoginUtils.Login(username, password);
			SessionInterface session = SessionImpl.getInstance();
			String random = UUID.randomUUID().toString().replace("-", "Q");
			Long expiry = LocalDateTime.now().plusMinutes(30).toEpochSecond(ZoneOffset.UTC);
			session.addSession(username, random.substring(0, 6), (double)expiry);
			return Response.status(200).entity("Login Success! Your session id is, " + random.substring(0, 6) + ". The session id expires in 30 minutes.").build();
		}
		catch(InvalidCredentialsException ex) {
			return Response.status(400).entity("Login Failed! " + ex.getException()).build();
		}
	}
	
	@POST
	@Path("/register")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerNew(Student details) {
		StudentInterface student = StudentImpl.getInstance();
		student.addNewStudent(details);
		return Response.status(200).entity("Successfully registered!").build();
	}
	
	@POST
	@Path("/updatePassword/{sessionid}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePassword(@PathParam("sessionid") String sessionid, String newPassword) {
		SessionInterface session = SessionImpl.getInstance();
		if(session.checkSession(sessionid)) {
			String id = session.getUserFromSession(sessionid);
			LoginUtils.updatePassword(id, newPassword);
			return Response.status(200).entity("Successfully changed the password!").build();
		}
		else {
			return Response.status(401).entity("Session expired! Login once again to get new session id.").build();
		}
	}
	
	@POST
	@Path("/logout/{sessionid}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout(@PathParam("sessionid") String sessionid) {
		SessionInterface session = SessionImpl.getInstance();
		session.removeSession(sessionid);
		return Response.status(200).entity("Successfully logged out!").build();
	}
}
