package fi.cs.helsinki.saada.ostoslista.servletit;

import fi.cs.helsinki.saada.ostoslista.mallit.Kayttaja;
import fi.cs.helsinki.saada.ostoslista.tyokalut.Kirjautuja;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author stb
 */
class OstoslistaServlet extends HttpServlet {

    protected boolean varmistaKirjautuminen(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(true);
        try {
            request.setAttribute("kayttaja", Kirjautuja.getKirjautunutKayttaja(session));
            return true;
        } catch (Exception ex) {
            Logger.getLogger(OstoslistaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        ohjaaSivulle("kirjautuminen", response);
        return false;
    }

    protected Kayttaja annaKayttaja(HttpServletRequest request) {
        return (Kayttaja) request.getAttribute("kayttaja");
    }

    protected String kayttajaSessioAvain() {
        return "kayttaja_id";
    }

    protected void asetaOtsikko(String otsikko, HttpServletRequest request) {
        request.setAttribute("otsikko", "Ostoslista - " + otsikko);
    }

    protected void palautaJSP(String sivu, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(sivu);
        dispatcher.forward(request, response);
    }

    protected void ohjaaSivulle(String sivu, HttpServletResponse response) throws IOException {
        response.sendRedirect(sivu);
    }

    protected void ohjaaOletusListaan(Kayttaja kayttaja, HttpServletResponse response) throws IOException {
        ohjaaSivulle("lista?lista=" + kayttaja.getOletusLista().getId(), response);
    }

    protected void annaVirhe(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
