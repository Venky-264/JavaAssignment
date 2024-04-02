package com.model;

import java.time.LocalDate;

public class Enrollment {

	private int enrollmentId;
	private int courseId;
	private int studentId;
	private LocalDate enrollmentDate;

	public Enrollment() { }

	public Enrollment(int courseId, int studentId, LocalDate enrollmentDate) {

		this.courseId = courseId;
		this.studentId = studentId;
		this.enrollmentDate = enrollmentDate;
	}

	public Enrollment(int enrollmentId, int courseId, int studentId, LocalDate enrollmentDate) {

		this.enrollmentId = enrollmentId;
		this.courseId = courseId;
		this.studentId = studentId;
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

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	@Override
	public String toString() {
		return "Enrollment [enrollmentId=" + enrollmentId + ", courseId=" + courseId + ", studentId=" + studentId
				+ ", enrollmentDate=" + enrollmentDate + "]";
	}

	
}
