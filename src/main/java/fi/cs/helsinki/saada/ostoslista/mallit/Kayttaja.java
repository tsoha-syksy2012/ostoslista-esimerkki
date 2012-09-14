package fi.cs.helsinki.saada.ostoslista.mallit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public ArrayList<Ostoslista> getKaikkiListat() {
        ArrayList<Ostoslista> listat = new ArrayList<Ostoslista>();
        listat.add(new Ostoslista(1, id, "lista 1", false));
        listat.add(new Ostoslista(2, id, "lista 2", true));
        listat.add(new Ostoslista(3, id, "lista 3", false));
        return listat;
    }

    public static Kayttaja kirjauduSisaan(String tunnus, String salasana) throws NamingException, SQLException {
        KayttajaKysely kysely = new KayttajaKysely();
        return kysely.haeKayttaja(tunnus, salasana);
    }

    public static Kayttaja haeKayttaja(long id) {
        return new Kayttaja(1);
    }

    public boolean setOletusLista(Ostoslista lista) {
        //TODO: luo oletus
        return true;
    }
}