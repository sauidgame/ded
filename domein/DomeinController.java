package domein;

import java.util.List;

import utils.GebouwKleur;

public class DomeinController {

	private final SpelerRepository spelerRepository;

	private Spel spel1;
	private Ronde ronde = new Ronde();
	private Dobbelresultaten dbrs = new Dobbelresultaten();

	public DomeinController() {
		spelerRepository = new SpelerRepository();
	}

	public void registreerSpeler(String gebruikersnaam, int geboortejaar) {
		Speler nieuweSpeler = new Speler(gebruikersnaam, geboortejaar);
		spelerRepository.voegToe(nieuweSpeler);
	}

	public List<Speler> geefAlleBeschikbareSpelers() {
		return spelerRepository.geefAlleBeschikbareSpeler();
	}

	public Speler getSpeler(String gebruikersnaam) {
		return spelerRepository.getSpeler(gebruikersnaam);
	}

	// TODO:
	public int startNieuwSpel(List<Speler> spelerLijst) {
		this.spel1 = new Spel(spelerLijst);
		int aantalzetstenen = this.spel1.startSpel();

		BonusEnStartspelerfiches gebiedBessf = new BonusEnStartspelerfiches();
		gebiedBessf.speelRonde();
		return aantalzetstenen;
	}

	public List<Bonusfiche> geefPlaatsBonusfichesEersteRondes() {
		Ronde r = new Ronde();
		return r.bepaalPlaatsBonusfiche();
	}

	public List<Bonusfiche> geefPlaatsBonusfichesLaatsteRonde() {
		LaatsteRonde lr = new LaatsteRonde();
		return lr.bepaalPlaatsFiche();
	}

	public Speler geefWinnaar() {
		return spel1.bepaalWinnaar();

	}

	public Speler bepaalStartSpeler() {
		return spel1.bepaalStartSpeler();
	}

	public List<String> bepaalVolgordeSpelersGebruikersnaam() {

		return spel1.bepaalVolgordeGebruikersnamen();

	}

	public int toonVolgnummerVanRonde() {
		return ronde.getVolgnummer();
	}

	public List<GebouwKleur> toonresultaten1rolbeurt(List<GebouwKleur> dobbelstenenLijst,
			int aantalDobbelstenenDieOpnieuwGeroldWorden) {
		return spel1.rolEenKeerDeDobbelStenen(dobbelstenenLijst, aantalDobbelstenenDieOpnieuwGeroldWorden);
	}

	public void plaatsZetsteen(Speler speler, GebouwKleur kleur, int aantalDobbelstenen, int hoeveelsteWorp) {
		spel1.plaatsZetsteen(speler, kleur, aantalDobbelstenen, hoeveelsteWorp);
	}

	public void bepaalPuntenPerSpeler(int ronde, int gebouw, int hoeveelstePlaats, Speler speler) {
		spel1.bepaalPuntenPerSpeler(ronde, gebouw, hoeveelstePlaats, speler);
	}

	public void geefBeloningPerGebouwPerSpeler(int gebouwKleur, int speler) {
		spel1.geefGebouwPuntenPerGebouwPerSpeler(gebouwKleur, speler);
	}

	public String geefSpelerNaamOpPositie(GebouwKleur kleur, int waarde, int positie) {
		return spel1.geefSpelerNaamOpPositie(kleur, waarde, positie);
	}
}
