/**
 * 
 */
package dms.bll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import dms.model.StudentModel1;
import dms.util.ConnectionDB;

/**
 * @author sureshadhikari
 *
 */
public class Student {
	ConnectionDB con;
	Connection conn;
	Statement stmt;
	int studentId;

	public Student(int studentId) {
		con = new ConnectionDB();
		this.studentId = studentId;

	}

	public StudentModel1 getStudentInfo() {
		ArrayList<StudentModel1> entries = new ArrayList<>();

		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query = "SELECT LastName,FirstName,Intake,Country,BuildingNumber,RoomNumber,Email FROM tblstudent where StudentId='"
					+ studentId + "';";
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) { // add the results into the list

					entries.add(new StudentModel1(rs.getString("LastName"), rs.getString("FirstName"),
							rs.getString("Intake"), rs.getString("Country"), rs.getString("BuildingNumber"),
							rs.getString("RoomNumber"), rs.getString("Email")));
				}
				rs.close();
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			con.closeConn(conn);
		}
		return (StudentModel1) entries.get(0);
	}

}
