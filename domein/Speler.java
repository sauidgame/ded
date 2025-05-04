package domein;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import utils.SpelerKleur;

public class Speler {
	public static final Collection<SpelerKleur> BESCHIKBARE_KLEUREN = Arrays.asList(SpelerKleur.values());

	private String gebruikersnaam;
	private int geboortejaar;
	private int aantalOverwinningen, aantalGespeeld;
	private SpelerKleur kleur;
	private int punten = 0;

	private Ronde ronde;

	private static List<String> alleSpelers = new ArrayList<>();

	private static SpelerRepository spelerRepository = new SpelerRepository();

	public static void resetAlleSpelers() {
		alleSpelers.clear();
	}

	public Speler(String gebruikersnaam, int geboortejaar) {
		this(gebruikersnaam, geboortejaar, 0, 0, null);
		alleSpelers.add(gebruikersnaam);

	}

	public Speler(String gebruikersnaam, int geboortejaar, int aantalOverwinningen, int aantalGespeeld,
			String spelerKleur) {
		setGebruikersnaam(gebruikersnaam);
		setGeboortejaar(geboortejaar);
		setAantalOverwinningen(aantalOverwinningen);
		setAantalGespeeld(aantalGespeeld);
		if (spelerKleur != null) {
			try {
				setKleur(SpelerKleur.valueOf(spelerKleur.toUpperCase())); // Zet de kleur om naar een enum
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Ongeldige kleur: " + spelerKleur);
			}
		}
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	private void setGebruikersnaam(String gebruikersnaam) {
		if (gebruikersnaam == null || gebruikersnaam.isBlank())
			throw new IllegalArgumentException("Naam mag niet leeg of een spatie bevatten.");
		if (gebruikersnaam.length() < 6)
			throw new IllegalArgumentException("Naam mag niet korter zijn dan 6 karakters.");
		for (String gb : alleSpelers) {
			if (gebruikersnaam.equals(gb))
				throw new IllegalArgumentException("Naam bestaat al, voer een nieuwe naam in.");
		}
		this.gebruikersnaam = gebruikersnaam;
	}

	public int getGeboortejaar() {
		return geboortejaar;
	}

	private void setGeboortejaar(int geboortejaar) {
		int huidigeJaar = Year.now().getValue();
		int leeftijd = huidigeJaar - geboortejaar;
		if (geboortejaar > huidigeJaar)
			throw new IllegalArgumentException("Geboortejaar mag niet in de toekomst liggen.");
		if (leeftijd < 6 || leeftijd > 100)
			throw new IllegalArgumentException("Speler moet minstens 6 jaar en maximaal 100 jaar oud zijn.");
		this.geboortejaar = geboortejaar;
	}

	public int getAantalOverwinningen() {
		return aantalOverwinningen;
	}

	private void setAantalOverwinningen(int aantalOverwinningen) {
		if (aantalOverwinningen < 0) {
			throw new IllegalArgumentException("Aantal gewonnen mag niet negatief zijn.");
		}
		this.aantalOverwinningen = aantalOverwinningen;
	}

	public int getAantalGespeeld() {
		return aantalGespeeld;
	}

	private void setAantalGespeeld(int aantalGespeeld) {
		if (aantalGespeeld < 0) {
			throw new IllegalArgumentException("Aantal gespeeld mag niet negatief zijn.");
		}
		this.aantalGespeeld = aantalGespeeld;
	}

	public SpelerKleur getKleur() {
		return kleur;
	}

	public void setKleur(SpelerKleur spelerKleur) {
		if (spelerKleur != null && !BESCHIKBARE_KLEUREN.contains(spelerKleur)) {
			throw new IllegalArgumentException("De gegeven kleur " + spelerKleur + " is ongeldig."); // null als
																										// defaultwaarde?
		}
		this.kleur = spelerKleur;
	}

	public void gewonnen() {
		this.setAantalOverwinningen(this.aantalOverwinningen + 1);
	}

	public int getPunten() {
		return punten;
	}

	public void bepaalPunten(int ronde, int gebouw, int hoeveelstePlaats) {
		switch (gebouw) {
		case 1: {
			switch (ronde) {
			case 1:
				if (hoeveelstePlaats == 1)
					this.punten += 1;
			case 2:
				if (hoeveelstePlaats == 1)
					this.punten += 8;
				else if (hoeveelstePlaats == 2)
					this.punten += 1;
			case 3:
				if (hoeveelstePlaats == 1)
					this.punten += 16;
				else if (hoeveelstePlaats == 2)
					this.punten += 8;
				else if (hoeveelstePlaats == 3)
					this.punten += 1;
			}
		}
		case 2: {
			switch (ronde) {
			case 1:
				if (hoeveelstePlaats == 1)
					this.punten += 2;
			case 2:
				if (hoeveelstePlaats == 1)
					this.punten += 9;
				else if (hoeveelstePlaats == 2)
					this.punten += 2;
			case 3:
				if (hoeveelstePlaats == 1)
					this.punten += 17;
				else if (hoeveelstePlaats == 2)
					this.punten += 9;
				else if (hoeveelstePlaats == 3)
					this.punten += 2;
			}
		}
		case 3: {
			switch (ronde) {
			case 1:
				if (hoeveelstePlaats == 1)
					this.punten += 3;
			case 2:
				if (hoeveelstePlaats == 1)
					this.punten += 10;
				else if (hoeveelstePlaats == 2)
					this.punten += 3;
			case 3:
				if (hoeveelstePlaats == 1)
					this.punten += 18;
				else if (hoeveelstePlaats == 2)
					this.punten += 10;
				else if (hoeveelstePlaats == 3)
					this.punten += 3;
			}
		}
		case 4: {
			switch (ronde) {
			case 1:
				if (hoeveelstePlaats == 1)
					this.punten += 4;
			case 2:
				if (hoeveelstePlaats == 1)
					this.punten += 11;
				else if (hoeveelstePlaats == 2)
					this.punten += 4;
			case 3:
				if (hoeveelstePlaats == 1)
					this.punten += 19;
				else if (hoeveelstePlaats == 2)
					this.punten += 11;
				else if (hoeveelstePlaats == 3)
					this.punten += 4;
			}
		}
		case 5: {
			switch (ronde) {
			case 1:
				if (hoeveelstePlaats == 1)
					this.punten += 5;
			case 2:
				if (hoeveelstePlaats == 1)
					this.punten += 12;
				else if (hoeveelstePlaats == 2)
					this.punten += 5;
			case 3:
				if (hoeveelstePlaats == 1)
					this.punten += 20;
				else if (hoeveelstePlaats == 2)
					this.punten += 12;
				else if (hoeveelstePlaats == 3)
					this.punten += 5;
			}
		}
		case 6: {
			switch (ronde) {
			case 1:
				if (hoeveelstePlaats == 1)
					this.punten += 6;
			case 2:
				if (hoeveelstePlaats == 1)
					this.punten += 13;
				else if (hoeveelstePlaats == 2)
					this.punten += 6;
			case 3:
				if (hoeveelstePlaats == 1)
					this.punten += 21;
				else if (hoeveelstePlaats == 2)
					this.punten += 13;
				else if (hoeveelstePlaats == 3)
					this.punten += 6;
			}
		}

		}
	}

	public void gespeeld() {
		this.setAantalGespeeld(this.aantalGespeeld + 1);
	}

	public static void controleerAantalSpelers(int aantalSpelers) {
		if (aantalSpelers < 3 || aantalSpelers > 6) {
			throw new IllegalArgumentException("Het spel heeft minimaal 3 en maximaal 6 spelers.");
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(gebruikersnaam);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Speler other = (Speler) obj;
		return Objects.equals(gebruikersnaam, other.gebruikersnaam);
	}

	public void voegGebouwPuntenToe(List<Speler> gekozenSpelers) {

		if (ronde.getVolgnummer() == 1) {

		} else if (ronde.getVolgnummer() == 2) {

		} else if (ronde.getVolgnummer() == 3) {

		}

	}

	public List<GebouwPunten> geefEersteBeloningAanSpeler(List<GebouwPunten> gebouwpunten) {
		gebouwpunten.add(new GebouwPunten());
		gebouwpunten.add(new GebouwPunten());
		return gebouwpunten;

	}

	public List<GebouwPunten> geefTweedeBeloningGebouwpuntenAanSpeler(List<GebouwPunten> gebouwpunten) {
		gebouwpunten.add(new GebouwPunten());
		return gebouwpunten;
	}

	public Fiche geefTweedeBeloningFicheAanSpeler(Fiche ficheVanHuidigGebouw) {
		// hierbij: belangrijk dat fiches nog eens goed bekeken wordt, als startspeler
		// wordt speler startspeler, anders is startspeler dezelfde
		return ficheVanHuidigGebouw;
	}

	public void voegPuntenFicheToe(Fiche huidigefiche) {

	}
}