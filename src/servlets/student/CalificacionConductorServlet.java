package servlets.student;

import databases.UserDB;
import org.jdom2.JDOMException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Recibe calificacion a un conductor y la registra.
 *
 * @author David Azofeifa H.
 */
public class CalificacionConductorServlet extends HttpServlet {

    UserDB userDB = new UserDB();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String json = request.getParameter("json");

        response.setContentType("text/plain");
        PrintWriter out=response.getWriter();

        if (userDB.ConductorExiste(json)){
            try {
                userDB.calificarConductor(json);
                out.print("{\"exitoso\": true}");
            } catch (JDOMException e) {
                e.printStackTrace();
            }
        }
        else {
            out.print("{\"exitoso\": false}");
        }
    }
}
