package fi.cs.helsinki.saada.ostoslista.mallit;

import fi.cs.helsinki.saada.ostoslista.mallit.kyselyt.OstoslistaKysely;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stb
 */
public class Ostoslista {

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
        try {
            OstoslistaKysely kysely = new OstoslistaKysely();
            if (kysely.muutaNimea(getId(), nimi)) {
                this.nimi = nimi;
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void lisaaTuote(String tuotteenNimi) {
        Tuote.luoUusi(this, tuotteenNimi);
    }

    public static Ostoslista kayttajanOletusLista(Kayttaja kayttaja) {
        try {
            OstoslistaKysely kysely = new OstoslistaKysely();
            return kysely.haeOletus(kayttaja.getId());
        } catch (Exception ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Ostoslista haeLista(Kayttaja kayttaja, long id) {
        try {
            OstoslistaKysely kysely = new OstoslistaKysely();
            return kysely.haeLista(kayttaja.getId(), id);
        } catch (Exception ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Ostoslista haeLista(long id) {
        try {
            OstoslistaKysely kysely = new OstoslistaKysely();
            return kysely.haeLista(id);
        } catch (Exception ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<Ostoslista> kayttajanListat(Kayttaja kayttaja) {
        try {
            OstoslistaKysely kysely = new OstoslistaKysely();
            return kysely.haeListat(kayttaja.getId());
        } catch (Exception ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Ostoslista luoUusi(Kayttaja kayttaja) {
        try {
            OstoslistaKysely kysely = new OstoslistaKysely();
            return kysely.luoLista(kayttaja.getId());
        } catch (Exception ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean asetaKayttajanOletusLista(Kayttaja kayttaja, Ostoslista lista) {
        try {
            OstoslistaKysely kysely = new OstoslistaKysely();
            return kysely.asetaOletusLista(kayttaja.getId(), lista.getId());
        } catch (Exception ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}