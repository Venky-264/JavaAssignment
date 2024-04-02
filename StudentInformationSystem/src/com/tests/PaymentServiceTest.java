package com.tests;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import com.Exceptions.StudentNotFoundException;
import com.service.PaymentService;

public class PaymentServiceTest {
	
	@Test
	public void validateStudentTest() {
		
		PaymentService paymentService=new PaymentService();
		
		/* valid student id usecase1 */
		try {
			Assert.assertEquals(true, paymentService.validateStudent(1));
		} catch (ClassNotFoundException | SQLException | StudentNotFoundException e) {
			
		}
		
		/* invalid student id usecase2 */
		try {
			Assert.assertEquals(true, paymentService.validateStudent(1));
		} catch (ClassNotFoundException | SQLException | StudentNotFoundException e) {
			Assert.assertEquals("Invalid studentId".toUpperCase(), e.getMessage().toUpperCase());
		}
	}
}
