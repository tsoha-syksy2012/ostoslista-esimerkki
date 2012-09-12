package fi.cs.helsinki.saada.ostoslista.servletit;

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
            asetaOtsikko("Viikonlopun hätävara", request);
            palautaJSP("lista.jsp", request, response);
        }
    }
}