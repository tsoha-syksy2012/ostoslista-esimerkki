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
public class Tuote {

    private static class TuoteKysely extends AbstractKysely {

        public TuoteKysely() throws NamingException {
            super();
        }

        public ArrayList<Tuote> haeTuotteet(long listaId) throws SQLException {
            ArrayList<Tuote> tuotteet = new ArrayList<Tuote>();
            Connection yhteys = luoYhteys();
            PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM items WHERE list_id = ?");
            prepareStatement.setLong(1, listaId);
            if (prepareStatement.execute()) {
                ResultSet resultSet = prepareStatement.getResultSet();
                while (resultSet.next()) {
                    tuotteet.add(new Tuote(resultSet.getLong("id"),
                            resultSet.getLong("list_id"),
                            resultSet.getString("name")));
                }
            }
            yhteys.close();
            return tuotteet;
        }

        public void luoTuote(long listaId, String nimi) throws SQLException {
            Connection yhteys = luoYhteys();
            PreparedStatement prepareStatement = yhteys.prepareStatement("INSERT INTO items (list_id, name) VALUES (?, ?)");
            prepareStatement.setLong(1, listaId);
            prepareStatement.setString(2, nimi);
            prepareStatement.executeUpdate();
            yhteys.close();
        }

        public Tuote haeTuote(long id) throws SQLException {
            Connection yhteys = luoYhteys();
            PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM items WHERE id = ?");
            prepareStatement.setLong(1, id);
            Tuote tuote = null;
            if (prepareStatement.execute()) {
                ResultSet resultSet = prepareStatement.getResultSet();
                while (resultSet.next()) {
                    tuote = new Tuote(resultSet.getLong("id"),
                            resultSet.getLong("list_id"),
                            resultSet.getString("name"));
                }
            }
            yhteys.close();
            return tuote;
        }

        public void poistaTuote(long id) throws SQLException {
            Connection yhteys = luoYhteys();
            PreparedStatement prepareStatement = yhteys.prepareStatement("DELETE FROM items WHERE id = ?");
            prepareStatement.setLong(1, id);
            prepareStatement.executeUpdate();
            yhteys.close();
        }

    }

    private final long id;
    private final String nimi;
    private final long listaId;

    public Tuote(long id, long listaId, String nimi) {
        this.id = id;
        this.listaId = listaId;
        this.nimi = nimi;
    }

    public long getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public Ostoslista getOstoslista() {
        return Ostoslista.haeLista(listaId);
    }

    public void poista() {
        try {
            TuoteKysely kysely = new TuoteKysely();
            kysely.poistaTuote(id);
        } catch (SQLException ex) {
            Logger.getLogger(Tuote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Tuote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Tuote haeTuote(long id) {
        try {
            TuoteKysely kysely = new TuoteKysely();
            return kysely.haeTuote(id);
        } catch (SQLException ex) {
            Logger.getLogger(Tuote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Tuote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<Tuote> listanTuotteet(Ostoslista lista) {
        try {
            TuoteKysely kysely = new TuoteKysely();
            return kysely.haeTuotteet(lista.getId());
        } catch (SQLException ex) {
            Logger.getLogger(Tuote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Tuote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void luoUusi(Ostoslista lista, String nimi) {
        //TODO: Luo uusi
        try {
            TuoteKysely kysely = new TuoteKysely();
            kysely.luoTuote(lista.getId(), nimi);
        } catch (SQLException ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
