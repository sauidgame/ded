package ui.cui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import ui.ChoiceMenu;

public class CUIChoiceMenu<T> implements ChoiceMenu<T> {

	private Scanner invoer = new Scanner(System.in);
	private final String titel;
	private final ArrayList<T> keuzes = new ArrayList<>();

	public CUIChoiceMenu(String titel) {
		this.titel = titel;
	}

	@Override
	public T geefKeuze(HashSet<? extends T> keuzeSet) {
		this.keuzes.clear();
		this.keuzes.addAll(keuzeSet);
		int keuze = 0;
		boolean keuzeOK = false;
		do {
			try {
				toonMenu();
				
				// nextline ipv nextInt, nextline wordt ingelezen in een
				// string, parseInt maakt er een getal van
				keuze = Integer.parseInt(invoer.nextLine());
				keuzeOK = keuze >= 1 && keuze <= keuzes.size();
				if (!keuzeOK)
					System.out.printf("Je keuze moet tussen 1 en %d liggen!%n", keuzes.size());
			} catch (NumberFormatException e) {
				System.out.printf("Gelieve een geheel getal tussen 1 en %d getal in te voeren! probeer opnieuw.... %n",
						keuzes.size());
			}
		} while (!keuzeOK);
		return this.keuzes.get(keuze);
	}

	private void toonMenu() {
		System.out.println(titel);
		System.out.printf("%s%n", "=".repeat(titel.length()));
		for (int i = 0; i < keuzes.size(); i++) {
			System.out.printf("%d. %s%n", i + 1, keuzes.get(i).toString());
		}
		System.out.print("Voer je keuze in: ");
	}

}
