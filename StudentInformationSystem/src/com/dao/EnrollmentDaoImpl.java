package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.Exceptions.DuplicateEnrollmentException;
import com.Exceptions.InvalidEnrollmentDataException;
import com.dto.CoursesEnrolled;
import com.dto.StudentsEnrolled;
import com.model.Enrollment;
import com.utility.DBConnection;

public class EnrollmentDaoImpl implements EnrollmentDao {

	@Override
	public int addEnrollment(Enrollment enrollment)
			throws ClassNotFoundException, SQLException, DuplicateEnrollmentException {

		Connection conn = DBConnection.getDBConn();

		String query1 = "insert into enrollments(student_id,course_id,enrollment_date) values(?,?,?)";

		String query2 = "select * from enrollments where student_id=? and course_id=?";

		int studentId = enrollment.getStudentId();
		int coursetId = enrollment.getCourseId();
		String enrollmentDate = enrollment.getEnrollmentDate().toString();

		PreparedStatement stmt = conn.prepareStatement(query2);
		stmt.setInt(1, studentId);
		stmt.setInt(2, coursetId);

		ResultSet result = stmt.executeQuery();

		if (result.next())
			throw new DuplicateEnrollmentException("student already enrolled for that course");

		PreparedStatement pstmt = conn.prepareStatement(query1);
		pstmt.setInt(1, studentId);
		pstmt.setInt(2, coursetId);
		pstmt.setString(3, enrollmentDate);

		int update = pstmt.executeUpdate();

		DBConnection.dbClose();
		return update;
	}

	@Override
	public boolean validateEnrollmentStudent(int studentId)
			throws ClassNotFoundException, SQLException, InvalidEnrollmentDataException {

		Connection conn = DBConnection.getDBConn();

		String query = "select * from students where student_id=?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, studentId);

		ResultSet result = pstmt.executeQuery();

		if (!result.next())
			throw new InvalidEnrollmentDataException("enrollment can't be done due to invaid student id");

		DBConnection.dbClose();
		return true;
	}

	public boolean validateEnrollmentCourse(int courseId)
			throws ClassNotFoundException, SQLException, InvalidEnrollmentDataException {

		Connection conn = DBConnection.getDBConn();

		String query = "select * from courses where course_id=?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, courseId);

		ResultSet result = pstmt.executeQuery();

		if (!result.next())
			throw new InvalidEnrollmentDataException("enrollment can't be done due to invaid course id");

		DBConnection.dbClose();
		return true;
	}

	@Override
	public List<StudentsEnrolled> getAllstudentsEnrolled() throws ClassNotFoundException, SQLException {

		Connection conn = DBConnection.getDBConn();

		String query = "select e.enrollment_id,e.student_id,concat(s.first_name,last_name) as name,e.enrollment_date from enrollments e join students s on e.student_id=s.student_id";

		Statement stmt = conn.createStatement();

		ResultSet result = stmt.executeQuery(query);

		List<StudentsEnrolled> list = new ArrayList<>();

		while (result.next()) {
			int id = result.getInt("enrollment_id");
			int studentId = result.getInt("student_id");
			String name = result.getString("name");
			LocalDate date = LocalDate.parse(result.getString("enrollment_date"));
			StudentsEnrolled se = new StudentsEnrolled(id, studentId, name, date);
			list.add(se);
		}

		DBConnection.dbClose();
		return list;
	}

	@Override
	public List<CoursesEnrolled> getAllcoursesEnrolled() throws ClassNotFoundException, SQLException {
		
		Connection conn = DBConnection.getDBConn();

		String query = "select e.enrollment_id,e.course_id,c.course_name,e.enrollment_date from enrollments e join courses c on e.course_id=c.course_id";

		Statement stmt = conn.createStatement();

		ResultSet result = stmt.executeQuery(query);

		List<CoursesEnrolled> list = new ArrayList<>();

		while (result.next()) {
			int id = result.getInt("enrollment_id");
			int courseId = result.getInt("course_id");
			String courseName = result.getString("course_name");
			LocalDate date = LocalDate.parse(result.getString("enrollment_date"));
			CoursesEnrolled ce = new CoursesEnrolled(id, courseId, courseName, date);
			list.add(ce);
		}

		DBConnection.dbClose();
		return list;
	}
}
