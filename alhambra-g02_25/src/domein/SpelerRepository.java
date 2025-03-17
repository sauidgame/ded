package domein;

import exceptions.GebruikersnaamInGebruikException;
import persistentie.SpelerMapper;

/** Checkt Domeinregels bij het registreren van nieuwe spelers.**/
public class SpelerRepository {

    private final SpelerMapper mapper;

    public SpelerRepository() 
    {
        mapper = new SpelerMapper();
    }
    
    public void voegToe(Speler speler) {
       if (bestaatSpeler(speler.getGebruikersnaam()))
            throw new GebruikersnaamInGebruikException();
       
       mapper.voegToe(speler);
    }

    public Speler geefSpeler(String gebruikersnaam) {
        return mapper.geefSpeler(gebruikersnaam);
    }

    private boolean bestaatSpeler(String gebruikersnaam){
        return mapper.geefSpeler(gebruikersnaam) != null;
    }

}