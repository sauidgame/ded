package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Year;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Speler;
import utils.SpelerKleur;

class SpelerTest {
	private Speler speler;
	@BeforeEach
    void setUp() {
        // Reset de lijst alleSpelers voor elke test
        Speler.resetAlleSpelers();
    }
	
	

	@Test
	void maakSpeler_alleGegevensCorrect_maaktObject() {
		speler = new Speler("avatar", 2003, 4, 25, "Groen");
		Assertions.assertEquals("avatar", speler.getGebruikersnaam());
		Assertions.assertEquals(2003, speler.getGeboortejaar());
		Assertions.assertEquals(4, speler.getAantalOverwinningen());
		Assertions.assertEquals(25, speler.getAantalGespeeld());
		Assertions.assertEquals(SpelerKleur.GROEN, speler.getKleur());//Enum gebruikt
	}

	@Test
	void maakSpeler_correcteGebruikersnaamGeboortejaar_maaktObject() {
		speler = new Speler("avatar", 2003);
		Assertions.assertEquals("avatar", speler.getGebruikersnaam());
		Assertions.assertEquals(2003, speler.getGeboortejaar());
		Assertions.assertEquals(0, speler.getAantalOverwinningen());
		Assertions.assertEquals(0, speler.getAantalGespeeld());
	}

	// Als de naam kleiner is dan 6 word er exception gegooid
	@Test

	void OngeldigeGebruikersnaam_TeKort_werpException() {

		assertThrows(IllegalArgumentException.class, () -> new Speler("Ava", 2000));
	}

	// Als de gebruikersNaam leeg is wordt er exception gegooid
	@Test

	void OngeldigeGebruikersnaam_Leeg_werpException() {

		assertThrows(IllegalArgumentException.class, () -> new Speler("  ", 2000));

	}

	// Als de naam al bestaat wordt er exception gegooid
	@Test
	void OngeldigeGebruikersnaam_NaamBestaatAll_werpException() {

		new Speler("Avatar1234", 2000); // Eerste keer moet lukken

		assertThrows(IllegalArgumentException.class, () -> {
			new Speler("Avatar1234", 1995);// Tweede keer moet falen
		});
	}

	// Als de Gebruiker een geboortejaar geeft dat in de toekomst ligt
	@Test

	void OngeldigGeboortejaar_ToekomstigGeboorteJaar_werpException() {

		int leeftijd = Year.now().getValue() + 1;// huidige jaar +1 -> 25+1 = 2026 ,Exception wordt gegooid
		assertThrows(IllegalArgumentException.class, () -> {
			new Speler("Avatar1234", leeftijd);
		});
	}

	// Als de Gebruiker te jong is word er exception gegooid
	@Test
	void OngeldigGeboortejaar_TeJong_werpException() {

		int huidigJaar = Year.now().getValue();// 2025-5 = 2020 ,dus leeftijd is jonger van 6 ,Exception wordt gegooid
		int teJong = huidigJaar - 5; // 5 jaar oud
		assertThrows(IllegalArgumentException.class, () -> {
			new Speler("Avatar1234", teJong);
		});
	}

	// Als de Gebruiker te oud is word er exception gegooid
	@Test

	void OngeldigGeboortejaar_TeOud_werpException() {

		int huidigJaar = Year.now().getValue();// 2025 -101 = 1924 ,leeftijd is oudere dan 100,Exception wordt gegooid
		int teOud = huidigJaar - 101; // 101 jaar oud
		assertThrows(IllegalArgumentException.class, () -> {
			new Speler("Avatar1234", teOud);
		});
	}

	// Als de Antaal gewonnen en gespeeld negatief is word er Exception gegooid
	@Test
	void NieuweSpeler_ZonderOverwinningenEnGespeeldeSpellen() {

		Speler speler = new Speler("NieuweSpeler", 2000);

		assertEquals(0, speler.getAantalOverwinningen()); // Controleer of aantalGewonnen standaard 0 is
		assertEquals(0, speler.getAantalGespeeld()); // Controleer of aantalGespeeld standaard 0 is
	}
	// push please werk
}