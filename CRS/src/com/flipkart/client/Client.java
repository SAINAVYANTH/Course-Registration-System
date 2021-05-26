package com.flipkart.client;

import java.util.Scanner;

import com.flipkart.bean.UserLogin;
import com.flipkart.utils.LoginUtils;
import com.flipkart.client.clientProfessor;
import com.flipkart.constants.Status;
import com.flipkart.constants.UserRole;
public class Client {

	public static void main(String[] args) {
		Client client = new Client();
		client.MainMenu();
		Scanner sc = new Scanner(System.in);
		int n=1;
		do {
			n = sc.nextInt();
			sc.nextLine();
			if (n==1) {
				client.login();
			}
			if (n==2) {
				client.updatepassword();
			}
			if (n==3)
				return;
			client.MainMenu();
		}while(n!=3);
		sc.close();
	}
	
	private void login() {
		System.out.println("\n----------Login-----------");
		System.out.println("Enter user ID");
		Scanner sc = new Scanner(System.in);
		String userid = sc.nextLine();
		
		System.out.println("Enter your password");
		String userpass = sc.nextLine();
		
		UserLogin user = LoginUtils.Login(userid,userpass); // Verifying login details!
		if (user.getUserId().length()==0) {
			System.out.println("username or password is incorrect");
			MainMenu();
		}
		else {
			UserRole role = user.getRole();
			if(role.equals(UserRole.STUDENT)) {
				StudentMenuClient smc= new StudentMenuClient();
				smc.create_menu(user.getUserId());
			}
			if(role.equals(UserRole.PROFESSOR)) {
				clientProfessor clientprof = new clientProfessor(user.getUserId());
				clientprof.ProfMenu();
			}
			if(role.equals(UserRole.ADMIN)) {
				clientAdmin clientadmin = new clientAdmin();
				clientadmin.AdminMenu();

			}
		}
		sc.close();
	}
		
	private void updatepassword() {
		System.out.println("Enter user ID");
		Scanner sc = new Scanner(System.in);
		String userid = sc.nextLine();
		
		System.out.println("Enter your old new password");
		String userpass = sc.nextLine();
		if(LoginUtils.Login(userid,userpass).getUserId().length()!=0){
			String userpass1,userpass2;
			do {
				System.out.println("Enter your new password");
				userpass1 = sc.nextLine();
				
				System.out.println("Confirm your new password");
				userpass2 = sc.nextLine();
				
			}while(userpass1.equals(userpass2)==false || (userpass1.length()==0));
			
			userpass=userpass1;
			if( LoginUtils.updatePassword(userid,userpass) == Status.SUCCESS){
				System.out.println("Successfully updated Passeword");
			}
			MainMenu();
		}
		else {
			
		}
	}
	
	public void MainMenu() {
		System.out.println("----------Welcome-----------");
		System.out.println("Press 1 to login");
		System.out.println("Press 2 to Update Passsword");
		System.out.println("Press 3 to Exit ");
	}
}
