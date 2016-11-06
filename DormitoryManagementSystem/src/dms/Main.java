package dms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * @author sureshadhikari
 *
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		 primaryStage.initStyle(StageStyle.DECORATED);
		 
		Parent root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
		primaryStage.setTitle("Dormitory Management System");
		Scene scene = new Scene(root, 300, 450);
		primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("css/Login.css").toExternalForm());
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
