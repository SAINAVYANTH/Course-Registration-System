/**
 * 
 */
package com.flipkart.client;

import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constants.Grade;
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
	
	private static Logger logger = Logger.getLogger(ProfessorCrsMenu.class);
	private static IO io = IO.getInstance();
	
	public ProfessorCrsMenu(String professorId) {
		super();
		this.professorId = professorId;
	}
	private String professorId;
	
	
	
	public void ProfMenu() {
		logger.info("\n----------Professor Menu-----------");
		logger.info("Press 1 to view all courses");
		logger.info("Press 2 to teach course");
		logger.info("Press 3 to view teaching courses");
		logger.info("Press 4 to view enrolled students");
		logger.info("Press 5 to give grades");
		logger.info("Press 6 to exit");
		profchoice();
		
	}
	public void profchoice() {
		int n;
		ProfessorInterface profinterface = ProfessorImpl.getInstance();
		do{
			n = io.input.nextInt();
			io.input.nextLine();
			if(n==1) {
				logger.info("\n----------Displaying all Courses-----------");
				List<Course> courses = CourseUtils.getCourseList();
				for(Course each:courses) {
					logger.info("Course Id: " + each.getCourseId() + "  Course Name: " + each.getCourseName() + "  Instructor: " + each.getInstructor());
				}
			}
			else if (n==2) {
				logger.info("\n----------Teach Course-----------");
				logger.info("Enter the id");
				String cid = io.input.nextLine();
				profinterface.teachCourse(professorId, cid);
			}
			else if (n==3) {
				logger.info("\n----------Displaying Teaching Courses-----------");
				List<Course> courses = profinterface.viewTeachingCourses(professorId);
				for(Course each:courses) {
					logger.info("Course Id: " + each.getCourseId() + "  Course Name: " + each.getCourseName());
				}
			}
			else if (n==4) {
				logger.info("\n----------Display students enrolld for a course-----------");
				logger.info("Enter the course id");
				String cid = io.input.nextLine();
				try {
					List<Student> students = profinterface.viewEnrolledStudents(professorId, cid);
					for(Student each: students) {
						logger.info("Student Id: "+ each.getId() + "  Student Name: "+ each.getName());
					}
				} catch (InvalidCourseIdException e) {
					e.printStackTrace();
				}
			}
			else if (n==5) {
				logger.info("\n----------Give Grades-----------");
				logger.info("Enter the course id");
				String cid = io.input.nextLine();
				logger.info("Enter the grades. Enter END to stop giving grades");
				Hashtable<String, Grade> grade= new Hashtable<String, Grade>();
				while(true) {
					logger.info("Enter Student id");
					String sid = io.input.nextLine();
					if(sid.equals("END")) {
						break;
					}
					logger.info("Enter the grade");
					String sgrade = io.input.nextLine();
					if(sgrade.equals("END")) {
						break;
					}
					grade.put(sid, Grade.valueOf(sgrade));
					try {
						profinterface.giveGrades(cid,cid,grade);
					} catch (InvalidGradeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else if (n==6) {
				return;
			}
			else {
				logger.info("Please enter a valid choice. Reurning to main menu.");
			}
				
			ProfMenu();
		}while(n!=5);
	}
	
}
