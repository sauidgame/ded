package testen;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Bonusfiche;

class BonusficheTest {

	private Bonusfiche bonusfiche;

	@BeforeEach
	void setUp() {
		bonusfiche = new Bonusfiche();
	}

	@Test
	void getWaarde_WaardeIsCorrectIngesteld() {
		int waarde = bonusfiche.getWaarde();
		assertTrue(waarde >= 1 && waarde <= 3, "Waarde moet tussen 1 en 3 zijn.");
	}

	@Test
	void setWaarde_WaardeWordtCorrectIngesteld() {
		bonusfiche.setWaarde(5);
		int waarde = bonusfiche.getWaarde();
		assertTrue(waarde >= 1 && waarde <= 3, "Waarde moet na setWaarde tussen 1 en 3 zijn.");
	}

	@Test
	void constructor_WaardeWordtWillekeurigIngesteld() {
		assertTrue(bonusfiche.getWaarde() >= 1 && bonusfiche.getWaarde() <= 3,
				"Constructor moet waarde tussen 1 en 3 instellen.");
	}
}