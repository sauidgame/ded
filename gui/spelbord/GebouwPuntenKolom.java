package gui.spelbord;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import utils.GebouwKleur;
import utils.SpelerKleur;

/**
 * Kolom met 12 cirkels, om gebouwpunten te tonen.
 */
public class GebouwPuntenKolom extends VBox {

	public GebouwPuntenKolom(GebouwKleur gebouwKleur) {
		super();
		// voeg 12 Circles toe, met juiste kleur die hoort bij gebouw
		for (int i = 1; i <= 12; i++) {
            Circle circle = new Circle();
            circle.setRadius(20);
            circle.setFill(gebouwKleur.getKleur());
            circle.setStroke(gebouwKleur.getKleur());
            getChildren().add(circle);
        }
	}
	
	public void setCircleColor(int index, SpelerKleur spelerKleur) {
		Circle circle = (Circle) getChildren().get(index);
		circle.setFill(spelerKleur.getKleur());
	}
	
}