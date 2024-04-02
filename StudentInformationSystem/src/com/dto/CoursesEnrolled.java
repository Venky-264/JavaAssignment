package com.dto;

import java.time.LocalDate;

public class CoursesEnrolled {

	private int enrollmentId;
	private int courseId;
	private String courseName;
	private LocalDate enrollmentDate;

	public CoursesEnrolled() {
		super();
	}

	public CoursesEnrolled(int courseId, String courseName, LocalDate enrollmentDate) {
		
		this.courseId = courseId;
		this.courseName = courseName;
		this.enrollmentDate = enrollmentDate;
	}

	public CoursesEnrolled(int enrollmentId, int courseId, String courseName, LocalDate enrollmentDate) {
		
		this.enrollmentId = enrollmentId;
		this.courseId = courseId;
		this.courseName = courseName;
		this.enrollmentDate = enrollmentDate;
	}

	public int getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	@Override
	public String toString() {
		return "CoursesEnrolled [enrollmentId=" + enrollmentId + ", courseId=" + courseId + ", courseName="
				+ courseName + ", enrollmentDate=" + enrollmentDate + "]";
	}
	
	
	
}
