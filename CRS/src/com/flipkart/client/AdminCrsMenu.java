/**
 * 
 */
package com.flipkart.client;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.InvalidCourseIdException;
import com.flipkart.exception.InvalidProfessorIdException;
import com.flipkart.exception.InvalidStudentIdException;
import com.flipkart.input.IO;
import com.flipkart.service.AdminImpl;
import com.flipkart.service.AdminInterface;
import com.flipkart.utils.CourseUtils;

/**
 * @author psnav
 *
 */
public class AdminCrsMenu {
	
	private static Logger logger = Logger.getLogger(AdminCrsMenu.class);
	private static IO io = IO.getInstance();
	
	public void AdminMenu() {
		logger.info("\n----------Admin Menu-----------");
		logger.info("Press 1 to view all courses");
		logger.info("Press 2 to add course");
		logger.info("Press 3 to remove course");
		logger.info("Press 4 to add new professor");
		logger.info("Press 5 to add new student");
		logger.info("Press 6 to remove student");
		logger.info("Press 7 to remove professor");
		logger.info("Press 8 to exit");
		
		adminchoice();
	}
	@SuppressWarnings("deprecation")
	public void adminchoice() {
		int n;
		AdminInterface admininterface = AdminImpl.getInstance();
		Course course = new Course("","","");
		do{
			n = io.input.nextInt();
			io.input.nextLine();
			if (n==1) {
				List<Course> courses = CourseUtils.getCourseList();
				logger.info("\n----------Displaying all courses-----------");
				for(Course each:courses) {
					logger.info("Course Id: " + each.getCourseId() + "  Course Name: " + each.getCourseName() + "  Instructor: " + each.getInstructor());
				}
			}
			else if (n==2) {
				logger.info("\n----------Add New Course-----------");
				logger.info("Enter the course ID");
				String inp = io.input.nextLine();
				course.setCourseId(inp);
				logger.info("Enter the course name");
				inp = io.input.nextLine();
				course.setCourseName(inp);
				course.setInstructor("Instructor NA");
				admininterface.addCourse(course);
				
			}
			else if (n==3) {
				logger.info("\n----------Remove Course-----------");
				logger.info("Enter the course ID");
				course.setCourseId(io.input.nextLine());
				try {
					admininterface.removeCourse(course.getCourseId());
				} catch (InvalidCourseIdException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (n==4) {
					logger.info("\n----------Add New Professor-----------");
					Professor professor = new Professor(null, null, null, null, null, null, null);
					
					logger.info("Enter the name");
					professor.setName(io.input.nextLine());
					logger.info("Enter the id");
					professor.setId(io.input.nextLine());
					logger.info("Enter the dob");
					io.input.nextLine();
					professor.setDob(new Date(12,3,2000) );
					logger.info("Enter the email");
					professor.setEmail(io.input.nextLine());
					logger.info("Enter the address");
					professor.setAddress(io.input.nextLine());
					logger.info("Enter the department");
					professor.setDepartment(io.input.nextLine());
					logger.info("Enter the designation");
					professor.setDesignation(io.input.nextLine());
					logger.info(professor.getName());
					admininterface.addNewProfessor(professor);
			}
			else if (n==5) {
				logger.info("\n----------Add New Student-----------");
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
				
			else if (n==6) {
				logger.info("\n----------Remove Student-----------");
				logger.info("Enter the id");
				try {
					admininterface.removeStudent(io.input.nextLine());
				} catch (InvalidStudentIdException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (n==7) {
				logger.info("\n----------Remove Professor-----------");
				logger.info("Enter the id");
				try {
					admininterface.removeProfessor(io.input.nextLine());
				} catch (InvalidProfessorIdException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (n==8) {
				return;
			}
			else {
				logger.info("Please enter a valid choice. Reurning to main menu.");
			}
				
			AdminMenu();
		}while(n!=8);
	}
}
