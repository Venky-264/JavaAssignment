package com.dto;

import java.time.LocalDate;

public class StudentsEnrolled {

	private int enrollmentId;
	private int studentId;
	private String studentName;
	private LocalDate enrollmentDate;
	
	
	
	public StudentsEnrolled() {  }



	public StudentsEnrolled(int studentId, String studentName, LocalDate enrollmentDate) {
		
		this.studentId = studentId;
		this.studentName = studentName;
		this.enrollmentDate = enrollmentDate;
	}



	public StudentsEnrolled(int enrollmentId, int studentId, String studentName, LocalDate enrollmentDate) {
		
		this.enrollmentId = enrollmentId;
		this.studentId = studentId;
		this.studentName = studentName;
		this.enrollmentDate = enrollmentDate;
	}



	public int getEnrollmentId() {
		return enrollmentId;
	}



	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}



	public int getStudentId() {
		return studentId;
	}



	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}



	public String getStudentName() {
		return studentName;
	}



	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}



	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}



	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}



	@Override
	public String toString() {
		return "StudentsEnrolled [enrollmentId=" + enrollmentId + ", studentId=" + studentId + ", studentName="
				+ studentName + ", enrollmentDate=" + enrollmentDate + "]";
	}
	
	
	
	
}
