package com.service;

import java.sql.SQLException;
import java.util.List;

import com.Exceptions.InvalidCourseDataException;
import com.Exceptions.InvalidStudentDataException;
import com.Exceptions.PaymentValidationException;
import com.Exceptions.StudentNotFoundException;
import com.model.Course;
import com.dao.StudentDaoImpl;
import com.dao.StudentDao;
import com.dto.StudentEnrolledCourses;
import com.model.Payment;
import com.model.Student;

public class StudentService {

	public Student getStudentInfo(int id) throws ClassNotFoundException, SQLException, StudentNotFoundException {

		StudentDao dao = new StudentDaoImpl();

		return dao.getStudentInfo(id);
	}

	public Student validateStudent(int studentId) throws ClassNotFoundException, SQLException, StudentNotFoundException {
		StudentDao dao = new StudentDaoImpl();

		return dao.getStudentInfo(studentId);
	}

	public List<StudentEnrolledCourses> getEnrolledCourses(int studentId) throws ClassNotFoundException, SQLException {
		
		StudentDao dao = new StudentDaoImpl();
		
		return dao.getEnrolledCourses(studentId);
	}

	public List<Payment> getPaymentHistory(int studentIdForPayment) throws ClassNotFoundException, SQLException {
		
		StudentDao dao = new StudentDaoImpl();
		return dao.getPaymentHistory(studentIdForPayment);
		
	}

	public int updateStudentDetails(Student student) throws ClassNotFoundException, SQLException,InvalidStudentDataException {
		StudentDao dao = new StudentDaoImpl();
		
		return dao.updateStudentDetails(student);
	}

	public List<Course> getAllCourses() throws ClassNotFoundException, SQLException {

		StudentDao dao = new StudentDaoImpl();
		return dao.getAllCourses();
	}

	public int enrollStudent(List<Course> courses, int studentIdForCourseEnrollment, int courseId) throws InvalidCourseDataException, ClassNotFoundException, SQLException {
		
		StudentDao dao = new StudentDaoImpl();
		boolean flag=false;
		for(Course c:courses) {
			if(c.getCourseID()==courseId)
				flag=true;
		}
		if(!flag)
			throw new InvalidCourseDataException("invalid course id");
		
		return dao.enrollStudent(studentIdForCourseEnrollment,courseId);
	}

	public int makePayment(int studentIdToMakePayment, double amount, String paymentDate) throws ClassNotFoundException, SQLException, PaymentValidationException {
		
		StudentDao dao = new StudentDaoImpl();
		return dao.makePayment(studentIdToMakePayment,amount, paymentDate);
	}

}
