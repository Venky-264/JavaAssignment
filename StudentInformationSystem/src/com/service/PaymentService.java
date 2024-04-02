package com.service;

import java.sql.SQLException;
import java.util.List;

import com.Exceptions.StudentNotFoundException;
import com.dao.PaymentDao;
import com.dao.PaymentDaoImpl;
import com.model.Payment;

public class PaymentService {

	public List<Payment> getAllPayments() throws ClassNotFoundException, SQLException {

		PaymentDao dao = new PaymentDaoImpl();

		return dao.getAllPayments();

	}

	public boolean validateStudent(int studentId) throws ClassNotFoundException, SQLException, StudentNotFoundException {
		PaymentDao dao = new PaymentDaoImpl();
		return dao.validateStudent(studentId);

	}

	public List<Payment> getPaymentsByStudentId(int studentId) throws ClassNotFoundException, SQLException {

		PaymentDao dao = new PaymentDaoImpl();
		return dao.getPaymentsByStudentId(studentId);
	}

	public List<Payment> getPaymentHistoryByAmount(double fromAmount, double toAmount) throws ClassNotFoundException, SQLException {

		PaymentDao dao = new PaymentDaoImpl();

		return dao.getPaymentHistoryByAmount(fromAmount,toAmount);

	}

	public List<Payment> getPaymentHistoryByDate(String fromDate, String toDate) throws ClassNotFoundException, SQLException {
		
		PaymentDao dao = new PaymentDaoImpl();

		return dao.getPaymentHistoryByDate(fromDate,toDate);

	}

}
