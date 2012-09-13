package fi.cs.helsinki.saada.ostoslista.mallit;

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
        return Ostoslista.kayttajanOletusLista(getId());
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

}