/**
 * 
 */
package com.flipkart.client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseRegistration;
import com.flipkart.bean.Credit;
import com.flipkart.bean.Debit;
import com.flipkart.bean.Payment;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Scholarship;
import com.flipkart.bean.Upi;
import com.flipkart.constants.GradeConstants;
import com.flipkart.constants.StatusConstants;
import com.flipkart.exception.GradesNotGivenException;
import com.flipkart.exception.InvalidCourseIdException;
import com.flipkart.exception.InvalidStudentIdException;
import com.flipkart.exception.RegistrationFailureException;
import com.flipkart.input.IO;
import com.flipkart.service.StudentImpl;
import com.flipkart.service.StudentInterface;
import com.flipkart.utils.CourseUtils;

/**
 * @author Rohit
 *
 */
public class StudentCrsMenu {
	
	private static Logger logger = Logger.getLogger(StudentCrsMenu.class);
	private static IO io = IO.getInstance();

	StudentInterface studentInterface = StudentImpl.getInstance();
	
	/** Creates the menu for Students
	 * @param String student_id
	 */
	public void create_menu(String student_id) {
		try {
			if(studentInterface.getVerificationStatus(student_id)) {
				while(true) {
					System.out.println("\n----------Student Menu-----------");
					System.out.println("Press 1 to view all courses");
					System.out.println("Press 2 for course registration");
					System.out.println("Press 3 to add a course");
					System.out.println("Press 4 to drop a course");
					System.out.println("Press 5 to view registered courses");
					System.out.println("Press 6 to view grades (Report Card)");
					System.out.println("Press 7 to pay fees");
					System.out.println("Press 8 to Logout");
					
					int choice=io.input.nextInt();
					io.input.nextLine();
					switch(choice) {
						case 1:
							viewAllCourses();
							break;
						case 2:
							registerCourses(student_id);
							break;
						case 3:
							addCourse(student_id);
							break;
						case 4:
							dropCourse(student_id);
							break;
						case 5: 
							viewRegisteredCourses(student_id);
							break;
						case 6:
							getGrades(student_id);
							break;
						case 7: 
							payFees(student_id);
							break;
						case 8: 
							System.out.println("Logged Out Successfully!\n");
							return;
							
					}
				}
			}
			else {
				System.out.println("Your profile verification is pending.");
				System.out.println("Returning to main menu");
			}
		}catch(InvalidStudentIdException ex) {
			logger.error("Invalid student id" + student_id);
		}
		return;
	}
	
	private void registerCourses(String studentId) {
		try {
			System.out.println("\n----------Course Registration-----------");
			System.out.println("First provide the primary course choices,");
			List<Course> primary = new ArrayList<Course>();
			System.out.println("Enter primary choice 1: ");
			Course details = CourseUtils.getCourseDetails(io.input.nextLine());
			primary.add(details);
			System.out.println("Enter primary choice 2: ");
			details = CourseUtils.getCourseDetails(io.input.nextLine());
			primary.add(details);
			System.out.println("Enter primary choice 3: ");
			details = CourseUtils.getCourseDetails(io.input.nextLine());
			primary.add(details);
			System.out.println("Enter primary choice 4: ");
			details = CourseUtils.getCourseDetails(io.input.nextLine());
			primary.add(details);
			List<Course> secondary = new ArrayList<Course>();
			System.out.println("\nNow enter the secondary choices,");
			System.out.println("Enter secondary choice 1: ");
			details = CourseUtils.getCourseDetails(io.input.nextLine());
			secondary.add(details);
			System.out.println("Enter seondary choice 2: ");
			details = CourseUtils.getCourseDetails(io.input.nextLine());
			secondary.add(details);
			CourseRegistration registration = new CourseRegistration(primary.toArray(new Course[0]), secondary.toArray(new Course[0]));
			studentInterface.semesterRegistration(studentId, registration);
			System.out.println("Successfully Completed registration");
		}
		catch(RegistrationFailureException ex) {
			logger.error(ex.getException());
		}catch(InvalidCourseIdException ex) {
			logger.error("Invalid course id. Aborting registration.");
		}
	}
	
	private void viewAllCourses() {
		System.out.println("\n----------Displaying All Courses-----------");
		List<Course> courses = CourseUtils.getCourseList();
		for(Course each:courses) {
			System.out.println("Course Id: " + each.getCourseId() + "  Course Name: " + each.getCourseName() + "  Instructor: " + each.getInstructor());
		}
	}
	
