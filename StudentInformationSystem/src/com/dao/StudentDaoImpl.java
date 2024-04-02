package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import com.Exceptions.InvalidStudentDataException;
import com.Exceptions.PaymentValidationException;
import com.Exceptions.StudentNotFoundException;
import com.dto.StudentEnrolledCourses;
import com.model.Course;
import com.model.Payment;
import com.model.Student;
import com.utility.DBConnection;

public class StudentDaoImpl implements StudentDao {

	@Override
	public Student getStudentInfo(int id) throws ClassNotFoundException, SQLException, StudentNotFoundException {

		Connection conn = DBConnection.getDBConn();

		String query = "select * from students where student_id=?";

		PreparedStatement stmt = conn.prepareStatement(query);

		stmt.setInt(1, id);

		ResultSet result = stmt.executeQuery();

		if (result.next()) {
			int studentId = result.getInt("student_id");
			String firstName = result.getString("first_name");
			String lastName = result.getString("last_name");
			LocalDate dob = LocalDate.parse(result.getDate("date_of_birth").toString());
			String email = result.getString("email");
			String phoneNumber = result.getString("phone_number");

			Student student = new Student(studentId, firstName, lastName, dob, email, phoneNumber);

			DBConnection.dbClose();
			return student;

		}

		throw new StudentNotFoundException("Invalid studentId");
	}

	@Override
	public List<StudentEnrolledCourses> getEnrolledCourses(int studentId) throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.getDBConn();

		String query = "select c.course_name as name,e.enrollment_date as date from students s join enrollments e on s.student_id=e.student_id join courses c on e.course_id=c.course_id where s.student_id=?";

		PreparedStatement stmt = conn.prepareStatement(query);

		stmt.setInt(1, studentId);

		ResultSet result = stmt.executeQuery();

		List<StudentEnrolledCourses> list = new ArrayList<>();

		while (result.next()) {
			String courseName = result.getString("name");
			LocalDate enrollmentDate = LocalDate.parse(result.getDate("date").toString());

			StudentEnrolledCourses obj = new StudentEnrolledCourses(courseName, enrollmentDate);
			list.add(obj);

		}

		DBConnection.dbClose();
		return list;
	}

	@Override
	public List<Payment> getPaymentHistory(int studentIdForPayment) throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.getDBConn();

		String query = "select * from payments where student_id=?";

		PreparedStatement stmt = conn.prepareStatement(query);

		stmt.setInt(1, studentIdForPayment);

		ResultSet result = stmt.executeQuery();

		List<Payment> payments = new ArrayList<>();

		while (result.next()) {
			int paymentId = result.getInt("payment_id");
			double amount = result.getDouble("amount");
			LocalDate paymentDate = LocalDate.parse(result.getDate("payment_date").toString());

			Payment payment = new Payment();
			payment.setPaymentId(paymentId);
			payment.setAmount(amount);
			payment.setPaymentDate(paymentDate);

			payments.add(payment);

		}
		DBConnection.dbClose();
		return payments;
	}

	@Override
	public int updateStudentDetails(Student student) throws ClassNotFoundException, SQLException, InvalidStudentDataException {
		
		Connection conn = DBConnection.getDBConn();

		String query = "update students set first_name=?,last_name=?,date_of_birth=?,email=?,phone_number=? where student_id=?";

		PreparedStatement stmt = conn.prepareStatement(query);
		
		int studentIdForUpdation=student.getStudentId();
		String firstName=student.getFirstName();
		String lastName=student.getLastName();
		int year=student.getDob().getYear();
		int month=student.getDob().getMonthValue();
		int date=student.getDob().getDayOfMonth();
		String email=student.getEmail();
		String phoneNumber=student.getPhoneNumber();

		if(firstName.equals(null) || firstName.equals(""))
			throw new InvalidStudentDataException("first name cant be empty");
		
		if(lastName.equals(null) || lastName.equals(""))
			throw new InvalidStudentDataException("last name cant be empty");
		if(year<1900 || year>2100)
			throw new InvalidStudentDataException("Invalid year");
		
		if(month<1 || month>12)
			throw new InvalidStudentDataException("Invalid month");
		
		if(date<1 || date>31)
			throw new InvalidStudentDataException("Invalid date");
		
		if(email.equals("null") || email.equals("") || !email.endsWith("@gmail.com"))
			throw new InvalidStudentDataException("invalid email");
		
		if(phoneNumber.equals(null) || phoneNumber.equals("") || phoneNumber.length()!=10)
			throw new InvalidStudentDataException("invalid phone number");
		
		String dob=Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(date);
		
		stmt.setString(1, firstName);
		stmt.setString(2, lastName);
		stmt.setString(3, dob);
		stmt.setString(4, email);
		stmt.setString(5, phoneNumber);
		stmt.setInt(6, studentIdForUpdation);
		
		int update = stmt.executeUpdate();
		
		DBConnection.dbClose();
		return update;
	}
	
	@Override
	public List<Course> getAllCourses() throws ClassNotFoundException, SQLException {
		
		Connection conn = DBConnection.getDBConn();
		
		String query="select * from courses";
		
		Statement stmt=conn.createStatement();
		ResultSet result = stmt.executeQuery(query);
		
		List<Course> courses=new ArrayList<>();
		
		while(result.next()) {
			int id=result.getInt("course_id");
			String name=result.getString("course_name");
			int credicts=result.getInt("credits");
			
			Course c=new Course();
			c.setCourseID(id);
			c.setCourseName(name);
			c.setCredits(credicts);
			courses.add(c);
		}
		
		
		DBConnection.dbClose();
		return courses;
	}
	
	@Override
	public int enrollStudent(int studentIdForCourseEnrollment, int courseId) throws ClassNotFoundException, SQLException {
		
		Connection conn = DBConnection.getDBConn();
		String query="insert into enrollments(student_id,course_id,enrollment_date) values(?,?,?)";
		
		PreparedStatement stmt=conn.prepareStatement(query);
		
		stmt.setInt(1, studentIdForCourseEnrollment);
		stmt.setInt(2, courseId);
		stmt.setString(3,LocalDate.now().toString());
		
		int update = stmt.executeUpdate();
		
		DBConnection.dbClose();
		return update;
	}
	
	@Override
	public int makePayment(int studentIdToMakePayment, double amount, String paymentDate) throws ClassNotFoundException, SQLException, PaymentValidationException {
		Connection conn = DBConnection.getDBConn();
		
		if(amount< 1.0)
			throw new PaymentValidationException("Invalid amount");
		
		String query="insert into payments(student_id,amount,payment_date) values(?,?,?)";
		
		PreparedStatement stmt=conn.prepareStatement(query);
		
		stmt.setInt(1, studentIdToMakePayment);
		stmt.setDouble(2, amount);
		stmt.setString(3,paymentDate);
		
		int update = stmt.executeUpdate();
		
		DBConnection.dbClose();
		return update;
	}
}
