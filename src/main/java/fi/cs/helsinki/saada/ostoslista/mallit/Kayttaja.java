package fi.cs.helsinki.saada.ostoslista.mallit;

import fi.cs.helsinki.saada.ostoslista.mallit.kyselyt.KayttajaKysely;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stb
 */
public class Kayttaja {

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
        } catch (Exception ex) {
            Logger.getLogger(Kayttaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Kayttaja haeKayttaja(long id) {
        try {
            KayttajaKysely kysely = new KayttajaKysely();
            return kysely.haeKayttaja(id);
        } catch (Exception ex) {
            Logger.getLogger(Kayttaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}