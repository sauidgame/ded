package gui;



import domein.DomeinController;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUp extends Application {
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
            //TaalScherm root = new TaalScherm();
			DomeinController dc = new DomeinController();
			TaalScherm root = new TaalScherm(dc);
			Scene scene = new Scene(root, 1200, 800);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}