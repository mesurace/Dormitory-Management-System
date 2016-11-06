/**
 * 
 */
package dms.bll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import dms.controller.LoginController;
import dms.model.ComplainModel;
import dms.util.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author sureshadhikari
 *
 */
public class Complain {
	ConnectionDB con;
	Connection conn;
	Statement stmt;
	

	public Complain() {
		con = new ConnectionDB();
		

	}

	public boolean sendComplain(String complain, LocalDate localDate) {
		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query = "INSERT INTO tblcomplain (StudentId,ComplainDate,Complain,BuildingNumber) VALUES ('" + LoginController.STUDENT_ID + "','"
					+ localDate + "','"+ complain + "','" + LoginController.BUILDING_NUMBER + "')";
			stmt.executeUpdate(query);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			con.closeConn(conn);
		}
		return false;

	}
	public ObservableList<ComplainModel> getComplainListById() {
		ObservableList<ComplainModel> notices = FXCollections.observableArrayList();
		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query = "select * from tblComplain where buildingNumber="+LoginController.BUILDING_NUMBER+" and StudentId="+LoginController.STUDENT_ID+" order by complainId desc";
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			while (rs.next()) {
			
				String date1 = rs.getString("ComplainDate");
			
				notices.add(new ComplainModel(Integer.parseInt(rs.getString("ComplainId")), Integer.parseInt(rs.getString("StudentId")), date1,
						(rs.getString("Complain"))));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.closeConn(conn);
		}
		return notices;
	}
	
}
