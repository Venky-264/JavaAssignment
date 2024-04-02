package com.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.Exceptions.DuplicateEnrollmentException;
import com.Exceptions.InvalidEnrollmentDataException;
import com.dto.CoursesEnrolled;
import com.dto.StudentsEnrolled;
import com.model.Enrollment;
import com.service.EnrollmentService;;

public class EnrollmentController {

	public static void main(String[] args) {

		EnrollmentService enrollmentService = new EnrollmentService();

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("press 1. to add a new enrollment");
			System.out.println("press 2. to get students enrolled");
			System.out.println("press 3. to get total enrolled courses");
			System.out.println("press 0. to EXIT...");

			int choice = sc.nextInt();
			if (choice == 0) {
				System.out.println("exiting...");
				break;
			}
			
			switch (choice) {
			case 1:
				
				System.out.println("enter the studentId for enrollment");
				int studentId=sc.nextInt();
			
				try {
					
					enrollmentService.validateEnrollmentStudent(studentId);
					
					System.out.println("enter the courseId for enrollment");
					int courseId=sc.nextInt();
					
					enrollmentService.validateEnrollmentCourse(courseId);
					
					LocalDate enrollmentDate=LocalDate.now();
					
					Enrollment enrollment=new Enrollment(courseId, studentId, enrollmentDate);
					
					enrollmentService.addEnrollment(enrollment);
					System.out.println("enrollment done succesfully");
					
					
				} catch (ClassNotFoundException | SQLException | InvalidEnrollmentDataException | DuplicateEnrollmentException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 2:
				
				try {
					List<StudentsEnrolled> studentsEnrolled=enrollmentService.getAllstudentsEnrolled();
					
					System.out.println("Total students enrolled are");
					System.out.println("enrollemntId    studentId     studentName     enrollmentDate");
					for(StudentsEnrolled se:studentsEnrolled) {
						System.out.println(se.getEnrollmentId()+"            "+se.getStudentId()+"           "+se.getStudentName()
						+"           "+se.getEnrollmentDate());
					}
					
					
				} catch (ClassNotFoundException | SQLException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 3:
				try {
					List<CoursesEnrolled> coursesEnrolled=enrollmentService.getAllcoursesEnrolled();
					
					System.out.println("Total courses enrolled are");
					System.out.println("enrollemntId    courseId     courseName     enrollmentDate");
					for(CoursesEnrolled ce:coursesEnrolled) {
						System.out.println(ce.getEnrollmentId()+"            "+ce.getCourseId()+"           "+ce.getCourseName()
						+"           "+ce.getEnrollmentDate());
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
