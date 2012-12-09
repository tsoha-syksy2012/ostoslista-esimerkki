package fi.cs.helsinki.saada.ostoslista.servletit;

import fi.cs.helsinki.saada.ostoslista.mallit.Kayttaja;
import fi.cs.helsinki.saada.ostoslista.tyokalut.Kirjautuja;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author stb
 */
public class Kirjautuminen extends OstoslistaServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        naytaLomake(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String tunnus = request.getParameter("tunnus");
        String salasana = request.getParameter("salasana");
        try {
            Kayttaja kayttaja = Kirjautuja.doKirjauduSisaan(session, tunnus, salasana);
            ohjaaOletusListaan(kayttaja, response);
        } catch (Exception e) {
            request.setAttribute("virheviesti", "Tarkista käyttäjätunnus ja salasana");
            naytaLomake(request, response);
        }
    }

    private void naytaLomake(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        asetaOtsikko("kirjautuminen", request);
        palautaJSP("kirjautumislomake.jsp", request, response);
    }
}