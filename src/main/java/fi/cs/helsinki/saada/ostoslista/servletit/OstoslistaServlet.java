package fi.cs.helsinki.saada.ostoslista.servletit;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stb
 */
class OstoslistaServlet extends HttpServlet {

    protected void ohjaaSivulle(String sivu, HttpServletResponse response) throws IOException {
        response.sendRedirect(sivu);
    }

}
