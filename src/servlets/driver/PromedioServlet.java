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
 * Se encarga de enviar el promedio de calificaciones del conductor.
 *
 * @author David Azofeifa H.
 */
public class PromedioServlet extends HttpServlet {

    UserDB userDB = new UserDB();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String json = request.getParameter("json");

        response.setContentType("text/plain");
        PrintWriter out=response.getWriter();

        try {
            double promedio = userDB.getPromedioConductor(json);
            out.print("{\"promedio\":"+promedio+"}");
        } catch (JDOMException e) {
            e.printStackTrace();
        }


    }

}
