package domein;

public class BonusEnStartspelerfiches extends Gebieden {

	private int aantalRondes = 0;

	public BonusEnStartspelerfiches() {

	}

	public void speelRonde() {
		if (aantalRondes <= 2) {
			aantalRondes++;
			Ronde r = new Ronde();
			r.bepaalPlaatsBonusfiche();
		} else if (aantalRondes <= 3) {
			aantalRondes++;
			LaatsteRonde lr = new LaatsteRonde();
			lr.bepaalPlaatsFiche();
		}

	}

}
