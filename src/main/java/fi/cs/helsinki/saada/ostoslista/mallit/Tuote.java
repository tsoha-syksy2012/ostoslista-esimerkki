package fi.cs.helsinki.saada.ostoslista.mallit;

import fi.cs.helsinki.saada.ostoslista.mallit.kyselyt.TuoteKysely;
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
