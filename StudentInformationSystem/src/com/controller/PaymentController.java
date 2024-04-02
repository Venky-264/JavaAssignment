package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.Exceptions.StudentNotFoundException;
import com.model.Payment;
import com.service.PaymentService;

public class PaymentController {

	public static void main(String[] args) {

		PaymentService paymentService = new PaymentService();

		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("press 1. to Display all payments");
			System.out.println("press 2. to Display all payments by studentId");
			System.out.println("press 3. to get total payments made within amount range");
			System.out.println("press 4. to display payments made in a date");
			System.out.println("press 0. to EXIT...");

			int choice = sc.nextInt();
			if (choice == 0) {
				System.out.println("exiting...");
				break;
			}

			switch (choice) {
			case 1:

				try {
					List<Payment> payments = paymentService.getAllPayments();
					System.out.println("paymentId  studentId    amount      paymentDate");

					for (Payment payment : payments) {
						System.out.println(payment.getPaymentId() + "          " + payment.getStudentId() + "          "
								+ payment.getAmount() + "          " + payment.getPaymentDate());
					}

				} catch (ClassNotFoundException | SQLException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 2:

				System.out.println("enter the studentId for payment history");
				int studentId = sc.nextInt();

				try {
					paymentService.validateStudent(studentId);
					List<Payment> payments = paymentService.getPaymentsByStudentId(studentId);

					System.out.println("the payment history for the above student");
					if(payments.isEmpty()) {
						System.out.println("there are no payments done");
						continue;
					}
					System.out.println("paymentId   amount      paymentDate");

					for (Payment payment : payments) {
						System.out.println(payment.getPaymentId() + "          " + payment.getAmount() + "          "
					+ payment.getPaymentDate());
					}

				} catch (ClassNotFoundException | SQLException | StudentNotFoundException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 3:
				
				System.out.println("enter the amount range");
				System.out.println("enter the from amount");
				double fromAmount=sc.nextDouble();
				if(fromAmount<1) {
					System.out.println("invalid from amount");
					continue;
				}
				System.out.println("enter the toAmount");
				double toAmount=sc.nextDouble();
				if(toAmount<1) {
					System.out.println("invalid to amount");
					continue;
				}
				
				try {
					List<Payment> payments=paymentService.getPaymentHistoryByAmount(fromAmount,toAmount);
					
					if(payments.isEmpty()) {
						System.out.println("there are no payments done within this amount range");
						continue;
					}
					
					System.out.println("paymentId  studentId    amount      paymentDate");

					for (Payment payment : payments) {
						System.out.println(payment.getPaymentId() + "          " + payment.getStudentId() + "          "
								+ payment.getAmount() + "          " + payment.getPaymentDate());
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					System.out.println(e.getMessage());
				}
				
				
				break;
			case 4:
				
				System.out.println("enter the date range--- date should be in the formate of yyyy-mm-dd");
				sc.nextLine();
				System.out.println("enter the fromDate");
				String fromDate=sc.nextLine();
				System.out.println("enter the toDate");
				String toDate=sc.nextLine();
				
				try {
					List<Payment> payments=paymentService.getPaymentHistoryByDate(fromDate,toDate);
					
					if(payments.isEmpty()) {
						System.out.println("there are no payments done within this date range");
						continue;
					}
					
					System.out.println("paymentId  studentId    amount      paymentDate");

					for (Payment payment : payments) {
						System.out.println(payment.getPaymentId() + "          " + payment.getStudentId() + "          "
								+ payment.getAmount() + "          " + payment.getPaymentDate());
					}
					
					
				} catch (ClassNotFoundException | SQLException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			default:

				System.out.println("invalid option");
				break;
			}

		}
		sc.close();

	}

}
