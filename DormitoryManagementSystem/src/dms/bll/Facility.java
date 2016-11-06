package dms.bll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import dms.model.DormFacilityModel;
import dms.util.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author sureshadhikari
 *
 */
public class Facility {
	ConnectionDB con;
	private int dormId;
	Connection conn;
	Statement stmt;

	public Facility(int dormId) {
		this.dormId = dormId;
		con = new ConnectionDB();
	}

	public ObservableList<DormFacilityModel> getFacilities() {
		ObservableList<DormFacilityModel> entries = FXCollections.observableArrayList();

		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query = "select Facility from tbldormfacility where DormId='" + dormId + "'";
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) { // add the results into the list
					entries.add(new DormFacilityModel(rs.getString("facility")));
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

	public boolean addFacility(String facility) {

		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();

			String query = "INSERT INTO tbldormfacility (DormId,Facility) VALUES ('"+dormId+"','" + facility + "')";
			stmt.executeUpdate(query);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			con.closeConn(conn);
		}
		return false;
	}

}
