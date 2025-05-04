package gui.dialogs;

import java.util.Collection;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ButtonMenu extends VBox { //TODO: moet in midden van applicatie OP het spelbord verschijnen, of een apart venster zijn

	public ButtonMenu(Collection<String> MenuOptions) {
		super();
		setWidth(120);
		setHeight(300);
		for (String option : MenuOptions) {
			Button oButton = new Button(option);
			// TODO: eventHandler, listeners, bij klikken ergens gekozen knop duidelijk maken en venster sluiten
			getChildren().add(oButton);
		}
	}

}