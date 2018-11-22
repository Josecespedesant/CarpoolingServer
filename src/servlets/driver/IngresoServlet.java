package servlets.driver;

import databases.UserDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Se encarga de recibir la informacion de inicio de sesion de un conductor.
 * Envia treinta posibles destinos cercanos al conductor.
 *
 * @author David Azofeifa H.
 */
public class IngresoServlet extends HttpServlet {

    private UserDB userDB = new UserDB();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String json = request.getParameter("json");

        response.setContentType("text/plain");
        PrintWriter out=response.getWriter();

        if (this.userDB.ConductorExiste(json)) {
            if (this.userDB.IngresarConductor("json")) {
                out.print("{\"exitoso\": true}");
            }
            else {
                out.print("{\"exitoso\": false}");
            }
        }
        else {
            out.print("{\"exitoso\": \"noExiste\"}");
        }
    }

}
