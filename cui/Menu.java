package cui;

import java.util.Scanner;
import utils.Vertaler;


public class Menu {
    private final Scanner invoer;
    private final String titel;
    private final String[] keuzes;

    
    public Menu(String titel, String[] keuzes, Scanner invoer) {
        this.titel = titel;
        this.keuzes = keuzes;
        this.invoer = invoer;
    }

    
    public int geefKeuze() {
        int keuze = -1;
        do {
            try {
                toonMenu();
                System.out.print(Vertaler.geefVertaling("menu.keuze") + " ");
                keuze = Integer.parseInt(invoer.nextLine());
                if (keuze < 1 || keuze > keuzes.length) {
                    System.out.printf(Vertaler.geefVertaling("menu.ongeldig.bereik"), keuzes.length);
                    keuze = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println(Vertaler.geefVertaling("menu.ongeldig.getal"));
                keuze = -1;
            }
        } while (keuze == -1);
        return keuze;
    }

    
    public void toonMenu() {
        System.out.println(titel);
        System.out.println("=".repeat(titel.length()));
        for (int i = 0; i < keuzes.length; i++) {
            System.out.printf("%d. %s%n", i + 1, keuzes[i]);
        }
    }
}
