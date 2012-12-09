/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.cs.helsinki.saada.ostoslista.tyokalut;

import fi.cs.helsinki.saada.ostoslista.mallit.Kayttaja;
import fi.cs.helsinki.saada.ostoslista.varastot.KayttajaVarasto;
import javax.servlet.http.HttpSession;

/**
 *
 * @author stb
 */
public class Kirjautuja {

    public static Kayttaja doKirjauduSisaan(HttpSession session, String tunnus, String salasana) throws Exception {
        KayttajaVarasto varasto = new KayttajaVarasto();
        Kayttaja kayttaja = varasto.haeKayttaja(tunnus, salasana);
        session.setAttribute(kayttajaSessioAvain(), kayttaja.getId());
        return kayttaja;
    }

    private static String kayttajaSessioAvain() {
        return "kayttaja_id";
    }

    public static Kayttaja getKirjautunutKayttaja(HttpSession session) throws Exception {
        long kayttajaId = ((Long) session.getAttribute(kayttajaSessioAvain())).longValue();
        KayttajaVarasto varasto = new KayttajaVarasto();
        Kayttaja kayttaja = varasto.haeKayttaja(kayttajaId);
        return kayttaja;
    }
}
