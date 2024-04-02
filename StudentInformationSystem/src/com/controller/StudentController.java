package com.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.Exceptions.InvalidCourseDataException;
import com.Exceptions.InvalidStudentDataException;
import com.Exceptions.PaymentValidationException;
import com.Exceptions.StudentNotFoundException;
import com.dto.StudentEnrolledCourses;
import com.model.Course;
import com.model.Payment;
import com.model.Student;
import com.service.StudentService;

public class StudentController {

	public static void main(String[] args) {
		
		StudentService studentService = new StudentService(); 
		
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			
			System.out.println("press 1. for Displaying your details");
			System.out.println("press 2. for Displaying your enrolled courses");
			System.out.println("press 3. for Displaying your payment history");
			System.out.println("press 4. for updating your details");
			System.out.println("press 5. to enroll in a new course");
			System.out.println("press 6. to make a new payment");
			System.out.println("press 0. to EXIT..");
			
			int choice=sc.nextInt();
			if(choice==0) {
				System.out.println("you are logged out....");
				break;
			}
			
			switch(choice){
			case 1:
				
				System.out.println("enter StudentId");
				int id=sc.nextInt();
				try {
					Student student=studentService.getStudentInfo(id);
					System.out.println(student);
				} catch (ClassNotFoundException | SQLException | StudentNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:

				System.out.println("enter StudentId");
				int studentId=sc.nextInt();
				
				try {
					Student student = studentService.validateStudent(studentId);
					System.out.println(student);
					List<StudentEnrolledCourses> enrolledCourses = studentService.getEnrolledCourses(studentId);
					System.out.println("YOUR ENROLLED COURSES");
					System.out.println("courseName     enrollmentDate");
					for(StudentEnrolledCourses sec:enrolledCourses) {
						System.out.println(sec.getCourseName()+"     "+sec.getEnrollmentDate());
					}
					
				} catch (ClassNotFoundException | SQLException | StudentNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
				
				break;
			case 3:
				
				System.out.println("enter StudentId");
				int studentIdForPayment=sc.nextInt();
				
				
				try {
					Student student = studentService.validateStudent(studentIdForPayment);
					System.out.println(student);
					List<Payment> payments=studentService.getPaymentHistory(studentIdForPayment);
					
					System.out.println("YOUR PAYMENT HISTORY");
					System.out.println("PaymentId    amount    paymentDate");
					for(Payment p:payments) {
						System.out.println(p.getPaymentId()+"         "+p.getAmount()+"         "+p.getPaymentDate());
					}
				} catch (ClassNotFoundException | SQLException | StudentNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 4:
				
				System.out.println("enter StudentId");
				int studentIdForUpdation=sc.nextInt();
				
				try {
					Student student = studentService.validateStudent(studentIdForUpdation);
					System.out.println("Present student details are ");
					System.out.println(student);
					sc.nextLine();
					System.out.println("enter the first name for updation");
					String firstName=sc.nextLine();
					System.out.println("enter the last name for updation");
					String lastName=sc.nextLine();
					System.out.println("enter the dob for updation");
					System.out.print("year :");
					int year=sc.nextInt();
					System.out.print("month :");
					int month=sc.nextInt();
					System.out.print("date :");
					int date=sc.nextInt();
					LocalDate dob=LocalDate.parse(Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(date));
					sc.nextLine();
					System.out.println("enter updated email address");
					String email=sc.nextLine();
					System.out.println("enter updated phone number");
					String phoneNumber=sc.nextLine();
					Student studentToUpdate=new Student(studentIdForUpdation, firstName, lastName,dob, email, phoneNumber);
					studentService.updateStudentDetails(studentToUpdate);
					System.out.println("data updated successfully");
					
				} catch (ClassNotFoundException | SQLException | StudentNotFoundException  | InvalidStudentDataException e) {
					System.out.println(e.getMessage());
					
				}
				
				break;
			case 5:
				
				System.out.println("enter StudentId");
				int studentIdForCourseEnrollment=sc.nextInt();
				
				try {
					Student student = studentService.validateStudent(studentIdForCourseEnrollment);
					System.out.println(student);
					
					List<Course> courses=studentService.getAllCourses();
					
					System.out.println("Course_id      course_name       credicts");
					for(Course c:courses) {
						System.out.println(c.getCourseID()+"     "+c.getCourseName()+"     "+c.getCredits());
					}
					
					System.out.println("enter the courseId to enroll");
					int courseId=sc.nextInt();
					
					studentService.enrollStudent(courses,studentIdForCourseEnrollment,courseId);
					
					System.out.println("course enrolled successfully");
					
				} catch (ClassNotFoundException | SQLException | StudentNotFoundException | InvalidCourseDataException e) {
					System.out.println(e.getMessage());
				}
				
				
				break;
			case 6:
				System.out.println("enter StudentId");
				int studentIdToMakePayment=sc.nextInt();
				
				try {
					studentService.validateStudent(studentIdToMakePayment);
					System.out.println("enter the money");
					double amount=sc.nextDouble();
					studentService.makePayment(studentIdToMakePayment,amount,LocalDate.now().toString());
					System.out.println("payment made sucessfully");
					
				} catch (ClassNotFoundException | SQLException | StudentNotFoundException | PaymentValidationException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			default:
				System.out.println("Invalid option");
			}
			
		}
		sc.close();
		System.out.println();
		
	}
}
