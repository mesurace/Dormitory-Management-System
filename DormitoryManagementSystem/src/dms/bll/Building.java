/**
 * 
 */
package dms.bll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import dms.model.DormModel;
import dms.util.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author sureshadhikari
 *
 */
public class Building {

	ConnectionDB con;
	Connection conn;
	Statement stmt;

	public Building() {
		con = new ConnectionDB();
	}

	public ObservableList<String> getDorms() {
		ObservableList<String> entries = FXCollections.observableArrayList();
		Statement stmt;
		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query = "SELECT DISTINCT(DormNumber) FROM tbldorm;";
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) { // add the results into the list
					entries.add(new DormModel(rs.getString("DormNumber")).getDormNumber());
				}
				rs.close();
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			con.closeConn(conn);
		}
		return entries;
	}

	public ObservableList<String> getRooms(String dormNumber) {
		ObservableList<String> entries = FXCollections.observableArrayList();
		Statement stmt;
		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query = "SELECT DISTINCT(RoomNumber) FROM tbldorm where DormNumber='" + dormNumber
					+ "' and IsEmpty is true;";
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) { // add the results into the list
					String rsResult = rs.getString("RoomNumber");
					if (!rsResult.equals("0")) {
						entries.add(new DormModel(rsResult).getDormNumber());
					}
				}
				rs.close();
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			con.closeConn(conn);
		}
		return entries;
	}

	public boolean addRoom(String buildingNumber, String roomNumber) {
		if (checkDuplicateRoom(buildingNumber, roomNumber)) {

			Statement stmt;
			try {
				conn = con.getConnection();
				stmt = (Statement) conn.createStatement();
				String query = "INSERT INTO tbldorm (DormNumber,RoomNumber) VALUES ('" + buildingNumber + "', '"
						+ roomNumber + "');";
				stmt.executeUpdate(query);

				return true;
			} catch (Exception e) {

				e.printStackTrace();
			} finally {
				con.closeConn(conn);
			}
		}

		return false;
	}

	public boolean addBuilding(String buildingNumber) {
		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query = "INSERT INTO tbldorm (DormNumber) VALUES ('" + buildingNumber + "');";
			stmt.executeUpdate(query);

			return true;
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			con.closeConn(conn);
		}

		return false;
	}

	public boolean assignRA(String buildingNumber, String raName, String raUserName, String raPassWord,
			String raRoomNumber) {

		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			// INSERT INTO `tbladmin` (`AdminId`, `Name`, `BuildingNumber`,
			// `UserName`, `Password`, `UserType`, `RoomNumber`) VALUES (NULL,
			// 'Suraj Lama', '143', 'suraj', 'suraj', 'RA', '229');
			String query = "INSERT INTO tbladmin (Name,BuildingNumber,UserName,Password,UserType,RoomNumber) VALUES ('"
					+ raName + "', '" + buildingNumber + "', '" + raUserName + "', '" + raPassWord + "', 'RA', '"
					+ raRoomNumber + "');";
			stmt.executeUpdate(query);

			String roomUpdateQuery = "UPDATE tbldorm SET IsEmpty = 0 WHERE DormNumber='" + buildingNumber
					+ "' and RoomNumber='" + raRoomNumber + "';";
			stmt.executeUpdate(roomUpdateQuery);

			return true;
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			con.closeConn(conn);
		}
		return false;
	}

	private boolean checkDuplicateRoom(String buildingNumber, String roomNumber) {
		Statement stmt;
		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query = "select * from tbldorm where DormNumber = '" + buildingNumber + "' and RoomNumber='"
					+ roomNumber + "'";
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			if (rs.next()) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.closeConn(conn);
		}

		return false;
	}

}
