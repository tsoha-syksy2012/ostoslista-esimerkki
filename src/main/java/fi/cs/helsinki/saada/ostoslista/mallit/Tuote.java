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

    public Tuote() {
        this.id = 1;
        this.nimi = "tuote";
        this.listaId = 1;
    }

    public long getId() {
        return id;
    }

    public String getNimi() {
        return "tuote";
    }

    public Ostoslista getOstoslista() {
        return Ostoslista.haeLista(listaId);
    }

    public static Tuote haeTuote(long id) {
        return new Tuote();
    }

    public static ArrayList<Tuote> listanTuotteet(long id) {
        ArrayList<Tuote> tuotteet = new ArrayList<Tuote>();
        tuotteet.add(new Tuote());
        tuotteet.add(new Tuote());
        tuotteet.add(new Tuote());
        tuotteet.add(new Tuote());
        return tuotteet;
    }

    public void poista() {
        // Poista
    }

}
