package fi.cs.helsinki.saada.ostoslista.mallit;

import java.util.ArrayList;

/**
 *
 * @author stb
 */
public class Kayttaja {

    private long id;

    public Kayttaja(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Ostoslista getOletusLista() {
        return Ostoslista.kayttajanOletusLista(this);
    }

    public ArrayList<Ostoslista> getKaikkiListat() {
        ArrayList<Ostoslista> listat = new ArrayList<Ostoslista>();
        listat.add(new Ostoslista(1, id, "lista 1", false));
        listat.add(new Ostoslista(2, id, "lista 2", true));
        listat.add(new Ostoslista(3, id, "lista 3", false));
        return listat;
    }

    public static Kayttaja kirjauduSisaan(String tunnus, String salasana) {
        if (tunnus.equals("foobar") && salasana.equals("password")) {
            return new Kayttaja(1);
        }
        return null;
    }

    public static Kayttaja haeKayttaja(long id) {
        return new Kayttaja(1);
    }

    public boolean setOletusLista(Ostoslista lista) {
        //TODO: luo oletus
        return true;
    }

}