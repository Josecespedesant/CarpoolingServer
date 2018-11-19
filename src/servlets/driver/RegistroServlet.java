package servlets.driver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Se encarga de tomar info de registro de un nuevo conductor.
 * De ser exitoso el registro, envia treinta posibles destinos cerca del nuevo conductor.
 *
 * @author David Azofeifa H.
 */
public class RegistroServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}