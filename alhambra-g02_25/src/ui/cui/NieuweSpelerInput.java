package ui.cui;

import java.util.Scanner;

import ui.NieuweSpelerInterface;

public class NieuweSpelerInput implements NieuweSpelerInterface {
	private final Scanner invoer = new Scanner(System.in);

	@Override
	public String getGebruikersnaam() {
		// TODO Auto-generated method stub
		System.out.println("Geef de gebruikersnaam in");
		return invoer.nextLine();
	}

	@Override
	public int getGeboortejaar() {
		System.out.println("Geef je geboortejaar in:");
		return Integer.parseInt(invoer.nextLine());
	}

}