package fi.cs.helsinki.saada.ostoslista.servletit;

import fi.cs.helsinki.saada.ostoslista.mallit.Kayttaja;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        if (tunnus != null && salasana != null) {
            Kayttaja kayttaja = null;
            try {
                kayttaja = Kayttaja.kirjauduSisaan(tunnus, salasana);
            } catch (Exception ex) {
                Logger.getLogger(Kirjautuminen.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (kayttaja != null) {
                session.setAttribute(kayttajaSessioAvain(), kayttaja.getId());
                ohjaaOletusListaan(kayttaja, response);
                return;
            }
        }
        request.setAttribute("virheviesti", "Tarkista käyttäjätunnus ja salasana");
        naytaLomake(request, response);
    }

    private void naytaLomake(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        asetaOtsikko("kirjautuminen", request);
        palautaJSP("kirjautumislomake.jsp", request, response);
    }
}