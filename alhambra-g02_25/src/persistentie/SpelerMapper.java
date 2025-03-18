package persistentie;

import domein.Speler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpelerMapper {

    private static final String INSERT_SPELER = "INSERT INTO G02.speler (gebruikersnaam, geboortejaar, aantalOverwinningen, aantalGespeeld)"
            + "VALUES (?, ?, ?, ?)";
    
    private static final String GEEF_SPELER = "SELECT * FROM G02.speler WHERE gebruikersnaam = ?";
    private static final String UPDATE_SPELER = "UPDATE G02.speler"
                + " SET geboortejaar = ?, aantalGewonnen = ?, aantalGespeeld = ?"
                + " WHERE gebruikersnaam = ?";

    public void voegToe(Speler speler) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
             PreparedStatement query = conn.prepareStatement(INSERT_SPELER)) {
            query.setString(1, speler.getGebruikersnaam());
            query.setInt(2, speler.getGeboortejaar());
            query.setInt(3, speler.getAantalOverwinningen());
            query.setInt(4, speler.getAantalGespeeld());
            query.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Speler geefSpeler(String gebruikersnaam) {
        Speler speler = null;

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
             PreparedStatement query = conn.prepareStatement(GEEF_SPELER)) {
            query.setString(1, gebruikersnaam);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    int geboortejaar = rs.getInt("geboortejaar");
                    int aantalOverwinningen = rs.getInt("aantalOverwinningen");
                    int aantalGespeeld = rs.getInt("aantalGespeeld");

                    speler = new Speler(gebruikersnaam, geboortejaar, aantalOverwinningen, aantalGespeeld);
                    conn.close();
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return speler;
    }

    public void updateSpeler(Speler speler) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
             PreparedStatement updateQuery = conn.prepareStatement(UPDATE_SPELER)) {
            updateQuery.setInt(1, speler.getGeboortejaar());
            updateQuery.setInt(2, speler.getAantalOverwinningen());
            updateQuery.setInt(3, speler.getAantalGespeeld());
            updateQuery.setString(4, speler.getGebruikersnaam());
            updateQuery.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}