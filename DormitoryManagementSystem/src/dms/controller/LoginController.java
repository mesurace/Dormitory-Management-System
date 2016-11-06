package dms.controller;

import dms.bll.Login;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * @author sureshadhikari
 *
 */
public class LoginController {

	public static int BUILDING_NUMBER = 141;
	public static int STUDENT_ID = 1;

	Login logIn;

	@FXML
	TextField thisUserName;

	@FXML
	PasswordField thisPassword;

	@FXML
	Label lblmsg;

	@FXML
	BorderPane rootLayout;

	@FXML
	ComboBox<String> comboUserType;

	@FXML
	private void btnLoginClick() throws Exception {
		lblmsg.setTextFill(Paint.valueOf("RED"));
		lblmsg.setText("");
		String userName = thisUserName.getText();
		String passWord = thisPassword.getText();
		if (!userName.equals("") && !passWord.equals("")) {
			logIn = new Login();
			boolean loginCheck = logIn.logIn(userName, passWord, comboUserType.getValue().toUpperCase());
			String menuUrl = "";
			int height = 400;
			int width = 500;
			if (loginCheck) {

				switch (comboUserType.getValue().toUpperCase()) {
				case "STUDENT":
					int[] stdInfo=logIn.getStudentLoginInfo(userName,passWord);
					STUDENT_ID=stdInfo[0];
					BUILDING_NUMBER=stdInfo[1];
					menuUrl = "../view/Student.fxml";
					break;
				case "RA":
					
					BUILDING_NUMBER=logIn.getRABuildingNumber(userName,passWord);
					width = 700;
					height = 700;
					menuUrl = "../view/RA.fxml";
					break;

				case "ADMIN":
					menuUrl = "../view/Admin.fxml";
					break;
				default:
					menuUrl = "../view/Admin.fxml";
				}

				Stage st = (Stage) rootLayout.getScene().getWindow(); // Load
																		// root
																		// layout
																		// from
																		// fxml
																		// file.
				Parent root = FXMLLoader.load(getClass().getResource(menuUrl));
				Scene scene = new Scene(root, width, height); // size of
																// fxml(same as
																// view)
				scene.getStylesheets().add(getClass().getResource("../css/Login.css").toExternalForm());
				st.setScene(scene);
				st.setX(150);
				st.setY(50);
				st.show();

			} else {
				lblmsg.setText("Login Failed. Invalid UserName/Password");
			}
		} else {
			lblmsg.setText("Please enter Username/Password");
		}

	}

	@FXML
	private void btnCancelClick() throws Exception {
		thisUserName.clear();
		this.thisPassword.clear();

	}

	@FXML
	private void setUserType() throws Exception {

	}

}
