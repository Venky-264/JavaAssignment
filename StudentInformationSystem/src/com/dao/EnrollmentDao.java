package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.Exceptions.DuplicateEnrollmentException;
import com.Exceptions.InvalidEnrollmentDataException;
import com.dto.CoursesEnrolled;
import com.dto.StudentsEnrolled;
import com.model.Enrollment;

public interface EnrollmentDao {

	public int addEnrollment(Enrollment enrollment)
			throws ClassNotFoundException, SQLException,DuplicateEnrollmentException;

	public boolean validateEnrollmentStudent(int studentId)
			 throws ClassNotFoundException, SQLException,InvalidEnrollmentDataException;

	public boolean validateEnrollmentCourse(int courseId) 
			throws ClassNotFoundException, SQLException,InvalidEnrollmentDataException;

	public List<StudentsEnrolled> getAllstudentsEnrolled()
			throws ClassNotFoundException, SQLException;

	public List<CoursesEnrolled> getAllcoursesEnrolled()
			 throws ClassNotFoundException, SQLException ;

}
