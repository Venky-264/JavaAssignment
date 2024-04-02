package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.Exceptions.StudentNotFoundException;
import com.model.Payment;

public interface PaymentDao {

	public List<Payment> getAllPayments() throws ClassNotFoundException, SQLException;

	public boolean validateStudent(int studentId)
			 throws ClassNotFoundException, SQLException,StudentNotFoundException;

	public List<Payment> getPaymentsByStudentId(int studentId) 
			throws ClassNotFoundException, SQLException;

	public List<Payment> getPaymentHistoryByAmount(double fromAmount, double toAmount)
			 throws ClassNotFoundException, SQLException;

	public List<Payment> getPaymentHistoryByDate(String fromDate, String toDate)
			throws ClassNotFoundException, SQLException;

}
