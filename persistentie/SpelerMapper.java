package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domein.Speler;

public class SpelerMapper {

	private static final String INSERT_SPELER = "INSERT INTO G02.speler (gebruikersnaam, geboortejaar, aantalOverwinningen, aantalGespeeld)"
			+ "VALUES (?, ?, ?, ?)";

	private static final String GEEF_SPELER = "SELECT * FROM G02.speler WHERE gebruikersnaam = ?";
	private static final String GEEF_ALLE_BESCHIKBARE_SPELERS = "SELECT * FROM G02.speler WHERE spelerKleur IS NULL";
	private static final String UPDATE_SPELER = "UPDATE G02.speler"
			+ " SET geboortejaar = ?, aantalGewonnen = ?, aantalGespeeld = ?" + " WHERE gebruikersnaam = ?";

	public void voegToe(Speler speler) {
		try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
				PreparedStatement query = conn.prepareStatement(INSERT_SPELER)) {
			query.setString(1, speler.getGebruikersnaam());
			query.setInt(2, speler.getGeboortejaar());
			query.setInt(3, speler.getAantalOverwinningen());
			query.setInt(4, speler.getAantalGespeeld());
			query.setString(5, speler.getKleur().toString());

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
					String spelerKleur = rs.getString("spelerKleur");

					speler = new Speler(gebruikersnaam, geboortejaar, aantalOverwinningen, aantalGespeeld, spelerKleur);

					conn.close();
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}

		return speler;
	}

	public List<Speler> geefAlleBeschikbareSpelers() {

		List<Speler> spelers = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
				PreparedStatement query = conn.prepareStatement(GEEF_ALLE_BESCHIKBARE_SPELERS)) {
			try (ResultSet rs = query.executeQuery()) {
				while (rs.next()) {
					int geboortejaar = rs.getInt("geboortejaar");
					int aantalOverwinningen = rs.getInt("aantalOverwinningen");
					int aantalGespeeld = rs.getInt("aantalGespeeld");
					String gebruikersnaam = rs.getString("gebruikersnaam");
					String spelerKleur = rs.getString("spelerKleur");

					spelers.add(
							new Speler(gebruikersnaam, geboortejaar, aantalOverwinningen, aantalGespeeld, spelerKleur));

				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}

		return spelers;
	}

	public void updateSpeler(Speler speler) {
		/**
		 * Update spelerdata in de databank, kijkt naar huidige waarden van gegeven
		 * Spelerobject.
		 **/
		try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
				PreparedStatement updateQuery = conn.prepareStatement(UPDATE_SPELER)) {

			updateQuery.setString(1, speler.getGebruikersnaam());
			updateQuery.setInt(2, speler.getGeboortejaar());
			updateQuery.setInt(3, speler.getAantalOverwinningen());
			updateQuery.setInt(4, speler.getAantalGespeeld());
			updateQuery.setString(5, speler.getKleur().toString());
			updateQuery.executeUpdate();

			conn.close();

		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

}
