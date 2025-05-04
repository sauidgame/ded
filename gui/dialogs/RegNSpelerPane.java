package gui.dialogs;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

public class RegNSpelerPane extends TitledPane {

	private TextField naamvak;
	private Button bevestigingsKnop;
	public RegNSpelerPane() {
		super();
		this.setText("Registreer nieuwe speler");
		
	}	
}