package com.service;

import java.sql.SQLException;
import java.util.*;

import com.Exceptions.CourseNotFoundException;
import com.Exceptions.InvalidCourseDataException;
import com.Exceptions.TeacherNotFoundException;
import com.dto.StudentsEnrolled;
import com.dao.CourseDaoImpl;
import com.dao.CourseDao;
import com.model.Course;
import com.model.Teacher;

public class CourseService {

	public List<Course> getAllCourses() throws ClassNotFoundException, SQLException {

		CourseDao dao = new CourseDaoImpl();

		return dao.getAllCourses();
	}

	public Course getCourseById(int courseId) throws ClassNotFoundException, SQLException, CourseNotFoundException {

		CourseDao dao = new CourseDaoImpl();

		return dao.getCourseById(courseId);

	}

	public Course validateCourseId(int courseId) throws ClassNotFoundException, SQLException, CourseNotFoundException {
		CourseDao dao = new CourseDaoImpl();

		return dao.getCourseById(courseId);
	}

	public int validateTeacher(int updatedTeacherId) throws ClassNotFoundException, SQLException, TeacherNotFoundException {
		CourseDao dao = new CourseDaoImpl();

		return dao.validateTeacher(updatedTeacherId);
	}

	public int updateCourse(int courseIdForUpdation,int updatedCredicts) throws ClassNotFoundException, SQLException, InvalidCourseDataException {
		CourseDao dao = new CourseDaoImpl();
		return dao.updateCourse(courseIdForUpdation,updatedCredicts);
	}

	public int assignTeacher(int courseId, int updatedTeacherId) throws ClassNotFoundException, SQLException {
		CourseDao dao = new CourseDaoImpl();
		return dao.assignTeacher(courseId,updatedTeacherId);
	}

	public List<StudentsEnrolled> getEnrolledStudents(int courseIdForStudentsEnrolled) throws ClassNotFoundException, SQLException {
		
		CourseDao dao = new CourseDaoImpl();
		return dao.getEnrolledStudents(courseIdForStudentsEnrolled);
	}

	public Teacher getAssignedTeachers(int courseIdForAssignedTeacher) throws ClassNotFoundException, SQLException {
		
		CourseDao dao = new CourseDaoImpl();
		return dao.getAssignedTeachers(courseIdForAssignedTeacher) ;
		
		
	}

}
