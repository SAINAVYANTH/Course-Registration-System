package com.flipkart.client;

import java.sql.Date;

import org.apache.log4j.Logger;

import com.flipkart.bean.Student;
import com.flipkart.bean.UserLogin;
import com.flipkart.utils.LoginUtils;
import com.flipkart.client.ProfessorCrsMenu;
import com.flipkart.constants.Status;
import com.flipkart.constants.UserRole;
import com.flipkart.input.IO;
import com.flipkart.service.AdminImpl;
import com.flipkart.service.AdminInterface;

public class CrsApplication {
	
	private static Logger logger = Logger.getLogger(CrsApplication.class);
	private static IO io = IO.getInstance();
	
	public static void main(String[] args) {
		CrsApplication client = new CrsApplication();
		client.MainMenu();
		IO io = IO.getInstance();
		int n=1;
		do {
			n = io.input.nextInt();
			io.input.nextLine();
			if (n==1) {
				client.login();
			}
			else if(n==2) {
				client.registerNew();
			}
			else if (n==3) {
				client.updatepassword();
			}
			else if (n==4)
				break;
			client.MainMenu();
		}while(n!=3);
		io.input.close();
	}
	
	private void login() {
		logger.info("\n----------Login-----------");
		logger.info("Enter user ID");
		String userid = io.input.nextLine();
		
		logger.info("Enter your password");
		String userpass = io.input.nextLine();
		
		UserLogin user = LoginUtils.Login(userid,userpass); 
		if (user.getUserId().length()==0) {
			logger.info("username or password is incorrect");
			MainMenu();
		}
		else {
			UserRole role = user.getRole();
			if(role.equals(UserRole.STUDENT)) {
				StudentCrsMenu smc= new StudentCrsMenu();
				smc.create_menu(user.getUserId());
			}
			if(role.equals(UserRole.PROFESSOR)) {
				ProfessorCrsMenu clientprof = new ProfessorCrsMenu(user.getUserId());
				clientprof.ProfMenu();
			}
			if(role.equals(UserRole.ADMIN)) {
				AdminCrsMenu clientadmin = new AdminCrsMenu();
				clientadmin.AdminMenu();

			}
		}
	}
		
	private void updatepassword() {
		logger.info("\n----------Password update-----------");
		logger.info("Enter user ID");
		String userid = io.input.nextLine();
		
		logger.info("Enter your old new password");
		String userpass = io.input.nextLine();
		if(LoginUtils.Login(userid,userpass).getUserId().length()!=0){
			String userpass1,userpass2;
			do {
				logger.info("Enter your new password");
				userpass1 = io.input.nextLine();
				
				logger.info("Confirm your new password");
				userpass2 = io.input.nextLine();
				
			}while(userpass1.equals(userpass2)==false || (userpass1.length()==0));
			
			userpass=userpass1;
			if( LoginUtils.updatePassword(userid,userpass) == Status.SUCCESS){
				logger.info("Successfully updated Passeword");
			}
			MainMenu();
		}
		else {
			
		}
	}
	
	private void registerNew() {
		AdminInterface admininterface = AdminImpl.getInstance();
		logger.info("\n----------New Student Registration-----------");
		Student student = new Student(null, null, null, null, null, null, null, null);
		
		logger.info("Enter the name");
		student.setName(io.input.nextLine());
		logger.info("Enter the id");
		student.setId(io.input.nextLine());
		logger.info("Enter the dob");
		student.setDob(new Date(12,3,2000));
		logger.info("Enter the email");
		student.setEmail(io.input.nextLine());
		logger.info("Enter the address");
		student.setAddress(io.input.nextLine());
		logger.info("Enter the department");
		student.setDepartment(io.input.nextLine());
		logger.info("Enter the Roll number");
		student.setRollNo(io.input.nextLine());
		logger.info("Enter the year of joining");
		student.setYearOfJoining(io.input.nextLine());
		
		admininterface.addNewStudent(student);
	}
	
	public void MainMenu() {
		logger.info("\n----------Welcome! CRS Main Menu-----------");
		logger.info("Press 1 to login");
		logger.info("Press 2 for new student registration");
		logger.info("Press 3 to Update Passsword");
		logger.info("Press 4 to Exit ");
	}
}
