package fi.cs.helsinki.saada.ostoslista.mallit;

import java.util.ArrayList;

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

    public static Tuote haeTuote(long id) {
        return new Tuote(id, 1, "esimerkki tuote");
    }

    public static ArrayList<Tuote> listanTuotteet(long listaId) {
        ArrayList<Tuote> tuotteet = new ArrayList<Tuote>();
        tuotteet.add(new Tuote(1, listaId, "tuote 1"));
        tuotteet.add(new Tuote(2, listaId, "tuote 2"));
        tuotteet.add(new Tuote(3, listaId, "tuote 3"));
        tuotteet.add(new Tuote(4, listaId, "tuote 4"));
        return tuotteet;
    }

    public void poista() {
        // Poista
    }

}
