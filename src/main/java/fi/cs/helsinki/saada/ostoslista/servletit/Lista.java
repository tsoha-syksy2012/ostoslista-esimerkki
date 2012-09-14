package fi.cs.helsinki.saada.ostoslista.servletit;

import fi.cs.helsinki.saada.ostoslista.mallit.Kayttaja;
import fi.cs.helsinki.saada.ostoslista.mallit.Ostoslista;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stb
 */
public class Lista extends OstoslistaServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (varmistaKirjautuminen(request, response)) {
            String listaId = request.getParameter("lista");
            if (listaId == null) {
                annaVirhe(response);
                return;
            }
            Ostoslista lista = Ostoslista.haeLista((Kayttaja) request.getAttribute("kayttaja"), new Long(listaId));
            if (lista.onKayttajan(annaKayttaja(request))) {
                asetaOtsikko(lista.getNimi(), request);
                request.setAttribute("lista", lista);
                request.setAttribute("tuotteet", lista.getTuotteet());
                palautaJSP("lista.jsp", request, response);
            } else {
                annaVirhe(response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (varmistaKirjautuminen(request, response)) {
            String listaId = request.getParameter("lista");
            if (listaId == null) {
                annaVirhe(response);
                return;
            }
            Ostoslista lista = Ostoslista.haeLista((Kayttaja) request.getAttribute("kayttaja"), new Long(listaId));
            if (lista.onKayttajan(annaKayttaja(request))) {
                String tuotteenNimi = request.getParameter("tuote");
                lista.lisaaTuote(tuotteenNimi);
            }
            ohjaaSivulle("lista?lista=" + lista.getId(), response);
        }
    }
}