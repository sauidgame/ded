package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import utils.GebouwKleur;
import utils.SpelerKleur;

public class Spel {

	private String startspelerfiche = "Startspeler";
	private String[] spelbordGebieden = { "Gebouwpunten", "Bonus- en startspelerfiches", "Dobbelresultaten" };
	private Speler startspeler;
	private int score = 0;
	private int gebouwstenen = 6;
	private int zetstenen = 0;
	private List<Speler> gekozenSpelers = new ArrayList<>();
	private HashSet<SpelerKleur> beschikbareKleuren = new HashSet<>();
	private int ronde = 0;
	private static final int MAX_RONDES = 3;
	private SpelerRepository spelerRepository = new SpelerRepository();

	private List<GebouwKleur> overgeblevenKleuren = new ArrayList<>();

	private Dobbelresultaten dobbelresultaten = new Dobbelresultaten();

	private GebouwPunten gebouwPunten = new GebouwPunten();

	// TODO : toevoegen GebouwPunten

	public Spel() {
		for (SpelerKleur kleur : SpelerKleur.values()) {
			beschikbareKleuren.add(kleur);
		}
	}

	public Spel(List<Speler> spelerLijst) {
		this.gekozenSpelers = spelerLijst;
		for (SpelerKleur kleur : SpelerKleur.values()) {
			beschikbareKleuren.add(kleur);
		}
	}

	public int startSpel() {
		if (gekozenSpelers.size() < 3) {
			throw new IllegalArgumentException("Minimum 3 geregistreerde spelers vereist.");
		}

		int zetstenen = setZetstenen();

		return zetstenen;

	}

	public Speler bepaalStartSpeler() { // aanpassen aan de hand van de startspelerfiche
		Random random = new Random();
		startspeler = gekozenSpelers.get(random.nextInt(gekozenSpelers.size()));

		return startspeler;

	}

	public List<String> bepaalVolgordeGebruikersnamen() {
		int indexStartSpeler = gekozenSpelers.indexOf(startspeler);
		List<String> volgordeSpelers = new ArrayList<>();

		for (int i = indexStartSpeler; i < gekozenSpelers.size(); i++) {
			volgordeSpelers.add(gekozenSpelers.get(i).getGebruikersnaam());
		}

		for (int i = 0; i < indexStartSpeler; i++) {
			volgordeSpelers.add(gekozenSpelers.get(i).getGebruikersnaam());
		}

		return volgordeSpelers;
	}

	public List<GebouwKleur> rolEenKeerDeDobbelStenen(List<GebouwKleur> overgeblevenKleuren,
			int aantalDobbelstenenDieOpnieuwGeroldWorden) {
		for (int j = 0; j < aantalDobbelstenenDieOpnieuwGeroldWorden; j++) {
			Dobbelsteen dobbelsteen = new Dobbelsteen(); // nog veranderen naar beschikbare dobbelstenen
			overgeblevenKleuren.add(dobbelsteen.rolDobbelsteen());

			this.overgeblevenKleuren = overgeblevenKleuren;

		}
		return overgeblevenKleuren;

	}

	public List<GebouwKleur> overgeblevenGebouwenKleurenBijhouden(GebouwKleur dobbelsteen) { // dobbelstenen die
																								// opnieuw gerold
																								// moeten worden
		if (overgeblevenKleuren.contains(dobbelsteen)) {
			overgeblevenKleuren.remove(dobbelsteen);
		}
		return overgeblevenKleuren;
	}

	public int getZetstenen() {
		return zetstenen;
	}

	public int setZetstenen() {
		switch (gekozenSpelers.size()) {
		case 3:
			return zetstenen = 5;

		case 4:
			return zetstenen = 4;

		default:// default gebruiken
			return zetstenen = 3;

		}

	}

	// wat moet deze doen: nergens aangeroepen:
	private void vulAan() {
		Random random = new Random();

		if (ronde == MAX_RONDES - 1) {
			System.out.println("Bonustokens willekeurig plaatsen.");
		} else {
			int startSpelerGebouw = random.nextInt(spelbordGebieden.length);
			System.out.println("Startspelerfiche plaatsen bij " + spelbordGebieden[startSpelerGebouw]);
		}
	}

	public Speler bepaalWinnaar() {
		Speler winnaar = gekozenSpelers.get(2);
		for (Speler speler : gekozenSpelers) {
			if (speler.getPunten() > winnaar.getPunten()) {
				winnaar = speler;
			}
		}

		winnaar.gewonnen();
		for (Speler speler : gekozenSpelers) {
			speler.gespeeld();
		}
		return winnaar;
	}

	public void voegSpelerToe(String gebruikersnaam, SpelerKleur kleur) {
		Speler speler = spelerRepository.getSpeler(gebruikersnaam);
		if (speler == null)
			throw new IllegalArgumentException("Speler niet gevonden.");

		if (!beschikbareKleuren.contains(kleur)) {
			throw new IllegalArgumentException("Kleur niet beschikbaar.");
		}
		speler.setKleur(kleur);
		gekozenSpelers.add(speler);
		beschikbareKleuren.remove(kleur);
	}

	public void plaatsZetsteen(Speler speler, GebouwKleur kleur, int aantalDobbelstenen, int hoeveelsteWorp) {
		dobbelresultaten.setZetsteen(speler, kleur, aantalDobbelstenen, hoeveelsteWorp);
		dobbelresultaten.printBord();
	}

	public void printGebouwPunten() {
		gebouwPunten.printBord();

	}

	public void geefGebouwPuntenPerGebouwPerSpeler(int gebouwKleur, int speler) {
		int isHoeveelstePlaats = dobbelresultaten.geefPositieNaZetstenen();
		gebouwPunten.geefBeloningPerGebouwPerSpeler(gebouwKleur, isHoeveelstePlaats, speler);
	}

	public void bepaalPuntenPerSpeler(int ronde, int gebouw, int hoeveelstePlaats, Speler speler) {
		speler.bepaalPunten(ronde, gebouw, hoeveelstePlaats);
	}

	public List<Speler> getGekozenSpelers() {
		return gekozenSpelers;
	}

	public String geefSpelerNaamOpPositie(GebouwKleur kleur, int waarde, int positie) {
		return dobbelresultaten.geefSpelerNaamOpPositie(kleur, waarde, positie);
	}

	@Override
	public String toString() {
		return "Spel{" + "startspelerfiche='" + startspelerfiche + '\'' + ", spelbordGebieden="
				+ Arrays.toString(spelbordGebieden) + ", startspeler=" + startspeler + ", score=" + score
				+ ", gebouwstenen=" + gebouwstenen + ", zetstenen=" + zetstenen + ", gekozenSpelers=" + gekozenSpelers
				+ '}';
	}
}