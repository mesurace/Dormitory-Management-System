/**
 * 
 */
package dms.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dms.bll.Complain;
import dms.bll.Complainbll;
import dms.bll.Notice;
import dms.bll.Student;
import dms.model.ComplainModel;
import dms.model.DormFacilityModel;
import dms.model.NoticeModel;
import dms.model.StudentModel1;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author sureshadhikari
 *
 */
public class StudentController implements Initializable {

	public static int BUILDING_NUMBER = LoginController.BUILDING_NUMBER;
	public static int STUDENT_ID = LoginController.STUDENT_ID;

	Student student;
	Notice notice = new Notice(BUILDING_NUMBER);
	@FXML
	private TableView<NoticeModel> tblNotice = new TableView<>();
	@FXML
	private TableColumn<NoticeModel, String> NoticeColumn = new TableColumn<>("Notice");
	@FXML
	private TableColumn<NoticeModel, String> NoticeDateColumn = new TableColumn<>("NoticeDate");

	@FXML
	private Label name;

	@FXML
	private Label email;

	@FXML
	private Label country;

	@FXML
	private Label entry;

	@FXML
	private Label building;

	@FXML
	private Label room;

	@FXML
	private TextField complain;

	
	
	Complain complainObject = new Complain();
	
	@FXML
	private TableView<ComplainModel> tblStudentComplain = new TableView<>();
	
	@FXML
	private TableColumn<ComplainModel, String> ComplainDateColumn = new TableColumn<>("ComplainDate");
	@FXML
	private TableColumn<ComplainModel, String> ComplainColumn = new TableColumn<>("Complain");

	@FXML
	private void loadTable() {

		NoticeColumn.setCellValueFactory(new PropertyValueFactory<>("Notice"));
		NoticeDateColumn.setCellValueFactory(new PropertyValueFactory<>("NoticeDate"));
		ObservableList<NoticeModel> data = notice.getNoticeList();
		tblNotice.setItems(data);

	}
	
	@FXML
	private void loadComplainTable() {

		ComplainDateColumn.setCellValueFactory(new PropertyValueFactory<>("ComplainDate"));
		ComplainColumn.setCellValueFactory(new PropertyValueFactory<>("Complain"));
		ObservableList<ComplainModel> data = complainObject.getComplainListById();
		
		tblStudentComplain.setItems(data);

	}

	

	@FXML
	void onComplainSendAction(ActionEvent event) {
		if(new Complain().sendComplain(complain.getText(), LocalDate.now()))
		{
			loadComplainTable();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success!");
			complain.clear();
			
			alert.setContentText("Data saved successfully !!!");
			alert.showAndWait();
		} else {
			System.out.println("Failed");
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadTable();
		loadComplainTable();
		student = new Student(STUDENT_ID);
		StudentModel1 sm = student.getStudentInfo();
		name.setText(sm.getFirstName() + " " + sm.getLastName());
		email.setText(sm.getEmail());
		country.setText(sm.getCountry());
		entry.setText(sm.getIntake());
		building.setText(sm.getBuildingNumber());
		room.setText(sm.getRoomNumber());

	}

}
