/**
 * 
 */
package com.flipkart.client;

import java.util.Scanner;

/**
 * @author psnav
 *
 */
public class clientStudent {
	public void StudentMenu() {
		System.out.println("Press 1 for Semester Registration");
		System.out.println("Press 2 to add course");
		System.out.println("Press 3 to drop course");
		System.out.println("Press 4 to view courses");//missing function in interface
		System.out.println("Press 5 to view regisered courses");
		System.out.println("Press 6 to view report card");
		System.out.println("Press 7 to exit");
		
		studentchoice();
	}
	public void studentchoice() {
		Scanner sc = new Scanner(System.in);
		int n;
		do{
			n = sc.nextInt();
			if (n==1) {
				
			}
			else if (n==2) {
				
			}
			else if (n==3) {
				
			}
			else if (n==4) {
				
			}
			else if (n==5) {
				
			}
			else if (n==6) {
				
			}
			else if (n==7) {
				
			}
			else {
				System.out.println("Please enter a valid choice. Reurning to main menu.");
			}
				
			StudentMenu();
		}while(n!=7);
		sc.close();
	}
}
