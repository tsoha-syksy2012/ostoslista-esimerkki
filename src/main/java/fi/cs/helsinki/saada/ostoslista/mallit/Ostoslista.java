package fi.cs.helsinki.saada.ostoslista.mallit;

import java.util.ArrayList;

/**
 *
 * @author stb
 */
public class Ostoslista {

    private final long id;
    private final long kayttajaId;

    private Ostoslista(long id, long kayttajaId) {
        this.id = id;
        this.kayttajaId = kayttajaId;
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

    public static Ostoslista kayttajanOletusLista(long kayttajaId) {
        return new Ostoslista(1, kayttajaId);
    }

    public static Ostoslista haeLista(Kayttaja kayttaja, long id) {
        return new Ostoslista(id, kayttaja.getId());
    }

    public static Ostoslista haeLista(long id) {
        return new Ostoslista(id, 1);
    }

    public boolean onKayttajan(Kayttaja kayttaja) {
        return kayttaja.getId() == kayttajaId;
    }

}
