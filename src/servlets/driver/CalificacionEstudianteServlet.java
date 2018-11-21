package servlets.driver;

import databases.UserDB;
import org.jdom2.JDOMException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Se encarga de recibir informacion sobre calificacion hacia un estudiante.
 *
 * @author David Azofeifa H.
 */
public class CalificacionEstudianteServlet extends HttpServlet {

    UserDB userDB = new UserDB();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String json = request.getParameter("json");

        response.setContentType("text/plain");
        PrintWriter out=response.getWriter();

        if (userDB.EstudianteExiste(json)){
            try {
                userDB.calificarEstudiante(json);
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
