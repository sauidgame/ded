package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Spel;
import domein.Speler;
import utils.SpelerKleur;

class SpelTest {
	private Spel spel;

	@BeforeEach
	void setUp() {
		spel = new Spel();
	}

	@Test
	void startNieuwSpel_metDrieSpelers_systeemMaaktNieuwSpel() {

		// Voeg spelers toe aan het spel
		spel.voegSpelerToe("Speler1", SpelerKleur.BLAUW);
		spel.voegSpelerToe("Speler2", SpelerKleur.GROEN);
		spel.voegSpelerToe("Speler3", SpelerKleur.WIT);

		// Start het spel
		spel.startSpel();

		// Controleer de spelinstelling
		List<Speler> gekozenSpelers = spel.getGekozenSpelers();
		assertEquals(3, gekozenSpelers.size());
		assertEquals(SpelerKleur.BLAUW, gekozenSpelers.get(0).getKleur());
		assertEquals(SpelerKleur.GROEN, gekozenSpelers.get(1).getKleur());
		assertEquals(SpelerKleur.WIT, gekozenSpelers.get(2).getKleur());
	}

	@Test
	void startSpel_metMinderDanMinSpelers_werpException() {

		// Voeg spelers toe aan het spel
		spel.voegSpelerToe("Speler1", SpelerKleur.BLAUW);
		spel.voegSpelerToe("Speler2", SpelerKleur.GROEN);

		// Probeer het spel te starten met minder dan 3 spelers
		assertThrows(IllegalArgumentException.class, () -> {
			spel.startSpel();
		});
	}

	@Test
	void startNieuwSpel_metMeerDanZesSpelers_werpException() {

		// Voeg spelers toe aan het spel
		spel.voegSpelerToe("Speler1", SpelerKleur.BLAUW);
		spel.voegSpelerToe("Speler2", SpelerKleur.GROEN);
		spel.voegSpelerToe("Speler3", SpelerKleur.WIT);
		spel.voegSpelerToe("Speler4", SpelerKleur.GEEL);
		spel.voegSpelerToe("Speler5", SpelerKleur.ORANJE);
		spel.voegSpelerToe("Speler6", SpelerKleur.ROOD);

		// Probeer een 7e speler toe te voegen
		assertThrows(IllegalArgumentException.class, () -> {
			spel.voegSpelerToe("Speler7", SpelerKleur.BLAUW);
		});
	}

	@Test
	void speelSpel_metDrieRondes_registreertWinnaar() {

		spel.voegSpelerToe("Speler1", SpelerKleur.BLAUW);
		spel.voegSpelerToe("Speler2", SpelerKleur.GEEL);
		spel.voegSpelerToe("Speler3", SpelerKleur.WIT);

		spel.startSpel();

		// Simuleer het spelen van het spel
		spel.speelSpel();

		// Controleer of de winnaar is geregistreerd en scores zijn bijgewerkt
		List<Speler> gekozenSpelers = spel.getGekozenSpelers();
		Speler winnaar = gekozenSpelers.get(0);
		for (Speler speler : gekozenSpelers) {
			if (speler.getAantalOverwinningen() > winnaar.getAantalOverwinningen()) {
				winnaar = speler;
			}
		}
		assertEquals(1, winnaar.getAantalOverwinningen());
		for (Speler speler : gekozenSpelers) {
			assertEquals(1, speler.getAantalGespeeld());
		}
	}
	
	
	
}