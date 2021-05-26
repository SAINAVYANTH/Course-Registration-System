/**
 * 
 */
package com.flipkart.client;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.Credit;
import com.flipkart.bean.Debit;
import com.flipkart.bean.Payment;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Scholarship;
import com.flipkart.bean.Upi;
import com.flipkart.constants.Grade;
import com.flipkart.constants.PaymentModes;
import com.flipkart.constants.Status;
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
		while(true) {
			logger.info("\n----------Student Menu-----------");
			logger.info("Press 1 to view all courses");
			logger.info("Press 2 to add a course");
			logger.info("Press 3 to drop a course");
			logger.info("Press 4 to view registered courses");
			logger.info("Press 5 to view grades (Report Card)");
			logger.info("Press 6 to pay fees");
			logger.info("Press 7 to Logout");
			
			int choice=io.input.nextInt();
			io.input.nextLine();
			switch(choice) {
				case 1:
					viewAllCourses();
					break;
				case 2:
					addCourse(student_id);
					break;
				case 3:
					dropCourse(student_id);
					break;
				case 4: 
					viewRegisteredCourses(student_id);
					break;
				case 5:
					getGrades(student_id);
					break;
				case 6: 
					payFees(student_id);
					break;
				case 7: 
					logger.info("Logged Out Successfully!\n");
					return;
					
			}
		}
	}
	
	private void viewAllCourses() {
		logger.info("\n----------Displaying All Courses-----------");
		List<Course> courses = CourseUtils.getCourseList();
		for(Course each:courses) {
			logger.info("Course Id: " + each.getCourseId() + "  Course Name: " + each.getCourseName() + "  Instructor: " + each.getInstructor());
		}
	}
	
	private void getGrades(String student_id) {
		logger.info("\n----------Report Card-----------");
		try {
			ReportCard report = studentInterface.viewReportCard(student_id);
			logger.info("Viewing grades:");
			Hashtable<String, Grade> grades = report.getGrades();
			Enumeration<String> e = grades.keys();
			while(e.hasMoreElements()) {
				String courseID = e.nextElement();
	            Grade grade = grades.get(courseID);
	            logger.info("Course Id: " + courseID + "\t" + "Grade: " + grade.toString());
			}
		}catch(GradesNotGivenException ex) {
			logger.info("The grading of your courses is not complete yet.");
		}catch(InvalidStudentIdException ex) {
			logger.info("Invalid Id");
		}
	}

	private void addCourse(String student_id) {
		logger.info("\n----------Add Course-----------");
		try
		{
			logger.info("Enter Course Code");
			String courseCode = io.input.next();
			if(studentInterface.addCourse(student_id,courseCode ) == Status.SUCCESS)
			{
				logger.info("Successfully Registered for course: "+courseCode+"\n");
			}
			else
			{
				logger.info("You have already registered for this course. Operation Failed \n");
			}
		}
		catch(InvalidCourseIdException | RegistrationFailureException ex)
		{
			logger.info(ex.getMessage());
		}
		
	}
	
	private void payFees(String student_id) {
		logger.info("\n----------Displaying Registered Courses-----------");
		logger.info("Select payment mode");
		PaymentModes mode = PaymentModes.valueOf(io.input.nextLine());
		logger.info("Enter amount");
		String amt = io.input.nextLine();
		String modeid = "";
		Payment details = null;
		if(mode == PaymentModes.CREDIT) {
			logger.info("Enter the credit card number");
			modeid = io.input.nextLine();
			details = new Credit(amt,Status.SUCCESS,modeid);
		}
		else if(mode == PaymentModes.DEBIT) {
			logger.info("Enter the debit card numbe");
			modeid = io.input.nextLine();
			details = new Debit(amt,Status.SUCCESS,modeid);
		}
		else if(mode == PaymentModes.SCHOLARSHIP) {
			logger.info("Enter the scholarship id");
			modeid = io.input.nextLine();
			details = new Scholarship(amt,Status.SUCCESS,modeid);
		}
		else if(mode == PaymentModes.UPI) {
			logger.info("Enter the upi id");
			modeid = io.input.nextLine();
			details = new Upi(amt,Status.SUCCESS,modeid);
		}
		studentInterface.payFee(student_id, details);
	}

	private void viewRegisteredCourses(String student_id) {
		logger.info("\n----------Displaying Registered Courses-----------");
		try {
			List<Course> course_list= studentInterface.viewRegisteredCourses(student_id);
			logger.info("Showing All Courses for student_id "+student_id);
			course_list.forEach((key)->logger.info("Course ID : " + key.getCourseId()));
		}
		catch(SQLException e) {
			logger.info(e.getMessage());
		}
		
	}

	private void dropCourse(String student_id) {
		logger.info("\n----------Drop Course-----------");
		logger.info("Enter Course Code");
		String courseCode = io.input.next();
		try
		{
			Status status=studentInterface.dropCourse(student_id, courseCode);
			if(status.equals(Status.SUCCESS)) {
				logger.info("Course Drop Successful");
			}
			else {
				logger.info("You have not registered for this course");
			}
			
			
		}
		catch(InvalidCourseIdException e)
		{
			logger.info(e.getMessage());
		} 
		catch (SQLException e) 
		{

			logger.info(e.getMessage());
		}
		
	}


}