package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Bonusfiche;
import domein.LaatsteRonde;

class LaatsteRondeTest {

	private LaatsteRonde laatsteRonde;

	@BeforeEach
	void setUp() {
		laatsteRonde = new LaatsteRonde();
	}

	@Test
	void getAantalBonusfiches_InitieelNul() {
		assertEquals(0, laatsteRonde.getAantalBonusfiches());
	}

	@Test
	void setAantalBonusfiches_AantalWordtCorrectIngesteld() {
		laatsteRonde.setAantalBonusfiches(5);
		assertEquals(5, laatsteRonde.getAantalBonusfiches());
	}

	@Test
	void bepaalPlaatsFiche_AantalBonusfichesIsNul_StartficheWordtToegevoegd() {
		laatsteRonde.setAantalBonusfiches(0);
		List<Bonusfiche> fiches = laatsteRonde.bepaalPlaatsFiche();
		assertFalse(fiches.isEmpty());
	}

	@Test
	void bepaalPlaatsFiche_AantalBonusfichesIsTussen1En4_FicheWordtToegevoegd() {
		laatsteRonde.setAantalBonusfiches(3);
		List<Bonusfiche> fiches = laatsteRonde.bepaalPlaatsFiche();
		assertFalse(fiches.isEmpty());
	}

	@Test
	void speelEenRonde_RoeptBepaalPlaatsFicheAan() {
		laatsteRonde.speelEenRonde();
		assertNotNull(laatsteRonde.bepaalPlaatsFiche());
	}
}