package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;


public class Vertaler {
    private static final String NL_BESTAND = "src/utils/translations_nl.properties";
    private static final String EN_BESTAND = "src/utils/translations_en.properties";
    private static Properties vertalingen = new Properties();
    private static String huidigeTaal = "nl";

    static {
        // Standaard Nederlands laden
        laadVertalingen(NL_BESTAND);
    }

    
    private static void laadVertalingen(String bestandsPad) {
        try {
            vertalingen.clear(); 
            vertalingen.load(new FileInputStream(bestandsPad));
        } catch (IOException e) {
            System.out.println("Fout bij het laden van vertalingen: " + e.getMessage());
        }
    }

    
    public static void initialiseer() {
        Scanner invoer = new Scanner(System.in);
        System.out.println("Select a languague (1: Nederlands , 2: English):");
        
        int keuze = invoer.nextInt();
        switch (keuze) {
            case 1:
                laadVertalingen(NL_BESTAND);
                huidigeTaal = "nl";
                System.out.println("Taal ingesteld op: Nederlands");
                break;
            case 2:
                laadVertalingen(EN_BESTAND);
                huidigeTaal = "en";
                System.out.println("Language set to: English");
                break;
            default:
                laadVertalingen(NL_BESTAND);
                huidigeTaal = "nl";
                System.out.println("Ongeldige keuze, standaard Nederlands wordt gebruikt");
                break;
        }
    }

    
    public static String geefVertaling(String key) {
        return vertalingen.getProperty(key, key);
    }

    public static String geefVertaling(String key, Object... args) {
        return String.format(vertalingen.getProperty(key, key), args);
    }

   
    public static void stelTaalIn(String taal) {
        if (taal.equals("nl") || taal.equals("en")) {
            huidigeTaal = taal;
            laadVertalingen(taal.equals("nl") ? NL_BESTAND : EN_BESTAND);
        }
    }

    public static String geefHuidigeTaal() {
        return huidigeTaal;
    }

    public static boolean isNederlands() {
        return huidigeTaal.equals("nl");
    }
} 