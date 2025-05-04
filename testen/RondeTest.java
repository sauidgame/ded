package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Bonusfiche;
import domein.Ronde;

class RondeTest {

	private Ronde ronde;

	@BeforeEach
	void setUp() {
		ronde = new Ronde();
	}

	@Test
	void getVolgnummer_InitieelNul() {
		assertEquals(0, ronde.getVolgnummer());
	}

	@Test
	void setVolgnummer_VolgnummerWordtCorrectVerhoogd() {
		ronde.setVolgnummer(0);
		ronde.setVolgnummer(0);
		assertEquals(1, ronde.getVolgnummer());
	}

	@Test
	void getAantalBonusfiches_InitieelNul() {
		assertEquals(0, ronde.getAantalBonusfiches());
	}

	@Test
	void setAantalBonusfiches_AantalWordtCorrectIngesteld() {
		ronde.setAantalBonusfiches(5);
		assertEquals(5, ronde.getAantalBonusfiches());
	}

	@Test
	void bepaalPlaatsBonusfiche_AantalBonusfichesKleinerGelijkAanVijf_FicheWordtToegevoegd() {
		ronde.setAantalBonusfiches(3);
		List<Bonusfiche> fiches = ronde.bepaalPlaatsBonusfiche();
		assertFalse(fiches.isEmpty());
	}

	@Test
	void bepaalPlaatsBonusfiche_AantalBonusfichesGroterDanVijf_FicheWordtNietToegevoegd() {
		ronde.setAantalBonusfiches(6);
		List<Bonusfiche> fiches = ronde.bepaalPlaatsBonusfiche();
		assertTrue(fiches.isEmpty());
	}
}