package domein;

import java.util.List;

import exceptions.GebruikersnaamInGebruikException;
import persistentie.SpelerMapper;

/** Checkt Domeinregels bij het registreren van nieuwe spelers.**/
public class SpelerRepository {

	private final SpelerMapper mapper;

	public SpelerRepository() {
		mapper = new SpelerMapper();
	}

	public void voegToe(Speler speler) {
		if (bestaatSpeler(speler.getGebruikersnaam()))
			throw new GebruikersnaamInGebruikException();

		mapper.voegToe(speler);
	}

	public List<Speler> geefAlleBeschikbareSpeler() {
		return mapper.geefAlleBeschikbareSpelers();
	}

	private boolean bestaatSpeler(String gebruikersnaam) {
		return mapper.geefSpeler(gebruikersnaam) != null;
	}

	public Speler getSpeler(String gebruikersnaam) {
		return mapper.geefSpeler(gebruikersnaam);
	}
}
