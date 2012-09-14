package fi.cs.helsinki.saada.ostoslista.servletit;

import fi.cs.helsinki.saada.ostoslista.mallit.Ostoslista;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stb
 */
public class ListanMuokkaus extends OstoslistaServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (varmistaKirjautuminen(request, response)) {
            String listaId = request.getParameter("lista");
            if (listaId == null) {
                annaVirhe(response);
                return;
            }
            Ostoslista lista = Ostoslista.haeLista(annaKayttaja(request), new Long(listaId));
            if (lista.onKayttajan(annaKayttaja(request))) {
                naytaLomake(lista, request, response);
            } else {
                annaVirhe(response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (varmistaKirjautuminen(request, response)) {
            String listaId = request.getParameter("lista");
            if (listaId == null) {
                annaVirhe(response);
                return;
            }
            Ostoslista lista = Ostoslista.haeLista(annaKayttaja(request), new Long(listaId));
            if (lista.onKayttajan(annaKayttaja(request))) {
                String nimi = request.getParameter("nimi");
                boolean oletus = request.getParameter("oletus") != null;
                if (lista.setNimi(nimi)) {
                    if (oletus && !annaKayttaja(request).setOletusLista(lista)) {
                        request.setAttribute("virheviesti", "Oletuksen asettaminen ei onnistunut");
                    } else {
                        ohjaaSivulle("lista?lista=" + lista.getId(), response);
                        return;
                    }
                } else {
                    request.setAttribute("virheviesti", "Nimen vaihtaminen ei onnistunut");
                }
                naytaLomake(lista, request, response);
            } else {
                annaVirhe(response);
            }
        }
    }

    private void naytaLomake(Ostoslista lista, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("lista", lista);
        asetaOtsikko(lista.getNimi() + " - muokkaus", request);
        palautaJSP("muokkauslomake.jsp", request, response);
    }
}