package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Speler;
import domein.SpelerRepository;
import exceptions.GebruikersnaamInGebruikException;

class SpelerRepositoryTest {

	private SpelerRepository spelerRepository;
	private Speler speler;

	@BeforeEach
	void setUp() {
		spelerRepository = new SpelerRepository();
		speler = new Speler("testGebruiker", 2000);
	}

	@Test
	void voegToe_GebruikersnaamNietInGebruik_SpelerWordtToegevoegd() {
		spelerRepository.voegToe(speler);
		Speler opgehaaldeSpeler = spelerRepository.getSpeler(speler.getGebruikersnaam());
		assertEquals(speler, opgehaaldeSpeler);
	}

	@Test
	void voegToe_GebruikersnaamAlInGebruik_GooitGebruikersnaamInGebruikException() {
		spelerRepository.voegToe(speler);
		Speler nieuweSpeler = new Speler("testGebruiker", 1990);
		assertThrows(GebruikersnaamInGebruikException.class, () -> spelerRepository.voegToe(nieuweSpeler));
	}

	@Test
	void geefAlleBeschikbareSpeler_GeeftLijstVanSpelers() {
		spelerRepository.voegToe(speler);
		assertFalse(spelerRepository.geefAlleBeschikbareSpeler().isEmpty());
	}

	@Test
	void getSpeler_GeeftSpelerMetGebruikersnaam() {
		spelerRepository.voegToe(speler);
		Speler opgehaaldeSpeler = spelerRepository.getSpeler(speler.getGebruikersnaam());
		assertEquals(speler, opgehaaldeSpeler);
	}
}