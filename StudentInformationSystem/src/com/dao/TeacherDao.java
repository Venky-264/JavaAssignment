package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.Exceptions.InvalidTeacherDataException;
import com.Exceptions.NotAssignedCoursesException;
import com.Exceptions.TeacherNotFoundException;
import com.model.Course;
import com.model.Teacher;

public interface TeacherDao {

	public Teacher getTeacherInfo(int teacherId) 
			throws ClassNotFoundException, SQLException,TeacherNotFoundException;
	public int updateTeacherInfo(Teacher teacher)
			throws ClassNotFoundException, SQLException,InvalidTeacherDataException;
	public List<Course> getAsiignedCourses(int teacherId)
			throws ClassNotFoundException, SQLException ,NotAssignedCoursesException;
}
