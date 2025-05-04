package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ronde {

	private int aantalBonusfiches;
	private int volgnummer = 0;
	

	public int getVolgnummer() {
		this.setVolgnummer(0);
		return volgnummer;
	}

	public void setVolgnummer(int volgnummer) {
		this.volgnummer++;
	}

	List<Bonusfiche> bonusfiches = new ArrayList<>();

	public Ronde() {
	
		
	}

	public int getAantalBonusfiches() {
		return aantalBonusfiches;
	}

	public void setAantalBonusfiches(int aantalBonusfiches) {
		this.aantalBonusfiches = aantalBonusfiches;
	}

	public List<Bonusfiche> bepaalPlaatsBonusfiche() {
		if (aantalBonusfiches <= 5) {
			Bonusfiche b = new Bonusfiche();
			Random random = new Random();
			int index = random.nextInt(bonusfiches.size() + 1); // nog iets doen met index dat b op index geplaatst
																// wordt die nog niet gevuld is
			bonusfiches.add(b);

		}
		return bonusfiches;
	}

}
