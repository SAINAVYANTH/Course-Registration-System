package com.flipkart.client;

import org.apache.log4j.Logger;

import com.flipkart.bean.UserLogin;
import com.flipkart.utils.LoginUtils;
import com.flipkart.client.ProfessorCrsMenu;
import com.flipkart.constants.Status;
import com.flipkart.constants.UserRole;
import com.flipkart.input.IO;

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
			if (n==2) {
				client.updatepassword();
			}
			if (n==3)
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
	
	public void MainMenu() {
		logger.info("\n----------Welcome! CRS Main Menu-----------");
		logger.info("Press 1 to login");
		logger.info("Press 2 to Update Passsword");
		logger.info("Press 3 to Exit ");
	}
}
