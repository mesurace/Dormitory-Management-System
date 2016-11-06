package dms.bll;

import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.cj.api.jdbc.Statement;

import dms.model.BuildingModel;
import dms.model.ComplainModel;
import dms.util.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * @author surajlama
 *
 */
public class Buildingbll {
	Connection conn;
	ConnectionDB con = new ConnectionDB();
	Statement stmt;
	
	
	public ObservableList<BuildingModel> getRoomListt(int i) {
		ObservableList<BuildingModel> notices = FXCollections.observableArrayList();
		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query="";
			if(i==0)
			query = "SELECT * FROM `tbldorm` WHERE isempty is false and RoomNumber !=0 order by DormNumber";
			else
			query = "SELECT * FROM `tbldorm` WHERE isempty is true and RoomNumber !=0 order by DormNumber";
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			while (rs.next()) {
			
				notices.add(new BuildingModel(Integer.parseInt(rs.getString("DormId")), Boolean.parseBoolean(rs.getString("IsEmpty")), Integer.parseInt(rs.getString("RoomNumber")),
						Integer.parseInt(rs.getString("DormNumber"))));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.closeConn(conn);
		}
		return notices;
	}
	public boolean updateBuilding(int dormNumber, int roomNumber,int isEmptyFlag)
	{
		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query="Update tblDorm set isempty ='"+isEmptyFlag+"' WHERE DormNumber = " + dormNumber+" AND DormNumber="+ dormNumber;
			System.out.println("update query = "+query);
			stmt.executeUpdate(query);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
			// TODO: handle exception
		}
		
	}
}
