package cui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import domein.DomeinController;
import domein.Speler;
import utils.GebouwKleur;
import utils.SpelerKleur;
import utils.Vertaler;

public class SpelApplicatie {
	private final Scanner invoer;
	private final String[] keuzesOverzichtsMenu = { Vertaler.geefVertaling("menu.optie1"),
			Vertaler.geefVertaling("menu.optie2"), Vertaler.geefVertaling("menu.optie3"),
			Vertaler.geefVertaling("menu.optie4") };
	private final Menu overzichtsMenu;

	private final DomeinController dc;

	private List<Speler> spelerLijst = new ArrayList<>();

	private List<GebouwKleur> resultatenDobbelstenen = new ArrayList<>();

	private int aantalDobbelstenenDieOpnieuwGeroldWorden = 8;

	public SpelApplicatie(DomeinController dc, Scanner invoer) {
		this.dc = dc;
		this.invoer = invoer;
		this.overzichtsMenu = new Menu(Vertaler.geefVertaling("menu.titel"), keuzesOverzichtsMenu, invoer);
	}

	public void start() {
		int keuze = -1;
		do {
			try {
				keuze = overzichtsMenu.geefKeuze();
				switch (keuze) {
				case 1 -> voegNieuweSpelerToe();
				case 2 -> speelSpel();
				case 3 -> toonSpelregels();
				}
			} catch (Exception e) {
				System.out.printf(Vertaler.geefVertaling("spel.fout.onverwacht"), e.getMessage());
			}
		} while (keuze != keuzesOverzichtsMenu.length);

		System.out.println(Vertaler.geefVertaling("spel.stop"));

	}

	private void speelSpel() {
		startSpel();

		speelRonde();

		scoreOverzicht();
	}

	private void toonSpelregels() {
		System.out.println(Vertaler.geefVertaling("spel.regels"));
		// TODO: Implementeer spelregels weergave indien tijd over
	}

	private void voegNieuweSpelerToe() {
		try {
			System.out.println(Vertaler.geefVertaling("spel.speler.gebruikersnaam"));
			String gebruikersnaam = invoer.nextLine();
			if (gebruikersnaam.length() < 3) {
				throw new IllegalArgumentException(Vertaler.geefVertaling("spel.speler.gebruikersnaam.ongeldig"));
			}

			System.out.println(Vertaler.geefVertaling("spel.speler.geboortejaar"));
			int geboortejaar = Integer.parseInt(invoer.nextLine());

			dc.registreerSpeler(gebruikersnaam, geboortejaar);
			System.out.println(Vertaler.geefVertaling("spel.speler.geregistreerd"));

		} catch (NumberFormatException e) {
			System.out.println(Vertaler.geefVertaling("spel.speler.geboortejaar.ongeldig"));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.printf(Vertaler.geefVertaling("spel.fout.onverwacht"), e.getMessage());
		}
	}

	// begin van het spel voor het spel zelf van start gaat
	public void startSpel() {
		try {
			List<Speler> beschikbareSpelers = dc.geefAlleBeschikbareSpelers();

			if (beschikbareSpelers.isEmpty()) {
				System.out.println(Vertaler.geefVertaling("spel.speler.geen.beschikbaar"));
				return;
			}

			HashSet<String> beschikbareKleuren = new HashSet<>();
			for (SpelerKleur kleur : SpelerKleur.values()) {
				String kleurNaam = kleur.name().toLowerCase();
				beschikbareKleuren.add(Vertaler.geefVertaling("spel.kleur." + kleurNaam));
			}

			if (spelerLijst.size() >= 3 && spelerLijst.size() < 6) {
				System.out.println(Vertaler.geefVertaling("spel.speler.minimum.vereist"));
				int antwoord = Integer.parseInt(invoer.nextLine());
				if (antwoord == 1) {
					kiesSpelerEnSpelerKleur(beschikbareSpelers, beschikbareKleuren);
				}
			}

			kiesSpelerEnSpelerKleur(beschikbareSpelers, beschikbareKleuren);

			if (spelerLijst.size() < 3 || spelerLijst.size() > 6) {
				throw new IllegalArgumentException(Vertaler.geefVertaling("spel.speler.aantal.ongeldig"));
			}

		} catch (IllegalArgumentException e) {
			System.out.println(Vertaler.geefVertaling("spel.fout") + ": " + e.getMessage());
		} catch (Exception e) {
			System.out.printf(Vertaler.geefVertaling("spel.fout.onverwacht"), e.getMessage());
		}
	}

