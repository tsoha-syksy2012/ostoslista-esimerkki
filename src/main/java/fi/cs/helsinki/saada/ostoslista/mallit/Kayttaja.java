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
public class Kayttaja {

    private static class KayttajaKysely extends AbstractKysely {

        public KayttajaKysely() throws NamingException {
            super();
        }

        public Kayttaja haeKayttaja(String tunnus, String salasana) throws SQLException {
            Connection yhteys = luoYhteys();
            PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            prepareStatement.setString(1, tunnus);
            prepareStatement.setString(2, salasana);
            Kayttaja kayttaja = null;
            if (prepareStatement.execute()) {
                ResultSet resultSet = prepareStatement.getResultSet();
                while (resultSet.next()) {
                    kayttaja = new Kayttaja(resultSet.getLong("id"));
                }
            }
            yhteys.close();
            return kayttaja;
        }
    }
    private long id;

    public Kayttaja(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Ostoslista getOletusLista() {
        return Ostoslista.kayttajanOletusLista(this);
    }

    public boolean setOletusLista(Ostoslista lista) {
        return Ostoslista.asetaKayttajanOletusLista(this, lista);
    }

    public ArrayList<Ostoslista> getKaikkiListat() {
        return Ostoslista.kayttajanListat(this);
    }

    public static Kayttaja kirjauduSisaan(String tunnus, String salasana) {
        try {
            KayttajaKysely kysely = new KayttajaKysely();
            return kysely.haeKayttaja(tunnus, salasana);
        } catch (SQLException ex) {
            Logger.getLogger(Kayttaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Kayttaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Kayttaja haeKayttaja(long id) {
        return new Kayttaja(1);
    }

}