package domein;

import utils.GebouwKleur;
import utils.Vertaler;

public class Dobbelresultaten extends Gebieden {

	final Speler[][][] resultaatPerGebouwPerWaardePerBeurt;

	public Dobbelresultaten() {
		this.resultaatPerGebouwPerWaardePerBeurt = new Speler[6][8][3];
	}

	public void printBord() {
		for (int gebouw = 0; gebouw < resultaatPerGebouwPerWaardePerBeurt.length; gebouw++) {
			String kleurNaam = GebouwKleur.values()[gebouw].name().toLowerCase();
			System.out.printf("%nGEBOUW: %s%n", Vertaler.geefVertaling("spel.gebouw." + kleurNaam).toUpperCase());
			for (int waarde = 7; waarde >= 0; waarde--) {
				System.out.printf("%s %d: ", Vertaler.geefVertaling("spel.gebouw.waarde"), waarde + 1);
				for (int beurt = 0; beurt < resultaatPerGebouwPerWaardePerBeurt[gebouw][waarde].length; beurt++) {
					if (resultaatPerGebouwPerWaardePerBeurt[gebouw][waarde][beurt] == null) {
						System.out.print("X ");
					} else {
						System.out.print(
								resultaatPerGebouwPerWaardePerBeurt[gebouw][waarde][beurt].getGebruikersnaam() + " ");
					}
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	public void setZetsteen(Speler speler, GebouwKleur gebouwKleur, int waarde, int beurt) {
		if (waarde < 1 || waarde > 8) {
			throw new IllegalArgumentException("Waarde moet tussen 1 en 8 liggen.");
		}
		if (speler == null || gebouwKleur == null) {
			throw new IllegalArgumentException("Speler en GebouwKleur mogen niet null zijn.");
		}
		if (beurt < 1 || beurt > 3) {
			throw new IllegalArgumentException("Beurt moet tussen 1 en 3 liggen.");
		}

		while (true) {
			if (resultaatPerGebouwPerWaardePerBeurt[gebouwKleur.ordinal()][waarde - 1][beurt - 1] == null) {
				break;
			}

			beurt++;

			if (beurt > 3) {
				beurt = 1;
				waarde--;

				if (waarde < 1) {
					throw new IllegalArgumentException("KIES EEN ANDERE KLEUR, LOSER");
				}
			}
		}

		resultaatPerGebouwPerWaardePerBeurt[gebouwKleur.ordinal()][waarde - 1][beurt - 1] = speler;
	}

	public int geefPositieNaZetstenen() {
		int positie = 0;
		for (int gebouw = 0; gebouw < resultaatPerGebouwPerWaardePerBeurt.length; gebouw++) {
			for (int waarde = 7; waarde >= 0; waarde--) {
				for (int beurt = 0; beurt < resultaatPerGebouwPerWaardePerBeurt[gebouw][waarde].length; beurt++) {
					if (resultaatPerGebouwPerWaardePerBeurt[gebouw][waarde][beurt] != null) {
						positie++;
						if (positie == 1) {
							return 1;
						} else if (positie == 2) {
							return 2;
						}
					}
				}
			}
		}
		return 0;
	}

//	public void geefBeloning(List<GebouwPunten> gebouwpunten, int isHoeveelstePlaats, Speler speler,
//			GebouwPunten gebouwPunten) {
//
//		int positie = 0;
//		for (int gebouw = 0; gebouw < resultaatPerGebouwPerWaardePerBeurt.length; gebouw++) {
//			for (int waarde = 7; waarde >= 0; waarde--) {
//				for (int beurt = 0; beurt < resultaatPerGebouwPerWaardePerBeurt[gebouw][waarde].length; beurt++) {
//					if (resultaatPerGebouwPerWaardePerBeurt[gebouw][waarde][beurt] != null) {
//						positie++;
//						if (positie == 1 || positie == 2) {
//							resultaatPerGebouwPerWaardePerBeurt[gebouw][waarde][beurt]
//									.geefBeloningPerGebouwPerSpeler(gebouw, positie, speler); // s
//						}
//					}
//				}
//			}
//		}
//
//	}

	public String geefSpelerNaamOpPositie(GebouwKleur kleur, int waarde, int positie) {
		Speler speler = resultaatPerGebouwPerWaardePerBeurt[kleur.ordinal()][waarde - 1][positie];
		return speler != null ? speler.getGebruikersnaam() : null;
	}

}