	private void speelRonde() {
		System.out.printf("%s %d%n", Vertaler.geefVertaling("spel.ronde.nummer"), dc.toonVolgnummerVanRonde());
		int aantalstartZetstenen = dc.startNieuwSpel(spelerLijst);
		Speler startSpeler = dc.bepaalStartSpeler();

		System.out.printf("%s%n", Vertaler.geefVertaling("spel.speler.start", startSpeler.getGebruikersnaam()));

		List<String> volgordeSpelerGebruikersnamen = dc.bepaalVolgordeSpelersGebruikersnaam();
		System.out.println(Vertaler.geefVertaling("spel.speler.volgorde"));
		System.out.println(volgordeSpelerGebruikersnamen);
		List<GebouwKleur> dobbelstenenLijst = new ArrayList<>();

		// 3 rondes spelen:
		for (int i = 0; i <= 3; i++) {

			List<String> volgordeSpelers = dc.bepaalVolgordeSpelersGebruikersnaam();
			System.out.println(Vertaler.geefVertaling("spel.speler.volgorde") + ": " + volgordeSpelers);

			// 1 ronde = alle zetstenen zijn 1x rond:
			for (int aantalZetstenen = aantalstartZetstenen; aantalZetstenen > 0; aantalZetstenen--) {
				System.out.printf("%s: %d%n", Vertaler.geefVertaling("spel.speler.stenen"), aantalZetstenen);
				dobbelstenenRollenVoorAlleSpelers(dobbelstenenLijst);
			}
			bepaalGebouwPunten();

			for (Speler s : spelerLijst) {
				List<GebouwKleur> alleGebouwen = new ArrayList<>();
				for (GebouwKleur gk : GebouwKleur.values()) {
					alleGebouwen.add(gk);
				}
				for (int gebouw = 0; gebouw < alleGebouwen.size(); gebouw++)
					dc.bepaalPuntenPerSpeler(i + 1, gebouw, aantalstartZetstenen, s);// hoeveelste plaats (parameter 3)
																						// moet nog bepaald worden
			}

			// lijst verwijderen na 1 ronde
			dobbelstenenLijst.clear();

			// alle fiches verwijderen die niet aan spelers gegeven zijn
		}
	}

	// aka 1 speelbeurt per speler
	private void dobbelstenenRollenVoorAlleSpelers(List<GebouwKleur> dobbelstenenLijst) {
		for (Speler s : spelerLijst) {
			int hoeveelsteWorp;

			aantalDobbelstenenDieOpnieuwGeroldWorden = 8;
			boolean stopMetWerpen = false;
			for (hoeveelsteWorp = 0; hoeveelsteWorp < 3 && !stopMetWerpen; hoeveelsteWorp++) {
				resultatenDobbelstenen = dc.toonresultaten1rolbeurt(dobbelstenenLijst,
						aantalDobbelstenenDieOpnieuwGeroldWorden);

				System.out.printf("%s, %s %d: [", s.getGebruikersnaam(),
						Vertaler.geefVertaling("spel.dobbelsteen.resultaten"), hoeveelsteWorp + 1);

				for (int i = 0; i < resultatenDobbelstenen.size(); i++) {
					String kleur = resultatenDobbelstenen.get(i).name().toLowerCase();
					System.out.print(Vertaler.geefVertaling("spel.gebouw." + kleur));
					if (i < resultatenDobbelstenen.size() - 1) {
						System.out.print(", ");
					}
				}
				System.out.println("]");

				for (int l = 0; l < resultatenDobbelstenen.size(); l++) {
					String kleur = resultatenDobbelstenen.get(l).name().toLowerCase();
					System.out.printf("%d. %s%n", l + 1, Vertaler.geefVertaling("spel.gebouw." + kleur));
				}

				int extraDobbelsteenRollen = 1;
				aantalDobbelstenenDieOpnieuwGeroldWorden = 0;

				while (extraDobbelsteenRollen == 1 && !stopMetWerpen) {
					System.out.println(Vertaler.geefVertaling("spel.dobbelsteen.acties"));
					System.out.println(Vertaler.geefVertaling("spel.dobbelsteen.verwijder"));
					System.out.println(Vertaler.geefVertaling("spel.dobbelsteen.behouden"));
					System.out.println(Vertaler.geefVertaling("spel.dobbelsteen.stoppen"));
					extraDobbelsteenRollen = Integer.parseInt(invoer.nextLine());

					switch (extraDobbelsteenRollen) {
					case 1:
						aantalDobbelstenenDieOpnieuwGeroldWorden++;
						dobbelstenenOpnieuwGebruiken(s);
						break;
					case 2:
						break;
					case 3:
						stopMetWerpen = true;
						break;
					default:
						System.out.println(Vertaler.geefVertaling("spel.dobbelsteen.ongeldig"));
						extraDobbelsteenRollen = 1;
					}
				}

			}

			kiesBeschikbareGebouwsoort(s, resultatenDobbelstenen, hoeveelsteWorp);

		}

	}

