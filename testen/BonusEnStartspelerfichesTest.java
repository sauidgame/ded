package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.BonusEnStartspelerfiches;

class BonusEnStartspelerfichesTest {

	private BonusEnStartspelerfiches bonusEnStartspelerfiches;

	@BeforeEach
	void setUp() {
		bonusEnStartspelerfiches = new BonusEnStartspelerfiches();
	}

	@Test
	void speelRonde_AantalRondesIsMinderDanOfGelijkAanTwee_RoeptRondeBepaalPlaatsBonusficheAan() {

		bonusEnStartspelerfiches.speelRonde();
		assertEquals(1, bonusEnStartspelerfiches.getAantalRondes(), "aantalRondes moet 1 zijn na de eerste ronde.");
	}

	@Test
	void speelRonde_AantalRondesIsDrie_RoeptLaatsteRondeBepaalPlaatsFicheAan() {
		bonusEnStartspelerfiches.speelRonde();
		bonusEnStartspelerfiches.speelRonde();
		bonusEnStartspelerfiches.speelRonde();
		assertEquals(3, bonusEnStartspelerfiches.getAantalRondes(), "aantalRondes moet 3 zijn na drie rondes.");
	}

	@Test
	void speelRonde_AantalRondesIsGroterDanDrie() {
		bonusEnStartspelerfiches.speelRonde();
		bonusEnStartspelerfiches.speelRonde();
		bonusEnStartspelerfiches.speelRonde();
		bonusEnStartspelerfiches.speelRonde();
		assertEquals(4, bonusEnStartspelerfiches.getAantalRondes(), "aantalRondes moet 4 zijn na vier rondes.");
	}

	@Test
	void speelRonde_MeerdereRondes() {
		bonusEnStartspelerfiches.speelRonde();
		bonusEnStartspelerfiches.speelRonde();
		bonusEnStartspelerfiches.speelRonde();
		bonusEnStartspelerfiches.speelRonde();
		assertEquals(4, bonusEnStartspelerfiches.getAantalRondes(), "aantalRondes moet correct worden bijgehouden.");
	}
}