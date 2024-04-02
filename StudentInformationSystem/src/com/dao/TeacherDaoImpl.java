package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Exceptions.InvalidTeacherDataException;
import com.Exceptions.NotAssignedCoursesException;
import com.Exceptions.TeacherNotFoundException;
import com.model.Course;
import com.model.Teacher;
import com.utility.DBConnection;

public class TeacherDaoImpl implements TeacherDao{

	@Override
	public Teacher getTeacherInfo(int teacherId)
			throws ClassNotFoundException, SQLException, TeacherNotFoundException {
		
		Connection conn=DBConnection.getDBConn();
		
		String query="select * from teacher where teacher_id=?";
		
		PreparedStatement stmt=conn.prepareStatement(query);
		stmt.setInt(1, teacherId);
		
		ResultSet result = stmt.executeQuery();
		
		if(result.next()) {
			int id=result.getInt("teacher_id");
			String firstName=result.getString("first_name");
			String lastName=result.getString("last_name");
			String email=result.getString("email");
			
			Teacher teacher=new Teacher(id, firstName, lastName, email);
			
			DBConnection.dbClose();
			return teacher;
		}
		
		DBConnection.dbClose();
		throw new TeacherNotFoundException("Inavlid teacher id");
	}
	
	@Override
	public int updateTeacherInfo(Teacher teacher) 
			throws ClassNotFoundException, SQLException, InvalidTeacherDataException {
		
		Connection conn=DBConnection.getDBConn();
		
		String query="update teacher set first_name=?,last_name=?,email=? where teacher_id=?";
		
		PreparedStatement stmt=conn.prepareStatement(query);
		
		int teacherId=teacher.getTeacherId();
		String firstName=teacher.getFirstName();
		String lastName=teacher.getLastName();
		String email=teacher.getEmail();
		
		if(firstName.equals(null) || firstName.equals(""))
			throw new InvalidTeacherDataException("first name cant be empty");
		
		if(lastName.equals(null) || lastName.equals(""))
			throw new InvalidTeacherDataException("last name cant be empty");
		
		if(email.equals("null") || email.equals("") || !email.endsWith("@gmail.com"))
			throw new InvalidTeacherDataException("invalid email");
		
		stmt.setString(1, firstName);
		stmt.setString(2, lastName);
		stmt.setString(3,email);
		stmt.setInt(4, teacherId);
		
		int update = stmt.executeUpdate();
		
		DBConnection.dbClose();
		return update;
	}

	@Override
	public List<Course> getAsiignedCourses(int teacherId) 
			throws ClassNotFoundException, SQLException, NotAssignedCoursesException {
		
		Connection conn=DBConnection.getDBConn();
		
		String query="select * from courses where teacher_id=?";
		
		PreparedStatement stmt=conn.prepareStatement(query);
		stmt.setInt(1, teacherId);
		
		ResultSet result = stmt.executeQuery();
		if(!result.next()) {
			DBConnection.dbClose();
			throw new NotAssignedCoursesException("you dont have any courses assigned");
		}
		
		List<Course> assignedCourses=new ArrayList<>();
		while(result.next()) {
			int id=result.getInt("course_id");
			String courseName=result.getString("course_name");
			int credits=result.getInt("credits");
			
			Course course=new Course(id, courseName, credits, teacherId);
			assignedCourses.add(course);
		}
		DBConnection.dbClose();
		
		return assignedCourses;
	}
}
