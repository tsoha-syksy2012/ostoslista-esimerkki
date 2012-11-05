/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.cs.helsinki.saada.ostoslista.mallit.kyselyt;

import fi.cs.helsinki.saada.ostoslista.mallit.Kayttaja;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author stb
 */
public class KayttajaKysely extends AbstractKysely {

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
