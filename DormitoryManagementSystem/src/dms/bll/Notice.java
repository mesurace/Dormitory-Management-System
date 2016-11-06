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
public class Notice {
	ConnectionDB con;
	Connection conn;
	Statement stmt;
	int buildingNumber;

	public Notice(int buildingNumber) {
		con = new ConnectionDB();
		this.buildingNumber = buildingNumber;

	}

	public ObservableList<NoticeModel> getNoticeList() {
		ObservableList<NoticeModel> entries = FXCollections.observableArrayList();

		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query = "SELECT Notice,NoticeDate FROM tblnotice WHERE BuildingNumber='" + buildingNumber
					+ "'ORDER BY NoticeDate DESC";
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) { // add the results into the list
					entries.add(new NoticeModel(rs.getString("Notice"), rs.getString("NoticeDate")));
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

}
