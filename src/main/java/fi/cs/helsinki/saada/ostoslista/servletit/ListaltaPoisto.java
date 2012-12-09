package fi.cs.helsinki.saada.ostoslista.servletit;

import fi.cs.helsinki.saada.ostoslista.mallit.Ostoslista;
import fi.cs.helsinki.saada.ostoslista.mallit.Tuote;
import fi.cs.helsinki.saada.ostoslista.varastot.TuoteVarasto;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            try {
                long tuoteId = Long.valueOf(request.getParameter("tuote")).longValue();
                TuoteVarasto varasto = new TuoteVarasto();
                Tuote tuote = varasto.haeTuote(tuoteId);
                Ostoslista lista = tuote.getOstoslista();
                if (lista.onKayttajan(annaKayttaja(request))) {
                    varasto.poistaTuote(tuote);
                }
                ohjaaSivulle("lista?lista=" + lista.getId(), response);
            } catch (Exception ex) {
                Logger.getLogger(ListaltaPoisto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
