package dms.bll;
/**
 * @author surajlama
 *
 */
import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.cj.api.jdbc.Statement;

import dms.controller.LoginController;
import dms.model.ComplainModel;
import dms.model.Notice;
import dms.util.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Complainbll {
	Connection conn;
	ConnectionDB con = new ConnectionDB();
	Statement stmt;
	public ObservableList<ComplainModel> getComplainList() {
		ObservableList<ComplainModel> notices = FXCollections.observableArrayList();
		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query = "select * from tblComplain where buildingNumber="+LoginController.BUILDING_NUMBER+" order by complainId desc";
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
