package com.tests;

import org.junit.Test;

import java.sql.SQLException;

import org.junit.Assert;

import com.Exceptions.InvalidTeacherDataException;
import com.Exceptions.TeacherNotFoundException;
import com.model.Teacher;
import com.service.TeacherService;


public class TeacherServiceTest {
	
	@Test
	public void getTeacherInfoTest() {
		
		TeacherService teacherService=new TeacherService();
		Teacher teacher=new Teacher(1,"mike","tyson","mike@gmail.com");
		
		/* getting teacher usecase1 */
		try {
			Assert.assertEquals(teacher,teacherService.getTeacherInfo(1));
		} catch (ClassNotFoundException | SQLException | TeacherNotFoundException e) {
			
		}
		
		/* getting teacher with invalid id usecase2 */
		try {
			Assert.assertEquals(teacher,teacherService.getTeacherInfo(20));
		} catch (ClassNotFoundException | SQLException | TeacherNotFoundException e) {
			Assert.assertEquals("Inavlid teacher id".toUpperCase(), e.getMessage().toUpperCase());
		}
	}
	
	@Test
	public void updateTeacherInfoTest() {
		
		TeacherService teacherService=new TeacherService();
		Teacher teacher=new Teacher(1,"mike","tyson","mike@gmail.com");
		
		/* updating teacher with valid Id usecase1 */
		try {
			Assert.assertEquals(1, teacherService.updateTeacherInfo(teacher));
		} catch (ClassNotFoundException | SQLException | InvalidTeacherDataException e) {
			
		}
		
		/* updating teacher with empty firstName usecase2 */
		teacher.setFirstName("");
		try {
			Assert.assertEquals(1, teacherService.updateTeacherInfo(teacher));
		} catch (ClassNotFoundException | SQLException | InvalidTeacherDataException e) {
			Assert.assertEquals("first name cant be empty".toUpperCase(), e.getMessage().toUpperCase());
		}
		
		/* updating teacher with empty lastName usecase3 */
		teacher.setFirstName("mike");
		teacher.setLastName("");
		try {
			Assert.assertEquals(1, teacherService.updateTeacherInfo(teacher));
		} catch (ClassNotFoundException | SQLException | InvalidTeacherDataException e) {
			Assert.assertEquals("last name cant be empty".toUpperCase(), e.getMessage().toUpperCase());
		}
		
		/* updating teacher with empty emailusecase4 */
		teacher.setLastName("Tyson");
		teacher.setEmail("");
		try {
			Assert.assertEquals(1, teacherService.updateTeacherInfo(teacher));
		} catch (ClassNotFoundException | SQLException | InvalidTeacherDataException e) {
			Assert.assertEquals("invalid email".toUpperCase(), e.getMessage().toUpperCase());
		}
	}

}
