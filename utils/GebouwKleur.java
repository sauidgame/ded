package utils;

import javafx.scene.paint.Color;

public enum GebouwKleur {
	BLAUW("Paviljoen", Color.BLUE), ROOD("Harem", Color.RED), BRUIN("Arcade", Color.SADDLEBROWN),
	GRIJS("Tuin", Color.GRAY), GROEN("Woning", Color.GREEN), PAARS("Toren", Color.PURPLE);

	private final String naam;
	private final Color kleur;

	GebouwKleur(String naam, Color kleur) {
		this.naam = naam;
		this.kleur = kleur;
	}

	public String getNaam() {
		return naam;
	}

	public Color getKleur() { // TODO: zie warning beschikbaarheid (javafx.graphics module)
		return kleur;
	}
}