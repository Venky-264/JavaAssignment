package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.Exceptions.StudentNotFoundException;
import com.model.Payment;
import com.utility.DBConnection;

public class PaymentDaoImpl implements PaymentDao {

	@Override
	public List<Payment> getAllPayments() throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.getDBConn();

		String query = "select * from payments";

		Statement stmt = conn.createStatement();

		ResultSet result = stmt.executeQuery(query);

		List<Payment> payments = new ArrayList<>();

		while (result.next()) {
			Payment payment = new Payment(result.getInt("payment_id"), result.getInt("student_id"),
					result.getDouble("amount"), LocalDate.parse(result.getDate("payment_date").toString()));
			payments.add(payment);
		}

		DBConnection.dbClose();
		return payments;
	}

	@Override
	public boolean validateStudent(int studentId) throws ClassNotFoundException, SQLException, StudentNotFoundException {

		Connection conn = DBConnection.getDBConn();

		String query = "select * from students where student_id=?";

		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, studentId);

		ResultSet result = stmt.executeQuery();

		if (!result.next())
			throw new StudentNotFoundException("Invalid studentId");

		DBConnection.dbClose();
		return true;
	}

	@Override
	public List<Payment> getPaymentsByStudentId(int studentId) throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.getDBConn();

		String query = "select * from payments where student_id=?";
		
		PreparedStatement stmt=conn.prepareStatement(query);
		stmt.setInt(1, studentId);
		
		ResultSet result = stmt.executeQuery();
		
		List<Payment> payments = new ArrayList<>();

		while (result.next()) {
			Payment payment = new Payment(result.getInt("payment_id"), result.getInt("student_id"),
					result.getDouble("amount"), LocalDate.parse(result.getDate("payment_date").toString()));
			payments.add(payment);
		}

		DBConnection.dbClose();
		return payments;

	}
	
	@Override
	public List<Payment> getPaymentHistoryByAmount(double fromAmount, double toAmount) throws ClassNotFoundException, SQLException {
		
		Connection conn = DBConnection.getDBConn();

		String query = "select * from payments where amount>=? and amount<=?";
		
		PreparedStatement stmt=conn.prepareStatement(query);
		stmt.setDouble(1, fromAmount);
		stmt.setDouble(2, toAmount);
		
		ResultSet result = stmt.executeQuery();
		
		List<Payment> payments = new ArrayList<>();
		
		while (result.next()) {
			Payment payment = new Payment(result.getInt("payment_id"), result.getInt("student_id"),
					result.getDouble("amount"), LocalDate.parse(result.getDate("payment_date").toString()));
			payments.add(payment);
		}

		DBConnection.dbClose();
		return payments;
		
	}
	
	@Override
	public List<Payment> getPaymentHistoryByDate(String fromDate, String toDate) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getDBConn();

		String query = "select * from payments where payment_date between ? and ?";
		
		PreparedStatement stmt=conn.prepareStatement(query);
		stmt.setString(1, fromDate);
		stmt.setString(2, toDate);
		
		ResultSet result = stmt.executeQuery();
		
		List<Payment> payments = new ArrayList<>();
		
		while (result.next()) {
			Payment payment = new Payment(result.getInt("payment_id"), result.getInt("student_id"),
					result.getDouble("amount"),LocalDate.parse(result.getDate("payment_date").toString()));
			payments.add(payment);
		}

		DBConnection.dbClose();
		return payments;
	}
	
}
