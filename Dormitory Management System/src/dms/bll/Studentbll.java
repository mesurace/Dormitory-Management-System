package dms.bll;
/**
 * @author surajlama
 *
 */
import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.cj.api.jdbc.Statement;

import dms.model.Notice;
import dms.model.StudentModel;
import dms.util.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Studentbll {
	Connection conn;
	ConnectionDB con = new ConnectionDB();
	Statement stmt;
	public ObservableList<StudentModel> getRunningStudentList() {
		ObservableList<StudentModel> students = FXCollections.observableArrayList();
		try {
			// ConnectionDB con=new ConnectionDB();
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query = "SELECT * FROM tblstudent where isEmpty is false";
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			while (rs.next()) {
				StudentModel single = new StudentModel();
				single.StudentId=Integer.parseInt(rs.getString("StudentId"));
				single.FirstName=rs.getString("FirstName");
				single.LastName=rs.getString("LastName");
				
				single.Intake=rs.getString("Intake");
				single.Country=rs.getString("Country");
				single.BuildingNumber=Integer.parseInt(rs.getString("BuildingNumber"));
				
				single.RoomNumber=Integer.parseInt(rs.getString("RoomNumber"));
				single.ArrivalDate=rs.getString("ArrivalDate");
				single.DepartureDate=rs.getString("DepartureDate");
				students.add(single);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.closeConn(conn);
		}
		return students;
	}
	public ObservableList<String> getStudentIdList() {
		ObservableList<String> Ids = FXCollections.observableArrayList();
		try {
			// ConnectionDB con=new ConnectionDB();
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query = "SELECT * FROM `tblstudent` WHERE isempty is false order by studentId desc";
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			while (rs.next()) {

				// String date1 = rs.getString("NoticeDate");
				Ids.add(rs.getString("StudentId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.closeConn(conn);
		}
		return Ids;
	}
	public StudentModel getSingleStudentModel(String studentId)
	{
		StudentModel model= new StudentModel();
		try {
			
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query = "SELECT * FROM `tblstudent` WHERE studentId = '"+studentId+"'";
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			while (rs.next()) {

				// String date1 = rs.getString("NoticeDate");
				model.RoomNumber=Integer.parseInt(rs.getString("RoomNumber"));
				System.out.println("my room number"+model.RoomNumber);
				model.BuildingNumber=Integer.parseInt(rs.getString("BuildingNumber"));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
	
		
		return model;
	}
	public boolean DeleteStudent(String notice) {
		//System.out.println("delete notice  "+notice);

		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query ="Update tblStudent set isempty =1 WHERE StudentId = " + notice;
			System.out.println("query= "+query);
			stmt.executeUpdate(query);
			StudentModel model=getSingleStudentModel(notice);
			Buildingbll bll=new Buildingbll();
			 boolean rst=bll.updateBuilding(model.BuildingNumber, model.RoomNumber,1);
			return rst;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false;
		} finally {
			con.closeConn(conn);
		}

	}
	public boolean SaveStudent(StudentModel student) {
		System.out.println("Save bll");

		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query = "INSERT INTO tblStudent (StudentId,LastName,FirstName,Intake,Country,VisaStatus,BuildingNumber,RoomNumber,ArrivalDate,DepartureDate,UserName,Password,Email,IsEmpty) VALUES ("
					+ student.StudentId + ", '" + student.LastName + "','" + student.FirstName + "','" + student.Intake
					+ "','" + student.Country + "','" + student.VisaStatus + "'," + student.BuildingNumber + ","
					+ student.RoomNumber + ",'" + student.ArrivalDate + "','" + student.DepartureDate + "','"
					+ student.UserName + "','" + student.Password + "','" + student.Email + "','0')";
			/*String query = "INSERT INTO tblStudent (StudentId,LastName,UserName) VALUES ('" + student.StudentId + "','" + student.LastName + "','"
					+ student.UserName + "');";*/
			System.out.println(query);
			stmt.executeUpdate(query);
			Buildingbll bll=new Buildingbll();
			bll.updateBuilding(student.BuildingNumber, student.RoomNumber,0);
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("unable to save");
			System.out.println(e.getMessage());
			return false;
		} finally {
			con.closeConn(conn);
		}

	}

}
