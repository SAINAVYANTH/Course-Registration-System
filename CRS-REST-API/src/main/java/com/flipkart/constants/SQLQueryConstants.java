/**
 * 
 */
package com.flipkart.constants;

/**
 * @author Rohit
 *
 */
public class SQLQueryConstants {
	public static final String DROP_COURSE = "delete from registration where courseid = ? AND studentid = ?;";
	
	public static final String ADD_COURSE="insert into registration (courseid, studentid, grade) values ( ? , ?, ? )";
	public static final String IS_REGISTERED=" select courseid from registration where courseid=? and studentid=? ";
	public static final String NUMBER_OF_REGISTERED_COURSES=" select studentid from registration where studentid = ? ";
	
	//********ADMIN DAO*********//
	public static final String SAVE_ADMIN = "insert into admins values(?,?,?,?,?)";
	public static final String GET_ADMIN = "select * from admins where id=?";
	
	//*******COURSE DAO**********//
	public static final String ADD_NEW_COURSE = "insert into courses values(?,?,?)";
	public static final String GET_COURSE_DETAILS = "select * from courses where courseid=?";
	public static final String REMOVE_COURSE = "delete from courses where courseid=?";
	public static final String GET_COURSE_LIST =  "select * from courses";
	public static final String UPDATE_COURSE_DETAILS = "update courses set instructor=?  where courseid=?";
	public static final String GET_TEACHING_COURSES = "select * from courses where instructor=?";
	
	//*******CREDENTIALS DAO*******//
	public static final String SAVE_CREDENTIALS = "insert into credentials values(?,?,?)";
	public static final String VERIFY_CREDENTIALS ="select * from credentials where username=?";
	public static final String UPDATE_CREDENTIALS ="update credentials set password=? where username=?";
	
	//*******PROFESSOR DAO********//
	public static final String ADD_PROFESSOR ="insert into professors values(?,?,?,?,?,?,?)";
	public static final String REMOVE_PROFESSOR = "select * from professors where id=?";
	public static final String GET_PROFESSOR_DETIALS ="select * from professors where id=?";
	
	//*******REGISTRATION DAO********//	
	public static final String SAVE_NEW_REG ="insert into registration values(?,?,?)";
	public static final String REMOVE_REG = "delete from registration where courseid = ? AND studentid = ?;";
	public static final String GET_GRADES = "select courseid, grade from registration where studentid = ?;";
	public static final String ADD_GRADE ="update registration set grade=? where courseid=? AND studentid=?";
	public static final String GET_STUDENT_CNT ="select count(*) from registration where courseid=?";
	public static final String VIEW_ENROLL_STUDENTS ="select studentid from registration where courseid=?";
	public static final String VIEW_REGIS_COURSES ="select courseid from registration where studentid=?";
	public static final String CLEAR_STUDENT_COURSES ="delete from registration where studentid = ?";
	
	//*******STUDENT DAO********//	
	public static final String ADD_STUDENT ="insert into students values(?,?,?,?,?,?,?,?,?)";
	public static final String GET_STUDENT_DETAILS ="select * from students where id=?";
	public static final String DEL_STUDENT ="delete from students where id = ?";
	public static final String CH_STUDENT_VERIFICATION = "update students set verified=? where id=?";
	public static final String GET_STUDENT_VERIFICATION = "select verified from students where id=?";
	
	//*******Notification DAO********//
	public static final String GET_ALL_NOTIFICATIONS = "select * from notification where studentid=?";
	public static final String GET_UNREAD_NOTIFICATIONS = "select * from notification where studentid=? AND unread=?";
	public static final String ADD_NOTIFICATION = "insert into notification values(?,?,?,?)";
	public static final String MARK_AS_READ = "update notification set unread=? where studentid=? AND message=? AND extras=?";
	
	//*******SESSION DAO********//
	public static final String ADD_SESSION = "insert into session values(?,?,?)";
	public static final String GET_SESSION = "select * from session where sessionid=?";
	public static final String REMOVE_SESSION = "delete from session where sessionid=?";
	public static final String CLEAR_USER_SESSION = "delete from session where userid=?";
}