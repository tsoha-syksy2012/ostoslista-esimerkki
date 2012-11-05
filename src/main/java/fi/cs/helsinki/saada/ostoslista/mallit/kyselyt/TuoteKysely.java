/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.cs.helsinki.saada.ostoslista.mallit.kyselyt;

import fi.cs.helsinki.saada.ostoslista.mallit.Tuote;
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
public class TuoteKysely extends AbstractKysely {

    public TuoteKysely() throws NamingException {
        super();
    }

    public ArrayList<Tuote> haeTuotteet(long listaId) throws SQLException {
        ArrayList<Tuote> tuotteet = new ArrayList<Tuote>();
        Connection yhteys = luoYhteys();
        PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM items WHERE list_id = ? ORDER BY name");
        prepareStatement.setLong(1, listaId);
        if (prepareStatement.execute()) {
            ResultSet resultSet = prepareStatement.getResultSet();
            while (resultSet.next()) {
                tuotteet.add(new Tuote(resultSet.getLong("id"), resultSet.getLong("list_id"), resultSet.getString("name")));
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
                tuote = new Tuote(resultSet.getLong("id"), resultSet.getLong("list_id"), resultSet.getString("name"));
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
