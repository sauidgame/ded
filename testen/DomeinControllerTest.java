package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.DomeinController;
import domein.Speler;
import utils.gebouwKleuren;

class DomeinControllerTest {

	private DomeinController domeinController;
	private Speler speler1;
	private Speler speler2;
	private Speler speler3;
	private List<Speler> spelers;

	@BeforeEach
	void setUp() {
		domeinController = new DomeinController();
		speler1 = new Speler("Speler1", 1990);
		speler2 = new Speler("Speler2", 1985);
		speler3 = new Speler("Speler3", 2000);
		spelers = new ArrayList<>();
		spelers.add(speler1);
		spelers.add(speler2);
		spelers.add(speler3);
	}

	@Test
	void registreerSpeler_SpelerWordtCorrectGeregistreerd() {
		domeinController.registreerSpeler("NieuweSpeler", 1995);
		Speler geregistreerdeSpeler = domeinController.getSpeler("NieuweSpeler");
		assertNotNull(geregistreerdeSpeler);
		assertEquals("NieuweSpeler", geregistreerdeSpeler.getGebruikersnaam());
		assertEquals(1995, geregistreerdeSpeler.getGeboortejaar());
	}

	@Test
	void geefAlleBeschikbareSpelers_GeeftLijstVanSpelers() {
		domeinController.registreerSpeler("TestSpeler1", 1980);
		domeinController.registreerSpeler("TestSpeler2", 1975);
		List<Speler> beschikbareSpelers = domeinController.geefAlleBeschikbareSpelers();
		assertFalse(beschikbareSpelers.isEmpty());
	}

	@Test
	void getSpeler_GeeftCorrecteSpeler() {
		domeinController.registreerSpeler("ZoekSpeler", 1988);
		Speler gevondenSpeler = domeinController.getSpeler("ZoekSpeler");
		assertNotNull(gevondenSpeler);
		assertEquals("ZoekSpeler", gevondenSpeler.getGebruikersnaam());
	}

	@Test
	void startNieuwSpel_SpelWordtGestart() {
		domeinController.startNieuwSpel(spelers);
		assertNotNull(domeinController.bepaalStartSpeler());
	}

	@Test
	void geefWinnaar_GeeftWinnaar() {
		domeinController.startNieuwSpel(spelers);
		assertNotNull(domeinController.geefWinnaar());
	}

	@Test
	void bepaalStartSpeler_GeeftStartSpeler() {
		domeinController.startNieuwSpel(spelers);
		assertNotNull(domeinController.bepaalStartSpeler());
	}

	@Test
	void bepaalVolgordeSpelersGebruikersnaam_GeeftLijstVanGebruikersnamen() {
		domeinController.startNieuwSpel(spelers);
		List<String> gebruikersnamen = domeinController.bepaalVolgordeSpelersGebruikersnaam();
		assertFalse(gebruikersnamen.isEmpty());
		assertEquals(spelers.size(), gebruikersnamen.size());
	}

	@Test
	void toonVolgnummerVanRonde_GeeftVolgnummer() {
		int volgnummer = domeinController.toonVolgnummerVanRonde();
		assertEquals(0, volgnummer);
	}

	@Test
	void toonresultaten1rolbeurt_GeeftLijstVanGebouwKleuren() {
		domeinController.startNieuwSpel(spelers);
		List<gebouwKleuren> dobbelstenenLijst = new ArrayList<>();
		dobbelstenenLijst.add(gebouwKleuren.BLAUW);
		dobbelstenenLijst.add(gebouwKleuren.GROEN);
		List<gebouwKleuren> resultaten = domeinController.toonresultaten1rolbeurt(dobbelstenenLijst, 0);
		assertFalse(resultaten.isEmpty());
	}

	@Test
	void plaatsZetsteen_GeeftStringResultaat() {
		domeinController.startNieuwSpel(spelers);
		String resultaat = domeinController.plaatsZetsteen(speler1, gebouwKleuren.BRUIN, 2, 1);
		assertNotNull(resultaat);
	}
}