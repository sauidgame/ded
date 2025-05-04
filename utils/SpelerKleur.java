package utils;

import javafx.scene.paint.Color;

public enum SpelerKleur {
    BLAUW(Color.BLUE),
    GROEN(Color.GREEN),
    WIT(Color.WHITE),
    GEEL(Color.YELLOW),
    ORANJE(Color.ORANGE),
    ROOD(Color.RED);
	
	 private final Color kleur;
	 
	 SpelerKleur(Color kleur) {
		 this.kleur = kleur;
	}

	 public Color getKleur() {
		 return kleur;
	 }
}