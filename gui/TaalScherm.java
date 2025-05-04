package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TaalScherm extends BorderPane {
	
	
	   private Stage stage;
	    private Scene scene;
	    private Parent root;
	    private Stage Hoofd;
	    private Scene hoofdmenu;
	
    @FXML
    private Pane TaalLabel;

    @FXML
    private Button btnEnglish;

    @FXML
    private Button btnNederlands;

    @FXML
    private Label labelTaal;
    


    @FXML
   private void taalEngels(ActionEvent event) throws IOException {
    	//Parent root = FXMLLoader.load(getClass().getResource("gui/TaalScherm.fxml"));
    	System.out.println("English selected.");
    	getScene().setRoot(new Hoofdmenu());
   
    	

    }
    
    

    @FXML
    private void taalNederlands(ActionEvent event) throws IOException {
    	System.out.println("Nederlands geselecteerd.");
    	getScene().setRoot(new Hoofdmenu());

    	
    }
    
    public TaalScherm(DomeinController dc) {
    	super();
    	loadFxmlScreen("TaalScherm.fxml");
    	
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


