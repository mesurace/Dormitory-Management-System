package dms.bll;
/**
 * @author surajlama
 *
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.cj.api.jdbc.Statement;

import dms.controller.LoginController;
import dms.model.Notice;
import dms.util.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Noticebll {
	Connection conn;
	ConnectionDB con = new ConnectionDB();
	Statement stmt;

	public ObservableList<Notice> getNoticeList() {
		// System.out.println("getNoticeList");
		ObservableList<Notice> notices = FXCollections.observableArrayList();
		try {
			// ConnectionDB con=new ConnectionDB();
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query = "select * from tblNotice  where buildingNumber="+LoginController.BUILDING_NUMBER+" order by noticeId desc";
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			while (rs.next()) {
				/*
				 * System.out.println("First Row"); for (int i = 1; i <=
				 * rs.getMetaData().getColumnCount(); i++) {
				 * System.out.println(rs.getString(i)); }
				 */
				/*
				 * Date date = new Date(); SimpleDateFormat dateFormater = new
				 * SimpleDateFormat("dd.MM.yyyy"); String formatedDate =
				 * dateFormater.format(date); System.out.println(formatedDate);
				 * System.out.println("my date: "+date);
				 */
				//
				String date1 = rs.getString("NoticeDate");
				/*
				 * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				 * Date parsedDate = format.parse(date1);
				 */
				// System.out.println("new date="+date1);
				//
				notices.add(new Notice(rs.getString("Notice"), Integer.parseInt(rs.getString("BuildingNumber")), date1,
						Integer.parseInt(rs.getString("NoticeId"))));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.closeConn(conn);
		}
		return notices;
	}

	public ObservableList<String> getNoticeOnlyList() {
		ObservableList<String> notices = FXCollections.observableArrayList();
		try {
			// ConnectionDB con=new ConnectionDB();
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query = "select * from tblNotice order by noticeId desc";
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			while (rs.next()) {

				String date1 = rs.getString("NoticeDate");
				notices.add(rs.getString("Notice"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.closeConn(conn);
		}
		return notices;
	}

	public boolean SaveNotice(Notice notice) {
		System.out.println("Save bll");

		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query = "INSERT INTO tblnotice (NoticeId,BuildingNumber,Notice,NoticeDate) VALUES ('"
					+ notice.NoticeId + "', '" + notice.BuildingNumber + "','" + notice.Notice + "','"
					+ notice.NoticeDate + "');";
			stmt.executeUpdate(query);
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		} finally {
			con.closeConn(conn);
		}

	}
	public boolean DeleteNotice(String notice) {
		System.out.println("delete notice  "+notice);

		try {
			conn = con.getConnection();
			stmt = (Statement) conn.createStatement();
			String query ="DELETE FROM tblnotice WHERE Notice = '" + notice + "'";
			System.out.println("query= "+query);
			stmt.executeUpdate(query);
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false;
		} finally {
			con.closeConn(conn);
		}

	}
}
