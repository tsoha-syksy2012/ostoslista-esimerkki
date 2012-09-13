package fi.cs.helsinki.saada.ostoslista.mallit;

import java.util.ArrayList;

/**
 *
 * @author stb
 */
public class Ostoslista {

    private final long id;

    private Ostoslista(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getNimi() {
        return "Ostoslistan nimi";
    }

    public ArrayList<Tuote> getTuotteet() {
        return Tuote.listanTuotteet(getId());
    }

    public static Ostoslista kayttajanOletusLista(long id) {
        return new Ostoslista(1);
    }

    public static Ostoslista haeLista(Kayttaja kayttaja, long id) {
        return new Ostoslista(id);
    }

}
