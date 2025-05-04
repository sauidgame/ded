package gui.spelbord;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.GebouwKleur;

public class Spelbord extends BorderPane {

	public Spelbord() {
		super();
		
		//Kolommen voor de gebouwen
		HBox kolomBox = new HBox();
		for (GebouwKleur sk : GebouwKleur.values()) {
			kolomBox.getChildren().add(new GebouwKolom(sk));
		}
		setCenter(kolomBox);
		
		//TODO waarderingstabellen	 -- ergens aan een rand, komt eig van bij gebouwptn, in gebouwkolom.
		//TODO: overzicht spelers (naam, kleur, 0-99 en mss gebouwpunten)
	}
	
}