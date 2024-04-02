package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.Exceptions.CourseNotFoundException;
import com.Exceptions.InvalidCourseDataException;
import com.Exceptions.TeacherNotFoundException;
import com.dto.StudentsEnrolled;
import com.model.Course;
import com.model.Teacher;

public interface CourseDao {

	public List<Course> getAllCourses() throws ClassNotFoundException, SQLException;

	public Course getCourseById(int courseId) throws ClassNotFoundException, SQLException,CourseNotFoundException;

	public int validateTeacher(int updatedTeacherId) throws ClassNotFoundException, SQLException,TeacherNotFoundException;

	public int updateCourse(int courseIdForUpdation,int updatedCredicts) throws ClassNotFoundException, SQLException,InvalidCourseDataException ;

	public int assignTeacher(int courseId, int updatedTeacherId)  throws ClassNotFoundException, SQLException;

	public List<StudentsEnrolled> getEnrolledStudents(int courseIdForStudentsEnrolled) throws ClassNotFoundException, SQLException;

	public Teacher getAssignedTeachers(int courseIdForAssignedTeacher)  throws ClassNotFoundException, SQLException ;

}