	private void getGrades(String student_id) {
		System.out.println("\n----------Report Card-----------");
		try {
			ReportCard report = studentInterface.viewReportCard(student_id);
			System.out.println("Viewing grades:");
			Hashtable<String, GradeConstants> grades = report.getGrades();
			Enumeration<String> e = grades.keys();
			while(e.hasMoreElements()) {
				String courseID = e.nextElement();
	            GradeConstants grade = grades.get(courseID);
	            System.out.println("Course Id: " + courseID + "\t" + "Grade: " + grade.toString());
			}
		}catch(GradesNotGivenException ex) {
			System.out.println("The grading of your courses is not complete yet.");
		}catch(InvalidStudentIdException ex) {
			System.out.println("Invalid Id");
		}
	}

	private void addCourse(String student_id) {
		System.out.println("\n----------Add Course-----------");
		try
		{
			System.out.println("Enter Course Code");
			String courseCode = io.input.next();
			if(studentInterface.addCourse(student_id,courseCode ) == StatusConstants.SUCCESS)
			{
				System.out.println("Successfully Registered for course: "+courseCode+"\n");
			}
			else
			{
				System.out.println("You have already registered for this course. Operation Failed \n");
			}
		}
		catch(InvalidCourseIdException | RegistrationFailureException ex)
		{
			System.out.println(ex.getMessage());
		}	
	}
	
	private boolean verifyCardNumber(String number) {
		if (number.length() == 16) {
			for(char each : number.toCharArray()) {
				if(!Character.isDigit(each)) {
					return false;
				}
			}
			return true;
		}else {
			return false;
		}
	}
	
	private void payFees(String student_id) {
		System.out.println("\n----------Fee Payment-----------");
		System.out.println("Select payment mode:");
		System.out.println("press 1 for credit card");
		System.out.println("press 2 for debit card");
		System.out.println("press 3 for scholarship");
		System.out.println("press 4 for upi");
		int paymentMode = io.input.nextInt();
		io.input.nextLine();
		System.out.println("Enter amount");
		String amt = io.input.nextLine();
		String modeid = "";
		Payment details = null;
		if(paymentMode == 1) {
			System.out.println("Enter the credit card number");
			modeid = io.input.nextLine();
			if(verifyCardNumber(modeid)) {
				details = new Credit(amt,StatusConstants.SUCCESS,modeid);
			}
			else {
				logger.error("Invalid credit card number! Aborting transaction.");
				return;
			}
		}
		else if(paymentMode == 2) {
			System.out.println("Enter the debit card numbe");
			modeid = io.input.nextLine();
			if(verifyCardNumber(modeid)) {
				details = new Debit(amt,StatusConstants.SUCCESS,modeid);
			}
			else {
				logger.error("Invalid debit card number! Aborting transaction.");
				return;
			}
		}
		else if(paymentMode == 3) {
			System.out.println("Enter the scholarship id");
			modeid = io.input.nextLine();
			details = new Scholarship(amt,StatusConstants.SUCCESS,modeid);
		}
		else if(paymentMode == 4) {
			System.out.println("Enter the upi id");
			modeid = io.input.nextLine();
			details = new Upi(amt,StatusConstants.SUCCESS,modeid);
		}
		studentInterface.payFee(student_id, details);
		System.out.println("Payment Notification: Payment Succeeded!");
	}

	private void viewRegisteredCourses(String student_id) {
		System.out.println("\n----------Displaying Registered Courses-----------");
		try {
			List<Course> course_list= studentInterface.viewRegisteredCourses(student_id);
			System.out.println("Showing All Courses for student id "+student_id);
			course_list.forEach((key)->System.out.println("Course ID : " + key.getCourseId()));
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private void dropCourse(String student_id) {
		System.out.println("\n----------Drop Course-----------");
		System.out.println("Enter Course Code");
		String courseCode = io.input.next();
		try
		{
			StatusConstants status=studentInterface.dropCourse(student_id, courseCode);
			if(status.equals(StatusConstants.SUCCESS)) {
				System.out.println("Course Drop Successful");
			}
			else {
				System.out.println("You have not registered for this course");
			}
			
			
		}
		catch(InvalidCourseIdException e)
		{
			System.out.println(e.getMessage());
		} 
		catch (SQLException e) 
		{

			System.out.println(e.getMessage());
		}
		
	}


}