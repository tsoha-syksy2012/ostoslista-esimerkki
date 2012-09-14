package fi.cs.helsinki.saada.ostoslista.mallit;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author stb
 */
public class AbstractKysely {

    private DataSource dataSource;

    public AbstractKysely() throws NamingException {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        this.dataSource = (DataSource) envCtx.lookup("jdbc/ostoslista");
    }

    protected Connection luoYhteys() throws SQLException {
        return dataSource.getConnection();
    }
}
