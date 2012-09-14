package fi.cs.helsinki.saada.ostoslista.mallit;

import java.util.ArrayList;

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
        return Tuote.listanTuotteet(getId());
    }

    public static Ostoslista kayttajanOletusLista(Kayttaja kayttaja) {
        return new Ostoslista(1, kayttaja.getId(), "oletuslista", true);
    }

    public static Ostoslista haeLista(Kayttaja kayttaja, long id) {
        return new Ostoslista(id, kayttaja.getId(), "lista", true);
    }

    public static Ostoslista haeLista(long id) {
        return new Ostoslista(id, 1, "lista", false);
    }

    public boolean onKayttajan(Kayttaja kayttaja) {
        return kayttaja.getId() == kayttajaId;
    }

    public static Ostoslista luoUusi(Kayttaja kayttaja) {
        return new Ostoslista(99, kayttaja.getId(), "uusi lista", false);
    }

    public boolean setNimi(String nimi) {
        return true;
    }

}