package domein;

import java.util.Random;

import utils.GebouwKleur;

public class Dobbelsteen {

	private String dobbelUitkomst;

	public Dobbelsteen() {

	}

	public String getDobbelUitkomst() {
		return dobbelUitkomst;
	}

	public GebouwKleur rolDobbelsteen() {

		int random = new Random().nextInt(GebouwKleur.values().length);
		return GebouwKleur.values()[random];
	}

}
