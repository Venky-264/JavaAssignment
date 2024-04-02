package com.model;

import java.util.Objects;

public class Course {

	private int courseID;
	private String courseName;
	private int credits;
	private int teacherId;
	
	public Course() { }
	
	public Course(String courseName, int credits, int teacherId) {
		
		this.courseName = courseName;
		this.credits = credits;
		this.teacherId = teacherId;
	}
	
	public Course(int courseID, String courseName, int credits, int teacherId) {
		
		this.courseID = courseID;
		this.courseName = courseName;
		this.credits = credits;
		this.teacherId = teacherId;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	@Override
	public String toString() {
		return "Course [courseID=" + courseID + ", courseName=" + courseName + ", credits=" + credits + ", teacherId="
				+ teacherId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseID, courseName, credits, teacherId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return courseID == other.courseID && Objects.equals(courseName, other.courseName) && credits == other.credits
				&& teacherId == other.teacherId;
	}
	
	
	
}
