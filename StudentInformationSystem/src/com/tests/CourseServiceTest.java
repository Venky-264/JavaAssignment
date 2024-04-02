package com.tests;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import com.Exceptions.CourseNotFoundException;
import com.Exceptions.InvalidCourseDataException;
import com.Exceptions.TeacherNotFoundException;
import com.model.Course;
import com.service.CourseService;

public class CourseServiceTest {

	@Test
	public void getCourseByIdTest() {

		CourseService courseService = new CourseService();

		Course course = new Course(1, "AI-ML", 10, 4);

		/* getting course with valid Id usecase1 */
		try {
			Assert.assertEquals(course, courseService.getCourseById(1));
		} catch (ClassNotFoundException | SQLException | CourseNotFoundException e) {
		}

		/* getting course with invalid Id usecase2 */
		try {
			Assert.assertEquals(course, courseService.getCourseById(20));
		} catch (ClassNotFoundException | SQLException | CourseNotFoundException e) {
			Assert.assertEquals("Invalid course id".toUpperCase(), e.getMessage().toUpperCase());
		}
	}

	@Test
	public void validateCourseIdTest() {

		CourseService courseService = new CourseService();

		Course course = new Course(1, "AI-ML", 10, 4);

		/* valid course Id usecase1 */
		try {
			Assert.assertEquals(course, courseService.validateCourseId(1));
		} catch (ClassNotFoundException | SQLException | CourseNotFoundException e) {
		}

		/* invalid courseId usecase2 */
		try {
			Assert.assertEquals(course, courseService.validateCourseId(20));
		} catch (ClassNotFoundException | SQLException | CourseNotFoundException e) {
			Assert.assertEquals("Invalid course id".toUpperCase(), e.getMessage().toUpperCase());
		}

	}
	
	@Test
	public void validateTeacherTest() {
		
		CourseService courseService = new CourseService();
		/* valid teacherId usecase1 */
		try {
			Assert.assertEquals(1, courseService.validateTeacher(1));
		} catch (ClassNotFoundException | SQLException | TeacherNotFoundException e) {
		}
		
		/* invalid teacherId usecase2 */
		try {
			Assert.assertEquals(1, courseService.validateTeacher(20));
		} catch (ClassNotFoundException | SQLException | TeacherNotFoundException e) {
			Assert.assertEquals("invalid teacher id".toUpperCase(), e.getMessage().toUpperCase());
		}
	}
	
	@Test
	public void updateCourseTest() {
		
		CourseService courseService=new CourseService();
		
		/* updating valid usecase1 */
		try {
			Assert.assertEquals(1, courseService.updateCourse(1, 20));
		} catch (ClassNotFoundException | SQLException | InvalidCourseDataException e) {
			
		}
		
		/* invalid credits usecase1 */
		try {
			Assert.assertEquals(1, courseService.updateCourse(1,-5));
		} catch (ClassNotFoundException | SQLException | InvalidCourseDataException e) {
			Assert.assertEquals("invalid credits".toUpperCase(), e.getMessage().toUpperCase());
		}
		
	}
	
	
	@Test
	public void assignTeacherTest() {
		
		CourseService courseService = new CourseService();
		
		/* valid data usecase1 */
		try {
			Assert.assertEquals(1, courseService.assignTeacher(2, 2));
		} catch (ClassNotFoundException | SQLException e) {
			
		}
		
		/* invalid data usecase2 */
		try {
			Assert.assertEquals(0, courseService.assignTeacher(15, 15));
		} catch (ClassNotFoundException | SQLException e) {

		}
		
	}
	
	

}
