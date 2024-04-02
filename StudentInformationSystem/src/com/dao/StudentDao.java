package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.Exceptions.InvalidStudentDataException;
import com.Exceptions.PaymentValidationException;
import com.Exceptions.StudentNotFoundException;
import com.dto.StudentEnrolledCourses;
import com.model.Payment;
import com.model.Student;
import com.model.Course;

public interface StudentDao {
	
	public Student getStudentInfo(int id) throws ClassNotFoundException, SQLException,StudentNotFoundException ;
	
	public List<StudentEnrolledCourses> getEnrolledCourses(int studentId) throws ClassNotFoundException, SQLException;

	public List<Payment> getPaymentHistory(int studentIdForPayment) throws ClassNotFoundException, SQLException;

	public int 
	updateStudentDetails(Student student) throws ClassNotFoundException, SQLException ,InvalidStudentDataException;

	public List<Course> getAllCourses() throws ClassNotFoundException, SQLException;

	public int enrollStudent(int studentIdForCourseEnrollment, int courseId) throws ClassNotFoundException, SQLException ;

	public int makePayment(int studentIdToMakePayment, double amount, String paymentDate) throws ClassNotFoundException, SQLException ,PaymentValidationException;

}
