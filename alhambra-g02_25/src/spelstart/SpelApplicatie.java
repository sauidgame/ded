package spelstart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import domein.DomeinController;
import ui.cui.CUIChoiceMenu;
import ui.cui.NieuweSpelerInput;

public class SpelApplicatie {
	
	//TODO: Hard coded Strings, die herhaald worden. Dit moet weg. Afspreken en vragen waar en hoe op te slaan.
	private final List<String> menuItems = Arrays.asList("Registreer nieuwe speler", "Start nieuw spel", "Afsluiten");
	private final HashSet<String> keuzesOverzichtsMenu = new HashSet<>(menuItems);
	
	//TODO: verhuizen nr CUI-klasse, CUI-klasse maken.
	private final CUIChoiceMenu<String> overzichtsMenu;
	private final DomeinController dc;
	private final NieuweSpelerInput nsi = new NieuweSpelerInput();

	public SpelApplicatie(DomeinController dc) {
		this.dc = dc;
		this.overzichtsMenu = new CUIChoiceMenu<>("Menu");
	}

	public void start() {
		String keuze;
		do {
			
			keuze = overzichtsMenu.geefKeuze(keuzesOverzichtsMenu);
			switch (keuze) {
			case "Registreer nieuwe speler" -> voegNieuweSpelerToe();
			case "Start nieuw spel" -> dc.startNieuwSpel();
			}
		} while (keuze != "Afsluiten");

		System.out.println("Tot een volgende keer!");

	}

	private void voegNieuweSpelerToe() {
		dc.registreerSpeler(nsi.getGebruikersnaam(), nsi.getGeboortejaar());
		System.out.println("De speler is geregistreerd!");
	}

}
