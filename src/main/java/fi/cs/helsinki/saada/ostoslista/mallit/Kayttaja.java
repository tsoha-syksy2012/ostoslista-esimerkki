package fi.cs.helsinki.saada.ostoslista.mallit;

import fi.cs.helsinki.saada.ostoslista.mallit.kyselyt.KayttajaKysely;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Käyttäjä-malli
 *
 * Kuvaa tietokannan käyttäjät-taulun rivejä olioina.
 *
 * @author stb
 */
public class Kayttaja {

    /**
     * Yksilöivä tunnus
     */
    private long id;

    /**
     * Luo käyttäjän
     *
     * @param id Käyttäjän yksilöivä tunnus
     */
    public Kayttaja(long id) {
        this.id = id;
    }

    /**
     * Antaa käyttäjän yksilöivän tunnuksen
     *
     * @return yksilöivä tunnus
     */
    public long getId() {
        return id;
    }

    /**
     * Hakee käyttäjän oletus ostoslistan
     *
     * @return oletus ostoslista
     */
    public Ostoslista getOletusLista() {
        return Ostoslista.kayttajanOletusLista(this);
    }

    /**
     * Asettaa käyttäjälle uuden oletus ostoslistan
     *
     * @param lista Uudeksi oletukseksi asetettava ostoslista
     * @return onnistuiko operaatio
     */
    public boolean setOletusLista(Ostoslista lista) {
        return Ostoslista.asetaKayttajanOletusLista(this, lista);
    }

    /**
     * Palauttaa käyttäjän kaikki ostoslistat
     *
     * @return Listan ostoslistoja
     */
    public ArrayList<Ostoslista> getKaikkiListat() {
        return Ostoslista.kayttajanListat(this);
    }

    /**
     * Tarkistaa löytyykö tunnusta ja salasanaa vastaavaa käyttäjää
     *
     * @param tunnus Kirjautuvan käyttäjän tunnus
     * @param salasana Kirjautuvan käyttäjän salasana
     * @return Kirjautuvan käyttäjän, jos tunnus ja salasana täsmäsivät
     */
    public static Kayttaja kirjauduSisaan(String tunnus, String salasana) {
        try {
            KayttajaKysely kysely = new KayttajaKysely();
            return kysely.haeKayttaja(tunnus, salasana);
        } catch (Exception ex) {
            Logger.getLogger(Kayttaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Hakee id:tä vastaavan käyttäjän
     * @param id Käyttäjän yksilöivä id
     * @return Käyttäjä
     */
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