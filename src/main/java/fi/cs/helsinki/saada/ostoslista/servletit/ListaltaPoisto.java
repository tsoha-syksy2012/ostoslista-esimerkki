package fi.cs.helsinki.saada.ostoslista.servletit;

import fi.cs.helsinki.saada.ostoslista.mallit.Ostoslista;
import fi.cs.helsinki.saada.ostoslista.mallit.Tuote;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stb
 */
public class ListaltaPoisto extends OstoslistaServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (varmistaKirjautuminen(request, response)) {
            Tuote tuote = Tuote.haeTuote(new Long(request.getParameter("tuote")));
            Ostoslista lista = tuote.getOstoslista();
            if (lista.onKayttajan(annaKayttaja(request))) {
                tuote.poista();
            }
            ohjaaSivulle("lista?lista=" + lista.getId(), response);
        }
    }
}
