package fi.cs.helsinki.saada.ostoslista.mallit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

        public ArrayList<Ostoslista> haeListat(long kayttajaId) throws SQLException {
            ArrayList<Ostoslista> listat = new ArrayList<Ostoslista>();
            Connection yhteys = luoYhteys();
            PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM lists WHERE user_id = ?");
            prepareStatement.setLong(1, kayttajaId);
            if (prepareStatement.execute()) {
                ResultSet resultSet = prepareStatement.getResultSet();
                while (resultSet.next()) {
                    listat.add(new Ostoslista(resultSet.getLong("id"),
                            resultSet.getLong("user_id"),
                            resultSet.getString("name"),
                            resultSet.getBoolean("is_default")));
                }
            }
            yhteys.close();
            return listat;
        }

        private Ostoslista haeLista(long kayttajaId, long id) throws SQLException {
            Connection yhteys = luoYhteys();
            PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM lists WHERE user_id = ? AND id = ?");
            prepareStatement.setLong(1, kayttajaId);
            prepareStatement.setLong(2, id);
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

        public Ostoslista luoLista(long kayttajaId) throws SQLException {
            Connection yhteys = luoYhteys();
            PreparedStatement prepareStatement = yhteys.prepareStatement("INSERT INTO lists (user_id, name) VALUES (?, ?) RETURNING id, user_id, name, is_default");
            prepareStatement.setLong(1, kayttajaId);
            SimpleDateFormat pvmFormaatti = new SimpleDateFormat("yyyy-MM-dd");
            String pvm = pvmFormaatti.format(new Date());
            prepareStatement.setString(2, "Ostoslista (" + pvm + ")");
            Ostoslista lista = null;
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                lista = new Ostoslista(resultSet.getLong("id"),
                        resultSet.getLong("user_id"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("is_default"));
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
        return Tuote.listanTuotteet(this);
    }

    public boolean onKayttajan(Kayttaja kayttaja) {
        return kayttaja.getId() == kayttajaId;
    }

    public boolean setNimi(String nimi) {
        return true;
    }

    public void lisaaTuote(String tuotteenNimi) {
        Tuote.luoUusi(this, tuotteenNimi);
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
        try {
            OstoslistaKysely kysely = new OstoslistaKysely();
            return kysely.haeLista(kayttaja.getId(), id);
        } catch (SQLException ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Ostoslista haeLista(long id) {
        return new Ostoslista(id, 1, "lista", false);
    }

    public static ArrayList<Ostoslista> kayttajanListat(Kayttaja kayttaja) {
        try {
            OstoslistaKysely kysely = new OstoslistaKysely();
            return kysely.haeListat(kayttaja.getId());
        } catch (SQLException ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Ostoslista luoUusi(Kayttaja kayttaja) {
        try {
            OstoslistaKysely kysely = new OstoslistaKysely();
            return kysely.luoLista(kayttaja.getId());
        } catch (SQLException ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}