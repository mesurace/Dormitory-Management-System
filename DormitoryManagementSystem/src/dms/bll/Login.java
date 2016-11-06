/**
 * 
 */
package dms.bll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import dms.model.NoticeModel;
import dms.util.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author sureshadhikari
 *
 */
public class Login {
	ConnectionDB con;
	Connection conn;
	Statement stmt;

	public Login() {
		con = new ConnectionDB();

	}

	public boolean logIn(String userName, String passWord, String userType) {

		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query = null;
			if (userType.equals("STUDENT")) {
				query = "select * from tblstudent here UserName = '" + userName + "' and Password='" + passWord + "'";
			} else {
				query = "select * from tbladmin where UserName = '" + userName + "' and Password='" + passWord
						+ "' and UserType='" + userType + "'";
			}

			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.closeConn(conn);
		}

		return false;
	}

	public int[] getStudentLoginInfo(String userName, String passWord) {
		int[] result = new int[2];

		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query = "SELECT StudentId,BuildingNumber FROM tblstudent WHERE UserName='" + userName
					+ "'and Password='" + passWord + "'";
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) { // add the results into the list

					result[0] = Integer.parseInt(rs.getString("StudentId"));
					result[1] = Integer.parseInt(rs.getString("BuildingNumber"));

				}
				rs.close();
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			con.closeConn(conn);
		}
		return result;

	}

	public int getRABuildingNumber(String userName, String passWord) {
		int result = 0;

		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query = "SELECT BuildingNumber FROM tbladmin WHERE UserName='" + userName + "'and Password='"
					+ passWord + "'";
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) { // add the results into the list

					result = Integer.valueOf(rs.getString("BuildingNumber"));

				}
				rs.close();
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			con.closeConn(conn);
		}
		return result;
	}

}
