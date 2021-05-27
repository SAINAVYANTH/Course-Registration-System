/**
 * 
 */
package com.flipkart.client;

import java.util.Hashtable;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constants.GradeConstants;
import com.flipkart.exception.InvalidCourseIdException;
import com.flipkart.exception.InvalidGradeException;
import com.flipkart.input.IO;
import com.flipkart.service.ProfessorImpl;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.utils.CourseUtils;

/**
 * @author psnav
 *
 */
public class ProfessorCrsMenu {
	
	private static IO io = IO.getInstance();
	
	public ProfessorCrsMenu(String professorId) {
		super();
		this.professorId = professorId;
	}
	
	private String professorId;
	
	public void ProfMenu() {
		System.out.println("\n----------Professor Menu-----------");
		System.out.println("Press 1 to view all courses");
		System.out.println("Press 2 to teach course");
		System.out.println("Press 3 to view teaching courses");
		System.out.println("Press 4 to view enrolled students");
		System.out.println("Press 5 to give grades");
		System.out.println("Press 6 to exit");
		profchoice();
		
	}
	
	public void profchoice() {
		int n;
		ProfessorInterface profinterface = ProfessorImpl.getInstance();
		do{
			n = io.input.nextInt();
			io.input.nextLine();
			if(n==1) {
				System.out.println("\n----------Displaying all Courses-----------");
				List<Course> courses = CourseUtils.getCourseList();
				for(Course each:courses) {
					System.out.println("Course Id: " + each.getCourseId() + "  Course Name: " + each.getCourseName() + "  Instructor: " + each.getInstructor());
				}
			}
			else if (n==2) {
				System.out.println("\n----------Teach Course-----------");
				System.out.println("Enter the id");
				String cid = io.input.nextLine();
				profinterface.teachCourse(professorId, cid);
				System.out.println("Success!");
			}
			else if (n==3) {
				System.out.println("\n----------Displaying Teaching Courses-----------");
				List<Course> courses = profinterface.viewTeachingCourses(professorId);
				for(Course each:courses) {
					System.out.println("Course Id: " + each.getCourseId() + "  Course Name: " + each.getCourseName());
				}
			}
			else if (n==4) {
				System.out.println("\n----------Display students enrolld for a course-----------");
				System.out.println("Enter the course id");
				String cid = io.input.nextLine();
				try {
					List<Student> students = profinterface.viewEnrolledStudents(professorId, cid);
					for(Student each: students) {
						System.out.println("Student Id: "+ each.getId() + "  Student Name: "+ each.getName());
					}
				} catch (InvalidCourseIdException e) {
					e.printStackTrace();
				}
			}
			else if (n==5) {
				System.out.println("\n----------Give Grades-----------");
				System.out.println("Enter the course id");
				String cid = io.input.nextLine();
				System.out.println("Enter the grades. Enter END to stop giving grades");
				Hashtable<String, GradeConstants> grade= new Hashtable<String, GradeConstants>();
				while(true) {
					System.out.println("Enter Student id");
					String sid = io.input.nextLine();
					if(sid.equals("END")) {
						break;
					}
					System.out.println("Enter the grade");
					String sgrade = io.input.nextLine();
					if(sgrade.equals("END")) {
						break;
					}
					grade.put(sid, GradeConstants.valueOf(sgrade));
					try {
						profinterface.giveGrades(cid,cid,grade);
					} catch (InvalidGradeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else if (n==6) {
				break;
			}
			else {
				System.out.println("Please enter a valid choice. Reurning to main menu.");
			}
				
			ProfMenu();
		}while(n!=5);
		System.out.println("Logged out! Returning to the main menu");
	}
	
}
