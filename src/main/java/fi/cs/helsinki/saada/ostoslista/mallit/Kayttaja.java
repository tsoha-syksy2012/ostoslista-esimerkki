package fi.cs.helsinki.saada.ostoslista.mallit;

import java.util.ArrayList;

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

}