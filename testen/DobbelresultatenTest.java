package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Dobbelresultaten;
import domein.Spel;
import domein.Speler;

class DobbelresultatenTest {

	private Dobbelresultaten dobbelresultaten;
	private Speler speler1;
	private Speler speler2;
	private List<Speler> spelers;
	private Spel spel;

	@BeforeEach
	void setUp() {
		dobbelresultaten = new Dobbelresultaten();
		speler1 = new Speler("Speler1", 1990);
		speler2 = new Speler("Speler2", 1985);
		spelers = new ArrayList<>();
		spelers.add(speler1);
		spelers.add(speler2);
		spel = new Spel();
	}

	@Test
	void setResultaat_ResultaatWordtCorrectIngesteld() {
		dobbelresultaten.setResultaat(5);
		assertEquals(5, dobbelresultaten.getResultaat());
	}

	@Test
	void getResultaat_ResultaatWordtCorrectGeretourneerd() {
		dobbelresultaten.setResultaat(8);
		assertEquals(8, dobbelresultaten.getResultaat());
	}

	@Test
	void geefBelongingToeAllesIn1_ZetstenenKleinerDanNul_RetourneertNul() {
		spel.setZetstenen(-1);
		dobbelresultaten.setSpel(spel);
		int resultaat = dobbelresultaten.geefBelongingToeAllesIn1(spelers);
		assertEquals(0, resultaat);
	}

	@Test
	void geefBelongingToeAllesIn1_AantalZetstenenIsEen_BeloningWordtToegekendAanEnigeSpeler() {
		spel.setZetstenen(1);
		dobbelresultaten.setSpel(spel);
		dobbelresultaten.setBeloning1(5);
		int resultaat = dobbelresultaten.geefBelongingToeAllesIn1(spelers);
		assertEquals(1, resultaat);
		assertEquals(5, speler1.getPunten());
	}

	@Test
	void geefBelongingToeAllesIn1_BesteEnTweedeBesteSpelerZelfde_BeloningenWordenAanBesteSpelerGegeven() {
		speler1.setPunten(10);
		speler2.setPunten(10);
		dobbelresultaten.setSpel(spel);
		dobbelresultaten.setBeloning1(5);
		dobbelresultaten.setBeloning2(3);
		int resultaat = dobbelresultaten.geefBelongingToeAllesIn1(spelers);
		assertEquals(1, resultaat);
		assertEquals(15, speler1.getPunten());
	}

	@Test
	void geefBelongingToeAllesIn1_BesteEnTweedeBesteSpelerVerschillend_BeloningenWordenAanJuisteSpelersGegeven() {
		speler1.setPunten(10);
		speler2.setPunten(5);
		dobbelresultaten.setSpel(spel);
		dobbelresultaten.setBeloning1(5);
		dobbelresultaten.setBeloning2(3);
		int resultaat = dobbelresultaten.geefBelongingToeAllesIn1(spelers);
		assertEquals(2, resultaat);
		assertEquals(15, speler1.getPunten());
		assertEquals(8, speler2.getPunten());
	}

	@Test
	void geefDeTweedeBeloningGebouwpuntenAanSpeler_RetourneertTwee() {
		dobbelresultaten.setBeloning1(5);
		int resultaat = dobbelresultaten.geefTweedeBeloningGebouwpuntenAanSpeler(5);
		assertEquals(2, resultaat);
	}
}