	private void bepaalGebouwPunten() {
		List<GebouwKleur> alleGebouwen = new ArrayList<>();
		System.out.println("De gebouwpunten per speler zijn:");
		for (GebouwKleur gk : GebouwKleur.values()) {
			alleGebouwen.add(gk);
		}
		for (Speler s : spelerLijst) {
			for (GebouwKleur gk : alleGebouwen) {
				dc.geefBeloningPerGebouwPerSpeler(alleGebouwen.indexOf(gk), spelerLijst.indexOf(s));

			}
		}

	}

	private void dobbelstenenOpnieuwGebruiken(Speler s) {
		int keuze = -1;

		while (keuze == -1) {
			System.out.printf("%s, %s%n", s.getGebruikersnaam(), Vertaler.geefVertaling("spel.dobbelsteen.kies"));

			keuze = Integer.parseInt(invoer.nextLine());

			if (keuze > resultatenDobbelstenen.size() || keuze < 1) {
				System.out.println(Vertaler.geefVertaling("spel.dobbelsteen.ongeldig"));
				keuze = -1;
				continue;
			}

			resultatenDobbelstenen.remove(keuze - 1);
			System.out.println(Vertaler.geefVertaling("spel.dobbelsteen.overgebleven"));
			for (int l = 0; l < resultatenDobbelstenen.size(); l++) {
				String kleur = resultatenDobbelstenen.get(l).name().toLowerCase();
				System.out.printf("%d. %s%n", l + 1, Vertaler.geefVertaling("spel.gebouw." + kleur));
			}
		}
	}

