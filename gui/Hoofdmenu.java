package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Hoofdmenu extends BorderPane

{

	private DomeinController dc;
	
    @FXML
    private Pane Hoofdmenu;

    @FXML
    private Button btnQuit;

    @FXML
    private Button btnRegels;

    @FXML
    private Button btnRegistreer;

    @FXML
    private Button btnStart;
    
    Stage stage;

    @FXML
    void bekijkRegels(ActionEvent event) {

    }

    @FXML
    void regsitreerSpeler(ActionEvent event) {
    	getScene().setRoot(new RegistreerScherm(dc));
    }
    
    @FXML
    void startSpel(ActionEvent event) {

    	getScene().setRoot(new KeuzeSpelersScherm());

    }

    @FXML
    void stopSpel(ActionEvent event) {
    	stage =(Stage) Hoofdmenu.getScene().getWindow();
    	System.out.println("Game gestopt");
    	stage.close();

    	
    }
    public Hoofdmenu() {
		 
    	super();
    	loadFxmlScreen("Hoofdmenu.fxml");
    	
	}
    
    
private void loadFxmlScreen(String name) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}
