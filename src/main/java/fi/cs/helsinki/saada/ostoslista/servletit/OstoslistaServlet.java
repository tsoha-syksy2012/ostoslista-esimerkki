package fi.cs.helsinki.saada.ostoslista.servletit;

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
        Long kayttajaId = (Long) session.getAttribute("kayttaja");
        if (kayttajaId == null) {
            ohjaaSivulle("kirjautuminen", response);
            return false;
        }
        return true;
    }

    protected void palautaJSP(String sivu, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(sivu);
        dispatcher.forward(request, response);
    }

    protected void ohjaaSivulle(String sivu, HttpServletResponse response) throws IOException {
        response.sendRedirect(sivu);
    }

}
