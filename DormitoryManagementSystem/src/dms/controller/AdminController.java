package dms.controller;

import java.net.URL;
import java.util.ResourceBundle;

import dms.bll.Building;
import dms.bll.Facility;
import dms.model.DormFacilityModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author sureshadhikari
 *
 */
public class AdminController implements Initializable {

	Facility objFacility;

	@FXML
	ComboBox<String> rBuildingNumber;

	@FXML
	TextField bBuildingNumber;

	@FXML
	ComboBox<String> bBuildingNumberCombo;

	@FXML
	ComboBox<String> bRoomNumberCombo;

	@FXML
	TextField raName;

	@FXML
	TextField raUserName;

	@FXML
	TextField raPassWord;

	@FXML
	TextField roomNumber;

	@FXML
	ComboBox<String> fBuildingNumber;

	@FXML
	TextField facilityName;

	@FXML
	private TableView<DormFacilityModel> tbldormfacility = new TableView<>();
	@FXML
	private TableColumn<DormFacilityModel, String> dormFacility = new TableColumn<>("facility");

	@FXML
	private void loadTable() {
		dormFacility.setCellValueFactory(new PropertyValueFactory<>("facility"));
		try {
			tbldormfacility.setItems(new Facility(Integer.parseInt(fBuildingNumber.getValue())).getFacilities());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		rBuildingNumber.setValue("Select");
		try {
			ObservableList<String> obserVableList = new Building().getDorms();

			rBuildingNumber.setItems(obserVableList);

			fBuildingNumber.setValue("Select");
			fBuildingNumber.setItems(obserVableList);

			bBuildingNumberCombo.setValue("Select");
			bBuildingNumberCombo.setItems(obserVableList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void btnRoomAddClick() throws Exception {
		if (!roomNumber.getText().equals("")) {

			if (new Building().addRoom(rBuildingNumber.getValue(), roomNumber.getText())) {
				rBuildingNumber.setValue("Select");
				roomNumber.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success!");
				alert.setContentText("Data saved successfully !!!");
				
				alert.showAndWait();
			} else {
				System.out.println("Failed");
			}
		} else {
			roomNumber.setText("Number is required.");
		}
	}

	@FXML
	private void btnBuildingAddClick() throws Exception {

		if (!bBuildingNumber.equals("")) {
			if (new Building().addBuilding(bBuildingNumber.getText())) {
				bBuildingNumber.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success!");
				alert.setContentText("Data saved successfully !!!");
				alert.showAndWait();
				ObservableList<String> obserVableList = new Building().getDorms();

				rBuildingNumber.setItems(obserVableList);

				fBuildingNumber.setValue("Select");
				fBuildingNumber.setItems(obserVableList);

				bBuildingNumberCombo.setValue("Select");
				bBuildingNumberCombo.setItems(obserVableList);
			} else {
				System.out.println("Failed");
			}
		}

	}

	@FXML
	private void btnAssignRAClick() throws Exception {

		if (new Building().assignRA(bBuildingNumberCombo.getValue(), raName.getText(), raUserName.getText(),
				raPassWord.getText(), bRoomNumberCombo.getValue())) {
			bBuildingNumberCombo.setValue("Select");
			raName.clear();
			raUserName.clear();
			raPassWord.clear();
			bRoomNumberCombo.getSelectionModel().clearSelection();
			bRoomNumberCombo.getItems().clear();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success!");
			alert.setContentText("Data saved successfully !!!");
			alert.showAndWait();
		} else {
			System.out.println("Failed");
		}

	}

	@FXML
	private void btnFacilityAddClick() throws Exception {

		if (new Facility(Integer.parseInt(fBuildingNumber.getValue())).addFacility(facilityName.getText())) {
			loadTable();
			facilityName.clear();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success!");
			alert.setContentText("Data saved successfully !!!");
			alert.showAndWait();
		} else {
			System.out.println("Failed");
		}
	}

	@FXML
	void bldOnAction(ActionEvent event) {
		loadTable();
	}

	@FXML
	void bBuildingComboOnAction(ActionEvent event) {
		bRoomNumberCombo.setValue("Select");
		try {
			bRoomNumberCombo.setItems(new Building().getRooms(bBuildingNumberCombo.getValue()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
