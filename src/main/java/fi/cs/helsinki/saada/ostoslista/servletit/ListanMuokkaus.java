package fi.cs.helsinki.saada.ostoslista.servletit;

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
            asetaOtsikko("Viikonlopun hätävara - muokkaus", request);
            palautaJSP("muokkauslomake.jsp", request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (varmistaKirjautuminen(request, response)) {
            ohjaaSivulle("lista", response);
        }
    }
}