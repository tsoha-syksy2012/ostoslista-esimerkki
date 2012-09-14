package fi.cs.helsinki.saada.ostoslista.mallit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author stb
 */
public class Ostoslista {

    private static class OstoslistaKysely extends AbstractKysely {

        public OstoslistaKysely() throws NamingException {
            super();
        }

        public Ostoslista haeOletus(long kayttajaId) throws SQLException {
            Connection yhteys = luoYhteys();
            PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM lists WHERE user_id = ? AND is_default IS true");
            prepareStatement.setLong(1, kayttajaId);
            Ostoslista lista = null;
            if (prepareStatement.execute()) {
                ResultSet resultSet = prepareStatement.getResultSet();
                while (resultSet.next()) {
                    lista = new Ostoslista(resultSet.getLong("id"),
                            resultSet.getLong("user_id"),
                            resultSet.getString("name"),
                            resultSet.getBoolean("is_default"));
                }
            }
            yhteys.close();
            return lista;
        }
    }
    private final long id;
    private final long kayttajaId;
    private String nimi;
    private final boolean oletus;

    public Ostoslista(long id, long kayttajaId, String nimi, boolean oletus) {
        this.id = id;
        this.kayttajaId = kayttajaId;
        this.nimi = nimi;
        this.oletus = oletus;
    }

    public long getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public boolean getOletus() {
        return oletus;
    }

    public ArrayList<Tuote> getTuotteet() {
        return Tuote.listanTuotteet(getId());
    }

    public static Ostoslista kayttajanOletusLista(Kayttaja kayttaja) {
        try {
            OstoslistaKysely kysely = new OstoslistaKysely();
            return kysely.haeOletus(kayttaja.getId());
        } catch (SQLException ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Ostoslista haeLista(Kayttaja kayttaja, long id) {
        return new Ostoslista(id, kayttaja.getId(), "lista", true);
    }

    public static Ostoslista haeLista(long id) {
        return new Ostoslista(id, 1, "lista", false);
    }

    public boolean onKayttajan(Kayttaja kayttaja) {
        return kayttaja.getId() == kayttajaId;
    }

    public static Ostoslista luoUusi(Kayttaja kayttaja) {
        return new Ostoslista(99, kayttaja.getId(), "uusi lista", false);
    }

    public boolean setNimi(String nimi) {
        return true;
    }

    public void lisaaTuote(String tuotteenNimi) {
        Tuote.luoUusi(this, tuotteenNimi);
    }
}