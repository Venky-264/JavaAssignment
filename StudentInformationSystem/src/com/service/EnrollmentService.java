package com.service;

import java.sql.SQLException;

import com.Exceptions.DuplicateEnrollmentException;
import com.Exceptions.InvalidEnrollmentDataException;
import java.util.List;

import com.dto.CoursesEnrolled;
import com.dto.StudentsEnrolled;
import com.dao.EnrollmentDao;
import com.dao.EnrollmentDaoImpl;
import com.model.Enrollment;

public class EnrollmentService {

	public int addEnrollment(Enrollment enrollment)
			throws ClassNotFoundException, SQLException, DuplicateEnrollmentException {

		EnrollmentDao dao = new EnrollmentDaoImpl();

		return dao.addEnrollment(enrollment);
	}

	public boolean validateEnrollmentStudent(int studentId) 
			throws ClassNotFoundException, SQLException, InvalidEnrollmentDataException {

		EnrollmentDao dao = new EnrollmentDaoImpl();

		return dao.validateEnrollmentStudent(studentId);

	}

	public boolean validateEnrollmentCourse(int courseId)
			throws ClassNotFoundException, SQLException, InvalidEnrollmentDataException {
		EnrollmentDao dao = new EnrollmentDaoImpl();

		return dao.validateEnrollmentCourse(courseId);
	}

	public List<StudentsEnrolled> getAllstudentsEnrolled() throws ClassNotFoundException, SQLException {
		
		EnrollmentDao dao = new EnrollmentDaoImpl();

		return dao.getAllstudentsEnrolled();
		
	}

	public List<CoursesEnrolled> getAllcoursesEnrolled() throws ClassNotFoundException, SQLException {
		
		EnrollmentDao dao = new EnrollmentDaoImpl();

		return dao.getAllcoursesEnrolled();
	}

}
