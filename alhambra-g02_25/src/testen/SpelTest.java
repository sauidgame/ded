package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Spel;
import domein.Speler;
import utils.BeschikbareKleuren;

class SpelTest {
    private Spel spel;

    @BeforeEach
    void setUp() {
        spel = new Spel();
    }

    @Test
    void startNieuwSpel_metDrieSpelers_systeemMaaktNieuwSpel() {


        // Voeg spelers toe aan het spel
        spel.voegSpelerToe("Speler1", BeschikbareKleuren.BLAUW);
        spel.voegSpelerToe("Speler2", BeschikbareKleuren.GROEN);
        spel.voegSpelerToe("Speler3", BeschikbareKleuren.WIT);

        // Start het spel
        spel.startSpel();

        // Controleer de spelinstelling
        List<Speler> gekozenSpelers = spel.getGekozenSpelers();
        assertEquals(3, gekozenSpelers.size());
        assertEquals("blauw", gekozenSpelers.get(0).getKleur());
        assertEquals("groen", gekozenSpelers.get(1).getKleur());
        assertEquals("wit", gekozenSpelers.get(2).getKleur());
    }

    @Test
    void startSpel_metMinderDanMinSpelers_werpException() {

        // Voeg spelers toe aan het spel
        spel.voegSpelerToe("Speler1", BeschikbareKleuren.BLAUW);
        spel.voegSpelerToe("Speler2", BeschikbareKleuren.GROEN);

        // Probeer het spel te starten met minder dan 3 spelers
        assertThrows(IllegalArgumentException.class, () -> {
            spel.startSpel();
        });
    }

    @Test
    void startNieuwSpel_metMeerDanZesSpelers_werpException() {

        // Voeg spelers toe aan het spel
        spel.voegSpelerToe("Speler1", BeschikbareKleuren.BLAUW);
        spel.voegSpelerToe("Speler2", BeschikbareKleuren.GROEN);
        spel.voegSpelerToe("Speler3", BeschikbareKleuren.WIT);
        spel.voegSpelerToe("Speler4", BeschikbareKleuren.GEEL);
        spel.voegSpelerToe("Speler5", BeschikbareKleuren.ORANJE);
        spel.voegSpelerToe("Speler6", BeschikbareKleuren.ROOD);

        // Probeer een 7e speler toe te voegen
        assertThrows(IllegalArgumentException.class, () -> {
            spel.voegSpelerToe("Speler7", BeschikbareKleuren.BLAUW);
        });
    }

    @Test
    void speelSpel_metDrieRondes_registreertWinnaar() {

        spel.voegSpelerToe("Speler1", BeschikbareKleuren.BLAUW);
        spel.voegSpelerToe("Speler2", BeschikbareKleuren.GEEL);
        spel.voegSpelerToe("Speler3", BeschikbareKleuren.WIT);

        spel.startSpel();

        // Simuleer het spelen van het spel
        spel.speelSpel();

        // Controleer of de winnaar is geregistreerd en scores zijn bijgewerkt
        List<Speler> gekozenSpelers = spel.getGekozenSpelers();
        Speler winnaar = gekozenSpelers.get(0);
        for (Speler speler : gekozenSpelers) {
            if (speler.getAantalOverwinningen() > winnaar.getAantalOverwinningen()) {
                winnaar = speler;
            }
        }
        assertEquals(1, winnaar.getAantalOverwinningen());
        for (Speler speler : gekozenSpelers) {
            assertEquals(1, speler.getAantalGespeeld());
        }
    }
}