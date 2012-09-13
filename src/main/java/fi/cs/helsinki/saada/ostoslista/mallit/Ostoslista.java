package fi.cs.helsinki.saada.ostoslista.mallit;

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

    public static Ostoslista kayttajanOletusLista(long id) {
        return new Ostoslista(1);
    }

}
