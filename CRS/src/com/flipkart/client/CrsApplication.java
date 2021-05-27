package com.flipkart.client;

import java.sql.Date;

import com.flipkart.bean.Student;
import com.flipkart.bean.UserLogin;
import com.flipkart.utils.LoginUtils;
import com.flipkart.client.ProfessorCrsMenu;
import com.flipkart.constants.StatusConstants;
import com.flipkart.constants.UserRoleConstants;
import com.flipkart.input.IO;
import com.flipkart.service.StudentImpl;
import com.flipkart.service.StudentInterface;

public class CrsApplication {
	
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
			else if (n==4) {
				break;
			}
			client.MainMenu();
		}while(n!=3);
		io.input.close();
		System.out.println("Thanks you for using the CRS!");
	}
	
	private void login() {
		System.out.println("\n----------Login-----------");
		System.out.println("Enter user ID");
		String userid = io.input.nextLine();
		
		System.out.println("Enter your password");
		String userpass = io.input.nextLine();
		
		UserLogin user = LoginUtils.Login(userid,userpass); 
		if (user.getUserId().length()==0) {
			System.out.println("username or password is incorrect");
		}
		else {
			UserRoleConstants role = user.getRole();
			System.out.println("Login success! role : " + role.toString());
			if(role.equals(UserRoleConstants.STUDENT)) {
				StudentCrsMenu smc= new StudentCrsMenu();
				smc.create_menu(user.getUserId());
			}
			else if(role.equals(UserRoleConstants.PROFESSOR)) {
				ProfessorCrsMenu clientprof = new ProfessorCrsMenu(user.getUserId());
				clientprof.ProfMenu();
			}
			else if(role.equals(UserRoleConstants.ADMIN)) {
				AdminCrsMenu clientadmin = new AdminCrsMenu();
				clientadmin.AdminMenu(user.getUserId());

			}
		}
	}
		
	private void updatepassword() {
		System.out.println("\n----------Password update-----------");
		System.out.println("Enter user ID");
		String userid = io.input.nextLine();
		
		System.out.println("Enter your old new password");
		String userpass = io.input.nextLine();
		if(LoginUtils.Login(userid,userpass).getUserId().length()!=0){
			String userpass1,userpass2;
			do {
				System.out.println("Enter your new password");
				userpass1 = io.input.nextLine();
				
				System.out.println("Confirm your new password");
				userpass2 = io.input.nextLine();
				
			}while(userpass1.equals(userpass2)==false || (userpass1.length()==0));
			
			userpass=userpass1;
			if( LoginUtils.updatePassword(userid,userpass) == StatusConstants.SUCCESS){
				System.out.println("Successfully updated Passeword");
			}
			MainMenu();
		}
		else {
			
		}
	}
	
	@SuppressWarnings("deprecation")
	private void registerNew() {
		StudentInterface studentinterface = StudentImpl.getInstance();
		System.out.println("\n----------New Student Registration-----------");
		Student student = new Student(null, null, null, null, null, null, null, null);
		
		System.out.println("Enter the name");
		student.setName(io.input.nextLine());
		System.out.println("Enter the id");
		student.setId(io.input.nextLine());
		System.out.println("Enter the dob");
		io.input.nextLine();
		student.setDob(new Date(12,3,2000));
		System.out.println("Enter the email");
		student.setEmail(io.input.nextLine());
		System.out.println("Enter the address");
		student.setAddress(io.input.nextLine());
		System.out.println("Enter the department");
		student.setDepartment(io.input.nextLine());
		System.out.println("Enter the Roll number");
		student.setRollNo(io.input.nextLine());
		System.out.println("Enter the year of joining");
		student.setYearOfJoining(io.input.nextLine());
		
		studentinterface.addNewStudent(student);
		System.out.println("Successfully registered! Verification Pending!");
	}
	
	public void MainMenu() {
		System.out.println("\n----------Welcome! CRS Main Menu-----------");
		System.out.println("Press 1 to login");
		System.out.println("Press 2 for new student registration");
		System.out.println("Press 3 to Update Passsword");
		System.out.println("Press 4 to Exit ");
	}
}
