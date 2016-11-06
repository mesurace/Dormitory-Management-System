package dms.controller;

/**
 * @author surajlama
 *
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.mysql.cj.api.jdbc.Statement;
import com.mysql.cj.fabric.xmlrpc.base.Struct;

import dms.Main;
import dms.bll.Building;
import dms.bll.Buildingbll;
import dms.bll.Complainbll;
import dms.bll.Noticebll;
import dms.bll.Studentbll;
import dms.model.BuildingModel;
import dms.model.ComplainModel;
import dms.model.Notice;
import dms.model.StudentModel;
import dms.util.ConnectionDB;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RAController {
	Connection conn;

	@FXML
	private void initialize() {
		// System.out.println("table");
		loadComplainTable();
		loadTable();
		loadOccupiedRoomTable();
		loadUnOccupiedRoomTable();
		loadRunningStudentTable();
		// Main main = new Main();
		// main.hideLogin();
		cmbNotice.setValue("--Select--");
		cmbNotice.setItems(noticebll.getNoticeOnlyList());

		cmbIntake.setValue("--Select--");
		cmbIntake.setItems(intakeList);

		// cmbBuilding.setValue("--Select--");
		// cmbBuilding.setItems(new Building().getDorms());

		cmbStudentIds.setValue("--Select--");
		cmbStudentIds.setItems(studentbll.getStudentIdList());

		lblBuildingNumber.setText(String.valueOf(BUILDING_NUMBER));

		cmbRoomNumber.setValue("--Select--");
		cmbRoomNumber.setItems(new Building().getRooms((String.valueOf(BUILDING_NUMBER))));

	}

	ObservableList<String> intakeList = FXCollections.observableArrayList("Fall", "Spring");
	public static int BUILDING_NUMBER = LoginController.BUILDING_NUMBER;
	Noticebll noticebll = new Noticebll();
	Studentbll studentbll = new Studentbll();
	Complainbll complainbll = new Complainbll();
	Buildingbll buildingbll = new Buildingbll();
	private IntegerProperty indexOfNotice = new SimpleIntegerProperty();
	// Notice block
	@FXML
	private Button btnDeleteNotice;
	@FXML
	private TableView<Notice> tblNotice = new TableView<>();
	/*
	 * @FXML private TableColumn<Student, Integer> NoticeIdColumn = new
	 * TableColumn<>("NoticeId");
	 */
	@FXML
	private TableColumn<Notice, String> BuildingNumberColumn = new TableColumn<>("BuildingNumber");
	@FXML
	private TableColumn<Notice, Integer> NoticeColumn = new TableColumn<>("Notice");
	@FXML
	private TableColumn<Notice, Integer> NoticeDateColumn = new TableColumn<>("NoticeDate");
	@FXML
	private ComboBox cmbNotice;

	@FXML
	private TableView<ComplainModel> tblComplain = new TableView<>();
	@FXML
	private TableColumn<ComplainModel, Integer> ComplainIdColumn = new TableColumn<>("ComplainId");
	@FXML
	private TableColumn<ComplainModel, Integer> StudentIdColumn = new TableColumn<>("StudentId");
	@FXML
	private TableColumn<ComplainModel, String> ComplainDateColumn = new TableColumn<>("ComplainDate");
	@FXML
	private TableColumn<ComplainModel, String> ComplainColumn = new TableColumn<>("Complain");

	//
	@FXML
	private TableView<BuildingModel> tblOccupied = new TableView<>();
	@FXML
	private TableColumn<BuildingModel, Integer> occupiedDormIdColumn = new TableColumn<>("DormId");
	@FXML
	private TableColumn<BuildingModel, Integer> occupiedRoomNumberColumn = new TableColumn<>("RoomNumber");
	@FXML
	private TableColumn<BuildingModel, Integer> occupiedDormNumberColumn = new TableColumn<>("DormNumber");

	@FXML
	private void loadOccupiedRoomTable() {

		occupiedDormIdColumn.setCellValueFactory(new PropertyValueFactory<>("DormId"));
		occupiedRoomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("RoomNumber"));
		occupiedDormNumberColumn.setCellValueFactory(new PropertyValueFactory<>("DormNumber"));
		ObservableList<BuildingModel> data = buildingbll.getRoomListt(0);
		tblOccupied.setItems(data);

	}

	//
	//
	@FXML
	private TableView<BuildingModel> tblUnOccupied = new TableView<>();
	@FXML
	private TableColumn<BuildingModel, Integer> unOccupiedDormIdColumn = new TableColumn<>("DormId");
	@FXML
	private TableColumn<BuildingModel, Integer> unOccupiedRoomNumberColumn = new TableColumn<>("RoomNumber");
	@FXML
	private TableColumn<BuildingModel, Integer> unOccupiedDormNumberColumn = new TableColumn<>("DormNumber");

	@FXML
	private void loadUnOccupiedRoomTable() {

		unOccupiedDormIdColumn.setCellValueFactory(new PropertyValueFactory<>("DormId"));
		unOccupiedRoomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("RoomNumber"));
		unOccupiedDormNumberColumn.setCellValueFactory(new PropertyValueFactory<>("DormNumber"));
		ObservableList<BuildingModel> data = buildingbll.getRoomListt(1);
		tblUnOccupied.setItems(data);

	}
	//

	@FXML
	private void loadComplainTable() {

		ComplainIdColumn.setCellValueFactory(new PropertyValueFactory<>("ComplainId"));
		StudentIdColumn.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
		ComplainDateColumn.setCellValueFactory(new PropertyValueFactory<>("ComplainDate"));
		ComplainColumn.setCellValueFactory(new PropertyValueFactory<>("Complain"));
		ObservableList<ComplainModel> data = complainbll.getComplainList();
		for (ComplainModel cmb : data) {
			System.out.println(cmb.ComplainId);
			System.out.println(cmb.StudentId);
			System.out.println(cmb.ComplainDate);
			System.out.println(cmb.Complain);
		}
		tblComplain.setItems(data);

	}

	@FXML
	private void loadTable() {

		BuildingNumberColumn.setCellValueFactory(new PropertyValueFactory<>("BuildingNumber"));
		NoticeColumn.setCellValueFactory(new PropertyValueFactory<>("Notice"));
		NoticeDateColumn.setCellValueFactory(new PropertyValueFactory<>("NoticeDate"));
		ObservableList<Notice> data = noticebll.getNoticeList();
		tblNotice.setItems(data);

	}

	@FXML
	private Button btnSaveNotice;
	@FXML
	private TextField txtNotice;

	@FXML
	private DatePicker dtNoticeDate;

	@FXML
	void SaveNotice(ActionEvent event) {
		System.out.println("Save");
		// noticebll.SaveNotice(new Notice());
		try {
			LocalDate localDate = dtNoticeDate.getValue();
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			Date date = Date.from(instant);
			Notice notice = new Notice();
			notice.NoticeId = 0;
			notice.BuildingNumber = BUILDING_NUMBER;
			notice.Notice = txtNotice.getText();
			notice.NoticeDate = localDate.toString();
			System.out.print("hi : " + notice.NoticeDate);
			if (noticebll.SaveNotice(notice)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Data saved successfully");

				alert.showAndWait();
				tblNotice.setItems(noticebll.getNoticeList());

				txtNotice.clear();
				txtNoticeId.clear();
				dtNoticeDate.setValue(null);
				cmbNotice.setValue("--Select--");
				cmbNotice.setItems(noticebll.getNoticeOnlyList());
			}

		} catch (Exception e) {
			System.out.println("My error " + e.getMessage());
		}

	}

	@FXML
	private Button btnCancelNotice;
	@FXML
	private TextField txtNoticeId;

	@FXML
	void ClearNoticeForm(ActionEvent event) {
		txtNotice.clear();
		txtNoticeId.clear();
		dtNoticeDate.setValue(null);
	}

	/*
	 * public void onDeleteItem(ActionEvent event) { int i =
	 * indexOfNotice.get(); tblNotice.getSelectionModel().clearSelection(); }
	 */
	@FXML
	void DeleteSelectedNotice(ActionEvent event) {
		try {
			String noticeToBeDeleted;
			noticeToBeDeleted = (String) cmbNotice.getValue();
			if (noticeToBeDeleted.equals("--Select--")) {
				return;
			}
			if (noticebll.DeleteNotice(noticeToBeDeleted)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Data deleted successfully");

				alert.showAndWait();
				tblNotice.setItems(noticebll.getNoticeList());
				cmbNotice.setValue("--Select--");
				cmbNotice.setItems(noticebll.getNoticeOnlyList());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	// Notice block end
	// Student block
	@FXML
	private Label lblBuildingNumber;

	@FXML
	private ComboBox cmbIntake;
	// @FXML
	// private ComboBox cmbBuilding;
	@FXML
	private ComboBox cmbRoomNumber;

	// @FXML
	// void BuildingNumberSelectedAction(ActionEvent event) {
	// System.out.println("dd changed = " + (String) cmbBuilding.getValue());
	//
	// }

	@FXML
	private Button btnSaveStudent;

	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtStudentId;
	@FXML
	private TextField txtCountry;
	@FXML
	private TextField txtVisaStatus;
	@FXML
	private DatePicker dtArrivalDate;
	@FXML
	private DatePicker dtDepartureDate;
	@FXML
	private TextField txtUserName;
	@FXML
	private TextField txtPassword;
	@FXML
	private TextField txtEmail;
	@FXML
	ComboBox cmbStudentIds;

	@FXML
	void SaveStudent(ActionEvent event) {
		StudentModel student = new StudentModel();
		student.StudentId = 12;
		student.LastName = "";
		student.FirstName = "";
		student.Intake = "";
		student.Country = "";
		student.VisaStatus = "";
		student.BuildingNumber = 0;
		student.RoomNumber = 0;
		student.ArrivalDate = "";
		student.DepartureDate = "";
		student.UserName = "";
		student.Password = "";
		student.Email = "";
		try {
			try {
				student.StudentId = Integer.parseInt(txtStudentId.getText());
			} catch (Exception e) {
				// TODO: handle exception
				new RAController().myNotice("Enter Student Id number !!!");
				return;
			}
			student.LastName = txtLastName.getText();
			student.FirstName = txtFirstName.getText();
			student.Intake = (String) cmbIntake.getValue();
			try {
				student.BuildingNumber = BUILDING_NUMBER;
				// student.BuildingNumber = Integer.parseInt((String)
				// cmbBuilding.getValue());
				student.RoomNumber = Integer.parseInt((String) cmbRoomNumber.getValue());

			} catch (Exception e) {
				// TODO: handle exception
				new RAController().myNotice("Choose Dorm number and Room number !!!");
				return;
			}
			if (student.Intake.equals("--Select--")) {
				new RAController().myNotice("Choose Intake !!!");
				return;
			}
			try {
				LocalDate localDate = dtArrivalDate.getValue();
				System.out.println("locadate " + localDate);
				Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
				Date date = Date.from(instant);
				/*
				 * int year=localDate.getYear(); int
				 * month=localDate.getMonthValue(); int
				 * day=localDate.getDayOfMonth();
				 * System.out.println(year+"/"+month+"/"+day);
				 */
				student.ArrivalDate = localDate.toString();
			} catch (Exception e) {
				new RAController().myNotice("Insert Arrival date !!!");
				return;
				// TODO: handle exception
			}
			try {
				LocalDate localDate = dtDepartureDate.getValue();
				System.out.println("locadate " + localDate);
				Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
				Date date = Date.from(instant);
				/*
				 * int year=localDate.getYear(); int
				 * month=localDate.getMonthValue(); int
				 * day=localDate.getDayOfMonth();
				 * System.out.println(year+"/"+month+"/"+day);
				 */
				student.DepartureDate = localDate.toString();
			} catch (Exception e) {
				new RAController().myNotice("You did not insert Departure date !!!");
				return;
				// TODO: handle exception
			}
			student.UserName = txtUserName.getText();
			if (student.UserName.equals("")) {
				new RAController().myNotice("Insert UserName !!!");
				return;
			}
			student.Password = txtPassword.getText();
			if (student.Password.equals("")) {
				new RAController().myNotice("Insert password !!!");
				return;
			}
			student.Email = txtEmail.getText();
			student.Country = txtCountry.getText();
			student.VisaStatus = txtVisaStatus.getText();
			System.out.println("called bro : " + student.Country);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
			// TODO: handle exception
		}
		if (studentbll.SaveStudent(student)) {
			new RAController().myNotice("Data saved successfully !!!");
			cmbStudentIds.setValue("--Select--");
			cmbStudentIds.setItems(studentbll.getStudentIdList());
			loadOccupiedRoomTable();
			loadUnOccupiedRoomTable();
			clearStudentForm();
			loadRunningStudentTable();
		}

	}

	@FXML
	private Button btnDeleteStudent;

	@FXML
	void DeleteStudent(ActionEvent event) {
		try {
			String studentToBeDeleted;
			studentToBeDeleted = (String) cmbStudentIds.getValue();
			if (studentToBeDeleted.equals("--Select--")) {
				return;
			}
			if (studentbll.DeleteStudent(studentToBeDeleted)) {
				new RAController().myNotice("Data deleted successfully !!!");
				cmbStudentIds.setValue("--Select--");
				cmbStudentIds.setItems(studentbll.getStudentIdList());
				loadOccupiedRoomTable();
				loadUnOccupiedRoomTable();
				loadRunningStudentTable();

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	@FXML
	private Button btnClearStudent;

	@FXML
	void ClearStudentAction(ActionEvent event) {
		clearStudentForm();
	}

	public void clearStudentForm() {
		txtStudentId.clear();
		txtLastName.clear();
		txtFirstName.clear();
		cmbIntake.setValue("--Select--");
		txtCountry.clear();
		txtVisaStatus.clear();
		// cmbBuilding.setValue("--Select--");
		cmbRoomNumber.setValue("--Select--");
		dtArrivalDate.setValue(null);
		dtDepartureDate.setValue(null);
		txtUserName.clear();
		txtPassword.clear();
		txtEmail.clear();
	}

	public void myNotice(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(msg);

		alert.showAndWait();
	}

	// student view
	@FXML
	private TableView<StudentModel> tblStudentDetail = new TableView<>();

	@FXML
	private TableColumn<StudentModel, String> StudentIdCol = new TableColumn<>("StudentId");
	@FXML
	private TableColumn<StudentModel, Integer> LastNameCol = new TableColumn<>("LastName");
	@FXML
	private TableColumn<StudentModel, Integer> FirstNameCol = new TableColumn<>("FirstName");

	@FXML
	private TableColumn<StudentModel, String> IntakeCol = new TableColumn<>("Intake");
	@FXML
	private TableColumn<StudentModel, Integer> CountryCol = new TableColumn<>("Country");
	@FXML
	private TableColumn<StudentModel, Integer> BuildingNumberCol = new TableColumn<>("BuildingNumber");

	@FXML
	private TableColumn<StudentModel, String> RoomNumberCol = new TableColumn<>("RoomNumber");
	@FXML
	private TableColumn<StudentModel, Integer> ArrivalDateCol = new TableColumn<>("ArrivalDate");
	@FXML
	private TableColumn<StudentModel, Integer> DepartureDateCol = new TableColumn<>("DepartureDate");

	@FXML
	private void loadRunningStudentTable() {
		System.out.println("view called");
		StudentIdCol.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
		FirstNameCol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
		LastNameCol.setCellValueFactory(new PropertyValueFactory<>("LastName"));

		IntakeCol.setCellValueFactory(new PropertyValueFactory<>("Intake"));
		CountryCol.setCellValueFactory(new PropertyValueFactory<>("Country"));
		BuildingNumberCol.setCellValueFactory(new PropertyValueFactory<>("BuildingNumber"));

		RoomNumberCol.setCellValueFactory(new PropertyValueFactory<>("RoomNumber"));
		ArrivalDateCol.setCellValueFactory(new PropertyValueFactory<>("ArrivalDate"));
		DepartureDateCol.setCellValueFactory(new PropertyValueFactory<>("DepartureDate"));
		ObservableList<StudentModel> data = studentbll.getRunningStudentList();
		for (StudentModel model : data) {
			System.out.println("k xa " + model.StudentId);
		}
		tblStudentDetail.setItems(data);

	}
	// student view end

	// Student block end
	// Complain block
	/*
	 * @FXML private TableView<ComplainModel> tblComplain = new TableView<>();
	 * 
	 * @FXML private TableColumn<ComplainModel, Integer> ComplainIdColumn = new
	 * TableColumn<>("ComplainId");
	 * 
	 * @FXML private TableColumn<ComplainModel, Integer> StudentIdColumn = new
	 * TableColumn<>("StudentId");
	 * 
	 * @FXML private TableColumn<ComplainModel, String> ComplainDateColumn = new
	 * TableColumn<>("ComplainDate");
	 * 
	 * @FXML private TableColumn<ComplainModel, String> ComplainColumn = new
	 * TableColumn<>("Complain");
	 * 
	 * @FXML
	 */
	/*
	 * private void loadComplainTable() {
	 * 
	 * //ComplainIdColumn.setCellValueFactory(new
	 * PropertyValueFactory<>("ComplainId"));
	 * //StudentIdColumn.setCellValueFactory(new
	 * PropertyValueFactory<>("StudentId"));
	 * //ComplainDateColumn.setCellValueFactory(new
	 * PropertyValueFactory<>("ComplainDate"));
	 * ComplainColumn.setCellValueFactory(new
	 * PropertyValueFactory<>("Complain")); ObservableList<ComplainModel> data =
	 * complainbll.getComplainList(); for(ComplainModel cmb : data) {
	 * System.out.println(cmb.ComplainDate); } tblComplain.setItems(data);
	 * 
	 * }
	 */
	// Complain block end
	// Room block

	// Room block end

}