	private void kiesBeschikbareGebouwsoort(Speler speler, List<GebouwKleur> lijstMetGegooideDobbelstenen,
			int hoeveelsteWorp) {

		List<GebouwKleur> overgeblevenGebouwen = new ArrayList<>();
		for (GebouwKleur gebouwKleur : lijstMetGegooideDobbelstenen) {
			if (!overgeblevenGebouwen.contains(gebouwKleur)) {
				overgeblevenGebouwen.add(gebouwKleur);
			}
		}

		System.out.print("[");
		for (int i = 0; i < overgeblevenGebouwen.size(); i++) {
			String kleur = overgeblevenGebouwen.get(i).name().toLowerCase();
			System.out.print(Vertaler.geefVertaling("spel.gebouw." + kleur));
			if (i < overgeblevenGebouwen.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");

		if (overgeblevenGebouwen.isEmpty()) {
			System.out.println(Vertaler.geefVertaling("spel.gebouw.geen.beschikbaar"));
			return;
		}

		if (overgeblevenGebouwen.size() == 1) {
			System.out.printf("%s %s%n", Vertaler.geefVertaling("spel.gebouw.één.beschikbaar"),
					Vertaler.geefVertaling("spel.gebouw." + overgeblevenGebouwen.get(0).name().toLowerCase()));
			GebouwKleur gekozenKleur = overgeblevenGebouwen.get(0);
			int aantalBijgehoudenDobbelstenen = 0;
			for (GebouwKleur g : lijstMetGegooideDobbelstenen) {
				if (g == gekozenKleur) {
					aantalBijgehoudenDobbelstenen++;
				}
			}
			dc.plaatsZetsteen(speler, gekozenKleur, aantalBijgehoudenDobbelstenen, hoeveelsteWorp);
			return;
		}

		System.out.println(Vertaler.geefVertaling("spel.gebouw.kies"));
		for (int i = 0; i < overgeblevenGebouwen.size(); i++) {
			String kleur = overgeblevenGebouwen.get(i).name().toLowerCase();
			System.out.printf("%d. %s%n", i + 1, Vertaler.geefVertaling("spel.gebouw." + kleur));
		}

		int antwoordZetsteen = Integer.parseInt(invoer.nextLine());

		if (antwoordZetsteen < 1 || antwoordZetsteen > overgeblevenGebouwen.size()) {
			System.out.println(Vertaler.geefVertaling("spel.gebouw.ongeldig"));
			return;
		}

		GebouwKleur gekozenKleur = overgeblevenGebouwen.get(antwoordZetsteen - 1);

		int aantalBijgehoudenDobbelstenen = 0;
		for (GebouwKleur g : lijstMetGegooideDobbelstenen) {
			if (g == gekozenKleur) {
				aantalBijgehoudenDobbelstenen++;
			}
		}

		dc.plaatsZetsteen(speler, gekozenKleur, aantalBijgehoudenDobbelstenen, hoeveelsteWorp);
	}

	public void scoreOverzicht() {
		for (Speler s : spelerLijst) {
			System.out.printf("De speler %s heeft %d punten", s.getGebruikersnaam(), s.getPunten());
		}
		dc.geefWinnaar();
		System.out.printf("%n%16s| %15s| %15s%n", "spelersnamen", "aantal gewonnen", "aantal gespeeld");
		System.out.println("==================================================");
		for (Speler i : spelerLijst) {
			System.out.printf("%16s| %15d| %15d%n", i.getGebruikersnaam(), i.getAantalOverwinningen(),
					i.getAantalGespeeld());
		}
	}

// speler en kleur selecteren om het spel te spelen
	public void kiesSpelerEnSpelerKleur(List<Speler> beschikbareSpelers, HashSet<String> beschikbareKleuren) {
		try {
			while (spelerLijst.size() < 6) {
				System.out.println(Vertaler.geefVertaling("spel.speler.beschikbaar"));
				for (int i = 0; i < beschikbareSpelers.size(); i++) {
					System.out.printf("%d. %s%n", i + 1, beschikbareSpelers.get(i).getGebruikersnaam());
				}

				System.out.println(Vertaler.geefVertaling("spel.speler.kleur"));
				int index = 1;
				for (String kleur : beschikbareKleuren) {
					System.out.printf("%d. %s%n", index++, kleur);
				}

				System.out.println(Vertaler.geefVertaling("spel.speler.kies.speler"));
				String input = invoer.nextLine();
				if (input.isEmpty())
					break;

				int spelerIndex = Integer.parseInt(input) - 1;
				if (spelerIndex < 0 || spelerIndex >= beschikbareSpelers.size()) {
					System.out.println(Vertaler.geefVertaling("spel.speler.ongeldig.speler"));
					continue;
				}

				System.out.println(Vertaler.geefVertaling("spel.speler.kies.kleur"));
				int kleurIndex = Integer.parseInt(invoer.nextLine()) - 1;
				if (kleurIndex < 0 || kleurIndex >= beschikbareKleuren.size()) {
					System.out.println(Vertaler.geefVertaling("spel.speler.ongeldig.kleur"));
					continue;
				}

				Speler gekozenSpeler = beschikbareSpelers.get(spelerIndex);
				String gekozenKleur = (String) beschikbareKleuren.toArray()[kleurIndex];

				SpelerKleur kleurEnum = null;
				for (SpelerKleur kleur : SpelerKleur.values()) {
					if (Vertaler.geefVertaling("spel.kleur." + kleur.name().toLowerCase()).equals(gekozenKleur)) {
						kleurEnum = kleur;
						break;
					}
				}
				gekozenSpeler.setKleur(kleurEnum);
				spelerLijst.add(gekozenSpeler);
				beschikbareKleuren.remove(gekozenKleur);
				beschikbareSpelers.remove(spelerIndex);
			}
		} catch (NumberFormatException e) {
			System.out.println(Vertaler.geefVertaling("spel.speler.kies.error"));
		} catch (Exception e) {
			System.out.printf(Vertaler.geefVertaling("spel.speler.kies.error.general"), e.getMessage());
		}
	}

//	private void toonGebouwen() {
//		for (GebouwKleur kleur : GebouwKleur.values()) {
//			String kleurNaam = kleur.name().toLowerCase();
//			System.out.printf("%nGEBOUW: %s%n", Vertaler.geefVertaling("spel.gebouw." + kleurNaam).toUpperCase());
//			for (int waarde = 8; waarde >= 1; waarde--) {
//				System.out.printf("%s %d: ", Vertaler.geefVertaling("spel.gebouw.waarde"), waarde);
//				for (int i = 0; i < 3; i++) {
//					String spelerNaam = dc.geefSpelerNaamOpPositie(kleur, waarde, i);
//					System.out.print(spelerNaam != null ? spelerNaam : "X");
//					if (i < 2) {
//						System.out.print(" ");
//					}
//				}
//				System.out.println();
//			}
//		}
//	}
}