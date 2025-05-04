package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LaatsteRonde extends Ronde {

	private int aantalBonusfiches;
	private Bonusfiche startfiche; // moet andere klasse zijn, weet niet goed hoe

	List<Bonusfiche> fiches = new ArrayList<>();

	public LaatsteRonde() {

	}

	public int getAantalBonusfiches() {
		return aantalBonusfiches;
	}

	public void setAantalBonusfiches(int aantalBonusfiches) {
		this.aantalBonusfiches = aantalBonusfiches;
	}

	public List<Bonusfiche> bepaalPlaatsFiche() {
		if (aantalBonusfiches == 0) {
			bonusfiches.add(startfiche);
		} else if (aantalBonusfiches <= 4) {
			Bonusfiche b = new Bonusfiche();
			Random random = new Random();
			int index = random.nextInt(bonusfiches.size() + 1);
			bonusfiches.add(b);

		}
		return bonusfiches;
	}

	public void speelEenRonde() {
		bepaalPlaatsFiche();
	}

}
