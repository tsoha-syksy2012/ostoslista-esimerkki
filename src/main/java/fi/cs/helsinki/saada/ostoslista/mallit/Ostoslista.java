package fi.cs.helsinki.saada.ostoslista.mallit;

import fi.cs.helsinki.saada.ostoslista.mallit.kyselyt.OstoslistaKysely;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Ostoslista-malli
 *
 * Kuvaa tietokannan ostoslistat-taulun rivejä olioina.
 *
 * @author stb
 */
public class Ostoslista {

    /**
     * Yksilöivä tunnus
     */
    private final long id;

    /**
     * Omistavan käyttäjän tunnus
     */
    private final long kayttajaId;

    /**
     * Ostoslistan nimi
     */
    private String nimi;

    /**
     * Onko ostoslista oletuksena
     */
    private final boolean oletus;

    /**
     * Luo ostoslistan
     *
     * @param id Yksilöivä tunnus
     * @param kayttajaId Omistavan käyttäjän yksilöivä tunnus
     * @param nimi Nimi
     * @param oletus Onko oletuksena
     */
    public Ostoslista(long id, long kayttajaId, String nimi, boolean oletus) {
        this.id = id;
        this.kayttajaId = kayttajaId;
        this.nimi = nimi;
        this.oletus = oletus;
    }

    /**
     * Antaa ostoslistan yksilöivän tunnuksen
     *
     * @return yksilöivä tunnus
     */
    public long getId() {
        return id;
    }

    /**
     * Antaa ostoslistan nimen
     *
     * @return listan nimi
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * Kertoo onko lista oletuksena
     *
     * @return onko oletus
     */
    public boolean getOletus() {
        return oletus;
    }

    /**
     * Antaa ostoslistaan liittyvät tuotteet
     *
     * @return Listan tuotteita
     */
    public ArrayList<Tuote> getTuotteet() {
        return Tuote.listanTuotteet(this);
    }

    /**
     * Kertoo onko ostoslista annetun käyttäjän
     *
     * @param kayttaja Mahdollinen omistaja
     * @return onko omistaja
     */
    public boolean onKayttajan(Kayttaja kayttaja) {
        return kayttaja.getId() == kayttajaId;
    }

    /**
     * Asettaa ostoslistalle nimen ja tallentaa sen kantaan.
     *
     * @param nimi Uusi nimi
     * @return onnistuiko operaatio
     */
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

    /**
     * Lisää tuotteen listaan
     *
     * @param tuotteenNimi Uuden tuotteen nimi
     */
    public void lisaaTuote(String tuotteenNimi) {
        Tuote.luoUusi(this, tuotteenNimi);
    }

    /**
     * Hakee käyttäjän oletus ostoslistan
     *
     * @param kayttaja Käyttäjä
     * @return Oikea ostoslista
     */
    public static Ostoslista kayttajanOletusLista(Kayttaja kayttaja) {
        try {
            OstoslistaKysely kysely = new OstoslistaKysely();
            return kysely.haeOletus(kayttaja.getId());
        } catch (Exception ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Hakee annetun käyttäjän listoista tunnusta vastaavan listan
     *
     * @param kayttaja Annettu käyttäjä
     * @param id Annettu tunnus
     * @return Oikea lista
     */
    public static Ostoslista haeLista(Kayttaja kayttaja, long id) {
        try {
            OstoslistaKysely kysely = new OstoslistaKysely();
            return kysely.haeLista(kayttaja.getId(), id);
        } catch (Exception ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Hakee tunnusta vastaavan listan
     *
     * @param id Annettu tunnus
     * @return Haluttu lista
     */
    public static Ostoslista haeLista(long id) {
        try {
            OstoslistaKysely kysely = new OstoslistaKysely();
            return kysely.haeLista(id);
        } catch (Exception ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Hakee käyttäjän ostoslistat
     *
     * @param kayttaja Annettu käyttäjä
     * @return Halutut ostoslistat
     */
    public static ArrayList<Ostoslista> kayttajanListat(Kayttaja kayttaja) {
        try {
            OstoslistaKysely kysely = new OstoslistaKysely();
            return kysely.haeListat(kayttaja.getId());
        } catch (Exception ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Luo uuden ostoslistan käyttäjälle
     *
     * @param kayttaja Annettu käyttäjä
     * @return Luotu ostoslista
     */
    public static Ostoslista luoUusi(Kayttaja kayttaja) {
        try {
            OstoslistaKysely kysely = new OstoslistaKysely();
            return kysely.luoLista(kayttaja.getId());
        } catch (Exception ex) {
            Logger.getLogger(Ostoslista.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Asettaa listan käyttäjälle oletukseksi
     *
     * @param kayttaja Annettu käyttäjä
     * @param lista Annettu lista
     * @return Onnistuiko operaatio
     */
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