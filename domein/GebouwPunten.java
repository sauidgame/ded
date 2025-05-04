package domein;

import java.util.List;

import utils.GebouwKleur;
import utils.Vertaler;

public class GebouwPunten extends Gebieden {

	// TODO

	final int[][] resultaatPerGebouwPerSpeler;

	private Ronde ronde;

	public GebouwPunten() {
		resultaatPerGebouwPerSpeler = new int[GebouwKleur.values().length][3]; // TODO: aantal spelers ipv 3
	}

	public void printBord() {
		for (int gebouw = 0; gebouw < resultaatPerGebouwPerSpeler.length; gebouw++) {
			String kleurNaam = GebouwKleur.values()[gebouw].name().toLowerCase();
			System.out.printf("%nGEBOUW: %s%n", Vertaler.geefVertaling("spel.gebouw." + kleurNaam).toUpperCase());
			for (int speler = 7; speler >= 0; speler--) {
				System.out.printf("%s %d: ", Vertaler.geefVertaling("spel.gebouw.waarde"), speler + 1);
				if (resultaatPerGebouwPerSpeler[gebouw][speler] == 0) {
					System.out.print("O ");
				} else {
					System.out.print(resultaatPerGebouwPerSpeler[gebouw][speler] + " "); // nog aanpassen
				}

				System.out.println();
			}
			System.out.println();
		}
	}

	public void geefBeloningPerGebouwPerSpeler(int gebouwKleur, int isHoeveelstePlaats, int speler) { // gebouw en speler meegeven aan
																					// functie en zeggen hoeveel punten
																					// hij moet krijgen

		resultaatPerGebouwPerSpeler[gebouwKleur][speler] = isHoeveelstePlaats == 1 ? 2 : 1;

	}

	public int[][] getGebouwPunten() {
		return resultaatPerGebouwPerSpeler;
	}

	public void geefGebouwPuntenVolgendsWaardering(List<Speler> gekozenSpelers) {

		for (int i = 0; i < ronde.getVolgnummer(); i++) {

			if (i < gekozenSpelers.size()) {

				gekozenSpelers.get(i).voegGebouwPuntenToe(gekozenSpelers);

			}
		}

		for (Speler speler : gekozenSpelers) {
			speler.voegPuntenFicheToe(null);
		}

	}
}
