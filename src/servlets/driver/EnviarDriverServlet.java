package servlets.driver;

import databases.UserDB;
import org.jdom2.JDOMException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Envia informacion sobre instancia particular de Conductor.
 *
 * @author David Azofeifa H.
 */
public class EnviarDriverServlet extends HttpServlet {

    UserDB userDB = new UserDB();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String json = request.getParameter("json");

        response.setContentType("text/plain");
        PrintWriter out=response.getWriter();

        if (userDB.ConductorExiste(json)) {
            String estudiante = null;
            try {
                estudiante = userDB.enviarDriver(json);
            } catch (JDOMException e) {
                e.printStackTrace();
            }
            out.print(estudiante);
        }
    }
}
