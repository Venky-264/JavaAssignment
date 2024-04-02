package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.Exceptions.InvalidTeacherDataException;
import com.Exceptions.NotAssignedCoursesException;
import com.Exceptions.TeacherNotFoundException;
import com.model.Course;
import com.model.Teacher;
import com.service.TeacherService;

public class TeacherController {

	public static void main(String[] args) {
		
		TeacherService teacherService = new TeacherService();
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			System.out.println("press 1. to display your details");
			System.out.println("press 2. to update your details");
			System.out.println("press 3. to display your assigned courses");
			System.out.println("press 0. to EXIT...");
			
			int choice=sc.nextInt();
			if(choice==0) {
				System.out.println("exiting...");
				break;
			}
			switch(choice) {
			case 1:
				System.out.println("enter teacher_id");
				int teacherId=sc.nextInt();
				try {
					Teacher teacher=teacherService.getTeacherInfo(teacherId);
					System.out.println(teacher);
				} catch (ClassNotFoundException | SQLException | TeacherNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				System.out.println("enter teacher_id for updating details");
				int teacherIdToUpdateData=sc.nextInt();
				
				try {
					Teacher teacher = teacherService.getTeacherInfo(teacherIdToUpdateData);
					System.out.println(teacher);
					
					sc.nextLine();
					System.out.println("enter the first name for updation");
					String firstName=sc.nextLine();
					System.out.println("enter the last name for updation");
					String lastName=sc.nextLine();
					System.out.println("enter updated email address");
					String email=sc.nextLine();
					
					Teacher teacherToUpdate=new Teacher();
					teacherToUpdate.setTeacherId(teacherIdToUpdateData);
					teacherToUpdate.setFirstName(firstName);
					teacherToUpdate.setLastName(lastName);
					teacherToUpdate.setEmail(email);
					
					teacherService.updateTeacherInfo(teacherToUpdate);
					
					System.out.println("data updated successfully");
					
				} catch (ClassNotFoundException | SQLException | TeacherNotFoundException | InvalidTeacherDataException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 3:
				
				System.out.println("enter teacher_id for displaying assigned courses");
				int teacherIdToDisplayCourses=sc.nextInt();
				
				
				try {
					Teacher teacher = teacherService.getTeacherInfo(teacherIdToDisplayCourses);
					System.out.println(teacher+"\n");
					System.out.println("Assigned courses for you");
					List<Course> assignedCourses=teacherService.getAsiignedCourses(teacherIdToDisplayCourses);
					System.out.println("course_id    course_name         credits");
					for(Course c:assignedCourses) {
						System.out.println(c.getCourseID()+"   "+c.getCourseName()+"          "+c.getCredits());
					}
				} catch (ClassNotFoundException | SQLException | TeacherNotFoundException | NotAssignedCoursesException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			default:
				System.out.println("invalid option");
				break;
			}
		}
		sc.close();
		System.out.println("\n");
	}

}
