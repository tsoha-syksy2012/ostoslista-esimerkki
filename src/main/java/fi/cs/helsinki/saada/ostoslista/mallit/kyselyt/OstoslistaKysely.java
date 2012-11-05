/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.cs.helsinki.saada.ostoslista.mallit.kyselyt;

import fi.cs.helsinki.saada.ostoslista.mallit.Ostoslista;
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
public class OstoslistaKysely extends AbstractKysely {

    public OstoslistaKysely() throws NamingException {
        super();
    }

    public Ostoslista haeOletus(long kayttajaId) throws SQLException {
        Connection yhteys = luoYhteys();
        PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM lists WHERE user_id = ? AND is_default IS true");
        prepareStatement.setLong(1, kayttajaId);
        Ostoslista lista = suoritaListaHaku(prepareStatement);
        yhteys.close();
        return lista;
    }

    public ArrayList<Ostoslista> haeListat(long kayttajaId) throws SQLException {
        ArrayList<Ostoslista> listat = new ArrayList<Ostoslista>();
        Connection yhteys = luoYhteys();
        PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM lists WHERE user_id = ? ORDER BY name");
        prepareStatement.setLong(1, kayttajaId);
        if (prepareStatement.execute()) {
            ResultSet resultSet = prepareStatement.getResultSet();
            while (resultSet.next()) {
                listat.add(luoLista(resultSet));
            }
        }
        yhteys.close();
        return listat;
    }

    public Ostoslista haeLista(long kayttajaId, long id) throws SQLException {
        Connection yhteys = luoYhteys();
        PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM lists WHERE user_id = ? AND id = ?");
        prepareStatement.setLong(1, kayttajaId);
        prepareStatement.setLong(2, id);
        Ostoslista lista = suoritaListaHaku(prepareStatement);
        yhteys.close();
        return lista;
    }

    public Ostoslista haeLista(long id) throws SQLException {
        Connection yhteys = luoYhteys();
        PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM lists WHERE id = ?");
        prepareStatement.setLong(1, id);
        Ostoslista lista = suoritaListaHaku(prepareStatement);
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
        if (resultSet.next()) {
            lista = luoLista(resultSet);
        }
        yhteys.close();
        return lista;
    }

    public boolean muutaNimea(long id, String nimi) throws SQLException {
        Connection yhteys = luoYhteys();
        PreparedStatement prepareStatement = yhteys.prepareStatement("UPDATE lists SET name = ? WHERE id = ?");
        prepareStatement.setString(1, nimi);
        prepareStatement.setLong(2, id);
        boolean onnistuiko = muutosMuuttiRiveja(prepareStatement);
        yhteys.close();
        return onnistuiko;
    }

    public boolean asetaOletusLista(long kayttajaId, long listaId) throws SQLException {
        Connection yhteys = luoYhteys();
        boolean autoCommit = yhteys.getAutoCommit();
        boolean onnistuiko = false;
        try {
            PreparedStatement poistetaanOletukset = yhteys.prepareStatement("UPDATE lists SET is_default = false WHERE user_id = ?");
            PreparedStatement asetetaanUusiOletus = yhteys.prepareStatement("UPDATE lists SET is_default = true WHERE id = ?");
            poistetaanOletukset.setLong(1, kayttajaId);
            asetetaanUusiOletus.setLong(1, listaId);
            onnistuiko = muutosMuuttiRiveja(poistetaanOletukset) && muutosMuuttiRiveja(asetetaanUusiOletus);
            yhteys.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
            yhteys.rollback();
        } finally {
            yhteys.setAutoCommit(autoCommit);
            yhteys.close();
            return onnistuiko;
        }
    }

    private Ostoslista suoritaListaHaku(PreparedStatement prepareStatement) throws SQLException {
        if (prepareStatement.execute()) {
            ResultSet resultSet = prepareStatement.getResultSet();
            if (resultSet.next()) {
                return luoLista(resultSet);
            }
        }
        return null;
    }

    private boolean muutosMuuttiRiveja(PreparedStatement prepareStatement) throws SQLException {
        return prepareStatement.executeUpdate() > 0;
    }

    private Ostoslista luoLista(ResultSet resultSet) throws SQLException {
        return new Ostoslista(resultSet.getLong("id"), resultSet.getLong("user_id"), resultSet.getString("name"), resultSet.getBoolean("is_default"));
    }
}
