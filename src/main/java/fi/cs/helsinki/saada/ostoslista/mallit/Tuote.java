package fi.cs.helsinki.saada.ostoslista.mallit;

import fi.cs.helsinki.saada.ostoslista.varastot.TuoteVarasto;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tuote-malli
 *
 * Kuvaa tietokannan tuotteet-taulun sisällön olioina
 *
 * @author stb
 */
public class Tuote {

    /**
     * Tuotteen tunnus
     */
    private final long id;

    /**
     * Tuotteen nimi
     */
    private final String nimi;

    /**
     * Tuotteen omistavan listan tunnus
     */
    private final long listaId;

    /**
     * Luo tuotteen
     *
     * @param id Tuotteen tunnus
     * @param listaId Listan tunnus
     * @param nimi Tuotteen nimi
     */
    public Tuote(long id, long listaId, String nimi) {
        this.id = id;
        this.listaId = listaId;
        this.nimi = nimi;
    }

    /**
     * Antaa tuotteen tunnuksen
     *
     * @return tuotteen tunnus
     */
    public long getId() {
        return id;
    }

    /**
     * Antaa tuotteen nimen
     *
     * @return tuotteen nimi
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * Antaa tuotteen omistavan ostoslistan
     *
     * @return omistava ostoslista
     */
    public Ostoslista getOstoslista() {
        return Ostoslista.haeLista(listaId);
    }

    /**
     * Poistaa tuotteen
     */
    public void poista() {
        try {
            TuoteVarasto kysely = new TuoteVarasto();
            kysely.poistaTuote(id);
        } catch (Exception ex) {
            Logger.getLogger(Tuote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Hakee tuotteen annetun tunnuksen perusteella
     *
     * @param id Annettu tunnus
     * @return haluttu tuote
     */
    public static Tuote haeTuote(long id) {
        try {
            TuoteVarasto kysely = new TuoteVarasto();
            return kysely.haeTuote(id);
        } catch (Exception ex) {
            Logger.getLogger(Tuote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Hakee annetun ostoslistan tuotteet
     *
     * @param lista Annettu lista
     * @return Halutut tuotteet
     */
    public static ArrayList<Tuote> listanTuotteet(Ostoslista lista) {
        try {
            TuoteVarasto kysely = new TuoteVarasto();
            return kysely.haeTuotteet(lista.getId());
        } catch (Exception ex) {
            Logger.getLogger(Tuote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Luo uuden tuotteen listaan
     *
     * @param lista Annettu lista
     * @param nimi Uuden tuotteen nimi
     */
    public static void luoUusi(Ostoslista lista, String nimi) {
        try {
            TuoteVarasto kysely = new TuoteVarasto();
            kysely.luoTuote(lista.getId(), nimi);
        } catch (Exception ex) {
            Logger.getLogger(Tuote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}