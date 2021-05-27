/**
 * 
 */
package com.flipkart.client;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;
import com.flipkart.bean.Professor;
import com.flipkart.exception.InvalidCourseIdException;
import com.flipkart.exception.InvalidProfessorIdException;
import com.flipkart.exception.InvalidStudentIdException;
import com.flipkart.input.IO;
import com.flipkart.service.AdminImpl;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.NotificationImpl;
import com.flipkart.service.NotificationInterface;
import com.flipkart.utils.CourseUtils;

/**
 * @author psnav
 *
 */
public class AdminCrsMenu {

	private static IO io = IO.getInstance();
	
	public void AdminMenu(String adminId) {
		System.out.println("\n----------Admin Menu-----------");
		System.out.println("Press 1 to view all courses");
		System.out.println("Press 2 to add course");
		System.out.println("Press 3 to remove course");
		System.out.println("Press 4 to add new professor");
		System.out.println("Press 5 to verify a student registration");
		System.out.println("Press 6 to remove student");
		System.out.println("Press 7 to remove professor");
		System.out.println("Press 8 to exit");
		
		adminchoice(adminId);
	}
	@SuppressWarnings("deprecation")
	public void adminchoice(String adminId) {
		int n;
		AdminInterface admininterface = AdminImpl.getInstance();
		Course course = new Course("","","");
		do{
			n = io.input.nextInt();
			io.input.nextLine();
			if (n==1) {
				List<Course> courses = CourseUtils.getCourseList();
				System.out.println("\n----------Displaying all courses-----------");
				for(Course each:courses) {
					System.out.println("Course Id: " + each.getCourseId() + "  Course Name: " + each.getCourseName() + "  Instructor: " + each.getInstructor());
				}
			}
			else if (n==2) {
				System.out.println("\n----------Add New Course-----------");
				System.out.println("Enter the course ID");
				String inp = io.input.nextLine();
				course.setCourseId(inp);
				System.out.println("Enter the course name");
				inp = io.input.nextLine();
				course.setCourseName(inp);
				course.setInstructor("Instructor NA");
				admininterface.addCourse(course);
				System.out.println("Successfully added course");
			}
			else if (n==3) {
				System.out.println("\n----------Remove Course-----------");
				System.out.println("Enter the course ID");
				course.setCourseId(io.input.nextLine());
				try {
					admininterface.removeCourse(course.getCourseId());
					System.out.println("Successfully removed course");
				} catch (InvalidCourseIdException e) {
					System.out.println("Invalid Course id. Returning to menu");
				}
			}
			else if (n==4) {
					System.out.println("\n----------Add New Professor-----------");
					Professor professor = new Professor(null, null, null, null, null, null, null);
				
					System.out.println("Enter the name");
					professor.setName(io.input.nextLine());
					System.out.println("Enter the id");
					professor.setId(io.input.nextLine());
					System.out.println("Enter the dob");
					io.input.nextLine();
					professor.setDob(new Date(12,3,2000) );
					System.out.println("Enter the email");
					professor.setEmail(io.input.nextLine());
					System.out.println("Enter the address");
					professor.setAddress(io.input.nextLine());
					System.out.println("Enter the department");
					professor.setDepartment(io.input.nextLine());
					System.out.println("Enter the designation");
					professor.setDesignation(io.input.nextLine());
					System.out.println(professor.getName());
					admininterface.addNewProfessor(professor);
					System.out.println("Successfully added professor");
			}
			else if (n==5) {
				System.out.println("\n----------Verify Student Registration-----------");
				System.out.println("Enter the student id");
				String id = io.input.nextLine();
				try {
					admininterface.verifyStudentRegistration(id);
					NotificationInterface notifications = NotificationImpl.getInstance();
					notifications.addNotification(new Notification(id, "Registration verified by: " + adminId, "timestamp: " + LocalTime.now().toString()));
					System.out.println("Successfully verified student profile");
				}catch(InvalidStudentIdException ex) {
					System.out.println("Invalid student id " + id + ". Returning to menu");
				}
			}
				
			else if (n==6) {
				System.out.println("\n----------Remove Student-----------");
				System.out.println("Enter the id");
				String id = io.input.nextLine();
				try {
					admininterface.removeStudent(id);
					System.out.println("Successfully removed student");
				} catch (InvalidStudentIdException e) {
					System.out.println("Invalid student id " + id + ". Returning to menu");
				}
			}
			else if (n==7) {
				System.out.println("\n----------Remove Professor-----------");
				System.out.println("Enter the id");
				String id = io.input.nextLine();
				try {
					admininterface.removeProfessor(id);
					System.out.println("Successfully removed professor");
				} catch (InvalidProfessorIdException e) {
					System.out.println("Invalid professor id " + id + ". Returning to menu");
				}
			}
			else if (n==8) {
				break;
			}
			else {
				System.out.println("Please enter a valid choice. Returning to main menu.");
			}
			AdminMenu(adminId);
		}while(n!=8);
		System.out.println("Loggin out! Returning to the main menu.");
		return;
	}
}
