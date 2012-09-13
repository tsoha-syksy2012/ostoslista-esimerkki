package fi.cs.helsinki.saada.ostoslista.servletit;

import fi.cs.helsinki.saada.ostoslista.mallit.Kayttaja;
import java.io.IOException;
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
        Object kayttajaId = session.getAttribute("kayttaja_id");
        if (kayttajaId != null) {
            Kayttaja kayttaja = Kayttaja.haeKayttaja((Long) kayttajaId);
            if (kayttaja != null) {
                request.setAttribute("kayttaja", kayttaja);
                return true;
            }
        }
        ohjaaSivulle("kirjautuminen", response);
        return false;
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
}
