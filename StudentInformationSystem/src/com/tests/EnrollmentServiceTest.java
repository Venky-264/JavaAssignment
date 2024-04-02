package com.tests;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.Assert;

import com.Exceptions.DuplicateEnrollmentException;
import com.Exceptions.InvalidEnrollmentDataException;
import com.model.Enrollment;
import com.service.EnrollmentService;

public class EnrollmentServiceTest {
	
	@Test
	public void addEnrollmentTest() {
		
		EnrollmentService enrollmentService=new EnrollmentService();
		
		Enrollment enrollment=new Enrollment(3,4,LocalDate.parse("2024-03-31"));
		
		/* valid enrollment usecase1 */
		try {
			Assert.assertEquals(1, enrollmentService.addEnrollment(enrollment));
		} catch (ClassNotFoundException | SQLException | DuplicateEnrollmentException e) { }
		
		Enrollment enrollment1=new Enrollment(2,1,LocalDate.parse("2024-03-31"));
		/* invalid enrollment usecase2 */
		try {
			Assert.assertEquals(1, enrollmentService.addEnrollment(enrollment1));
		} catch (ClassNotFoundException | SQLException | DuplicateEnrollmentException e) { 
			Assert.assertEquals("student already enrolled for that course".toUpperCase(), e.getMessage().toUpperCase());
		}
	}
	
	@Test
	public void validateEnrollmentStudentTest() {
		
		EnrollmentService enrollmentService=new EnrollmentService();
		
		/* valid student id usecase1 */
		try {
			Assert.assertEquals(true, enrollmentService.validateEnrollmentStudent(1));
		} catch (ClassNotFoundException | SQLException | InvalidEnrollmentDataException e) {
			
		}
		
		/* invalid student id usecase2 */
		try {
			Assert.assertEquals(true, enrollmentService.validateEnrollmentStudent(20));
		} catch (ClassNotFoundException | SQLException | InvalidEnrollmentDataException e) {
			Assert.assertEquals("enrollment can't be done due to invaid student id".toUpperCase(),e.getMessage().toUpperCase());
		}
	}
	
	@Test
	public void validateEnrollmentCourseTest() {
		
		EnrollmentService enrollmentService=new EnrollmentService();
		
		/* valid course id usecase1 */
		try {
			Assert.assertEquals(true, enrollmentService.validateEnrollmentCourse(1));
		} catch (ClassNotFoundException | SQLException | InvalidEnrollmentDataException e) {
			
		}
		
		/* invalid course id usecase2 */
		try {
			Assert.assertEquals(true, enrollmentService.validateEnrollmentCourse(20));
		} catch (ClassNotFoundException | SQLException | InvalidEnrollmentDataException e) {
			Assert.assertEquals("enrollment can't be done due to invaid course id".toUpperCase(),e.getMessage().toUpperCase());
		}
	}

}
