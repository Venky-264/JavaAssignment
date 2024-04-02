package com.service;

import java.sql.SQLException;
import java.util.List;

import com.Exceptions.InvalidTeacherDataException;
import com.Exceptions.NotAssignedCoursesException;
import com.Exceptions.TeacherNotFoundException;
import com.dao.TeacherDaoImpl;
import com.dao.TeacherDao;
import com.model.Course;
import com.model.Teacher;

public class TeacherService {

	public Teacher getTeacherInfo(int teacherId) 
			throws ClassNotFoundException, SQLException, TeacherNotFoundException {

		TeacherDao dao = new TeacherDaoImpl();

		return dao.getTeacherInfo(teacherId);
	}

	public int updateTeacherInfo(Teacher teacher) 
			throws ClassNotFoundException, SQLException, InvalidTeacherDataException {

		TeacherDao dao = new TeacherDaoImpl();

		return dao.updateTeacherInfo(teacher);

	}

	public List<Course> getAsiignedCourses(int teacherId) 
			throws ClassNotFoundException, SQLException, NotAssignedCoursesException {
		
		TeacherDao dao = new TeacherDaoImpl();
		
		return dao.getAsiignedCourses(teacherId);
		
	}

}
