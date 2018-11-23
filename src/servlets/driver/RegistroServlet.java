package servlets.driver;

import databases.UserDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Se encarga de tomar info de registro de un nuevo conductor.
 * De ser exitoso el registro, envia treinta posibles destinos cerca del nuevo conductor.
 *
 * @author David Azofeifa H.
 */
public class RegistroServlet extends HttpServlet {

    private UserDB userDB = new UserDB();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String json = request.getParameter("json");

        response.setContentType("text/plain");
        PrintWriter out=response.getWriter();

        System.out.println(json);
        if (!this.userDB.ConductorExiste(json)) {
            if (this.userDB.RegistrarConductor(json)) {
                out.print("{\"exitoso\": true}");
                System.out.println("exito");
            } else {
                out.print("{\"exitoso\": false}");
            }
        }
        else {
            System.out.println("hola");
            out.print("{\"exitoso\": \"yaExiste\"}");
        }
    }
}
