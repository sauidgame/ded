package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RegistreerScherm extends BorderPane {

    private DomeinController dc;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button btnRegistreer;

    @FXML
    private Button btnTerug;

    @FXML
    private TextField fldGebruikersnaam;

    @FXML
    private DatePicker klnKalender;

    @FXML
    private Label lblGebruikersnaam;

    @FXML
    private Label lblGeboorteDatum;

    @FXML
    void RegistreerSpeler(ActionEvent event) {
        try {
			if (klnKalender.getValue() == null || klnKalender.getValue().getYear() < 6 && klnKalender.getValue().getYear() > 100){
			    signaliseerFout("Selecteer een datum!\n Je leeftijd mag niet jonger dan 6 of ouder dan 100 zijn");
			    return;
			}
	        if (fldGebruikersnaam.getText() == null || fldGebruikersnaam.getText().isBlank()) {
				    signaliseerFout("Voer een naam in!");
				    return;
				    
			}
			dc.registreerSpeler(fldGebruikersnaam.getText(), klnKalender.getValue().getYear());
			loadFxmlScreen("Hoofdmenu.fxml");
			} catch (Exception e) {
				signaliseerFout("Error");			
			e.printStackTrace();
		}
    }

    @FXML
    void gebruikersnaamFeld(ActionEvent event) {
    	
    }
    

    @FXML
    void registreerDatum(ActionEvent event) {
    }

    @FXML
    void terugNaarVorigeScherm(ActionEvent event) throws IOException {
    	getScene().setRoot(new Hoofdmenu());

    }

    private void loadFxmlScreen(String name) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
        loader.setRoot(this);
        loader.setController(this);
        try {
            root = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public RegistreerScherm(DomeinController dc) {
        this.dc = dc;
        loadFxmlScreen("Registreerscherm.fxml");
    }

    private void signaliseerFout(String melding) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Foutieve invoer");
        alert.setContentText(melding);
        alert.showAndWait();
    }
}
