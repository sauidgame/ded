package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import utils.BeschikbareKleuren;

public class Spel {

    private String startspelerfiche = "Startspeler";
    private String[] spelbordGebieden = { "Gebouwpunten", "Bonus- en startspelerfiches", "Dobbelresultaten" };
    private Speler startspeler;
    private int score = 0;
    private int gebouwstenen = 6;
    private int zetstenen = 0;
    private List<Speler> gekozenSpelers = new ArrayList<>();
    private HashSet<String> beschikbareKleuren = new HashSet<>();
    private int ronde = 0;
    private static final int MAX_RONDES = 3;
    private SpelerRepository spelerRepository = new SpelerRepository();

    public Spel() {
        beschikbareKleuren.addAll(Speler.BESCHIKBARE_KLEUREN);
    }

    public void startSpel() {
        if (gekozenSpelers.size() < 3) {
            throw new IllegalArgumentException("Minimum 3 geregistreerde spelers vereist.");
        }

        // Determine number of action stones based on number of players
        switch (gekozenSpelers.size()) {
            case 3:
                zetstenen = 5;
                break;
            case 4:
                zetstenen = 4;
                break;
            case 5:
            case 6:
                zetstenen = 3;
                break;
        }

        // Randomly select a start player
        Random random = new Random();
        startspeler = gekozenSpelers.get(random.nextInt(gekozenSpelers.size()));

        System.out.println("Spel gestart met spelers: " + gekozenSpelers);
    }

    public void speelSpel() {
        while (ronde < MAX_RONDES) {
            vulAan();
            speelRonde();
            ronde++;
        }
        registreerWinnaar();
        toonScoreOverzicht();
    }

    private void vulAan() {
        Random random = new Random();
        if (ronde == MAX_RONDES - 1) {
            // Last round: place bonus tokens randomly
            System.out.println("Bonustokens willekeurig plaatsen.");
        } else {
            // Other rounds: place start player token and bonus tokens
            int startSpelerGebouw = random.nextInt(spelbordGebieden.length);
            System.out.println("Startspelerfiche plaatsen bij " + spelbordGebieden[startSpelerGebouw]);
        }
    }

    private void speelRonde() {
        System.out.println("Ronde " + (ronde + 1) + " spelen");
        // Simulate playing a round
        // (Should be handled by a user interface class)
    }

    private void registreerWinnaar() {
        Speler winnaar = gekozenSpelers.get(0);
        for (Speler speler : gekozenSpelers) {
            if (speler.getScore() > winnaar.getScore()) {
                winnaar = speler;
            }
        }
        winnaar.gewonnen();
        for (Speler speler : gekozenSpelers) {
            speler.gespeeld();
        }
        System.out.println("Winnaar geregistreerd: " + winnaar.getGebruikersnaam());
    }

    private void toonScoreOverzicht() {
        System.out.println("Scoreoverzicht:");
        for (Speler speler : gekozenSpelers) {
            System.out.println(speler.getGebruikersnaam() + " - Overwinningen: " + speler.getAantalOverwinningen() + ", Gespeelde spellen: " + speler.getAantalGespeeld());
        }
    }

    public void voegSpelerToe(String gebruikersnaam, BeschikbareKleuren groen) {
        Speler speler = spelerRepository.geefSpeler(gebruikersnaam);
        if (speler == null) {
            throw new IllegalArgumentException("Speler niet gevonden.");
        }
        if (!beschikbareKleuren.contains(groen)) {
            throw new IllegalArgumentException("Kleur niet beschikbaar.");
        }
        speler.setKleur(groen);
        gekozenSpelers.add(speler);
        beschikbareKleuren.remove(groen);
    }

    public List<Speler> getGekozenSpelers() {
        return gekozenSpelers;
    }

    @Override
    public String toString() {
        return "Spel{" +
                "startspelerfiche='" + startspelerfiche + '\'' +
                ", spelbordGebieden=" + Arrays.toString(spelbordGebieden) +
                ", startspeler=" + startspeler +
                ", score=" + score +
                ", gebouwstenen=" + gebouwstenen +
                ", zetstenen=" + zetstenen +
                ", gekozenSpelers=" + gekozenSpelers +
                '}';
    }
}