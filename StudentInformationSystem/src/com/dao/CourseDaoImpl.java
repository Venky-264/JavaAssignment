package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.Exceptions.CourseNotFoundException;
import com.Exceptions.InvalidCourseDataException;
import com.Exceptions.TeacherNotFoundException;
import com.dto.StudentsEnrolled;
import com.model.Course;
import com.model.Teacher;
import com.utility.DBConnection;

public class CourseDaoImpl implements CourseDao {

	@Override
	public List<Course> getAllCourses() throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.getDBConn();

		String query = "select * from courses";

		Statement stmt = conn.createStatement();

		ResultSet result = stmt.executeQuery(query);

		List<Course> courses = new ArrayList<>();

		while (result.next()) {
			int id = result.getInt("course_id");
			String courseName = result.getString("course_name");
			int credits = result.getInt("credits");
			int teacher_id = result.getInt("teacher_id");

			Course c = new Course(id, courseName, credits, teacher_id);
			courses.add(c);
		}

		DBConnection.dbClose();
		return courses;
	}

	@Override
	public Course getCourseById(int courseId) throws ClassNotFoundException, SQLException, CourseNotFoundException {

		Connection conn = DBConnection.getDBConn();

		String query = "select * from courses where course_id=?";

		PreparedStatement stmt = conn.prepareStatement(query);
		
		stmt.setInt(1, courseId);

		ResultSet result = stmt.executeQuery();
		
		if(result.next()) {
			int id = result.getInt("course_id");
			String courseName = result.getString("course_name");
			int credits = result.getInt("credits");
			int teacher_id = result.getInt("teacher_id");

			Course course = new Course(id, courseName, credits, teacher_id);
			
			DBConnection.dbClose();
			return course;
		}
		DBConnection.dbClose();
		throw new CourseNotFoundException("Invalid course id");
		
	}
	
	@Override
	public int validateTeacher(int updatedTeacherId) throws ClassNotFoundException, SQLException, TeacherNotFoundException {
		
		Connection conn = DBConnection.getDBConn();
		String query="select * from teacher where teacher_id=?";
		
		PreparedStatement stmt=conn.prepareStatement(query);
		stmt.setInt(1, updatedTeacherId);
		
		ResultSet result = stmt.executeQuery();
		if(result.next()) {
			DBConnection.dbClose();
			return 1;
		}
		else {
			DBConnection.dbClose();
			throw new TeacherNotFoundException("invalid teacher id");
		}
	}
	@Override
	public int updateCourse(int courseIdForUpdation,int updatedCredicts) throws ClassNotFoundException, SQLException, InvalidCourseDataException {
		
		Connection conn = DBConnection.getDBConn();
		String query="update courses set credits=? where course_id=?";
		
		PreparedStatement stmt=conn.prepareStatement(query);
		
		if(updatedCredicts<1)
			throw new InvalidCourseDataException("invalid credits");
		
		stmt.setInt(1, updatedCredicts);
		stmt.setInt(2, courseIdForUpdation);
		
		int update = stmt.executeUpdate();
		DBConnection.dbClose();
		return update;
	}
	
	@Override
	public int assignTeacher(int courseId, int updatedTeacherId)  throws ClassNotFoundException, SQLException{
		Connection conn = DBConnection.getDBConn();
		String query="update courses set teacher_id=? where course_id=?";
		
		PreparedStatement stmt=conn.prepareStatement(query);
		
		stmt.setInt(1, updatedTeacherId);
		stmt.setInt(2, courseId);
		
		int update = stmt.executeUpdate();
		DBConnection.dbClose();
		return update;
	}
	
	@Override
	public List<StudentsEnrolled> getEnrolledStudents(int courseIdForStudentsEnrolled) throws ClassNotFoundException, SQLException {
		
		Connection conn = DBConnection.getDBConn();
		
		String query="select e.enrollment_id,s.student_id,concat(s.first_name,last_name) as name,e.enrollment_date from enrollments e join students s on e.student_id=s.student_id where e.course_id=?";
		
		PreparedStatement stmt=conn.prepareStatement(query);
		
		stmt.setInt(1, courseIdForStudentsEnrolled);
		
		ResultSet result = stmt.executeQuery();
		
		List<StudentsEnrolled> studentsEnrolled=new ArrayList<>();
		
		while(result.next()) {
			int id=result.getInt("enrollment_id");
			int studentId=result.getInt("student_id");
			String name=result.getString("name");
			LocalDate enrollmentDate=LocalDate.parse(result.getDate("enrollment_date").toString());
			StudentsEnrolled se=new StudentsEnrolled(id, studentId, name, enrollmentDate);
			
			studentsEnrolled.add(se);
		}
		DBConnection.dbClose();
		return studentsEnrolled;
	}
	
	@Override
	public Teacher getAssignedTeachers(int courseIdForAssignedTeacher)
			throws ClassNotFoundException, SQLException {
		
		Connection conn = DBConnection.getDBConn();
		
		String query="select * from teacher where teacher_id in (select teacher_id from courses where course_id=?)";
		
		PreparedStatement stmt=conn.prepareStatement(query);
		stmt.setInt(1, courseIdForAssignedTeacher);
		
		ResultSet result = stmt.executeQuery();
		Teacher teacher=null;
		while(result.next()) {
			int teacherId=result.getInt("teacher_id");
			String firstName=result.getString("first_name");
			String lastName=result.getString("last_name");
			String email=result.getString("email");
			
			teacher=new Teacher(teacherId, firstName, lastName, email);
			
		}
		
		DBConnection.dbClose();
		return teacher;
	}
	
}
