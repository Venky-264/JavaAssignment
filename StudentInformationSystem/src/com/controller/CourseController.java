package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.service.CourseService;
import com.Exceptions.CourseNotFoundException;
import com.Exceptions.InvalidCourseDataException;
import com.Exceptions.TeacherNotFoundException;
import com.dto.StudentsEnrolled;
import com.model.Course;
import com.model.Teacher;;

public class CourseController {

	public static void main(String[] args) {
		CourseService courseService=new CourseService();
		
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("press 1. to display all courses");
			System.out.println("press 2. to display specific course");
			System.out.println("press 3. to update specific course");
			System.out.println("press 4. to assign teacher to a specific course");
			System.out.println("press 5. to display students enrolled for a specific course");
			System.out.println("press 6. to display teachers assigned to a specific course");
			System.out.println("press 0. to EXIT..");
			
			int choice =sc.nextInt();
			
			if(choice==0) {
				System.out.println("exiting ...");
				break;
			}
			switch(choice) {
			
			case 1:
				
				try {
					List<Course> courses=courseService.getAllCourses();
					System.out.println("ALL COURSES");
					System.out.println("course_id             course_name               credits          teacher_id");
					for(Course c:courses) {
						System.out.println(c.getCourseID()+"                "+c.getCourseName()+"                "+
					c.getCredits()+"                "+c.getTeacherId());
					}
				} catch (ClassNotFoundException | SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				System.out.println("enter the course id");
				int courseId=sc.nextInt();
				try {
					Course course=courseService.getCourseById(courseId);
					System.out.println(course);
				} catch (ClassNotFoundException | SQLException | CourseNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("enter the course id for updation");
				int courseIdForUpdation=sc.nextInt();
				try {
					Course course=courseService.validateCourseId(courseIdForUpdation);
					System.out.println(course);
					System.out.println("enter updated credicts for course");
					int updatedCredicts=sc.nextInt();
	
					courseService.updateCourse(courseIdForUpdation,updatedCredicts);
					
					System.out.println("course updated successfully");
					
				} catch (ClassNotFoundException | SQLException | CourseNotFoundException | InvalidCourseDataException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 4:
				System.out.println("enter the course id for updation");
				int courseIdToAssignTeacher=sc.nextInt();
				
				try {
					Course course = courseService.validateCourseId(courseIdToAssignTeacher);
					System.out.println(course);
					System.out.println("enter updated teacher_id to assign to the course");
					int updatedTeacherId=sc.nextInt();
					courseService.validateTeacher(updatedTeacherId);
					
					courseService.assignTeacher(courseIdToAssignTeacher,updatedTeacherId);
					System.out.println("Teacher assigned successfully");
					
				} catch (ClassNotFoundException | SQLException | CourseNotFoundException | TeacherNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 5:
				System.out.println("enter the course id");
				int courseIdForStudentsEnrolled=sc.nextInt();
				
				try {
					Course course=courseService.validateCourseId(courseIdForStudentsEnrolled);
					System.out.println(course);
					List<StudentsEnrolled> studentsEnrolled=courseService.getEnrolledStudents(courseIdForStudentsEnrolled);
					if(studentsEnrolled.isEmpty()) {
						System.out.println("no students enrolled in this course");
						continue;
					}
					
					System.out.println("Total students enrolled in the course are");
					
					System.out.println("EnrollmentId      studentId      studentName        enrollmentDate");
					for(StudentsEnrolled se:studentsEnrolled) {
						System.out.println(se.getEnrollmentId()+"             "+se.getStudentId()+"               "+
					se.getStudentName()+"              "+se.getEnrollmentDate());
					}
					
				} catch (ClassNotFoundException | SQLException | CourseNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 6:
				
				System.out.println("enter the course id");
				int courseIdForAssignedTeacher=sc.nextInt();
				
				try {
					Course course=courseService.validateCourseId(courseIdForAssignedTeacher);
					System.out.println(course);
					
					Teacher teacherAssigned=courseService.getAssignedTeachers(courseIdForAssignedTeacher);
					
					System.out.println("Assigned teacher is");
					System.out.println(teacherAssigned);
					
				} catch (ClassNotFoundException | SQLException | CourseNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			default:
				System.out.println("Invalid choice");
				break;
			}
		}
		sc.close();
		System.out.println("\n");
	}

}
