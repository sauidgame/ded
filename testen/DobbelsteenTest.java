package testen;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.Dobbelsteen;
import utils.gebouwKleuren;

class DobbelsteenTest {

	private Dobbelsteen dobbelsteen;

	@BeforeEach
	void setUp() {
		dobbelsteen = new Dobbelsteen();
	}

	@Test
	void rolDobbelsteen_GeeftGeldigeGebouwKleur() {
		gebouwKleuren resultaat = dobbelsteen.rolDobbelsteen();
		assertNotNull(resultaat);
	}

	@Test
	void rolDobbelsteen_GeeftWillekeurigeKleur_MeerdereOproepen() {
		gebouwKleuren resultaat1 = dobbelsteen.rolDobbelsteen();
		gebouwKleuren resultaat2 = dobbelsteen.rolDobbelsteen();
		gebouwKleuren resultaat3 = dobbelsteen.rolDobbelsteen();
		boolean minstensEenVerschil = !resultaat1.equals(resultaat2) || !resultaat2.equals(resultaat3)
				|| !resultaat1.equals(resultaat3);
		assertTrue(minstensEenVerschil);
	}

	@Test
	void getDobbelUitkomst_IsLeegByDefault() {
		assertNull(dobbelsteen.getDobbelUitkomst());
	}
}