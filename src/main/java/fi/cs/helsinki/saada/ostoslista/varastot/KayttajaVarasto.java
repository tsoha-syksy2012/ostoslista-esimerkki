package fi.cs.helsinki.saada.ostoslista.varastot;

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
public class KayttajaVarasto extends AbstractVarasto {

    public KayttajaVarasto() throws NamingException {
        super();
    }

    public Kayttaja haeKayttaja(String tunnus, String salasana) throws SQLException {
        Connection yhteys = luoYhteys();
        PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
        prepareStatement.setString(1, tunnus);
        prepareStatement.setString(2, salasana);
        return haeKayttaja(prepareStatement, yhteys);
    }

    public Kayttaja haeKayttaja(long id) throws SQLException {
        Connection yhteys = luoYhteys();
        PreparedStatement prepareStatement = yhteys.prepareStatement("SELECT * FROM users WHERE id = ?");
        prepareStatement.setLong(1, id);
        return haeKayttaja(prepareStatement, yhteys);
    }

    private Kayttaja haeKayttaja(PreparedStatement prepareStatement, Connection yhteys) throws SQLException {
        Kayttaja kayttaja = null;
        if (prepareStatement.execute()) {
            ResultSet resultSet = prepareStatement.getResultSet();
            if (resultSet.next()) {
                kayttaja = new Kayttaja(resultSet.getLong("id"));
            }
        }
        yhteys.close();
        return kayttaja;
    }

}
