package servlets.student;

import databases.UserDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Se encarga de recibir info de ingreso y de ser exitoso, enviar treinta ubucaciones cercanas
 *
 * @author David Azofeifa H.
 */
public class RegistroEstudianteServlet extends HttpServlet {

    private UserDB userDB = new UserDB();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String json = request.getParameter("json");

        response.setContentType("text/plain");
        PrintWriter out=response.getWriter();

        if (!this.userDB.EstudianteExiste(json))
            if (this.userDB.RegistrarEstudiante(json)) {
                out.print("{\"exitoso\": true}");
            }
            else {
                out.print("{\"exitoso\": false}");
            }
        else {
            out.print("{\"exitoso\": \"yaExiste\"}");
        }
    }
}

