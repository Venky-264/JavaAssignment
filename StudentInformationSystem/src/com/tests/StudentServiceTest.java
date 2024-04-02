package com.tests;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.Exceptions.InvalidCourseDataException;
import com.Exceptions.InvalidStudentDataException;
import com.Exceptions.PaymentValidationException;
import com.Exceptions.StudentNotFoundException;
import com.model.Course;
import com.model.Student;
import com.service.StudentService;

public class StudentServiceTest {

	@Test
	public void getStudentInfoTest() {

		StudentService studentService = new StudentService();

		Student student = new Student();
		student.setStudentId(2);
		student.setFirstName("virat");
		student.setLastName("kohli");
		student.setDob(LocalDate.parse("2002-05-10"));
		student.setEmail("virat@gmail.com");
		student.setPhoneNumber("9966728862");

		/* valid student Id usecase1 */
		try {
			Assert.assertEquals(student, studentService.getStudentInfo(2));
		} catch (ClassNotFoundException | SQLException | StudentNotFoundException e) {
			Assert.assertEquals("Invalid studentId".toUpperCase(), e.getMessage().toUpperCase());
		}

		/* invalid student Id usecase2 */
		try {
			Assert.assertEquals(student, studentService.getStudentInfo(100));
		} catch (ClassNotFoundException | SQLException | StudentNotFoundException e) {
			Assert.assertEquals("Invalid studentId".toUpperCase(), e.getMessage().toUpperCase());
		}

	}

	@Test
	public void updateStudentDetailsTest() {

		StudentService studentService = new StudentService();
		int studentIdForUpdation = 1;
		String firstName = "jack";
		String lastName = "Williams";
		LocalDate dob = LocalDate.parse("2024-12-20");
		String email = "jack.williams@gmail.com";
		String phoneNumber = "9988775566";

		Student student = new Student(studentIdForUpdation, firstName, lastName, dob, email, phoneNumber);

		/* updating valid usecase1 */
		try {
			Assert.assertEquals(1, studentService.updateStudentDetails(student));
		} catch (ClassNotFoundException | SQLException | InvalidStudentDataException e) {

		}

		student = new Student(studentIdForUpdation, "", lastName, dob, email, phoneNumber);

		/* invalid firstName usecase2 */
		try {
			Assert.assertEquals(1, studentService.updateStudentDetails(student));
		} catch (ClassNotFoundException | SQLException | InvalidStudentDataException e) {
			Assert.assertEquals("first name cant be empty".toUpperCase(), e.getMessage().toUpperCase());
		}

		student = new Student(studentIdForUpdation, firstName, "", dob, email, phoneNumber);

		/* invalid lastName usecase3 */
		try {
			Assert.assertEquals(1, studentService.updateStudentDetails(student));
		} catch (ClassNotFoundException | SQLException | InvalidStudentDataException e) {
			Assert.assertEquals("last name cant be empty".toUpperCase(), e.getMessage().toUpperCase());
		}

		student = new Student(studentIdForUpdation, firstName, lastName, LocalDate.parse("2024-03-31"), "",
				phoneNumber);
		/* invalid email usecase4 */
		try {
			Assert.assertEquals(1, studentService.updateStudentDetails(student));
		} catch (ClassNotFoundException | SQLException | InvalidStudentDataException e) {
			Assert.assertEquals("Invalid email".toUpperCase(), e.getMessage().toUpperCase());
		}

		student = new Student(studentIdForUpdation, firstName, lastName, LocalDate.parse("2024-03-31"), email, "9988");

		/* invalid phone number usecase5 */
		try {
			Assert.assertEquals(1, studentService.updateStudentDetails(student));
		} catch (ClassNotFoundException | SQLException | InvalidStudentDataException e) {
			Assert.assertEquals("Invalid phone number".toUpperCase(), e.getMessage().toUpperCase());
		}

	}

	@Test
	public void validateStudentTest() {

		StudentService studentService = new StudentService();

		Student student = new Student();
		student.setStudentId(2);
		student.setFirstName("virat");
		student.setLastName("kohli");
		student.setDob(LocalDate.parse("2002-05-10"));
		student.setEmail("virat@gmail.com");
		student.setPhoneNumber("9966728862");

		/* valid studentId usecase1 */
		try {
			Assert.assertEquals(student, studentService.validateStudent(2));
		} catch (ClassNotFoundException | SQLException | StudentNotFoundException e) {

		}

		/* invalid studentId usecase2 */
		try {
			Assert.assertEquals(student, studentService.validateStudent(20));
		} catch (ClassNotFoundException | SQLException | StudentNotFoundException e) {
			Assert.assertEquals("Invalid studentId".toUpperCase(), e.getMessage().toUpperCase());
		}

	}
	
	@Test
	public void makePaymentTest() {
		
		StudentService studentService = new StudentService();
		
		/* valid payment usecase1 */
		try {
			Assert.assertEquals(1, studentService.makePayment(2,1000, "2024-03-31"));
		} catch (ClassNotFoundException | SQLException | PaymentValidationException e) {
			
		}
		/* invalid amount usecase2 */
		try {
			Assert.assertEquals(1, studentService.makePayment(2,-1, "2024-03-31"));
		} catch (ClassNotFoundException | SQLException | PaymentValidationException e) {
			Assert.assertEquals("invalid amount".toUpperCase(), e.getMessage().toUpperCase());
		}
		
	}
	
	@Test
	public void enrollStudentTest() {
		
		StudentService studentService = new StudentService();
		
		List<Course> courses=new ArrayList<>();
		courses.add(new Course(1,"AI-ML",10,4));
		courses.add(new Course(2,"data science",15,1));
		
		/* valid enrollment data usecase1 */
		try {
			Assert.assertEquals(1, studentService.enrollStudent(courses, 2, 2));
		} catch (ClassNotFoundException | InvalidCourseDataException | SQLException e) {
		}
		/* invalid enrollment data usecase2 */
		try {
			Assert.assertEquals(1, studentService.enrollStudent(courses, 2, 5));
		} catch (ClassNotFoundException | InvalidCourseDataException | SQLException e) {
			Assert.assertEquals("invalid course id".toUpperCase(), e.getMessage().toUpperCase());
		}
		
	}

}
